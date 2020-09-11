package org.apframework.spring.event.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apframework.spring.event.event.PersonSavedEvent;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by wangyunfei on 2017/6/19.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "domainEvents")
public class Person  {//extends AbstractAggregateRoot{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer gender;//1:male;2:female

//    public Person afterPersonSavedCompleted(){
//        registerEvent(new PersonSavedEvent(this.id,this.gender));
//        return this;
//    }

    //该方法会在 PersonRepository save() 调用时被触发调用
    @DomainEvents
    Collection<Object> domainEvents() {
        List<Object> events= new ArrayList<Object>();
        events.add(new PersonSavedEvent(this.id,this.gender));
        return events;
    }

    @AfterDomainEventPublication
    void callbackMethod() {
        //
        //
    }

}
