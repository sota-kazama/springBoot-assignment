package com.example.idol.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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

    @NotBlank(message = "メンバー名を入力してください")
    private String memberName;

    @NotBlank(message = "メンバー名(読み)を入力してください")
    private String memberHiraganaName;

    @Past(message = "誕生日は過去の日付にしてください")
    private Date memberBirthday;

    private String memberPhoto;
    @Transient
    private MultipartFile memberPhotoFile;

    @NotNull(message = "アーティストを選択してください")
    @ManyToOne
    @JoinColumn(name = "artistId")
    private Artist artist;

    public Integer getMemberAge() {
        if (memberBirthday == null) return null;
        return Period.between(memberBirthday.toLocalDate(), LocalDate.now()).getYears();
    }
}
