package com.jjonami.datajpa.controller;

import com.jjonami.datajpa.entity.Member;
import com.jjonami.datajpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;


@RestController
@RequiredArgsConstructor
public class MemberController {

    private static final Logger log = LogManager.getLogger(MemberController.class);

    private final MemberRepository memberRepository;



    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Member member){
        return member.getUsername();
    }

    @GetMapping("/members")
    public Page<Member> list(Pageable pageable){
        return memberRepository.findAll(pageable);
    }

    @PostConstruct
    public void init(){
        memberRepository.save(new Member("jjonami"));
    }

}
