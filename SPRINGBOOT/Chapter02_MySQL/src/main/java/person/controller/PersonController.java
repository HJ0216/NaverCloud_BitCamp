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
		// static/storage 폴더가 이미 프로젝트의 구조에 존재하고 있다면,
		//파일 업로드 처리를 위한 별도의 설정이나 코드가 없어도 이미지가 해당 폴더에 저장될 수 있음???????????
		
		// session.getServletContext(): 현재 웹 애플리케이션의 서블릿 컨텍스트 객체를 가져오는 코드
		// 서블릿 컨텍스트는 웹 애플리케이션의 실행 환경을 제공하는 객체로, 웹 애플리케이션의 전역 정보와 리소스에 접근할 수 있는 기능을 제공
		// 현재 웹 애플리케이션의 서블릿 컨텍스트를 통해 상대 경로 "/storage"에 해당하는 실제 파일 시스템의 경로를 가져오는 것을 의미
		String fileName = img.getOriginalFilename();
		
		System.out.println("실제 폴더: " + filePath);
		
		File file = new File(filePath, fileName);
		// file 객체는 실제 파일이 저장될 경로와 파일 이름을 가지게 됨
		
		try {
			img.transferTo(file);
			// img에 저장된 업로드된 파일을 file 객체가 나타내는 경로에 복사
			// fileName은 img의 getOriginalFilename
			// filePath는 storage 폴더의 realpath로 지정되어있으며, 그 자리에 img 파일이 복사됨
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