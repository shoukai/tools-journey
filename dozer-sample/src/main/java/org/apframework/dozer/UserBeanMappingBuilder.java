package org.apframework.dozer;

import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import com.github.dozermapper.core.loader.api.FieldsMappingOptions;

public class UserBeanMappingBuilder extends BeanMappingBuilder {

    @Override
    protected void configure() {
        mapping(UserModel.class, UserData.class).fields("password", "passwd", FieldsMappingOptions.oneWay());
    }

}
