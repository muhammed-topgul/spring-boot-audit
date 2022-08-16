package com.muhammedtopgul.springbootaudit.audit;

import com.muhammedtopgul.springbootaudit.enumaration.AuditEvent;
import com.muhammedtopgul.springbootaudit.model.AuditLog;
import com.muhammedtopgul.springbootaudit.service.AuditLogService;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author muhammed-topgul
 * @since 16/08/2022 10:47
 */
@Component
public class AuditLogInterceptor extends EmptyInterceptor {
    @Autowired
    private AuditLogService auditLogService;

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        if (entity instanceof AuditAware) {
            AuditLog auditLog;
            for (int i = 0; i < currentState.length; i++) {
                if(!previousState[i].equals(currentState[i])) {
                    System.out.println("********** On Flush Dirty : " + propertyNames[i] + " **********");
                    auditLog = AuditLog.builder()
                            .className(entity.getClass().getCanonicalName())
                            .persistedObjectId(id.toString())
                            .eventName(AuditEvent.UPDATE.name())
                            .propertyName(propertyNames[i])
                            .oldValue(previousState[i].toString())
                            .newValue(currentState[i].toString())
                            .build();
                    auditLogService.save(auditLog);
                }
            }
        }
        return true;
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof AuditAware) {
            AuditLog auditLog;
            for (int i = 0; i < propertyNames.length; i++) {
                System.out.println("********** On Save : " + propertyNames[i] + " **********");
                auditLog = AuditLog.builder()
                        .className(entity.getClass().getCanonicalName())
                        .persistedObjectId(id.toString())
                        .eventName(AuditEvent.UPDATE.name())
                        .propertyName(propertyNames[i])
                        .oldValue(null)
                        .newValue(state[i].toString())
                        .build();
                auditLogService.save(auditLog);
            }
        }
        return super.onSave(entity, id, state, propertyNames, types);
    }

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof AuditAware) {
            AuditLog auditLog;
            for (int i = 0; i < propertyNames.length; i++) {
                System.out.println("********** On Delete : " + propertyNames[i] + " **********");
                auditLog = AuditLog.builder()
                        .className(entity.getClass().getCanonicalName())
                        .persistedObjectId(id.toString())
                        .eventName(AuditEvent.DELETE.name())
                        .propertyName(propertyNames[i])
                        .oldValue(state[i].toString())
                        .newValue(null)
                        .build();
                auditLogService.save(auditLog);
            }
        }
    }
}
