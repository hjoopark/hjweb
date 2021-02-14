package com.hjweb.service;


import com.hjweb.common.Util;
import com.hjweb.dao.AccountDao;
import com.hjweb.vo.Member;

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
		Member member2 = accountDao.selectMemberByIdAndPasswd(member);
		
		return member2;
	}

}
