package com.mmmenzel.swapifun;
//package com.mmmenzel.swapitrileuco;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import static org.hamcrest.CoreMatchers.is;
//
//import com.mmmenzel.swapitrileuco.servicios.FilmService;
//import com.mmmenzel.swapitrileuco.servicios.PlanetService;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class SwapiControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private PlanetService utilityService;
//
//    @MockBean
//    private FilmService filmService;
//    
//    @Test
//    void testGetResultSuccess() throws Exception {
//    		mockMvc.perform(MockMvcRequestBuilders.
//            get("/swapi-proxy/person-info")
//    		.param("name", "Han Solo")		
//    	           .accept(MediaType.APPLICATION_JSON))
//    	           .andExpect(MockMvcResultMatchers.status().isOk())
//    	           .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//    	           .andExpect(jsonPath("$.name", is("Han Solo")));
//    }
//
//}
