package com.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bean.SumDTO;

@Controller
public class SumController {

	// @RequestMapping(value="/input.do", method=RequestMethod.GET)
	@GetMapping(value="/input.do")
	public String input() {
		return "sum/input";
		// servlet-context.xml이 추가된 최종 결과: /WEB-INF/sum/input.jsp
		// 문자열 return 시, 파일명으로 인식됨
	}

/*
	// @RequestMapping(value="/result.do", method=RequestMethod.POST)
	@PostMapping(value="/result.do")
	public String result() {
		return "sum/result";
		// servlet-context.xml이 추가된 최종 결과: /WEB-INF/sum/input.jsp
	}
*/
	

/*
	@PostMapping(value="/result.do")
	public ModelAndView result(@RequestParam int calcX, @RequestParam int calcY) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("calcX", calcX);
		mav.addObject("calcY", calcY);
		mav.addObject("sum", calcX + calcY);
		
		mav.setViewName("sum/result");
		
		return mav;
	}

}

*/		

	
/*	
	ModelAndView: addObject
	
	@PostMapping(value="/result.do")
	public ModelAndView result(@RequestParam(required = false, defaultValue="0") String calcX,
							   @RequestParam(required = false, defaultValue="0") String calcY) {
	// NumberFormatException 방지를 위해 String 처리
	// method에서 입력된 값을 직접 받음

	HTTP 상태 400 – 잘못된 요청
	사용자 callback method에서 데이터가 안들어옴
	▶ 유효성 검사를 통해서 데이터 미 입력을 방지
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("calcX", calcX);
		mav.addObject("calcY", calcY);
		mav.addObject("sum", Integer.parseInt(calcX) + Integer.parseInt(calcY));
		
		mav.setViewName("sum/result");
		
		return mav;
	}
*/

/*	
	ModelMap: put

	@PostMapping(value="/result.do")
	public String result(@RequestParam Map<String, String> map, ModelMap modelMap) {
	// 데이터를 한개씩 받을 때, @RequestParam
	// 데이터를 여러개 받을 때, ModelMap
		modelMap.put("calcX", map.get("calcX"));
		modelMap.put("calcY", map.get("calcY"));
		// map.get("calcX"): "calcX" = input.jsp name value와 동일한 변수명으로 선언
		
		return "sum/result";
	}
*/
	
	// Map: addAttribute

	@PostMapping(value="/result.do")
	public String result(@ModelAttribute SumDTO sumDTO, Model model) {
	// 데이터를 한개씩 받을 때, @RequestParam
	// 데이터를 여러개 받을 때, ModelMap
		model.addAttribute("calcX", sumDTO.getCalcX());
		model.addAttribute("calcY", sumDTO.getCalcY());		
		return "sum/result";
	}

	
	
}
