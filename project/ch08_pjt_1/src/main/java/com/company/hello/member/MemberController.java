package com.company.hello.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@RequestMapping("/signUp")
	public String signUp() {
		return "sign_up";	
	}
	
	@RequestMapping("/signIn")
	public String signIn() {
		return "sign_in";
	}
	
	
	@RequestMapping("/signUpConfirm")
	public String signUpConfirm(MemberVo memberVo) {
		System.out.println("[MemberController] signUpConfirm()");
		
		System.out.println("m_id: " +memberVo.getM_id());
		System.out.println("m_id: " +memberVo.getM_pw());
		System.out.println("m_id: " +memberVo.getM_mail());
		System.out.println("m_id: " +memberVo.getM_phone());
		
		memberService.signUpConfirm(memberVo);
		
		return "sign_up_ok";
	}
	
	@RequestMapping("/signInConfirm")
	public String signInConfirm(MemberVo memberVo) {
		System.out.println("[MemberController] signUpConfirm()");
		
		MemberVo signInedMember = memberService.signInConfirm(memberVo);
		
		if(signInedMember != null) 
			return "sign_in_ok";   //로그인 성공
		else				
			return "sign_in_ng";   //로그인 실패
	}
	
}
