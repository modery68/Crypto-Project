package org.crypto.training.config;

import org.crypto.training.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory getHibernateSessionFactory() {

        return HibernateUtil.getSessionFactory();
    }
}
