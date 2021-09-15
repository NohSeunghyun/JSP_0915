package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardWriteProService;
import vo.ActionForward;
import vo.Boardbean;

//파일업로드하기 위해서 cos.jar를 lib폴더에 추가
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class BoardWriteProAction implements Action {

	@Override
	public ActionForward exeute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =null;
		
		//새로 등록할 글 정보를 저장할 BoardBean클래스
	
		int fileSize=5*1024*1024;//한 번에 업로드할 수 있는 파일 크기 5m
		
		//파일이 업로드 될 서버상의 실제 디렉토리(폴더) 경로
		ServletContext context = request.getServletContext();
		String realFolder = context.getRealPath("/boardUpload");
		
		MultipartRequest multi= new MultipartRequest(request, realFolder,fileSize,"UTF-8",
													new DefaultFileRenamePolicy());
													//파일이름 중복처리를 위한 객체 (ex)a.txt	a1.txt로 자동변경하여 업로드됨
		//새로 등록할 글 정보를 저장할 BoardBean클래스
		Boardbean boardbean =new Boardbean();
		
		//기본값으로 채워진 BoardBean객체를 사용자가 입력한 정보로 채움		
		boardbean.setBoard_name(multi.getParameter("board_name"));
		boardbean.setBoard_pass(multi.getParameter("board_pass"));
		boardbean.setBoard_subject(multi.getParameter("board_subject"));
		boardbean.setBoard_content(multi.getParameter("board_content"));
		boardbean.setBoard_file(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		
		//새로운 글(boardBean)을 등록하는 BoardWriteProService 객체 생성 후
		BoardWriteProService boardWriteProService = new BoardWriteProService();
		//객체 안의 registArticle()메소드로 DB연결,BoardBean객체 DB의 BOARD테이블에 추가
		boolean isWriteSucess = boardWriteProService.registArticle(boardbean);
		
		//추가 후 성공하면 true, 실패하면 false
		if(!isWriteSucess) {//isWriteSucessfalse==false 실패
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('등록실패');");
			out.print("history.back();");
			out.print("</script>");
		} else {//성공
			forward= new ActionForward();
			forward.setRedirect(true);//true:리다이렉트(=새요청) (기본값false:디스패치)
			forward.setPath("boardList.bo");//'글 전체 목록보기' 요청하면 다시 프론트컨트롤러로 이동
		}
		return forward;
	}
}
