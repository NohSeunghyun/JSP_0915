/*
 * '글 등록 요청'을 처리하는 비지니스 로직을 구현 Service클래스
 */

package svc;

import vo.Boardbean;
import static db.Jdbcutil.*;

import java.sql.Connection;

import dao.BoardDAO;

//import db.Jdbcutil;

public class BoardWriteProService {
	//기본생성자
	
	 public boolean registArticle(Boardbean article) throws Exception {	
		 boolean isWriteSucess = false;//글 등록 성공 여부(기본값은 실패)
		 Connection con = null;
		 try {
			//Connection pool에서 DB연결
			 //Jdbcutil.getConnection();//클래스명.static메서드 호출
			 con = getConnection();
			 
			 //BoardDAO:싱글톤 패턴-단 1개의 객체만 생성하여 외부클래스에서 공유하여 사용하도록
			 BoardDAO boardDAO = BoardDAO.getInstance();
			 
			 //BoardDAO객채에서 DB작업을 할 때 사용하도록 매개값으로 설정함
			 boardDAO.setConnection(con);
			 
			 //DB의 board테이블에 '사용자가 입력한 값들(Boardbean객체)'로 추가 -> 성공하면 1리턴받음
			 int insertCount = boardDAO.insertArticle(article);
			 
			 if(insertCount > 0) {//글 등록 성공
				 commit(con);//트랜잭션 완료
				 isWriteSucess= true;
			 } else {
				 rollback(con);//트랜잭션 취소
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		 close(con);
		}
		 return isWriteSucess;
	 }
}
