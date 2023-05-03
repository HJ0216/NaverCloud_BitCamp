package user.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserImgDTO {
	private int seq;
	private String imageName;
	private String imageContent;
	// DB COL_NAME과 동일하게 기재
	
	// DB는 이미지의 이름만 올려두고 하드디스크에 실제 이미지를 끌어다쓰는 구조로 사용
	// UserController에서 MultipartFile을 거쳐 이미지를 업로드
	// img는 서버로의 업로드가 목적이지 DTO에 저장시키는 게 목적이 아님
	// img의 최종 목적지는 UserController로 가야함

	private String image;
	
}
