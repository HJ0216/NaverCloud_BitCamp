package user.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserUpdateService implements UserService {

	@Override
	public void execute() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("수정할 아이디 입력: ");
		try {
			String id = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
