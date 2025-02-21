package com.assignment.demo.core.services;

public interface ClassConfigurationService {
    boolean isClassLimitReached(int currentStudentCount);
    int getPassingMarks();
}
