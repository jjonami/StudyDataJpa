package com.jjonami.datajpa.controller;

import com.jjonami.datajpa.dto.MemberDTO;
import com.jjonami.datajpa.entity.Member;
import com.jjonami.datajpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
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

    //http://localhost:8088/members?page=0&size=10&sort=id,desc&sord=username,desc
    @GetMapping("/members")
    public Page<MemberDTO> list(Pageable pageable){
        return memberRepository.findAll(pageable)
                .map(MemberDTO::new);
    }

    @GetMapping("/members2")
    public Page<MemberDTO> list2(@PageableDefault(size = 5, sort = "username") Pageable pageable){
        return memberRepository.findAll(pageable)
                .map(MemberDTO::new);
    }

    @GetMapping("/members3")
    public Slice<MemberDTO> list3(Pageable pageable){
        return memberRepository.findAll(pageable)
                .map(MemberDTO::new);
    }

    @PostConstruct
    public void init(){
        for(int i=1; i <100; i++){
            memberRepository.save(new Member("user" + i, i+20));
        }
    }

}
