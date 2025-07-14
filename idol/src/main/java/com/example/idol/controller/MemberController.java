package com.example.idol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.idol.service.memberService;

@Controller
public class MemberController {
	
	private final memberService memberService;
	
	@Autowired
	public MemberController(memberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/members")
	public String serMembers(Model model) {
		var members = memberService.findAll();
		model.addAttribute("member",members);
		return "members";
		
	}
	
}
