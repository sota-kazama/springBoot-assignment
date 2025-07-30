package com.example.idol.service;

import java.io.IOException;
import java.nio.file.Files;
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

	public List<Artist> findAll() {
		return artistRepository.findAll();
	}

	public void delete(Integer artistId) {
		artistRepository.deleteById(artistId);
	}

	public void save(Artist artist, MultipartFile file) throws IOException {
		String filePath = "images/" + file.getOriginalFilename();
		artist.setArtistPhoto(filePath);
		Files.write(Paths.get("static/images/" + file.getOriginalFilename()),file.getBytes());
		artistRepository.save(artist);
	}

	public Artist findById(Integer artistId) {
		return artistRepository.findById(artistId).orElseGet(Artist::new);
	}
}
