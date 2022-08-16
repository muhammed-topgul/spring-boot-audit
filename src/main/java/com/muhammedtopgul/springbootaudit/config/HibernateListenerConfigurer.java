package com.muhammedtopgul.springbootaudit.config;

import com.muhammedtopgul.springbootaudit.audit.AuditLogListener;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * @author muhammed-topgul
 * @since 16/08/2022 11:25
 */
@Component
public class HibernateListenerConfigurer {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    
    @Autowired
    private AuditLogListener auditLogListener;
    
    @PostConstruct
    protected void init() {
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.POST_INSERT)
                .appendListener(auditLogListener);
        registry.getEventListenerGroup(EventType.POST_UPDATE)
                .appendListener(auditLogListener);
        registry.getEventListenerGroup(EventType.POST_DELETE)
                .appendListener(auditLogListener);
    }
}
