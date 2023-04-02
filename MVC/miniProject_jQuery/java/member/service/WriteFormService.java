package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

public class WriteFormService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("display", "/member/writeForm.jsp");
		// 회원가입 버튼 클릭 시, display를 키값으로 해서 value를 /member/writeForm.jsp 입력
		// 회원가입 버튼을 클릭할 경우, display에 value 값이 저장되어 빈 값이 아니게 됨
		// 이후 index에서 key값에 저장된 value jsp를 호출하여 include
		
		return "/index.jsp";
	}

}
