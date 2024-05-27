package org.pubHabby.program.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    /**로그인 */
    @GetMapping("/login")
    public String cutstomLogin(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "exception", required = false) String exception, Model model){
        System.out.println("EXCEPTION :: " + exception);
        return exception;
    }



    /**로그아웃*/


    /**회원가입*/
}
