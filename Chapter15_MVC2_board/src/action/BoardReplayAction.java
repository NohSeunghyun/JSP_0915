//'답변쓰기 폼 출력' 요청을 처리하는 Action 클래스
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardDetailservice;
import vo.ActionForward;
import vo.Boardbean;

public class BoardReplayAction implements Action {

	@Override
	public ActionForward exeute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//답변글 등록 처리를 한 후 '원래 있던 페이지로 되돌아가야 하기 떄문에' 파라미터로 전송된 '페이지 번호'를 얻어옴
		String nowPage = request.getParameter("page");
		//답변을 달기 위한 원글의 글번호를 얻어와
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		BoardDetailservice boardDetailservice =new BoardDetailservice();
		Boardbean article = boardDetailservice.getArticle(board_num);
		/* ★원들의 정보를 조회하는 이유?
		 * 원글의 그룹(BOARD_RE_REF)이나 들여쓰기(BOARD_RE_LEV)와 위치(BOARD_RE_SEQ)를 알아
		 * 
		 * 뒤에서 BoardDAO 클래스의 insertReplyArticle()메서드를 호출하여
		 * (p624-아래그림)'글쓴이,비밀번호,제목,내용'만 답변글에 관한 정보이고
		 * '나머지 값들'은 원글(=article)의 내용 그대로를 사용하거나 수정하여 board테이블에 추가할 것임
		 */
		
		ActionForward forward = new ActionForward();
		request.setAttribute("page",nowPage);
		request.setAttribute("article",article);//원글 정보
		
		forward.setPath("/board/qna_board_reply.jsp");
		return forward;//isRedirect = false이므로 디스패치 방식
	}

}
