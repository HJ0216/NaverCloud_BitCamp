package member.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class LoginService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		// 1. Data
		String id  = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		// 2. DB: MemberDAO가 수행(+myBatis-config.xml)
		MemberDAO memberDAO = MemberDAO.getInstance();
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("pwd", pwd);
		MemberDTO memberDTO = memberDAO.memberLogin(map);
		
		// 3. Response
		if(memberDTO==null) {
			return "/member/loginFail.jsp";
		} else {
			// 내장객체 session이 없으므로 생성
			HttpSession session = request.getSession();
			session.setAttribute("memName", memberDTO.getName());
			session.setAttribute("memId", id);
			session.setAttribute("memPwd", pwd);
			session.setAttribute("memEmail", memberDTO.getEmail1()+"@"+memberDTO.getEmail2());
			
			return "/member/loginOkay.jsp";
		}
	}

}
