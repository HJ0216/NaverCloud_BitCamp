package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bean.SungJukDTO;


@Controller
@RequestMapping(value="sungJuk") // 중복 사용에 따른 간편화
public class SungJukController {
	@RequestMapping(value="input.do", method=RequestMethod.GET)
	// @RequestMapping(value="/sungJuk/input.do", method=RequestMethod.GET)
	// @RequestMapping(value="/input.do", method=RequestMethod.GET)
	// Ambiguous mapping found 발생
	// ▶ namespaces를 추가하여 오류 해결
	public String input() {
		return "sungJuk/input";
	}

	@RequestMapping(value="result.do", method=RequestMethod.POST)
	// @RequestMapping(value="/sungJuk/result.do", method=RequestMethod.POST)
	public String result(@ModelAttribute SungJukDTO sungJukDTO, Model model) {
		int tot = sungJukDTO.getKor()
				+ sungJukDTO.getEng()
				+ sungJukDTO.getMath();
		double avg = tot/3.0;
		
		sungJukDTO.setTot(tot);
		sungJukDTO.setAvg(avg);

		model.addAttribute("sungJukDTO", sungJukDTO);
		// sungJukDTO 이름으로 result.jsp로 전달
/*	
		model.addAttribute("name", sungJukDTO.getName());
		model.addAttribute("kor", sungJukDTO.getKor());		
		model.addAttribute("eng", sungJukDTO.getEng());		
		model.addAttribute("math", sungJukDTO.getMath());
		model.addAttribute("tot", tot);
		model.addAttribute("avg", avg);
*/
		return "sungJuk/result";
	}

}
