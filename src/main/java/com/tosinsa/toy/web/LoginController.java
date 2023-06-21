package com.tosinsa.toy.web;


import com.tosinsa.toy.SessionConst;
import com.tosinsa.toy.domain.member.Member;
import com.tosinsa.toy.domain.member.Role;
import com.tosinsa.toy.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/")
    public String home(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) Member loginMember, Model model){

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "login";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        return "redirect:/items";
    }

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request){

        if (bindingResult.hasErrors()){
            return "loginForm";
        }

        Member loginMember = loginService.login(loginForm);

        if (loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            log.debug("debug={} ", bindingResult);
            return "loginForm";
        }

        //로그인 성공 처리

        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession();

        //세션에 로그인 회원 정보 보관
        if(loginMember.getRole() == Role.ADMINISTER){
            session.setAttribute(SessionConst.LOGIN_ADMINISTER,loginMember);
        }else {
            session.setAttribute(SessionConst.LOGIN_USER, loginMember);
        }

        return "redirect:/items";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {

        //세션을 삭제한다.
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        return "redirect:/items";
    }

}
