package sungJuk;

public class SungJukMain {

	public static void main(String[] args) {
		SungJukService sungJukService = new SungJukService();
		sungJukService.menu();
		
	}
}

/*
SungJukMain
-> SungJukService
-> Parent: (Interface SungJuK)
    Child: 	SungJukInsert, List, Update, Delete, Sort
*/


/*
Package   : sungJuk

Interface : SungJuk.java
추상메소드: public void execute();

Class     : SungJukMain.java
            SungJukService.java - menu() 작성

			SungJukDTO.java
            SungJukInsert.java
            SungJukList.java
            SungJukUpdate.java
            SungJukDelete.java            
            SungJukSort.java

[조건]
1. SungJukDTO.java
필드 : 번호(no, 중복X), 이름, 국어, 영어, 수학, 총점, 평균
메소드 : 생성자를 통해서 이용하여 data 얻기
         setter / getter
         calc - 총점, 평균하는 계산

2. SungJukService.java
- menu() 작성

3. SungJukInsert.java
- 번호, 이름, 국어, 영어, 수학 입력 -> 총점 및 평균 계산
- 번호를 중복해서 입력하지 않는다.

번호 입력 : 
이름 입력 : 
국어 입력 : 
영어 입력 : 
수학 입력 : 

입력되었습니다

4. SungJukList.java
- ArrayList에 저장된 모든 데이터를 출력
- avg 소수이하 2째자리까지 출력
 
번호   이름   국어   영어   수학    총점   평균

5. SungJukUpdate.java
- 없는 번호가 입력되면 "잘못된 번호 입니다." 라고 출력한다.
- 있는 번호가 입력되면 번호에 해당하는 데이터 출력 후 수정한다.

번호 입력 : 
잘못된 번호 입니다.

또는 

번호   이름   국어   영어   수학    총점   평균

수정 할 이름 입력 : 
수정 할 국어 입력 : 
수정 할 영어 입력 : 
수정 할 수학 입력 : 

수정하였습니다.

6. SungJukDelete.java
- 이름을 입력하여 회원정보가 조회되지 않으면, "회원 정보가 없습니다."를 출력
- 똑같은 이름이 있으면 모두 삭제

삭제할 이름 입력 : 천사
조회되지 않는 이름입니다.

또는 

n건을 삭제하였습니다.


7. SungJukSort.java
********************
1. 총점으로 내림차순
2. 이름으로 오름차순
3. 이전 메뉴
*******************
번호: 
 */
