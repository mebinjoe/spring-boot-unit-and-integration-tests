package com.testing.unit;

import com.testing.model.AppModel;
import com.testing.model.AppResult;
import com.testing.service.AppService;
import com.testing.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppServiceUnitTests {

    @Autowired
    private AppService appService;

    @MockBean
    private RestTemplate template;

    @Test
    public void testGetMangasByTitle() throws IOException {
        // Parsing mock file
        AppResult mRs = JsonUtils.jsonFile2Object("data.json", AppResult.class);
        // Mocking remote service
        when(template.getForEntity(any(String.class), any(Class.class))).thenReturn(new ResponseEntity(mRs, HttpStatus.OK));
        // I search for goku but system will use mocked response containing only ken, so I can check that mock is used.
        List<AppModel> mangasByTitle = appService.getDataByTitle("goku");
        assertThat(mangasByTitle).isNotNull()
                .isNotEmpty()
                .allMatch(p -> p.getTitle()
                        .toLowerCase()
                        .contains("ken"));

    }
}
