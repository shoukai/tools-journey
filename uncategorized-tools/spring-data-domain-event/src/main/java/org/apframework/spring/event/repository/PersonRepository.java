package org.apframework.spring.event.repository;

import org.apframework.spring.event.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wangyunfei on 2017/6/19.
 */
public interface PersonRepository extends JpaRepository<Person,Long>{
}
