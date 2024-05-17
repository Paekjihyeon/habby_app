package org.pubHabby.program.security.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.pubHabby.program.model.member.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@ToString
public class UserDetailInfo implements UserDetails {

    //UserDetails 구현
    private String username;
    private String password;
    private boolean enabled = true; // 사용자 활성화 여부
    private boolean accountNonExpired = true;   // 사용자 계정 만료 없음
    private boolean credentialsNonExpired = true;   // 비밀번호 만료 없음
    private boolean accountNonLocked = true;    // 계정 잠금 여부

    private String member_id;
    private String member_nick;
    private String member_image;
    private String update_date;
    private Collection<? extends GrantedAuthority> authorities;


    public UserDetailInfo(Member member){
        Collection<GrantedAuthority> collectors = new ArrayList<>();

        this.member_id = member.getMember_id();
        this.password = member.getMember_pass();
        this.member_nick = member.getMember_nick();
        this.member_image = member.getMember_nick();
        this.update_date = member.getUpdate_date();

        this.authorities = collectors;

    }
}
