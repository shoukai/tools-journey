package org.apframework.webflux.city;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

    private Long id;
    private Long provinceId;
    private String cityName;
    private String description;
}
