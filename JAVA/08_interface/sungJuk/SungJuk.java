package sungJuk;

import java.util.ArrayList;

public interface SungJuk {
	public void execute(ArrayList<SungJukDTO> arrayList);
	// SungJukDTO obj를 전체 class간 공유를 위해 parameter로 SungJukService에서 받음
}
