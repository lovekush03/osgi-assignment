package com.assignment.demo.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Class Configuration Service")
public @interface ClassConfiguration {

    @AttributeDefinition(name = "Max Students Allowed", description = "Maximum number of students allowed in class")
    int max_students() default 30;

    @AttributeDefinition(name = "Passing Marks", description = "Minimum marks required to pass")
    int passing_marks() default 40;
}