package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

public class LoginFormService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		return "/member/loginForm.jsp";
		// 1. *.do를 통한 client의 servlet 접근
		// 2. commandProperties를 통해서 .do에 따른 관련 java 파일을 servlet에 전달
		// 3. 해당 java파일로 이동 후, 자바 파일은 결과 화면인 jsp return
		// 4. servelt은 return받은 jsp를 화면에 출력
		// 5. jsp는 결과를 return하므로 url 출력 부분은 *.do 형식의 servlet이 기재되어야 함
		// 6. 이후 똑같이 command.properties를 참조하여 페이지 이동 및 동작 구현
	}

}
