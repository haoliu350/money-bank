package com.fitch.mb.rest.controller;

import com.fitch.mb.rest.dto.ApiResponse;
import com.fitch.mb.rest.dto.CRCard;
import com.fitch.mb.rest.dto.TestResponse;
import com.fitch.mb.rest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

//test controller by hliu
@RestController
@RequestMapping("/test")
public class TestController implements IndexController {

    @Value("${test.string}")
    private String testString;

    @Autowired
    private TestService testService;

    @RequestMapping("/index")
    public String index() {
        return testString;
    }

    @RequestMapping(value = "/json-response", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ApiResponse testResponse() {
        TestResponse t = new TestResponse();
        t.setId(123);
        t.setName("Hao Liu");
        t.setDescription("Hahahahahaha");
        return t;
    }

    @RequestMapping(value = "/json-exception/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ApiResponse testException(@PathVariable int id) {
        return testService.testServiceException(id,"Hao", "His is a nice guy!");
    }

    @RequestMapping(value = "/param", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ApiResponse testParam(@RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "cost", required = false) int cost,
                                  @RequestParam(value = "rarity", required = false) String rarity,
                                  @RequestParam(value = "type", required = false) String type ) {
        return new CRCard(name, cost, rarity, type);

    }


    @RequestMapping("/db")
    public String dbTest() {
        return testService.getSysdate();
    }
}
