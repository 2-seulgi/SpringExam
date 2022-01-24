package seulki.lee.springexam.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import seulki.lee.springexam.domain.model.GroupOrder;
import seulki.lee.springexam.domain.model.SignupForm;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class signupController {
    private Map<String, String> radioMarriage;
    private Map<String, String> initRadioMarriage(){
        Map<String, String> radio = new LinkedHashMap<>();
        radio.put("기혼","true");
        radio.put("미혼","false");
        return radio;
    }
    @GetMapping("/signup")
    public String getSignup(@ModelAttribute SignupForm form, Model model) {
        model.addAttribute("signupForm", form);
        // 라디오버튼의 초기화 메소드 호출
        radioMarriage = initRadioMarriage();
        // 라디오버튼용 Map 을 Model 에 등록
        model.addAttribute("radioMarriage", radioMarriage);
        return "login/signup";
    }
    @PostMapping("/signup")
    public String postSignup(@ModelAttribute @Validated(GroupOrder.class)
                                     SignupForm form,
                             BindingResult bindingResult,
                             Model model) {
        // 입력항목 조건에 걸리면 사용자 등록 화면으로 리턴
        if(bindingResult.hasErrors()) {
            // Get 요청을 위한 메소드를 호출하여 사용자등록 화면으로 리턴
            return getSignup(form,model);
        }
        System.out.println(form); // form 내용을 콘솔에 출력
        // login.html 로 리다이렉션
        return "redirect:/login";
    }
}
