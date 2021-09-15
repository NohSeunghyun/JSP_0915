/*
 * 글 수정 처리하는 Action 클래스
 */
package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardModifyProService;
import vo.ActionForward;
import vo.Boardbean;

public class BoardModifyProAction implements Action {

	@Override
	public ActionForward exeute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String nowPage = request.getParameter("nowPage");//원래 있던 페이지로 돌아가기 위해
		ActionForward forward = null;
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String pass = request.getParameter("board_pass");
		
		Boardbean article = null;//이번엔 선언만하고 밑에서 기본값으로 채워진 Boardbean객체를 넣는다
		
		//전송된 비번을 사용해서 글 수정 요청한 작성자가 글을 작성한 사용자인지 판단
		BoardModifyProService boardModifyProService = new BoardModifyProService();
		boolean isRightUser = boardModifyProService.isArticleWriter(board_num, pass);
		
		if(!isRightUser) {
			//글 수정 요청한 사용자가 글을 작성한 사용자가 아니면 경고창
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");//자바스크립트로
			out.println("alert('수정할 권한이 없습니다.');");//경고창띄우고
			out.println("history.back();");//이전페이지로 돌아감
			out.println("</script>");
		} else {
			//글 수정 요청한 사용자가 글을 작성한 사용자면
			article = new Boardbean();//기본값으로 채워진
			
			article.setBoard_num(board_num);//파라미터로 전송된(=매개값) board_num으로 값설정
			
			//이름은 수정안함
			
			//사용자가 다시 입력한 파라미터값으로 채움
			article.setBoard_subject(request.getParameter("board_subject"));
			article.setBoard_content(request.getParameter("board_content"));
			
			boolean isModifySuccess = boardModifyProService.modifyArticle(article);
			
			if(!isModifySuccess) {//isModifySuccess==false이면 (=수정이 실패하면)
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");//자바스크립트로
				out.println("alert('수정 실패!');");//수정실패 경고창띄우고
				out.println("history.back();");//이전페이지로 돌아감
				out.println("</script>");
			} else {//수정작업이 성공하면
				forward = new ActionForward();
				forward.setRedirect(true);//리다이렉트방식(=새요청)으로. 이미 갱신된 데이터를 확인하기위해
				forward.setPath("boardDetail.bo?board_num="+article.getBoard_num()+"&page="+nowPage);//수정이 제대로 되었는지 확인하기 위해 하나의 글 상세보기 요청(글번호로 전송)
				//위에 선언한 board_num사용할수있는지 물어봐야함->가능
			}
		}
		return forward;
	}

}
