//'답변글 등록 요청' 처리하는 Action
package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardReplyProService;
import vo.ActionForward;
import vo.Boardbean;

public class BoardReplayFormAction implements Action {

	@Override
	public ActionForward exeute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String nowPage = request.getParameter("page");//원래 있던 페이지로 돌아가기 위해
		
		//BoardBean객체생성하여(기본값으로 채워진)
		Boardbean article = new Boardbean();
		
		//답변글 작성 품에서 작성한 파라미터 값들을 전송받아 BoardBean객체의 속성값으로 설정
		article.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
		
		/*사용자가 실제 답변폼에서 입력한 값들(이름,비번,제목,내용)*/
		article.setBoard_name(request.getParameter("board_name"));
		article.setBoard_pass(request.getParameter("board_pass"));
		article.setBoard_subject(request.getParameter("board_subject"));
		article.setBoard_content(request.getParameter("board_content"));
		
		/*나머지 4개는 원글의 정보를 그대로 얻어옴*/
		article.setBoard_re_ref(Integer.parseInt(request.getParameter("board_re_ref")));
		article.setBoard_re_lev(Integer.parseInt(request.getParameter("board_re_lev")));
		article.setBoard_re_seq(Integer.parseInt(request.getParameter("board_re_seq")));
		
		BoardReplyProService boardReplyProService = new BoardReplyProService();
		boolean isReplySuccess = boardReplyProService.replyArticle(article);
		ActionForward forward = null;
		if(isReplySuccess) {//답변글 등록이 성공했으면
			forward = new ActionForward();
			//원래있던 페이지로 되돌아가야 함
			forward.setPath("boardList.bo?page=" + nowPage);
			forward.setRedirect(true);//새요청을 하는 리다이렉트 방식으로 포워딩
		}else {//답변글 등록 실패하면 경고창
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('답변글 등록 실패');");
			out.println("history.back();");
			out.println("</script>");
		}
		return forward;
	}

}
