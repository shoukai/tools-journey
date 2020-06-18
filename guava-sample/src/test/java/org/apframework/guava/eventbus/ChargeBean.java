package org.apframework.guava.eventbus;

import lombok.Data;

@Data
public class ChargeBean {

    private static final long serialVersionUID = 3934449059968595133L;

    private Integer id;
    private Integer orderId;

    /**
     * 分
     */
    private Integer money;
}
