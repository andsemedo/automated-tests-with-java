package com.andsemedodev.automatedtestsmockito.bussiness;

import com.andsemedodev.automatedtestsmockito.service.CourseService;
import com.andsemedodev.automatedtestsmockito.stubs.CourseServiceStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseBussinessStubTest {

    @Test
    void testCourseRelatedToSpring_When_UsingStub() {
        // Given / Arrange
        CourseService stubservice = new CourseServiceStub();
        CourseBussiness bussiness = new CourseBussiness(stubservice);

        // When / Act
        var filteredCourse =
                bussiness.retrieveCoursesRelatedToSpring("Leandro");

        // Then / Assert
        assertEquals(4, filteredCourse.size());

    }

    @Test
    void testCoursesRelatedToSpring_When_UsinFooBarStudent() {
        // Given / Arrange
        CourseService stubservice = new CourseServiceStub();
        CourseBussiness bussiness = new CourseBussiness(stubservice);

        // When / Act
        var filteredCourse =
                bussiness.retrieveCoursesRelatedToSpring("Foo Bar");

        // Then / Assert

        assertEquals(0, filteredCourse.size());
    }

}
