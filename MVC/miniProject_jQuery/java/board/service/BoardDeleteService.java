package board.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

// BoardReplyService.java 참조
public class BoardDeleteService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		// 1. Data
		// From boardViewT.jsp + boardView.js
		String seq = request.getParameter("seq");
		

		// 2. DB
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.boardDelete(seq);

		
		// 3. Response
		return "/board/boardDelete.jsp";
	}

}

/*
 * boardView.js *



 * command.properties *
/board/boardDeleteForm.do=board.service.BoardDeleteFormService
/board/boardDelete.do=board.service.BoardDeleteService



 * BoardDeleteFormService.java *
// 1. Data
String seq = request.getParameter("seq");

// 3. Response
return "BoardDeleteForm.jsp";



 * BoardDeleteForm.jsp *
-> 삭제 시, 비밀번호 재확인하는 구간
CDM jQuery 삽입
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
	$(function(){
		$.ajax({
			type: 'post';
			url: '/miniProject_jQuery/board/boardDelete.do'
			data: 'seq=' + ${seq}
			success: function(){
				alert('글 삭제 완료');
				locatoin.href='/miniProject_jQuery/board/boardList.do?pg=1'
			},
			error: function(err){
				console.log(err);
			}
		});
	});
</script>



 * BoardDeleteService.java *
// 1. Data
String seq = request.getParameter("seq");

// 2. DB
BoardDAO boardDAO = BoardDAO.getInstance();
boardDAO.boardDelete(map);

// 3. Response
return "/board/boardDelete.jsp"



 * boardDelete.jsp *
빈 파일


 * BoardDAO.java - boardDelete() *
public void boardDelete(String seq) {
	SqlSession sqlSession = sqlSessionFactory.openSession();
	sqlSession.delete("boardSQL.boardDelete", Integer.parseInt(seq));
	sqlSession.commit();
	sqlSession.close();
}



 * boardMapper - boardDelete *
<delete id="boardDelete" parameterType="int>
	begin <!-- 시작 -->
	UPDATE BOARD SET REPLY=REPLY-1 WHERE SEQ=(SELECT PSEQ FROM BOARD WHERE SEQ=${seq});
	UPDATE BOARD SET SUBJECT=('[원글이 삭제된 답글] ' || SUBJECT) WHERE PSEQ=#{seq};
	DELETE FROM BOARD WHERE SEQ=#{seq};
	
	end; <!-- 끝 -->
</delete>


*/
