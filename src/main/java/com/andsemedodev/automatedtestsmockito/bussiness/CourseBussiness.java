package com.andsemedodev.automatedtestsmockito.bussiness;

import com.andsemedodev.automatedtestsmockito.service.CourseService;

import java.util.ArrayList;
import java.util.List;

// STU - System (Method) Under Test
public class CourseBussiness {

    // CouseService is a dependency
    private CourseService service;

    public CourseBussiness(CourseService service) {
        this.service = service;
    }

    public List<String> retrieveCoursesRelatedToSpring(String student) {

        var filteredCourses = new ArrayList<String>();

        if ("Foo Bar".equals(student)) return filteredCourses;

        var allCourses = service.retrieveCourses(student);

        for (String course : allCourses) {
            if (course.contains("Spring")) filteredCourses.add(course);
        }

        return filteredCourses;
    }

    public void deleteCoursesNotRelatedToSpring(String student) {

        var allCourses = service.retrieveCourses(student);

        for (String course : allCourses) {
            if (!course.contains("Spring")) service.deleteCourse(course);
        }

    }

}
