package com.openClassRoom.watchlist.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CustomErrorController implements ErrorController {
	
	public String getErrorPath() {
		return "/error";
	}
	
	@GetMapping("/error")
	public ModelAndView handleEroor(HttpServletResponse response) {
		int code = response.getStatus();
		System.out.print("this error has code " + code + "happened!");
		return new ModelAndView("error");
	}
}	
