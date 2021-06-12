package com.graphql.deliveryShare2.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
@Controller
public class WebController {	
	@RequestMapping("/")
	public String jspCheck(Model model) {
		System.out.println(" /jsp 타는지 ");
		
		model.addAttribute("name", "name 입니다.");
		return "index";
	}
}