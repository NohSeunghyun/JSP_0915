package svc;

import static db.Jdbcutil.*;

import java.sql.Connection;

import dao.BoardDAO;

public class BoardDeleteProService {
	
	public boolean isArticleWriter(int board_num, String pass) throws Exception {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		//전송된 비번을 사용해서 글 수정 요청한 사용자가 글을 작성한 사용자인지 판단
		boolean isArticleWriter = boardDAO.isArticleBoardWriter(board_num, pass);
		
		close(con);
		
		return isArticleWriter;
	}
	
	public boolean removeArticle(int board_num) throws Exception {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int deleteCount = boardDAO.deleteArticle(board_num);
		boolean isRemoveSuccess = false;
		if(deleteCount > 0) {
			commit(con);
			isRemoveSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isRemoveSuccess;
	}
	
	
}
