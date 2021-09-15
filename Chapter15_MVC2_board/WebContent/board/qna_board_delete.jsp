<!-- 삭제요청시 글의 비밀번호를 입력하는 화면을 보여주는 뷰페이지 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//페이지번호 전송 이유? 삭제 후 다시 원래페이지로 돌아와서 삭제되었는지 확인
	int board_num = (Integer)request.getAttribute("board_num");
	String nowPage = (String)request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC2 게시판</title>
<style>
	#passForm {
		margin: auto;
		width: 400px;
		border: 1px solid orange;
	}
</style>
</head>
<body>
	<section id="passForm">
		<form action="boardDeletePro.bo?board_num=<%=board_num%>" method="post" name="deleteForm">
			<input type="hidden" name="page" value="<%=nowPage%>">
			<table>
				<tr>
					<td>글 비밀번호</td>
					<td>
						<input type="password" name="board_pass">
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="삭제"></td>&nbsp;&nbsp;
					<td><input type="button" value="돌아가기" onclick="javascript:history.go(-1)"></td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>