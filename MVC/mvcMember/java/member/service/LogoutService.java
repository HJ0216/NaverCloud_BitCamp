package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

public class LogoutService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(); // session 객체 생성
		session.removeAttribute("memName");
		session.removeAttribute("memId");
		session.removeAttribute("memPwd");
		session.removeAttribute("memEmail");
		
		// or session.invalidate(); 가능
		
		return "/member/logout.jsp";
	}

}
