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
			String originalFileName = file.getOriginalFilename();
			String fileName = originalFileName;

			// 保存先ディレクトリ
			Path directory = Paths.get("static/images");
			if (!Files.exists(directory)) {
				Files.createDirectories(directory); // ディレクトリがなければ作成
			}

			Path savePath = directory.resolve(fileName);
			int count = 1;

			// 重複がある場合、ファイル名を変更していく（例: image(1).jpg）
			while (Files.exists(savePath)) {
				String nameWithoutExt = getFileNameWithoutExtension
						(originalFileName);
				String extension = getFileExtension(originalFileName);
				fileName = nameWithoutExt + "(" + count + ")." + extension;
				savePath = directory.resolve(fileName);
				count++;
			}

			// アーティストにファイルパスをセット
			artist.setArtistPhoto("images/" + fileName);

			// ファイル書き込み
			Files.write(savePath, file.getBytes());
		}

		artistRepository.save(artist);
	}

	// ファイル名から拡張子を取り除いた部分を返す
	private String getFileNameWithoutExtension(String fileName) {
		int dotIndex = fileName.lastIndexOf('.');
		return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
	}

	// ファイル名から拡張子のみを返す
	private String getFileExtension(String fileName) {
		int dotIndex = fileName.lastIndexOf('.');
		return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
	}

	// IDでアーティスト検索
	public Artist findById(Integer artistId) {
		return artistRepository.findById(artistId).orElseGet(Artist::new);
	}
}
