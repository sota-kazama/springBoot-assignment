package com.example.idol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.idol.entity.Member;
import com.example.idol.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // メンバー保存
    public void save(Member member) {
        memberRepository.save(member);
    }

    // 全メンバー取得
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    // IDでメンバー検索
    public Member findById(Integer memberId) {
        return memberRepository.findById(memberId).orElseGet(Member::new);
    }
}
