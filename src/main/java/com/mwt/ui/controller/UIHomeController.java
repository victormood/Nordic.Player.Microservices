package com.mwt.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home page controller.
 * 
 * @author v.manea
 */
@Controller
public class UIHomeController {

	@RequestMapping("/")
	public String home() {
		return "index";
	}

}
