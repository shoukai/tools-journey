package org.apframework.dozer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnotationTargetBean {
    private String pk;
    private String name;
    private String binaryData;
}
