<!-- 글 하나의 상세정보 보여주는 뷰페이지 (643p 그림 참조) -->
<%@page import="vo.Boardbean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Boardbean article = (Boardbean) request.getAttribute("article");
String nowPage = (String) request.getAttribute("page");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC2 게시판</title>
<style type="text/css">
* {margin: 0; padding: 0;}

#articleForm {
width:500px;
overflow:hidden;
border:1px solid red;
margin: auto;
}

h2 {
text-align: center;
}

#basicInfoArea {
margin: auto;
text-align: center;
height: 40px;
}

#articleContentArea {
margin: auto;
text-align: center;

margin-top20px;

height: 40px;
overflow:auto;/*지정한 영역 스크롤바 생기게하기*/
}

#commandList {/*[답변][수정][삭제][목록]*/
margin:auto;
text-align: center;
width: 500px
}
</style>
</head>
<body>
	<section id="articleForm">
		<h2>글 내용 상세보기</h2>
		<section id="basicInfoArea">
			제목 : <%=article.getBoard_subject() %>
			<%if(article.getBoard_file().length() > 0){%>
			첨부파일 : <!-- 파일 다운 숙제 -->
				<a href="board/file_down.jsp?file_name=<%=article.getBoard_file() %>"><%=article.getBoard_file() %></a>
			<%
				}
			%>
		</section>
		
		<section id="articleContentArea">
			<%=article.getBoard_content() %>
		</section>
		
		<section id="commandList">
			<!-- [답변][삭제] 두 파라미터 값 전송 이유?
			1.'글 번호' : board테이블에서 '원글을 조회'하기 위해 전송
				★원글을 조회하는 이유?
				원글의 그룹(board_re_ref)이나 들여쓰기(board_re_lev)와 위치(board_re_seq)를 알아야 답변글을 원글 아래에 제대로 표시할 수 있으므로
			★2. '현재페이지 번호' : 답변글 등록 처리를 한 후 '원래 있던 페이지로 되돌아가야 하기 때문에' -->
			<a href="boardReplayForm.bo?board_num=<%=article.getBoard_num() %>&page=<%=nowPage %>">[답변]</a><!-- 주위 : 값 보낼때 중간에 띄어쓰기하면 안됨 -->
			<a href="boardModifyForm.bo?board_num=<%=article.getBoard_num() %>&page=<%=nowPage %>">[수정]</a>
			<a href="boardDeleteForm.bo?board_num=<%=article.getBoard_num() %>&page=<%=nowPage %>">[삭제]</a>
			
			<!-- 파라미터로 '현재페이지 번호'를 전송 -->
			<a href="boardList.bo?page=<%=nowPage %>">[목록]</a>
		</section>
	</section>
</body>
</html>