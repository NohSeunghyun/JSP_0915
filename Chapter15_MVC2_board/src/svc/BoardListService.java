
package svc;

import static db.Jdbcutil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.Boardbean;

public class BoardListService {
	
	/*게시판의 총(전체) 글의 개수를 반환하는 메서드 ex) select 10개*/
	public int getListCount() throws Exception {
		 //Connection pool에서 DB연결
		 //Jdbcutil.getConnection();//클래스명.static메서드 호출
		 Connection con = getConnection();
		 
		 //BoardDAO:싱글톤 패턴-단 1개의 객체만 생성하여 외부클래스에서 공유하여 사용하도록
		 BoardDAO boardDAO = BoardDAO.getInstance();
		 
		 //BoardDAO객채에서 DB작업을 할 때 사용하도록 매개값으로 설정함
		 boardDAO.setConnection(con);
		 
		 //DB의 board테이블에 '사용자가 입력한 값들(Boardbean객체)'로 추가 -> 성공하면 1리턴받음
		 int listCount = boardDAO.selectListCount();
		 
		 close(con);
		 
		 return listCount;
	}
	
	//페이지 번호와 한 페이지당 출력될 글의 개수10을 전송받아 '지정한 페이지에 출력될 글 목록을 ArrayList<BoardBean>객체로 반환
	public ArrayList<Boardbean> getArticleList(int page,int limit) throws Exception {
		 //Connection pool에서 DB연결
		 //Jdbcutil.getConnection();//클래스명.static메서드 호출
		 Connection con = getConnection();
		 
		 //BoardDAO:싱글톤 패턴-단 1개의 객체만 생성하여 외부클래스에서 공유하여 사용하도록
		 BoardDAO boardDAO = BoardDAO.getInstance();
		 
		 //BoardDAO객채에서 DB작업을 할 때 사용하도록 매개값으로 설정함
		 boardDAO.setConnection(con);
		 
		 ArrayList<Boardbean> articleList = boardDAO.selectArticleList(page,limit);
		 
		 close(con);
		 
		 return articleList;
	}
}
