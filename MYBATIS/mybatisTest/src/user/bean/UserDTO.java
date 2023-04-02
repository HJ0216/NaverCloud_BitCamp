package user.bean;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO {
	private String name;
	@NonNull
	private String id;
	private String pwd;
	
	// Lombok annotation: annotation 사용 후, 생성되었는지 확인
	// @Setter: setData
	// @Getter: getData
	// @NoArgsConstructor: Default Constructor
	// @AllArgsConstructor: 모든 변수에 대한 Constructor
	// @RequiredArgsConstructor + @NonNull 지정: 필수 인자를 받는 생성자

}
