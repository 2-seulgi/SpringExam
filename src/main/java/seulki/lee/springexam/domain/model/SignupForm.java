package seulki.lee.springexam.domain.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class SignupForm {
    // 입력형식, 이메일주소
    @NotBlank(groups = ValiGroup1.class)
    @Email(groups = ValiGroup2.class)
    private String userId;
    // 입력필수, 4-100 자까지, 영숫자만
    @NotBlank(groups = ValiGroup1.class)
    @Length(min=4, max=100, groups = ValiGroup2.class)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValiGroup3.class)
    private String password;
    // 입력필수
    @NotBlank(groups = ValiGroup1.class)
    private String userName;
    // 입력필수
    @NotNull(groups = ValiGroup1.class)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthday;
    // 입력은 20-100 까지만만
    @Min(value = 20, groups = ValiGroup2.class)
    @Max(value = 100, groups = ValiGroup2.class)
    private int age;
    // false 만 가능
    @AssertFalse(groups = ValiGroup2.class)
    private boolean marriage;
}