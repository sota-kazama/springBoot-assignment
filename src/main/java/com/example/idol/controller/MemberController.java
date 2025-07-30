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
import com.example.idol.service.MemberService;

import jakarta.validation.Valid;

@Controller
public class MemberController {

	private final MemberService memberService;
	private final ArtistService artistService;

	//Autowired
	@Autowired
	public MemberController(MemberService memberService, ArtistService artistService) {
		this.memberService = memberService;
		this.artistService = artistService;
	}

	// デフォルトでID=1のアーティストのメンバー一覧を表示（トップ画面など）
	@GetMapping("/members")
	public String defaultMembers(Model model) {
		Artist artist = artistService.findById(1);
		model.addAttribute("artist", artist);
		return "Member/members"; // Memberフォルダを含める
	}

	// メンバー登録画面の表示
	@GetMapping("/artists/registrationMember")
	public String registrationMember(Model model) {
		List<Artist> artists = artistService.findAll();
		model.addAttribute("artists", artists);
		model.addAttribute("member", new Member());
		model.addAttribute("title", "メンバー新規登録");
		return "Member/registrationMember"; // Memberフォルダを含める
	}

	// 新規メンバー登録処理
	@PostMapping("/members")
	public String registerMember(
			@ModelAttribute @Valid Member member,
			BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("artists", artistService.findAll());
			return "Member/registrationMember"; // Memberフォルダを含める
		}

		memberService.save(member);
		return "redirect:/artists";
	}

	// メンバー更新画面の表示
	@GetMapping("/member/{id}")
	public String updateMember(@PathVariable("id") Integer memberId, Model model) {
		List<Artist> artists = artistService.findAll();
		Member member = memberService.findById(memberId);

		model.addAttribute("artists", artists);
		model.addAttribute("member", member);
		model.addAttribute("title", "メンバー更新");

		return "Member/updateMember"; // Memberフォルダを含める
	}

	// メンバー更新処理
	@PostMapping("/members/{id}")
	public String updateMember(
			@PathVariable("id") Integer memberId,
			@ModelAttribute @Valid Member member,
			BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("artists", artistService.findAll());
			return "Member/updateMember"; // Memberフォルダを含める
		}

		Member existingMember = memberService.findById(memberId);
		existingMember.setMemberName(member.getMemberName());
		existingMember.setMemberHiraganaName(member.getMemberHiraganaName());
		existingMember.setMemberBirthday(member.getMemberBirthday());
		existingMember.setArtist(member.getArtist()); // Artist更新が必要なら

		memberService.save(existingMember);
		return "redirect:/artists";
	}

}
