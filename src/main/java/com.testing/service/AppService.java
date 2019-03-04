package com.testing.service;

import com.testing.model.AppModel;
import com.testing.model.AppResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AppService {

    private static final Logger logger = LoggerFactory.getLogger(AppService.class);
    private static final String DATA_SEARCH_URL="http://api.jikan.moe/search/manga/";

    @Autowired
    RestTemplate restTemplate;


    public List<AppModel> getDataByTitle(String title) {
        return restTemplate.getForEntity(DATA_SEARCH_URL+title, AppResult.class).getBody().getResult();
    }
}
