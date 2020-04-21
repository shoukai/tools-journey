package org.apframework.dozer;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DozerTest {

    private Mapper mapper;
    private UserModel model;

    @Before
    public void before() {
        mapper = DozerBeanMapperBuilder.buildDefault();
        model = new UserModel("admin", "123456", "Admin");
        System.out.println("UserModel:" + model);
    }

    @Test
    public void testNormal() {
        UserData data = mapper.map(model, UserData.class);
        Assert.assertNotNull(data);
        System.out.println("UserData:" + data);

        Assert.assertNotNull(data.getUserId());
        Assert.assertNotNull(data.getNickname());
        Assert.assertNull(data.getPasswd());
    }

    @Test
    public void testMapperBuilder() {
        DozerBeanMapperBuilder builder = DozerBeanMapperBuilder.create()
                .withMappingBuilder(new UserBeanMappingBuilder());
        Mapper mapper = builder.build();
        UserData data = mapper.map(model, UserData.class);

        System.out.println("UserData:" + data);

        Assert.assertNotNull(data.getUserId());
        Assert.assertNotNull(data.getNickname());
        Assert.assertNotNull(data.getPasswd());
    }

    @Test
    public void testInnerBean() {
        model.setInner(new UserModelInner("user-model-inner-a", "user-model-inner-b"));
        System.out.println("UserModel:" + model);

        UserData data = mapper.map(model, UserData.class);
        Assert.assertNotNull(data);
        System.out.println("UserData:" + data);

        Assert.assertNotNull(data.getUserId());
        Assert.assertNotNull(data.getNickname());
        Assert.assertNull(data.getPasswd());

        Assert.assertNotNull(data.getInner());
        Assert.assertEquals(model.getInner().getInnerA(), data.getInner().getInnerA());
        Assert.assertEquals(model.getInner().getInnerB(), data.getInner().getInnerB());
    }
}
