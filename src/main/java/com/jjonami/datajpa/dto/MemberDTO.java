package com.jjonami.datajpa.dto;

import com.jjonami.datajpa.entity.Member;
import lombok.Data;

@Data
public class MemberDTO {

    private Long id;
    private String userName;
    private String teamName;

    public MemberDTO(Long id, String userName, String teamName) {
        this.id = id;
        this.userName = userName;
        this.teamName = teamName;
    }

    public MemberDTO(Member member){
        this.id = member.getId();
        this.userName = member.getUsername();
    }
}
