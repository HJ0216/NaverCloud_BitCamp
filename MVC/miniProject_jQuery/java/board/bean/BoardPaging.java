package board.bean;

import lombok.Data;

@Data
// 1. Setter, Getter
// 2. Default Constructor
// 3. toString() / equals() / hashCode()
public class BoardPaging {

		private int currentPage; // 현재 페이지
		private int pageBlock; // [이전][1][2][3][다음]
		//		startP endP
		// 		 [1][2][3][다음]
		// [이전][4][5][6][다음]
		// [이전][7][8]
		private int pageSize; // 1페이지당 5개씩
		private int totalA; // 총 글 수
		private StringBuffer pagingHTML;
		// String: 수정 불가
		// StringBuffer or StringBuilder: 수정 가능
		
		public void makePagingHTML() {
			pagingHTML = new StringBuffer(); // StringBuffer 생성
			
			// Calculate Total Page number
			int totalP = (totalA+(pageSize-1)) / pageSize;
			
			int startP = (currentPage-1) / pageBlock * pageBlock + 1;
			// 계산 순서
			// 1. (currentPage-1)
			// 2. (currentPage-1) / pageBlock -> int casting 
			// 3. (casting된 (currentPage-1)) / pageBlock * pageBlcok + 1
			int endP = startP + pageBlock - 1;
			
			if(endP > totalP) {endP = totalP;}
			// endP만큼 totalP가 없는 경우
			
			if(startP!=1) { // [이전] 블럭을 출력하는 경우
				pagingHTML.append("<span id='paging' onclick='boardPaging(" + (startP-1) + ")'>이전</span>");
				// HTML tag return, 이전 클릭 시 js()를 활용하여 boardPaging("3")으로 이동
				// ""로 감싸는 이유: query param = String type
			}
			
			for(int i=startP; i<=endP; i++) {
				if(i==currentPage) {pagingHTML.append("<span id='currentPaging' onclick='boardPaging(" + i + ")'>" + i + "</span>");}
				else {pagingHTML.append("<span id='paging' onclick='boardPaging(" + i + ")'>" + i + "</span>");}
			}
			
			if(endP < totalP) { // [다음] 블럭을 출력하는 경우
				pagingHTML.append("<span id='paging' onclick='boardPaging(" + (endP+1) + ")'>다음</span>");
			}
		}
	
}
