package de.jenssproede.subapi.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import de.jenssproede.subapi.pojo.Season;
import de.jenssproede.subapi.pojo.Series;
import de.jenssproede.subapi.service.IService;
import de.jenssproede.subapi.service.Services;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class ServiceControllerTest {

    public static final String TV4USER = "TV4User";
    private MockMvc mvc;

    @Before
    public void setup() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new ServiceController()).build();
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    }

    @Test
    public void getAvaibableServices() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/services").accept(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println("Found services: " + result.getResponse().getContentAsString());
    }

    @Test
    public void getCorrectService() {
        assertNotNull(Services.getInstance().getService(TV4USER));
        assertEquals(TV4USER, Services.getInstance().getService(TV4USER).getClass().getSimpleName());
    }

    @Test
    public void getHTMLSourceFromTV4UserService() {
        IService service = Services.getInstance().getService(TV4USER);
        assertNotNull(service);

        List<Series> seriesList = service.searchSeries("Breaking Bad");
        assertEquals("299", seriesList.get(0).getLink());

        List<Season> seasonList = service.searchSeasons(seriesList.get(0));
        Collections.sort(seasonList);
        System.out.println(seasonList);
    }
}
