package com.office.library.admin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/member")
public class AdminMemberCotroller {
	
	@Autowired
	AdminMemberService adminMemberService;
	
	@RequestMapping(value="/createAccountForm", method=RequestMethod.GET)
	public String createAccountForm() {
		System.out.println("[AdminMemberController] createAccoutForm()");
		String nextPage = "admin/member/create_account_form";
		
		return nextPage;
	}
	
	@RequestMapping(value="/createAccountConfirm", method = RequestMethod.POST)
	public String createAccountConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberController] createAccoutConfirm()");
		
		String nextPage = "admin.member/create_account_ok";
		
		int result = adminMemberService.createAccountConfirm(adminMemberVo);
		
		if(result <= 0)
			nextPage = "admin/member/create_account_ng";
		
		return nextPage;
		
		return null;
	}

}
