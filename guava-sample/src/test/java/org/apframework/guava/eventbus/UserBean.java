package org.apframework.guava.eventbus;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserBean implements Serializable{
    private static final long serialVersionUID = 3864751233076912473L;

    private Integer id;
    private String name;
}
