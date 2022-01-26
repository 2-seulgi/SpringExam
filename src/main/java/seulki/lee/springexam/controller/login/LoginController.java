package seulki.lee.springexam.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    // post나 get 차이 없음..아직 단순한 이동 기능만 하고 있기 때문
    @GetMapping("/login")
    public String getLogin(Model mode){
        return "login/login";
    }

    // 로그인 할때 받아주는 부분
    @PostMapping("/login")
    public String postLogin(Model model) {
        return "login/home";
    }
}
