package com.example.idol.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "artists")
@Data
@NoArgsConstructor
public class Artist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer artistId;

	@NotBlank(message = "空要素は許可されていません")
	private String artistName;

	@NotBlank(message = "空要素は許可されていません")
	private String artistHiraganaName;

	private String artistArtUrl;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "artistId")
	private List<Member> members;
}
