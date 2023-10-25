package com.company.hello.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	MemberDao memberDao;
	
	public int signUpConfirm(MemberVo memberVo) {
		System.out.println("[MemberService] signUpConfirm()");
		
		memberDao.insertMember(memberVo);
		
		return 0;
	}
	
	public MemberVo signInConfirm(MemberVo memberVo) {
		System.out.println("[MemberService] signInConfirm()");
		
		MemberVo signInedMember = memberDao.selectMember(memberVo);
		
		return signInedMember;
	}
	
}
