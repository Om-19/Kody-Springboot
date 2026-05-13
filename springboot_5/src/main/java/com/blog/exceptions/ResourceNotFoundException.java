package com.blog.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    long fieldvalue;

    public ResourceNotFoundException(String resourceName, String fieldName, long filedValue){
        super(String.format("%s not found with s", resourceName, fieldName, filedValue));

        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldvalue = filedValue;
    }
}
