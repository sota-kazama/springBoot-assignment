package com.example.idol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.idol.entity.Member;
import com.example.idol.repository.MemberRepository;

@Service
public class memberService {
	private final MemberRepository memberRepository;

	@Autowired
	public memberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public void save(Member member) {
		memberRepository.save(member);
	}

	public List<Member> findAll() {
		return memberRepository.findAll();
	}
}
