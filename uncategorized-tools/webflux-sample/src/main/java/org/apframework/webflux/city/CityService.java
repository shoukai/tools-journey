package org.apframework.webflux.city;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CityService {

    // 模拟数据库，存储 City 信息
    private static Map<Long, City> CITY_DB = Maps.newHashMap();

    public List<City> findAllCity() {
        return Lists.newArrayList(CITY_DB.values());
    }

    public City findCityById(Long id) {
        return CITY_DB.get(id);
    }

    public Long saveCity(City city) {
        city.setId(CITY_DB.size() + 1L);
        CITY_DB.put(city.getId(), city);
        return city.getId();
    }

    public Long updateCity(City city) {
        CITY_DB.put(city.getId(), city);
        return city.getId();
    }

    public Long deleteCity(Long id) {
        CITY_DB.remove(id);
        return id;
    }

}
