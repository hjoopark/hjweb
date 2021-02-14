package com.demoweb.service;

import com.demoweb.common.Util;
import com.demoweb.dao.AccountDao;
import com.demoweb.vo.Member;

public class AccountService {
	
	public void registerMember(Member member) {
		
		// 비밀번호 암호화
		String passwd = member.getPasswd();
		passwd = Util.getHashedString(passwd, "SHA-256");
		member.setPasswd(passwd);
		
		AccountDao accountDao = new AccountDao();
		accountDao.insertMember(member);
		
	}

	public Member findMemberByIdAndPasswd(Member member) {
		
		String passwd = member.getPasswd();
		passwd = Util.getHashedString(passwd, "SHA-256");
		member.setPasswd(passwd);
		
		AccountDao accountDao = new AccountDao();
		Member member2 = accountDao.selecMemberByIdAndPasswd(member);
		
		return member2;
	}

}
