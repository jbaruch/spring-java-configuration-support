package org.springframework.context.support;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created by IntelliJ IDEA.
 *
 * @author JBaruch
 * @since 24-May-2010
 */
public class PojoFactoryBean implements FactoryBean<Pojo> {
    private final String pojoName;

    public PojoFactoryBean(String pojoName) {
        this.pojoName = pojoName;
    }

    @Override
    public Pojo getObject() throws Exception {
        return new Pojo(pojoName);
    }

    @Override
    public Class<?> getObjectType() {
        return Pojo.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
