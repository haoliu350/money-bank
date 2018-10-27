package com.fitch.mb.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fitch.mb.rest.service.MBService;

/**
 * Created by hliu on 2016/7/5.
 */
@RestController
public class MBController implements IndexController {

    @Autowired
    MBService mbService;



}
