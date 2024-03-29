package person.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import person.bean.PersonDTO;
import person.service.PersonService;

@Controller
public class PersonController {
	
	@Autowired // 생성된 bean 객체와 자동 맵핑
	// PersonServiceImpl 객체가 @Service로 객체가 생성되어있으므로 해당 객체와 mapping됨
	// PersonService personService = new PersonServiceImpl();
	PersonService personService;
	

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		return "index";
	}
	

	@GetMapping(value="/person/writeForm")
	public String writeForm() {
		return "/person/writeForm";
		// 기본 시작경로: WEB-INF (application.properties: JSP 참조)
		// 하위 폴더 내에 위치할 경우, 추가 경로 지정 필요
	}
	
	@PostMapping("/person/write")
	@ResponseBody
	public void write(@RequestParam MultipartFile img,
					  // img: <input type="file">에서 넘어오는 이미지 데이터
					  @ModelAttribute PersonDTO personDTO,
					  // ModelAttribute: 전달받은 데이터와 DTO간 같은 이름을 자동 Mapping
					  HttpSession session) {

		System.out.println(img.getOriginalFilename());
		System.out.println(personDTO.getName() + ", " + personDTO.getAge());
		
		// 실제 폴더
		String filePath = session.getServletContext().getRealPath("/storage");
		// session.getServletContext(): 현재 웹 애플리케이션의 서블릿 컨텍스트 객체를 가져오는 코드
		// 서블릿 컨텍스트는 웹 애플리케이션의 실행 환경을 제공하는 객체로, 웹 애플리케이션의 전역 정보와 리소스에 접근할 수 있는 기능을 제공
		// 현재 웹 애플리케이션의 서블릿 컨텍스트를 통해 상대 경로 "/storage"에 해당하는 실제 파일 시스템의 경로를 가져오는 것을 의미
		// getRealPath("/storage"): webapp 폴더 안에 storage 폴더가 있음을 의미

		String fileName = img.getOriginalFilename();
		
		System.out.println("실제 폴더: " + filePath);
		// D:\SpringBoot\workSpace\Chapter02_MySQL\src\main\webapp\storage
		
		File file = new File(filePath, fileName);
		// 실제 파일이 저장될 경로와 파일 이름을 결합한 File 객체를 생성, 이후 img.transferTo(file) 메소드를 통해 파일이 저장
		
		try {
			img.transferTo(file);
			// img에 저장된 업로드된 파일을 file 객체가 나타내는 경로에 복사하는 것
			// fileName 변수에는 img 객체에서 추출한 원본 파일 이름이 저장되어 있으며, 이 파일 이름으로 file 객체가 가리키는 경로에 파일이 저장
			/*
				img.transferTo(file); filePath를 어디로 정하는지에 따라 local or server에 이미지를 저장할 수 있음

				실제 경로에 저장
				String filePath = "/var/www/html/myapp/images"; // 경로를 서버의 실제 경로로 지정
				File file = new File(filePath, fileName);
				
				로컬에 저장
				String filePath = session.getServletContext().getRealPath("/storage"); // 경로를 로컬의 실제 경로로 지정
				File file = new File(filePath, fileName);

			 */


		} catch (IOException e) {
			e.printStackTrace();
		}
		
		personDTO.setPhoto(fileName);
		
		personService.write(personDTO);
		
	}
	
	@GetMapping("/person/list")
	public String list(String pg){
		return "/person/list";
	}
	
	
	@PostMapping("/person/getPersonList")
	@ResponseBody
	public List<PersonDTO> getPersonList(){
		
		List<PersonDTO> list = personService.getPersonList(); 
		
		System.out.println("List" + list);
		return list;
	}
	
	
}