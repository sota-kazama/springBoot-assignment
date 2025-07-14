package com.example.idol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class ArtistController {

	private final ArtistService artistService;
	private final memberService memberService;

	@Autowired
	public ArtistController(ArtistService artistService, memberService memberService) {
		this.artistService = artistService;
		this.memberService = memberService;
	}

	//アーティスト一覧画面の表示
	@GetMapping("/artists")
	public String getArtists(Model model) {
		var artists = artistService.findAll();
		model.addAttribute("artists", artists);
		model.addAttribute("title", "アーティスト一覧");
		return "artists";
	}

	//指定されたIDのアーティストを削除する
	@DeleteMapping("/artists/{id}")
	public String delete(@PathVariable("id") Integer artistId) {
		artistService.delete(artistId);
		return "redirect:/artists";
	}

	//アーティスト登録画面の表示
	@GetMapping("/artists/registration")
	public String registrationArtists(Model model) {
		model.addAttribute("artist", new Artist());
		model.addAttribute("title", "アーティスト新規登録");
		return "registration";
	}

	//フォームから送信されたアーティスト情報を登録
	@PostMapping("/artists")
	public String registerArtist(@ModelAttribute @Valid Artist artist, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			// バリデーションエラー時は入力画面に戻してエラー情報を表示
			model.addAttribute("title", "アーティスト新規登録");
			return "registration"; // リダイレクトではなくテンプレート名を返す
		}
		artistService.save(artist);
		return "redirect:/artists";
	}

	//メンバー登録画面の表示
	@GetMapping("/artists/registrationMember")
	public String registrationMember(Model model) {
		List<Artist> artists = artistService.findAll();
		model.addAttribute("artists", artists);
		model.addAttribute("member", new Member());
		model.addAttribute("title", "メンバー新規登録");
		return "registrationMember";
	}

	//メンバー登録
	@PostMapping("/members")
	public String registerMember(@ModelAttribute @Valid Member member, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("artists", artistService.findAll());
			return "registrationMember";
		}
		memberService.save(member);
		return "redirect:/artists";
	}

	//指定されたアーティストIDに紐づくメンバー一覧画面を表示する
	@GetMapping("/artists/{id}/members")
	public String getMembers(@PathVariable("id") Integer artistId, Model model) {
		Artist artist = artistService.findById(artistId);
		model.addAttribute("artist", artist);
		return "members";
	}

}
