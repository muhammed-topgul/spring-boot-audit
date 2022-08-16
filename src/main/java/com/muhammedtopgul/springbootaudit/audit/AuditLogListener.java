package com.muhammedtopgul.springbootaudit.audit;

import com.muhammedtopgul.springbootaudit.enumaration.AuditEvent;
import com.muhammedtopgul.springbootaudit.model.AuditLog;
import com.muhammedtopgul.springbootaudit.service.AuditLogService;
import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author muhammed-topgul
 * @since 16/08/2022 10:48
 */
@Component
public class AuditLogListener implements PostInsertEventListener, PostUpdateEventListener, PostDeleteEventListener {
    @Autowired
    private AuditLogService auditLogService;

    private String getPropertyValue(Object property) {
        if (property == null) {
            return null;
        }
        if (property instanceof AuditRelation) {
            return ((AuditRelation) property).toAuditLog();
        }
        return property.toString();
    }

    private boolean isAuditAware(Class<?> clazz) {
        return clazz.isAnnotationPresent(AuditAware.class);
    }

    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {
        Object entity = postInsertEvent.getEntity();
        if (isAuditAware(entity.getClass())) {
            String[] propertyNames = postInsertEvent.getPersister().getPropertyNames();
            Object[] states = postInsertEvent.getState();
            AuditLog auditLog = new AuditLog();
            Map<String, Map<String, String>> changeLog = new HashMap<>();
            for (int i = 0; i < propertyNames.length; i++) {
                System.out.println("********** On Post Insert **********");
                Map<String, String> changes = new HashMap<>();
                auditLog.setClassName(entity.getClass().getCanonicalName());
                auditLog.setPersistedObjectId(postInsertEvent.getId().toString());
                auditLog.setEventName(AuditEvent.INSERT.name());

                changes.put("newValue", getPropertyValue(states[i]));
                changeLog.put(propertyNames[i], changes);
            }
            auditLog.setChangeLog(changeLog);
            auditLogService.save(auditLog);
        }
    }

    @Override
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        Object entity = postUpdateEvent.getEntity();
        if (isAuditAware(entity.getClass())) {
            String[] propertyNames = postUpdateEvent.getPersister().getPropertyNames();
            Object[] currentStates = postUpdateEvent.getState();
            Object[] previousStates = postUpdateEvent.getOldState();
            AuditLog auditLog = new AuditLog();
            Map<String, Map<String, String>> changeLog = new HashMap<>();
            for (int i = 0; i < currentStates.length; i++) {
                if (!previousStates[i].equals(currentStates[i])) {
                    System.out.println("********** On Post Update **********");
                    Map<String, String> changes = new HashMap<>();
                    auditLog.setClassName(entity.getClass().getCanonicalName());
                    auditLog.setPersistedObjectId(postUpdateEvent.getId().toString());
                    auditLog.setEventName(AuditEvent.UPDATE.name());

                    changes.put("oldValue", getPropertyValue(previousStates[i]));
                    changes.put("newValue", getPropertyValue(currentStates[i]));
                    changeLog.put(propertyNames[i], changes);
                }
            }
            auditLog.setChangeLog(changeLog);
            auditLogService.save(auditLog);
        }
    }

    @Override
    public void onPostDelete(PostDeleteEvent postDeleteEvent) {
        Object entity = postDeleteEvent.getEntity();
        if (isAuditAware(entity.getClass())) {
            String[] propertyNames = postDeleteEvent.getPersister().getPropertyNames();
            Object[] deletedState = postDeleteEvent.getDeletedState();
            AuditLog auditLog = new AuditLog();
            Map<String, Map<String, String>> changeLog = new HashMap<>();
            for (int i = 0; i < propertyNames.length; i++) {
                System.out.println("********** On Post Delete **********");
                Map<String, String> changes = new HashMap<>();
                auditLog.setClassName(entity.getClass().getCanonicalName());
                auditLog.setPersistedObjectId(postDeleteEvent.getId().toString());
                auditLog.setEventName(AuditEvent.DELETE.name());

                changes.put("oldValue", getPropertyValue(deletedState[i]));
                changes.put("newValue", null);
                changeLog.put(propertyNames[i], changes);
            }
            auditLog.setChangeLog(changeLog);
            auditLogService.save(auditLog);
        }
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return false;
    }
}
