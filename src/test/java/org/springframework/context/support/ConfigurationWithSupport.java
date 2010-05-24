package org.springframework.context.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 *
 * @author JBaruch
 * @since 24-May-2010
 */
@Configuration
public class ConfigurationWithSupport {

    public static final String POJO_NAME = "a";

    @Autowired
    private ConfigurationSupport configurationSupport;

    @Bean
    public Pojo pojo() {
        PojoFactoryBean pojoFactoryBean = new PojoFactoryBean(POJO_NAME);
        return configurationSupport.getObject(pojoFactoryBean);
    }
}
