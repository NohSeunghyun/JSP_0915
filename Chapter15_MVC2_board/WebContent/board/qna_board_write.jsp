<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
.last_tr {
text-align: center;
}
.last_tr input {
width: 70px;
}
</style>
</head>
<body>
	<section id="writeFrom">
		<h2>게시판 글 등록</h2>
		<!-- [등록]버튼 클릭하면 boardWritePro.bo요청하여 프론트컨트롤러로 이동 -->
		<form action="boardWritePro.bo" method="post" enctype="multipart/form-data" name="boardform">
			<table>
				<tr>
					<th class="td_left"><label for="board_name">글쓴이</label></th><!-- required : value값이 없을시 요청이 안됨 -->
					<td class="td_right"><input type="text" name="board_name" value="" required="required" id="board_name" ></td>
				</tr>
				<tr>
					<th class="td_left"><label for="board_pass">비밀번호</label></th><!-- required : value값이 없을시 요청이 안됨 -->
					<td class="td_right"><input type="password" name="board_pass" value="" size="21" required="required" id="board_pass" ></td>
				</tr>
				<tr>
					<th class="td_left"><label for="board_subject">제목</label></th><!-- required : value값이 없을시 요청이 안됨 -->
					<td class="td_right"><input type="text" name="board_subject" value="" required="required" id="board_subject" ></td>
				</tr>
				<tr>
					<th class="td_left"><label for="board_content">글 내용</label></th><!-- required : value값이 없을시 요청이 안됨 -->
					<td class="td_right">
						<textarea rows="15" cols="40" name="board_content" required="required" id="board_content"></textarea>
					</td>
				</tr>
				<tr>
					<th class="td_left"><label for="board_file">파일 첨부</label></th><!-- required : value값이 없을시 요청이 안됨 -->
					<td class="td_right"><input type="file" name="board_file" value="" required="required" id="board_file" ></td>
				</tr>
				<tr class="last_tr">
					<td colspan="2">
						<input type="submit" value="등록">
						<input type="reset" value="다시 쓰기">
						<input type="button" value="목록" onclick="location='boardList.bo'">
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>