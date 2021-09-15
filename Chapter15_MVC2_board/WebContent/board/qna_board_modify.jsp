<%@page import="vo.Boardbean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Boardbean article = (Boardbean)request.getAttribute("article");
	String nowPage = (String) request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC2 게시판</title>
<style type="text/css">
* {margin: 0; padding: 0;}

#registFrom {
width:500px;
overflow:hidden;
border:1px solid red;
margin: auto;
}

h2 {
text-align: center;
}

table {
margin: auto;
width: 450px;
}
/*테이블의 왼쪽셀*/
.td_left{
width: 150px;
background-color: orange;
}
/*테이블의 오른쪽셀*/
.td_right{
width: 300px;
background-color: skyblue;
}
#commandCell {
	text-align: center;
}
</style>
</head>
<body>
	<section id="writeFrom">
		<h2>게시판 글 등록</h2>
		<!-- [수정]버튼 클릭하면 boardModifyPro.bo요청하여 프론트컨트롤러로 이동 -->
		<form action="boardModifyPro.bo" method="post" name="modifyform">
		<input type="hidden" name="board_num" value="<%=article.getBoard_num() %>">
		<input type="hidden" name="nowPage" value="<%=nowPage %>">
			<table>
				<tr>
					<th class="td_left"><label for="board_name">글쓴이</label></th><!-- required : value값이 없을시 요청이 안됨 -->
					<td class="td_right"><input type="text" name="board_name" value="<%=article.getBoard_name() %>" id="board_name" ></td>
				</tr>
				<tr><!-- 비번은 표시안함 -->
					<th class="td_left"><label for="board_pass">비밀번호</label></th><!-- required : value값이 없을시 요청이 안됨 -->
					<td class="td_right"><input type="password" name="board_pass" value="" size="21"  id="board_pass" ></td>
				</tr>
				<tr>
					<th class="td_left"><label for="board_subject">제목</label></th><!-- required : value값이 없을시 요청이 안됨 -->
					<td class="td_right"><input type="text" name="board_subject" value="<%=article.getBoard_subject() %>"  id="board_subject" ></td>
				</tr>
				<tr>
					<th class="td_left"><label for="board_content">글 내용</label></th><!-- required : value값이 없을시 요청이 안됨 -->
					<td class="td_right">
						<textarea rows="15" cols="40" name="board_content" value="<%=article.getBoard_content() %>" id="board_content"></textarea>
					</td>
				</tr>
			</table>
			<section id="commandCell">
					<a href="javascript:modifyform.submit()">[수정]</a>&nbsp;&nbsp;
					<a href="javascript:history.back()">[뒤로]</a>
			</section>
		</form>
	</section>
</body>
</html>