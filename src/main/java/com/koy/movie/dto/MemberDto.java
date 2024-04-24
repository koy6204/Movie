package com.koy.movie.dto;

import com.koy.movie.model.Member;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {

    private long id;
    private String username;
    private String password;
    private String email;

    static public MemberDto toDto(Member member) {

        return MemberDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .email(member.getEmail())
                .build();
    }

    public Member toEntity() {

        return Member.builder()
                .id(id)
                .username(username)
                .email(email)
                .build();
    }
}
