package com.mmmenzel.swapifun.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
public class FaviconController {
 
	@Hidden
	@GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }
}