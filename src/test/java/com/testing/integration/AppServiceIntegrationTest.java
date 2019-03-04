package com.testing.integration;

import com.testing.model.AppModel;
import com.testing.service.AppService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppServiceIntegrationTest {

    @Autowired
    private AppService appService;

    @Test
    public void testGetMangasByTitle() {
        List<AppModel> dataByTitle = appService.getDataByTitle("ken");
        assertThat(dataByTitle).isNotNull().isNotEmpty();
    }
}
