/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.context.support;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.*;
import org.springframework.stereotype.Component;


/**
 * Convenient injectable support class for Configurations, allowing easy factory beans to beans convention.
 *
 * @author  Rod Johnson
 * @author  Chris Beams
 * @author Baruch S.
 */
@Component
public class ConfigurationSupport {

    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Return the object created by this FactoryBean instance, first invoking
     * any container callbacks on the instance
     *
     * @param fb FactoryBean instance
     * @return the object created by the configured FactoryBean instance
     */
    @SuppressWarnings({"unchecked"})
    public <T> T getObject(FactoryBean fb) {
        try {

            FactoryBean factoryBean = (FactoryBean) getConfigured(fb);
            return (T) factoryBean.getObject();
        }
        catch (Exception ex) {
            // TODO clean up
            throw new RuntimeException(ex);
        }
    }

    /* Invoke callbacks on the object, as though it was configured in the
      * factory
      * @param o object to configure
      * @return object after callbacks have been called on it
      */

    private Object getConfigured(Object o) {
        if (this.autowireCapableBeanFactory == null) {
            throw new UnsupportedOperationException(
                    "Cannot configure object - not running in an AutowireCapableBeanFactory");
        }

        autowireCapableBeanFactory.initializeBean(o, null);

        // TODO could replace with ApplicationContextAwareProcessor call if that class were public
        if (this.applicationContext != null) {
            if (o instanceof ResourceLoaderAware) {
                ((ResourceLoaderAware) o).setResourceLoader(this.applicationContext);
            }
            if (o instanceof ApplicationEventPublisherAware) {
                ((ApplicationEventPublisherAware) o).setApplicationEventPublisher(this.applicationContext);
            }
            if (o instanceof MessageSourceAware) {
                ((MessageSourceAware) o).setMessageSource(this.applicationContext);
            }
            if (o instanceof ApplicationContextAware) {
                ((ApplicationContextAware) o).setApplicationContext(this.applicationContext);
            }
        }

        return o;
    }


}
