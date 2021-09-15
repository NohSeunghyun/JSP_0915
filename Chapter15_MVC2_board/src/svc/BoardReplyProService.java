//'답변정보 등록 요청' 처리하는 Service클래스
package svc;
import static db.Jdbcutil.*;

import java.sql.Connection;

import dao.BoardDAO;
import vo.Boardbean;

public class BoardReplyProService {
	
	//답변글 정보 BoardBean객체
	public boolean replyArticle(Boardbean article) throws Exception {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int insertCount = boardDAO.insertReplyArticle(article);
		
		boolean isReplySuccess = false;
		
		if(insertCount > 0) {
			commit(con);
			isReplySuccess = true;
		} else rollback(con);
		
		return isReplySuccess;
	}
}
