package com.example.idol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.idol.entity.Artist;
import com.example.idol.entity.Member;
import com.example.idol.service.ArtistService;
import com.example.idol.service.memberService;

import jakarta.validation.Valid;

@Controller
public class MemberController {

	private final memberService memberService;
	private final ArtistService artistService;

	@Autowired
	public MemberController(memberService memberService, ArtistService artistService) {
		this.memberService = memberService;
		this.artistService = artistService;
	}
	
	@GetMapping("/members")
	public String defaultMembers(Model model) {
	    Artist artist = artistService.findById(1);
	    model.addAttribute("artist", artist);
	    return "members";
	}


	// メンバー登録画面の表示
	@GetMapping("/artists/registrationMember")
	public String registrationMember(Model model) {
		List<Artist> artists = artistService.findAll();
		model.addAttribute("artists", artists);
		model.addAttribute("member", new Member());
		model.addAttribute("title", "メンバー新規登録");
		return "registrationMember";
	}

	// フォームから送信されたメンバー情報を保存
	@PostMapping("/members")
	public String registerMember(@ModelAttribute @Valid Member member,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("artists", artistService.findAll());
			return "registrationMember";
		}
		memberService.save(member);
		return "redirect:/artists";
	}

	// 指定されたIDのメンバー情報を更新ページの表示
	@GetMapping("/member/{id}")
	public String updateMember(@PathVariable("id") Integer memberId, Model model) {
		List<Artist> artists = artistService.findAll();
		model.addAttribute("artists", artists);
		Member member = memberService.findById(memberId);
		model.addAttribute("member", member);
		model.addAttribute("title", "メンバー更新");
		return "updateMember";
	}

	// フォームから送信されたメンバー情報を更新
	@PostMapping("/members/{id}")
	public String updateMember(@PathVariable("id") Integer memberId, @ModelAttribute @Valid Member member,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("artists", artistService.findAll());
			return "updateMember";
		}
		Member existingMember = memberService.findById(memberId);
		existingMember.setMemberName(member.getMemberName());
		existingMember.setMemberHiraganaName(member.getMemberHiraganaName());
		existingMember.setMemberBirthday(member.getMemberBirthday());
		// 必要に応じてArtistの設定もここで

		memberService.save(existingMember);
		return "redirect:/artists";
	}

}
