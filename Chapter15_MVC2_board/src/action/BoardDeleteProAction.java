/*
 * 글삭제 처리하는 Action 클래스
 */
package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardDeleteProService;
import svc.BoardDetailservice;
import vo.ActionForward;
import vo.Boardbean;

public class BoardDeleteProAction implements Action {

	@Override
	public ActionForward exeute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String nowPage = request.getParameter("page");
		String pass = request.getParameter("board_pass");
		
		BoardDeleteProService boardDeleteProService= new BoardDeleteProService();
		boolean isRightUser = boardDeleteProService.isArticleWriter(board_num, pass);
		
		if(!isRightUser) {
			//글 삭제 요청한 사용자가 글을 작성한 사용자가 아니면 경고창
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");//자바스크립트로
			out.println("alert('삭제할 권한이 없습니다.');");//경고창띄우고
			out.println("history.back();");//이전페이지로 돌아감
			out.println("</script>");
		} else {
			//글 삭제 요청한 사용자가 글을 작성한 사용자면
			boolean isRemoveSuccess = boardDeleteProService.removeArticle(board_num);
			
			if(!isRemoveSuccess) {//isRemoveSuccess==false이면 (=삭제가 실패하면)
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");//자바스크립트로
				out.println("alert('삭제 실패!');");//삭제실패 경고창띄우고
				out.println("history.back();");//이전페이지로 돌아감
				out.println("</script>");
			} else {//삭제작업이 성공하면
				forward = new ActionForward();
				forward.setRedirect(true);//리다이렉트방식(=새요청)으로. 이미 갱신된 데이터를 확인하기위해
				forward.setPath("boardList.bo?page="+nowPage);//수정이 제대로 되었는지 확인하기 위해 전체글 목록보기 요청(현재 페이지번호 전송)
			}
		}
		return forward;
	}

}
