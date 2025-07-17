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
import com.example.idol.service.ArtistService;

import jakarta.validation.Valid;

@Controller
public class ArtistController {

	private final ArtistService artistService;

	@Autowired
	public ArtistController(ArtistService artistService) {
		this.artistService = artistService;
	}

	// アーティスト一覧画面の表示
	@GetMapping("/artists")
	public String getArtists(Model model) {
		List<Artist> artists = artistService.findAll();
		model.addAttribute("artists", artists);
		model.addAttribute("title", "アーティスト一覧");
		return "artists";
	}

	// 指定されたIDのアーティストを削除する
	@DeleteMapping("/artists/{id}")
	public String delete(@PathVariable("id") Integer artistId) {
		artistService.delete(artistId);
		return "redirect:/artists";
	}

	// 指定されたIDのアーティストを更新ページの表示
	@GetMapping("/artists/{id}")
	public String updateArtist(@PathVariable("id") Integer artistId, Model model) {
		Artist artist = artistService.findById(artistId);
		model.addAttribute("artist", artist);
		model.addAttribute("title", "アーティスト更新");
		return "updateArtist";
	}

	// アーティスト登録画面の表示
	@GetMapping("/artists/registration")
	public String registrationArtists(Model model) {
		model.addAttribute("artist", new Artist());
		model.addAttribute("title", "アーティスト新規登録");
		return "registration";
	}

	// フォームから送信されたアーティスト情報を保存
	@PostMapping("/artists")
	public String registerArtist(@ModelAttribute @Valid Artist artist, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("title", "アーティスト新規登録");
			return "registration";
		}
		artistService.save(artist);
		return "redirect:/artists";
	}

	// フォームから送信されたアーティスト情報を更新
	@PostMapping("/artists/{id}")
	public String updateArtist(@PathVariable("id") Integer artistId, @ModelAttribute @Valid Artist artist,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("title", "アーティスト更新");
			model.addAttribute("artist", artist);
			return "updateArtist";
		}
		Artist existingArtist = artistService.findById(artistId);
		existingArtist.setArtistName(artist.getArtistName());
		existingArtist.setArtistHiraganaName(artist.getArtistHiraganaName());
		existingArtist.setArtistArtUrl(artist.getArtistArtUrl());

		artistService.save(existingArtist);
		return "redirect:/artists";
	}

	// 指定されたアーティストIDに紐づくメンバー一覧画面を表示する
	@GetMapping("/artists/{id}/members")
	public String getMembers(@PathVariable("id") Integer artistId, Model model) {
		Artist artist = artistService.findById(artistId);
		model.addAttribute("artist", artist);
		return "members";
	}
}
