package com.testing.integration;

import com.testing.controller.AppController;
import com.testing.model.AppResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppControllerIntegrationTests {

    MockMvc mockMvc;

    @Autowired
    AppController appController;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.appController).build();// Standalone context
    }

    /**
     * @throws Exception
     */
    @Test
    public void testSearchSync() throws Exception {
        mockMvc.perform(get("/sync/ken").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*.title", hasItem(is("Hokuto no Ken"))));
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
                .andExpect(jsonPath("$.*.title", hasItem(is("Hokuto no Ken"))));

    }

}
