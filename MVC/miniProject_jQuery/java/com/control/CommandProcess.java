package com.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response);
	// 다형성 활용: servlet - CommandProcess에 접근
	// 자식: 추상메서드 requestPro를 반드시 구현
}
