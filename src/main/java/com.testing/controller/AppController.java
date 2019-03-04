package com.testing.controller;

import com.testing.model.AppModel;
import com.testing.service.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private AppService appService;

    @RequestMapping(value = "/async/{title}", method = RequestMethod.GET)
    @Async
    public CompletableFuture<List<AppModel>> searchASync(@PathVariable(name = "title") String title) {
        return CompletableFuture.completedFuture(appService.getDataByTitle(title));
    }

    @RequestMapping(value = "/sync/{title}", method = RequestMethod.GET)
    public @ResponseBody List<AppModel> searchSync(@PathVariable(name = "title") String title) {
        return appService.getDataByTitle(title);
    }

}
