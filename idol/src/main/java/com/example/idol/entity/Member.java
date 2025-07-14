package com.example.idol.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@Data
@NoArgsConstructor
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer memberId;

	@NotBlank(message = "空要素は許可されていません")
	private String memberName;

	@NotBlank(message = "空要素は許可されていません")
	private String memberHiraganaName;
	
	private Date memberBirthday;
	private String memberPhoto;

	@ManyToOne
	@JoinColumn(name = "artistId")
	private Artist artist;

	public Integer getMemberAge() {
		return Period.between(memberBirthday.toLocalDate(), LocalDate.now()).getYears();
	}

}
