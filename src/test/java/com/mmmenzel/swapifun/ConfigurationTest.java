package com.mmmenzel.swapifun;
//package com.mmmenzel.swapitrileuco;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Assertions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestPropertySource;
//
//
//@SpringBootTest
//@ContextConfiguration(classes = { SwapiTrileucoApplication.class })
//@TestPropertySource(properties = { "spring.config.location=classpath:application.properties" })
//class ConfigurationTest {
//
//    @Autowired
//    private ApiProperties apiProperties;
//
//    @Test
//    void testExternalConfiguration() {
//        String mainEntryPoint = apiProperties.getMainEndPoint();
//        Assertions.assertNotNull(mainEntryPoint);
//        Assertions.assertEquals(mainEntryPoint, "http://swapi.trileuco.com:1138/api/people/");
//    }
//}