package com.grandel.storj.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import storj.api.PartiteApi;

@Slf4j
@Controller
@EnableAutoConfiguration
@RequestMapping("/api/v1")
public class PartiteController implements PartiteApi {

}
