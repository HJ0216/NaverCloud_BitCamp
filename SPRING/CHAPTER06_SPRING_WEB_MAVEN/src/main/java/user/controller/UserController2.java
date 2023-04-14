package user.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import user.bean.UserImgDTO;
import user.service.UserService;

@Controller
@RequestMapping(value="user")
public class UserController2 {
	@Autowired
	private UserService userService;
	
	
	// upload files
	@GetMapping(value="uploadForm")
	public String uploadForm(){
		return "user/uploadForm";		
	}
	
	// upload files
	@GetMapping(value="uploadFormAjax")
	public String uploadFormAjax(){
		return "user/uploadFormAjax";		
	}

	
/*
	// 업로드하고자하는 img가 1개일 경우,
	@PostMapping(value="upload", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String upload(@RequestParam MultipartFile img,
						@ModelAttribute UserImgDTO userImgDTO, // userDTO 데이터 읽어오기
						HttpSession session) {
	// 업로드 시, 반드시 MultupartFile class 사용
		// 가상폴더
		String filePathVertual = "D:\\Spring\\workspace\\chapter06_SpringWebMaven\\src\\main\\webapp\\WEB-INF\\storage";
		// String filePathVertual = "D:/Spring/workspace/chapter06_SpringWebMaven/src/main/webapp/WEB-INF/storage";

		
		String filePath = session.getServletContext().getRealPath("/WEB-INF/storage");
		// getRealPath("/WEB-INF/storage") "" 안의 가상 폴더의 실제 주소를 출력
		System.out.println("실제 주소: " + filePath);
		// /WEB-INF/storage의 실제 주소(서버 주소) 출력
		// D:\Spring\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps
		// \chapter06_SpringWebMaven\WEB-INF\storage
		
		String fileName = img.getOriginalFilename();
		// 이미지 파일이 업로드될 때 2비트로 변환되어 들어오므로 다시 실제 이름으로 변환
		
		File file = new File(filePath, fileName);
		// File fileVertual = new File(filePathVertual, fileName);
		
		try { // 이미지 폴더를 파일 위치에 복사
			// FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(fileVertual));
			// input된 img(파일 업로드 시 실제 서버-org.eclipse.wst.server.core에만 저장)를 가상 폴더에 복사
			
			img.transferTo(file);
			// 이미지 이동: img > file
			// * transferTo 이후 copy할 경우, 파일이 없어서 발생하는 IOException 발생
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		userImgDTO.setImage(fileName);
		// 이름이 달라 image변수에는 img가 설정되어있지 않으므로 setter 활용
		
		// UserService > UserDAO > userMapper.xml
		

		
		return "<img src='/chapter06_SpringWebMaven/storage/" + fileName + "'width='300' height='300' />";
		// 만일, 자신의 local인 img 폴더를 파일 업로드할 경우, 서버 저장 및 copy 메서드를 통해 storage에도 저장됨
		
	}

*/


	
	
/*	
	// 업로드하고자하는 img가 2개이상일 경우,
	@PostMapping(value="upload", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String upload(@RequestParam MultipartFile[] img,
						@ModelAttribute UserImgDTO userImgDTO, // userDTO 데이터 읽어오기
						HttpSession session) {
	// 업로드 시, 반드시 MultupartFile class 사용
		// 가상폴더
		// String filePathVertual = "D:\\Spring\\workspace\\chapter06_SpringWebMaven\\src\\main\\webapp\\WEB-INF\\storage";
		// String filePathVertual = "D:/Spring/workspace/chapter06_SpringWebMaven/src/main/webapp/WEB-INF/storage";

		
		String filePath = session.getServletContext().getRealPath("/WEB-INF/storage");
		// getRealPath("/WEB-INF/storage") "" 안의 가상 폴더의 실제 주소를 출력
		System.out.println("실제 주소: " + filePath);
		// /WEB-INF/storage의 실제 주소(서버 주소) 출력
		// D:\Spring\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps
		// \chapter06_SpringWebMaven\WEB-INF\storage
		
		// String fileName = img.getOriginalFilename();
		// 이미지 파일이 업로드될 때 2비트로 변환되어 들어오므로 다시 실제 이름으로 변환
		
		String fileName;
		File file;
		// File file = new File(filePath, fileName);
		// File fileVertual = new File(filePathVertual, fileName);
		
		if(img[0]!=null) { // null: 파일 이미지를 선택해서 올리지 않은 상황
			fileName = img[0].getOriginalFilename();
			file = new File(filePath, fileName);
			try { // 이미지 폴더를 파일 위치에 복사
				// FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(fileVertual));
				// input된 img(파일 업로드 시 실제 서버-org.eclipse.wst.server.core에만 저장)를 가상 폴더에 복사
				img[0].transferTo(file);
				// 이미지 이동: img > file
				// * transferTo 이후 copy할 경우, 파일이 없어서 발생하는 IOException 발생			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if(img[1]!=null) { // null: 파일 이미지를 선택해서 올리지 않은 상황
			fileName = img[1].getOriginalFilename();
			file = new File(filePath, fileName);
			try { // 이미지 폴더를 파일 위치에 복사
				// FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(fileVertual));
				// input된 img(파일 업로드 시 실제 서버-org.eclipse.wst.server.core에만 저장)를 가상 폴더에 복사
				img[1].transferTo(file);
				// 이미지 이동: img > file
				// * transferTo 이후 copy할 경우, 파일이 없어서 발생하는 IOException 발생			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return "이미지 등록 완료";
		// 만일, 자신의 local인 img 폴더를 파일 업로드할 경우, 서버 저장 및 copy 메서드를 통해 storage에도 저장됨
		
	}
*/
	

	
	// 다중 업로드를 하는 경우,
	@PostMapping(value="upload", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String upload(@RequestParam("img[]") List<MultipartFile> list,
						// MultipartFile이 배열X,
						// MultipartFile이 3개 선택할 경우 3개가 담겨오게되는 구조
						@ModelAttribute UserImgDTO userImgDTO,
						HttpSession session) {
		
		String filePath = session.getServletContext().getRealPath("/WEB-INF/storage");
		System.out.println("실제 주소: " + filePath);
		
		String fileName;
		File file;

		List<String> fileNameList = new ArrayList<>();
		
		for(MultipartFile img : list) {
			fileName = img.getOriginalFilename();
			try {
				fileName = new String(fileName.getBytes("8859_1"),"utf-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			file = new File(filePath, fileName);			

			try {
				img.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			fileNameList.add(fileName);

		} // for
		
		try {
			String imageName = new String(userImgDTO.getImageName().getBytes("8859_1"),"utf-8");
			userImgDTO.setImageName(imageName);
			String imageContent = new String(userImgDTO.getImageContent().getBytes("8859_1"),"utf-8");
			userImgDTO.setImageContent(imageContent);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		userService.upload(userImgDTO, fileNameList);
		
		return "이미지 등록 완료";
		
	}	
	
	
	
	
	// 이미지 목록
	
	@GetMapping(value="uploadFormAjaxList")
	public String uploadFormAjaxList() {
		// 데이터를 동적으로 데려올 경우, 화면에 틀만 띄움
		return "user/uploadFormAjaxList";
	}
	
	@PostMapping(value="getUploadFormAjaxList")
	@ResponseBody // 페이지 이동이 아닐 경우,
	public List<UserImgDTO> getUploadFormAjaxList() {
		return userService.getUploadFormAjaxList();
	}
	
	
}
