package seulki.lee.springexam.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import seulki.lee.springexam.domain.model.SignupForm;
import seulki.lee.springexam.domain.model.User;
import seulki.lee.springexam.domain.service.UserService;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    //결혼여부 라디오버튼
    private Map<String, String> radioMarriage;

    /**
     * 라디오버튼 초기화 메소드
     */
    private Map<String, String> initRadioMarriage() {
        Map<String, String> radio = new LinkedHashMap<>();
        radio.put("기혼", "true");
        radio.put("미혼", "false");
        return radio;
    }

    @GetMapping("/home")
    public String getHome(Model model) {

        model.addAttribute("contents", "login/home :: home_contents");
        return "login/homeLayout";
    }

    @GetMapping("/userList")
    public String getUserList(Model model) {

        //컨텐츠 부분에 사용자 목록을 표시하기 위한 문자열 등록
        model.addAttribute("contents", "login/userList :: userList_contents");
        //사용자 목록 생성
        List<User> userList = userService.selectMany();
        //Model에 사용자 목록 등록
        model.addAttribute("userList", userList);
        //데이터건수
        int count = userService.count();
        model.addAttribute("userListCount", count);
        return "login/homeLayout";
    }

    /**
     * 사용자 상세화면 GET메소드 처리
     */
    @GetMapping("/userDetail/{id:.+}")
    public String getUserDetail(@ModelAttribute SignupForm form,
                                Model model,
                                @PathVariable("id") String userId) {
        // 사용자id 입력확인
        System.out.println("userId = " + userId);

        // 컨텐츠부분에 사용자 세부정보를 표시하기 위한 문자열 등록
        model.addAttribute("contents", "login/userDetail :: userDetail_contents");
        // 결혼여부 라띠오 버튼 초기화
        radioMarriage = initRadioMarriage();
        // 라디오 버튼 Map을 Model에 등록
        model.addAttribute("radioMarriage", radioMarriage);

        // 사용자 id 확인
        if (userId != null && userId.length() > 0) {
            // 사용자 정보 얻기
            User user = userService.selectOne(userId);
            // User클래스를 form클래스로 변환
            form.setUserId(user.getUserId());
            form.setUserName(user.getUserName());
            form.setBirthday(user.getBirthday());
            form.setAge(user.getAge());
            form.setMarriage(user.isMarriage());
            // Model에 등록
            model.addAttribute("signupForm", form);
        }
        return "login/homeLayout";
    }

    @PostMapping(value = "/userDetail", params = "update")
    public String postUserDetailUpdate(@ModelAttribute SignupForm form,
                                       Model model) {

        System.out.println("변경 버튼 처리");

        //User인스턴스 생성
        User user = new User();
        //Form클래스를 User클래스로 변환
        user.setUserId(form.getUserId());
        user.setPassword(form.getPassword());
        user.setUserName(form.getUserName());
        user.setBirthday(form.getBirthday());
        user.setAge(form.getAge());
        user.setMarriage(form.isMarriage());
        // 변경실행
        boolean result = userService.updateOne(user);

        if (result == true) {
            model.addAttribute("result", "변경성공");
        } else {
            model.addAttribute("result", "변경실패");
        }
        // 사용자목록화면 표시
        return getUserList(model);
    }

    @PostMapping(value = "/userDetail", params = "delete")
    public String postUserDetailDelete(@ModelAttribute SignupForm form,
                                       Model model) {
        System.out.println("삭제 버튼 실행");

        //삭제실행
        boolean result = userService.deleteOne(form.getUserId());
        if (result == true) {
            model.addAttribute("result", "삭제성공");
        } else {
            model.addAttribute("result", "삭제실패");
        }
        // 사용자목록화면 표시
        return getUserList(model);
    }

    /**
     * 사용자 목록 CSV로 출력
     */
    @GetMapping("/userList/csv")
    public ResponseEntity<byte[]> getUserListCsv(Model model) {

        // 모든 사용자를 검색하고 CSV파일을 서버에 저장
        userService.userCsvOut();

        byte[] bytes = null;

        try {

            // 서버에 저장된 sample.csv파일을 byte로 검색
            bytes = userService.getFile("sample.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }

        //HTTP 헤더 설정
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "text/csv; charset=UTF-8");
        //header.add("Content-Type", "text/csv; charset=MS949");
        header.setContentDispositionFormData("filename", "sample.csv");


        //sample.csv리턴
        return new ResponseEntity<>(bytes, header, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public String postLogout() {
        return "redirect:/login";
    }

}
