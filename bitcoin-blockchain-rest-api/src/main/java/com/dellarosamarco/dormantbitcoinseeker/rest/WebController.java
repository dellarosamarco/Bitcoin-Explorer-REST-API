package com.dellarosamarco.dormantbitcoinseeker.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void _default(){}
}
