package org.apframework.spring.event.repository;

import org.apframework.spring.event.domain.GenderStat;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wangyunfei on 2017/6/19.
 */
public interface GenderRepository  extends JpaRepository<GenderStat,Long> {
}
