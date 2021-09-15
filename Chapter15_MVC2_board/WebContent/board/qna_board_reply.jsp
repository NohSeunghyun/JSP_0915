<!-- 답변글 쓰기 화면을 보여주는 뷰페이지(교제-p642 아래 그림 참조) -->
<%@page import="vo.Boardbean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
Boardbean article =(Boardbean)request.getAttribute("article");//원글 정보
String nowPage = (String)request.getAttribute("page");//원래 페이지로 돌아가기 위한 페이지 번호
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC2 게시판</title>
<style type="text/css">
* {margin: 0; padding: 0;}

#writeFrom {
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
		<h2>게시판 글등록(답변)</h2>
		<form action="boardReplayPro.bo" method="post" name="boardform">
		<!-- hidden 이유?[답변글등록]클릭하면 아래 2개의 값도 함께 전송하기 위해 
		page : 원래있던 페이지로 돌아가기 위해, board_num : 원글 조회하기 위해-->
		<input type="hidden" name="page" value="<%=nowPage %>" />
		<input type="hidden" name="board_num" value="<%=article.getBoard_num() %>" />

		<!-- hidden 이유?'원글의 그룹, 들여쓰기, 위치값'은 보여줄 필요없이 전송하기 위해
		'답변글의 그룹, 들여쓰기, 위치를 원글의 그룹, 위치 값'으로 계산하기 때문에-->
		<input type="hidden" name="board_re_ref" value="<%=article.getBoard_re_ref() %>" />		
		<input type="hidden" name="board_re_lev" value="<%=article.getBoard_re_lev() %>" />		
		<input type="hidden" name="board_re_seq" value="<%=article.getBoard_re_seq() %>" />		
		<table>
			<tr>
				<th class="td_left"><label for="board_name">글쓴이</label></th><!-- required : value값이 없을시 요청이 안됨 -->
				<td class="td_right"><input type="text" name="board_name" value="" id="board_name" ></td>
			</tr>
			<tr>
				<th class="td_left"><label for="board_pass">비밀번호</label></th><!-- required : value값이 없을시 요청이 안됨 -->
				<td class="td_right"><input type="password" name="board_pass" value="" size="21" id="board_pass" ></td>
			</tr>
			<tr>
				<th class="td_left"><label for="board_subject">제목</label></th><!-- required : value값이 없을시 요청이 안됨 -->
				<td class="td_right"><input type="text" name="board_subject" value="" id="board_subject" ></td>
			</tr>
			<tr>
				<th class="td_left"><label for="board_content">글 내용</label></th><!-- required : value값이 없을시 요청이 안됨 -->
				<td class="td_right">
					<textarea rows="15" cols="40" name="board_content" id="board_content"></textarea>
				</td>
			</tr>
		</table>
			<section id="commandCell">
				<input type="submit" value="답변글등록">
				<input type="reset" value="다시 쓰기">
			</section>
		</form>
	</section>
</body>
</html>