
package com.assignment.demo.core.services.impl;

import com.assignment.demo.core.config.ClassConfiguration;
import com.assignment.demo.core.services.ClassConfigurationService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = ClassConfigurationService.class,
        configurationPolicy = ConfigurationPolicy.OPTIONAL,
        immediate = true)
@Designate(ocd = ClassConfiguration.class)
public class ClassConfigurationServiceImpl implements ClassConfigurationService {

    private int maxStudents;
    private int passingMarks;

    @Activate
    @Modified
    protected void activate(ClassConfiguration config) {
        this.maxStudents = config.max_students();
        this.passingMarks = config.passing_marks();
    }

    @Override
    public boolean isClassLimitReached(int currentStudentCount) {
        return currentStudentCount >= maxStudents;
    }

    @Override
    public int getPassingMarks() {
        return passingMarks;
    }
}