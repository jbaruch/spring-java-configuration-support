package org.springframework.context.support;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by IntelliJ IDEA.
 *
 * @author JBaruch
 * @since 24-May-2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-config.xml")
public class ConfigurationSupportTest {

    @Autowired
    private Pojo pojo;

    @Test
    public void testGetObject() throws Exception {
        Assert.assertEquals(ConfigurationWithSupport.POJO_NAME, pojo.getName());
    }
}
