package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import member.dao.MemberDAO;

public class CheckIdService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		// 1. Data
		String id = request.getParameter("id");

		// 2. DB: MemberDAO가 수행(+myBatis-config.xml)
		boolean existId = false;
		MemberDAO memberDAO = MemberDAO.getInstance();
		existId = memberDAO.isExistId(id);
		
		// 3. Response
		if(existId) { // id가 있을 경우, true: 중복으로 인한 id 사용 불가
			return "/member/checkIdFail.jsp";
		} else {
			return "/member/checkIdOkay.jsp";
		}
		
		/*
		request.setAttribute("변수", 값); ${requestScope.id } > jsp는 servlet과 forward되었으므로 request, response 공유 가능
		request.setAttribute("id", id);
		if(existId) { // id가 있을 경우, true: 중복으로 인한 id 사용 불가
			return "/member/checkIdFail.jsp?id="+id;			
		} else {
			return "/member/checkIdOkay.jsp?id="+id;
		}
		*/
	}

}
