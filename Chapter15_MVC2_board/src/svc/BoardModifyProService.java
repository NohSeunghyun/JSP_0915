/*
 * 글 수정 처리요청을 구현한 Service 클래스
 */
package svc;

import static db.Jdbcutil.*;
import java.sql.Connection;

import dao.BoardDAO;
import vo.Boardbean;

public class BoardModifyProService {
	
	public boolean isArticleWriter(int board_num, String pass) throws Exception {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		//전송된 비번을 사용해서 글 수정 요청한 사용자가 글을 작성한 사용자인지 판단
		boolean isArticleWriter = boardDAO.isArticleBoardWriter(board_num, pass);
		
		close(con);
		
		return isArticleWriter;
	}

	public boolean modifyArticle(Boardbean article) throws Exception {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int updateCount = boardDAO.updateArticle(article);
		boolean isModifySuccess = false;
		
		if(updateCount > 0) {
			commit(con);
			isModifySuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isModifySuccess;
	}
	
}
