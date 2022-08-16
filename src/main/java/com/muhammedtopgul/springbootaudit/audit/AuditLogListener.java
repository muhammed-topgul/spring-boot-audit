package com.muhammedtopgul.springbootaudit.audit;

import com.google.gson.Gson;
import com.muhammedtopgul.springbootaudit.enumaration.AuditEvent;
import com.muhammedtopgul.springbootaudit.model.AuditLog;
import com.muhammedtopgul.springbootaudit.service.AuditLogService;
import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author muhammed-topgul
 * @since 16/08/2022 10:48
 */
@Component
public class AuditLogListener implements PostInsertEventListener, PostUpdateEventListener, PostDeleteEventListener {
    @Autowired
    private AuditLogService auditLogService;
    private final Gson gson = new Gson();

    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {
        Object entity = postInsertEvent.getEntity();
        if (entity instanceof AuditAware) {
            String[] propertyNames = postInsertEvent.getPersister().getPropertyNames();
            Object[] states = postInsertEvent.getState();
            AuditLog auditLog;
            for (int i = 0; i < propertyNames.length; i++) {
                System.out.println("********** On Post Insert **********");
                auditLog = AuditLog.builder()
                        .className(entity.getClass().getCanonicalName())
                        .persistedObjectId(postInsertEvent.getId().toString())
                        .eventName(AuditEvent.INSERT.name())
                        .propertyName(propertyNames[i])
                        .oldValue(null)
                        // .newValue(states[i].toString())
                        .newValue(gson.toJson(states[i]))
                        .build();
                auditLogService.save(auditLog);
            }
        }
    }

    @Override
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        Object entity = postUpdateEvent.getEntity();
        if (entity instanceof AuditAware) {
            String[] propertyNames = postUpdateEvent.getPersister().getPropertyNames();
            Object[] currentStates = postUpdateEvent.getState();
            Object[] previousStates = postUpdateEvent.getOldState();
            AuditLog auditLog;
            for (int i = 0; i < currentStates.length; i++) {
                if (!previousStates[i].equals(currentStates[i])) {
                    System.out.println("********** On Post Update **********");
                    auditLog = AuditLog.builder()
                            .className(entity.getClass().getCanonicalName())
                            .persistedObjectId(postUpdateEvent.getId().toString())
                            .eventName(AuditEvent.UPDATE.name())
                            .propertyName(propertyNames[i])
                            .oldValue(previousStates[i].toString())
                            .newValue(currentStates[i].toString())
                            .build();
                    auditLogService.save(auditLog);
                }
            }
        }
    }

    @Override
    public void onPostDelete(PostDeleteEvent postDeleteEvent) {
        Object entity = postDeleteEvent.getEntity();
        if (entity instanceof AuditAware) {
            String[] propertyNames = postDeleteEvent.getPersister().getPropertyNames();
            Object[] deletedState = postDeleteEvent.getDeletedState();
            AuditLog auditLog;
            for (int i = 0; i < propertyNames.length; i++) {
                System.out.println("********** On Post Delete **********");
                auditLog = AuditLog.builder()
                        .className(entity.getClass().getCanonicalName())
                        .persistedObjectId(postDeleteEvent.getId().toString())
                        .eventName(AuditEvent.DELETE.name())
                        .propertyName(propertyNames[i])
                        .oldValue(deletedState[i].toString())
                        .newValue(null)
                        .build();
                auditLogService.save(auditLog);
            }
        }
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return false;
    }
}
