package ru.netology.conditional_spring_boot_dz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    private static final GenericContainer<?> devapp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);


    private static final GenericContainer<?> prodapp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);


    @BeforeAll
    public static void setUp() {
        devapp.start();
        prodapp.start();
    }

    @Test
    void contextLoadsDev() {
        final String expectedDev = "Current profile is dev";
        ResponseEntity<String> forEntityDev = restTemplate.getForEntity("http://localhost:" + devapp
                .getMappedPort(8080)  + "/profile", String.class);
        System.out.println("devapp " + forEntityDev.getBody());
        Assertions.assertEquals(expectedDev,forEntityDev.getBody());
    }

    @Test
    void contextLoadsProd() {
        final String expectedProd = "Current profile is production";
        ResponseEntity<String> forEntityPro = restTemplate.getForEntity("http://localhost:" + prodapp
                .getMappedPort(8081) + "/profile", String.class);
        System.out.println("prodapp " + forEntityPro.getBody());
        Assertions.assertEquals(expectedProd,forEntityPro.getBody());
    }


}



