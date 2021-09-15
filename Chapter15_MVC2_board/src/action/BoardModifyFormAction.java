package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardDetailservice;
import vo.ActionForward;
import vo.Boardbean;

public class BoardModifyFormAction implements Action {

	@Override
	public ActionForward exeute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 수정대상이 되는 글번호 얻어와 정수로변환
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String page = request.getParameter("page");
		
		BoardDetailservice boardDetailService = new BoardDetailservice();
		Boardbean article = boardDetailService.getArticle(board_num);
		
		ActionForward forward = new ActionForward();
		request.setAttribute("article", article);
		request.setAttribute("page", page);
		
		forward.setPath("/board/qna_board_modify.jsp");
		
		return forward;
	}

}
