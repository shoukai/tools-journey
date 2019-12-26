package org.apframework.drools.simple.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
      
    private Date bookingDate;//下单日期
      
    private int amout;//订单原价金额  
      
    private User user;//下单人  
      
    private int score;  

}