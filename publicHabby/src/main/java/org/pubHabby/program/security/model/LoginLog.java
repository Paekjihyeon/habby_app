package org.pubHabby.program.security.model;

import lombok.Data;

@Data
public class LoginLog {
    private int log_no;
    private String write_id;
    private String ip;
    private String regdate;

    public LoginLog(String write_id, String ip) {
        this.write_id = write_id;
        this.ip = ip;
    }
}
