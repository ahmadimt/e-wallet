package com.imti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by imteyaz on 01/12/18
 **/

@Controller
@RequestMapping(HomeController.HOME_PAGE)
public class HomeController {

  static final String HOME_PAGE = "/";

  @GetMapping
  public String home() {
    return "index";
  }
}
