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
		// From loginForm.jsp
		// $.ajax({data: id, pwd})
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
			// service.java > return .jsp는 상대번지로 작성해야함
			// controlServlet.java에서 RequestDispatcher에 return 값을 입력될 때 반드시 상대번지로 작성되어야 함
			// /member/loginOkay.jsp: .이 빠진 상대번지

			// session 값을 활용한 index내 login 성공 화면 nav에 띄우기
			// request.setAttribute("display2", "/member/loginOkay.jsp");
			// return "/index.jsp";

		}
	}

}
