package com.andsemedodev.automatedtestsmockito.mockito;

import com.andsemedodev.automatedtestsmockito.bussiness.CourseBussiness;
import com.andsemedodev.automatedtestsmockito.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseBussinessMockitoInjectMocksTest {

    @Mock
    CourseService mockservice;
    @InjectMocks
    CourseBussiness bussiness;
    //bussiness = new CourseBussiness(mockservice);

    @Captor
    ArgumentCaptor<String> argumentCaptor;

    List<String> courses;

    @BeforeEach
    void setup() {
        // Given / Arrange

        courses = Arrays.asList(
                "REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker",
                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "Spotify Engineering Culture Desmistificado",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker",
                "Docker do Zero à Maestria - Contêinerização Desmistificada",
                "Docker para Amazon AWS Implante Apps Java e .NET com Travis CI",
                "Microsserviços do 0 com Spring Cloud, Spring Boot e Docker",
                "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker",
                "Kotlin para DEV's Java: Aprenda a Linguagem Padrão do Android",
                "Microsserviços do 0 com Spring Cloud, Kotlin e Docker"
        );
    }

    @Test
    void testCourseRelatedToSpring_When_UsingMockWIthBDD() {

        // Given / Arrange
        given(mockservice.retrieveCourses("Leandro"))
                .willReturn(courses);

        // When / Act
        var filteredCourse =
                bussiness.retrieveCoursesRelatedToSpring("Leandro");

        // Then / Assert
        assertThat(filteredCourse.size(), is(4));

    }

    @DisplayName("Delete courses not related to spring should return method deleteCourse()")
    @Test
    void testDeleteCoursesNotRelatedToSpring_UsingMockitoVerify_ShouldCallMethod_deleteCourse() {

        // Given / Arrange
        given(mockservice.retrieveCourses("Anderson"))
                .willReturn(courses);

        // When / Act
        bussiness.deleteCoursesNotRelatedToSpring("Anderson");

        // Then / Assert
        // times(o numero de vezes que vai invocar o deleteCourse)
        verify(mockservice, times(1))
                .deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
        verify(mockservice, atLeast(1))
                .deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
        verify(mockservice)
                .deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
        verify(mockservice, never())
                .deleteCourse("REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker");
    }

    @DisplayName("Delete courses not related to spring should return method deleteCourse() V2")
    @Test
    void testDeleteCoursesNotRelatedToSpring_UsingMockitoVerify_ShouldCallMethod_deleteCourseV2() {

        // Given / Arrange
        given(mockservice.retrieveCourses("Anderson"))
                .willReturn(courses);

        // When / Act
        bussiness.deleteCoursesNotRelatedToSpring("Anderson");

        // Then / Assert
        then(mockservice)
                .should()
                    .deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
        then(mockservice)
                .should()
                    .deleteCourse("Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#");
        then(mockservice)
                .should(never())
                    .deleteCourse("REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker");
    }

    @DisplayName("Delete courses not related to spring usin capturing arguments should return method deleteCourse()")
    @Test
    void testDeleteCoursesNotRelatedToSpring_CapturingArguments_ShouldCallMethod_deleteCourse() {

        // Given / Arrange

        given(mockservice.retrieveCourses("Anderson"))
                .willReturn(courses);

        //ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        // When / Act
        bussiness.deleteCoursesNotRelatedToSpring("Anderson");

        // Then / Assert
        String agileCourse = "Agile Desmistificado com Scrum, XP, Kanban e Trello";

        then(mockservice).should(times(7)).deleteCourse(argumentCaptor.capture());
        assertThat(argumentCaptor.getAllValues().size(), is(7));
    }
}
