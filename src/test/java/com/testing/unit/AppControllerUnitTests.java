package com.testing.unit;

import com.testing.controller.AppController;
import com.testing.model.AppModel;
import com.testing.service.AppService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppControllerUnitTests {

    @MockBean
    AppService appService;

    MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired
    AppController appController;

    private List<AppModel> samples;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.appController).build();// Standalone context
        AppModel appModel1 = AppModel.builder()
                .title("Aey ou you")
                .description("The year is 199X. The Earth has been devastated by nuclear war...")
                .build();
        AppModel appModel2 = AppModel.builder()
                .title("Sam Pas")
                .description("For those who suffer nightmares, help awaits at the Ginseikan Tea House, where patrons can order much more than just Darjeeling. Hiruko is a special kind of a private investigator. He's a dream eater....")
                .build();

        samples = new ArrayList<>();
        samples.add(appModel1);
        samples.add(appModel2);
        // Mocking service
        when(appService.getDataByTitle(any(String.class))).thenReturn(samples);
    }

    /**
     * @throws Exception
     */
    @Test
    public void testSearchSync() throws Exception {
        mockMvc.perform(get("/sync/ken").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("Aey ou you")))
                .andExpect(jsonPath("$[1].title", is("Sam Pas")));
    }

    /**
     * @throws Exception
     */
    @Test
    public void testSearchASync() throws Exception {
        MvcResult result = mockMvc.perform(get("/async/ken").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(request().asyncStarted())
                .andDo(print())
                .andReturn();

        mockMvc.perform(asyncDispatch(result))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("Aey ou you")));

    }

}
