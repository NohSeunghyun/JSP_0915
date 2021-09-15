/*
 * 1.controller
 */

package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.BoardDeleteProAction;
import action.BoardDetailAction;
import action.BoardListAction;
import action.BoardModifyFormAction;
import action.BoardModifyProAction;
import action.BoardReplayAction;
import action.BoardReplayFormAction;
import action.BoardWriteProAction;
import vo.ActionForward;

/**
 * Servlet implementation class BoardController
 */
//확장자가 bo이면 무조건 BoardFrontController로 이동하여 doProcess()메서드 실행함
@WebServlet("*.bo")//마지막 url이 *.bo로 끝나는 요청을 맵핑
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	//이 서블릿으로 들어오는 post나 get방식의 모든 요청은 doProcess()를 호출하여 처리
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//요청객체로부터 '프로젝트명+파일경로'까지 가져옴 예)/project/boardWriteFrom.bo
		String requestURI = request.getRequestURI();
		//요청 URL : http://localhost:8090/project/boardWriteFrom.bo
		//요청 URI : 도메인 네임 +/project/boardWriteFrom.bo
		
		//요청객체로부터 '프로젝트 path'만 가져옴(예)/project
		String contextPath =  request.getContextPath();
		
		/* URI에서 contextPath길이만큼 잘라낸 나머지 문자열
		 * /project/boardWriteFrom.bo - /project = /boardWriteForm.bo
		 */
		
		String command = requestURI.substring(contextPath.length());//index 8~끝까지 부분문자열 반환
		
		/* 요청이 파악되면 해당 요청을 처리하는 각 Action클래스를 사용해서 요청 처리
		 * 각 요청에 해당하는 Action클래스 객체들을 다형성으로 이용해서 동일한 타입으로 참조하기 위해
		 * 'Action 인터페이스' 타입의 변수 선언(교재 p574)
		 */
		
		ActionForward forward = null;
		Action action = null;
		
		/* 글쓰기 페이지를 열어주는 요청인 경우는 특별한 비지니스 로직을 실행할 필요없이
		 * 글쓰기 할 수 있는 뷰페이로 포워딩하면 됨
		 */
		
		/*글 작성하는 페이지 */
		if(command.equals("/boardWriteForm.bo")) {//'사용자가 글 등록하는 폼화면' 요청
			forward = new ActionForward();
			forward.setPath("/board/qna_board_write.jsp");//디스패치 방식(isRedirect변수 값이 false)
		} else if (command.equals("/boardWritePro.bo")) {//사용자가 입력한 자료들을 DB에 추가하는 요청이면
			action = new BoardWriteProAction();
			try {
				forward = action.exeute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardList.bo")) {//'테이블 목록 보여주기' 요청이면
			action = new BoardListAction();
			try {
				forward = action.exeute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardDetail.bo")) {//'하나의 글 내용 뵤여주기' 요청
			action = new BoardDetailAction();
			try {
				forward = action.exeute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		} else if (command.equals("/boardReplayForm.bo")) {//'답변쓰기 폼 보여주는' 요청
			action = new BoardReplayAction();
			try {
				forward = action.exeute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardReplayPro.bo")) {//'답변정보 등록하기 위한 처리' 요청
			action = new BoardReplayFormAction();
			try {
				forward = action.exeute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardModifyForm.bo")) {//'글 수정 폼 화면 보기' 요청
			action = new BoardModifyFormAction();
			try {
				forward = action.exeute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardModifyPro.bo")) {//'글 수정 처리' 요청
			action = new BoardModifyProAction();
			try {
				forward = action.exeute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*
		 * JSP책 647p - 두번째 그림 참조
		 * 게시판 삭제를 위해 비밀번호를 입력하는 페이지로 이동하는데
		 * 이때, 해당 페이지 번호와 글번호를 요청 영역에 속성값으로 공유한 후
		 * 바로 디스패치 방식으로 뷰 페이지로 이동
		 */
		else if (command.equals("/boardDeleteForm.bo")) {//'글 삭제 폼 화면 보기' 요청
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			
			int board_num = Integer.parseInt(request.getParameter("board_num"));
			request.setAttribute("board_num", board_num);
			
			forward = new ActionForward();
			forward.setPath("/board/qna_board_delete.jsp");
		} else if (command.equals("/boardDeletePro.bo")) {//'글 삭제 처리' 요청
			action = new BoardDeleteProAction();
			try {
				forward = action.exeute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*
		 * 포워딩
		 */
		if(forward != null) {
			if(forward.isRedirect()) {//isRedirect=true : 주소변경(새요청), request
				response.sendRedirect(forward.getPath());//응답-리다이렉트 방식
			} else {//isRedirect=false : 디스패치 방식
//			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			//dispatcher.forward(request, response);//기존 요청, 기존 응답 그대로 보내므로 주소 그대로
			request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
	}

}
