//글 하나의 상세 내용보기 요청을 처리
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardDetailservice;
import svc.BoardListService;
import vo.ActionForward;
import vo.Boardbean;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward exeute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//파라미터로 전송된 '상세 내용을 볼 글 번호'를 얻어와
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		//페이지 번호를 받음(상세 내용보기 후 원래 페이지로 돌아가기 위해)
		String page = request.getParameter("page");
		
		BoardDetailservice boardDetailservice = new BoardDetailservice();
		Boardbean article = boardDetailservice.getArticle(board_num);
		
		ActionForward forward = new ActionForward();
		request.setAttribute("article", article);
		request.setAttribute("page", page);
				
		forward.setPath("/board/qna_board_view.jsp");		
		return forward;//isRedirect = false이므로 디스패치 방식
	}

}
