package org.oualid.ssi.controllers;

import org.oualid.ssi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainPageController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/main")
    public String welcome(Authentication a,Model model){
        model.addAttribute("username",a.getName());
        model.addAttribute("products",productService.findAllProduct());
        return "main";
    }

    @GetMapping(value = "/hello")
    public String hello(){
        return "hello";
    }
}
