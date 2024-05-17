package org.pubHabby.program.model.member;

import lombok.Data;
import org.apache.ibatis.annotations.Result;

@Data
public class Member {
    private int member_no;
    private String member_id;
    private String member_pass;
    private String member_nick;
    private String member_image;
    private String regdate;
    private String update_date;
    private String pass_update_date;

}
