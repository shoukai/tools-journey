package org.apframework.guava.eventbus;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChargeEvent implements Serializable{

    private static final long serialVersionUID = -8775590840527884827L;

    private UserBean user;
    private ChargeBean charge;
}