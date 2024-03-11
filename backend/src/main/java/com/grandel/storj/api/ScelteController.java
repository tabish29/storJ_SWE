package com.grandel.storj.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import storj.api.ScelteApi;

@Slf4j
@Controller
@EnableAutoConfiguration
@RequestMapping("/api/v1")
public class ScelteController implements ScelteApi {

}
