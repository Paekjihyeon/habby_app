package org.pubHabby.program.security.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.pubHabby.program.model.member.Member;
import org.pubHabby.program.security.mapper.UserMapper;
import org.pubHabby.program.security.model.LoginLog;
import org.pubHabby.program.security.model.UserDetailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        System.out.println("USER DETAIL");

        //회원 검증
        Member member = userMapper.findMember(userId);
        if(member == null) {
            new UsernameNotFoundException("사용자가 존재하지 않습니다.");
        }

        //로그인 로그 저장
        try {
            String memberIp = "";
            HttpServletRequest request =
                    ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            memberIp = request.getRemoteAddr();

            LoginLog loginLog  = new LoginLog(member.getMember_id(), memberIp);
            userMapper.saveLoginLog(loginLog);


        }catch(Exception e){
            throw new RuntimeException(e);
        }

        // authority 정보 저장
        UserDetailInfo userDetailInfo = new UserDetailInfo(member);
        return userDetailInfo;
    }



}
