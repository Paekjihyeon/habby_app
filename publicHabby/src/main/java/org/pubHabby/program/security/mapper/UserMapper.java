package org.pubHabby.program.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.pubHabby.program.model.member.Member;
import org.pubHabby.program.security.model.LoginLog;

@Mapper
public interface UserMapper {

    public Member findMember(String userId);
    public void saveLoginLog(LoginLog loginLog);
}
