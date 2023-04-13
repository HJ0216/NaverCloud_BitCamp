package user.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="user")
public class UserController2 {
	// upload files
	@GetMapping(value="uploadForm")
	public String uploadForm(){
		return "user/uploadForm";
		
	}
	
	@PostMapping(value="upload")
	@ResponseBody
	public String upload(@RequestParam MultipartFile img, HttpSession session) {
	// 업로드 시, 반드시 MultupartFile class 사용
		// 가상폴더
		String filePathVertual = "D:\\Spring\\workspace\\chapter06_SpringWebMaven\\src\\main\\webapp\\WEB-INF\\storage";
		// String filePathVertual = "D:/Spring/workspace/chapter06_SpringWebMaven/src/main/webapp/WEB-INF/storage";

		
		String filePath = session.getServletContext().getRealPath("/WEB-INF/storage");
		System.out.println("실제 주소: " + filePath);
		// /WEB-INF/storage의 실제 주소(서버 주소) 출력
		// D:\Spring\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps
		// \chapter06_SpringWebMaven\WEB-INF\storage
		
		String fileName = img.getOriginalFilename();
		
		File file = new File(filePath, fileName);
		File fileVertual = new File(filePathVertual, fileName);
		
		try { // 이미지 폴더를 파일 위치에 복사
			FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(fileVertual));
			// input된 img(파일 업로드 시 실제 서버-org.eclipse.wst.server.core에만 저장)를 가상 폴더에 복사
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "<img src='../storage/" + fileName + "'width='300' height='300' />";
		// 만일, 자신의 local인 img 폴더를 파일 업로드할 경우, 서버 저장 및 copy 메서드를 통해 storage에도 저장됨
		
	}

}
