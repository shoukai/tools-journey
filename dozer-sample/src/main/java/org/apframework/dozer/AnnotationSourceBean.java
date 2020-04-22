package org.apframework.dozer;

import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnotationSourceBean {
    @Mapping("pk")
    private Long id;
    // 同名属性默认拷贝
    private String name;
    @Mapping("binaryData")
    private String data;
}
