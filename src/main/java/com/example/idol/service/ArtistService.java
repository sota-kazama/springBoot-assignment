package com.example.idol.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.idol.entity.Artist;
import com.example.idol.repository.ArtistRepository;

@Service
public class ArtistService {

	private final ArtistRepository artistRepository;

	@Autowired
	public ArtistService(ArtistRepository artistRepository) {
		this.artistRepository = artistRepository;
	}

	// 全アーティスト取得
	public List<Artist> findAll() {
		return artistRepository.findAll();
	}

	// アーティスト削除
	public void delete(Integer artistId) {
		artistRepository.deleteById(artistId);
	}

	// アーティスト保存（画像含む）
	public void save(Artist artist, MultipartFile file) throws IOException {
		if (file != null && !file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			String filePath = "images/" + fileName;

			artist.setArtistPhoto(filePath);

			Path savePath = Paths.get("static/images/" + fileName);
			Files.write(savePath, file.getBytes());
		}

		artistRepository.save(artist);
	}

	// IDでアーティスト検索
	public Artist findById(Integer artistId) {
		return artistRepository.findById(artistId).orElseGet(Artist::new);
	}
}
