/*
 * DB 관련 공통 기능 클래스
 */
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Jdbcutil {//모든 메서드가 static : 객체 생성없이 바로 메모리에 올라감
	//커넥션 풀에서 Connection객체를 얻어와 반환
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			Context ititCtX = new InitialContext();//톰켓 자체의 Context객체 얻어와
			//Resource에 정의된 Context를 Object 타입으로 얻어와 Context타입으로 강제 형변환(=다운캐스팅)
			Context envCtx = (Context)ititCtX.lookup("java:comp/env");
			
			//이름으로 Connection Poll인 DateSource객체를 얻어와
			DataSource ds = (DataSource)envCtx.lookup("jdbc/MySQLDB");//이름으로 커넥션 풀인 DateSource
			
			//Connection Poll(DateSource)에서 Connection객체를 얻어와
			con = ds.getConnection();
			
			//★Connection객체의 트랜잭션을 자동거밋되지 않도록 하기 위해
			con.setAutoCommit(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	/*
	 * Connection객체를 닫아주는 메서드
	 */
	
	public static void close(Connection con) {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Statement객체를 닫아주는 메서드
	 */
	
	public static void close(Statement st) {
		try {
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * PreparedStatement객체를 닫아주는 메서드
	 */
	
	public static void close(PreparedStatement ps) {
		try {
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * ResultSet객체를 닫아주는 메서드
	 */
	
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*-------------------------------------*/
	
	/*
	 * 트랜젝션 중에 실행된 작업들을 '완료'시키는 메서드
	 */
	
	public static void commit(Connection con) {
		try {
			con.commit();
			System.out.println("commit success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 트랜젝션 중에 실행된 작업들을 '취소'시키는 메서드
	 */
	
	public static void rollback(Connection con) {
		try {
			con.rollback();
			System.out.println("rollback success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
