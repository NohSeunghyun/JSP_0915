<!-- 전체 게시판 글목록을 보여주는 뷰 페이지 -->
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="vo.PageInfo" %>
<%@page import="vo.Boardbean" %>

<%
//request영역에 공유한 pageInfo와 articleList속성을 얻어옴
PageInfo pageInfo =(PageInfo)request.getAttribute("pageInfo");
ArrayList<Boardbean> articleList =(ArrayList<Boardbean>)request.getAttribute("articleList");

//페이징 처리 관련 정보를 사용학 편하도록 각 벼수에 저장함
int nowPage = pageInfo.getPage();//페이지 번호
int listCount = pageInfo.getListCount();//게시판의 총 글의 개수
int maxPage = pageInfo.getMaxPage();//총 페이지 수
int startPage = pageInfo.getStartPage();//현재 페이지에 보여줄 시작 페이지 수
int endPage = pageInfo.getEndPage();//현재 페이지에 보여줄 마지막 페이지 수

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC2 게시판</title>
<style type="text/css">
* {margin: 0; padding: 0;}

#listFrom {
width:500px;
height:600px;
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
#tr_top {
	background-color: orange;
}
#pageList {/*아래쪽 페이지 번호*/
margin: auto;
width: 500px;
text-align: center;
}

#emptyArea {
margin: auto;;
width: 500px;
text-align: center;
}
</style>
</head>
<body>
<section id="listFrom">
	<h2>글 목록 <a href="boardWriteForm.bo">게시판 글쓰기</a></h2>
		<table>
		<%if(articleList != null && listCount > 0) { %>
			<tr id="tr_top">
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>
			<%for(int i =0; i <articleList.size(); i++) { %>
			<tr>
				<td><%=articleList.get(i).getBoard_num() %></td><!-- 글 번호 -->
				<!-- 해당글이 답변글일 경우 '들여쓰기' 하기 위한 용도 -->
				<td>
					<%if(articleList.get(i).getBoard_re_lev()!=0) { %><!-- 답변글이면 -->
					<!-- Board_re_lev*2만큼 들여쓰기한 다음 ▶만 출력  -->
						<% for(int j=0; j<articleList.get(i).getBoard_re_lev()*2;j++) {%>
							&nbsp;
						<%} %>▶
					<%} else {%><!-- 답변글이 아니라 원글이면 ▶만 출력 -->
						▶
					<%} %>
					    
				<!-- 글 제목 클릭하면 '글 내용 상세보기 요청'을 할 수 있도록 링크
				★board_num(글번호) 전송 이유?링크를 클릭했을 때 선택된 글 내용을 보여줘야 하므로
				★page(페이지 번호) 전송 이유?글 내용을 본 후 다시 원래 보던 페이지로 돌아가야 하므로 -->
					<a href="boardDetail.bo?board_num=<%=articleList.get(i).getBoard_num() %>&page=<%=nowPage %>"><%=articleList.get(i).getBoard_subject() %></a>
				</td>
				<td><%=articleList.get(i).getBoard_name() %></td><!-- 작성자 -->
				<td><%=articleList.get(i).getBoard_date() %></td><!-- 날짜 -->
				<td><%=articleList.get(i).getBoard_readcount() %></td><!-- 조회수 -->
			</tr>
			<%} %>
		</table>
		<!-- 페이징 처리를 위해서 '페이지 번호' 출력 -->
		<section id="pageList">
		<%if(nowPage <=1) { %> [이전]&nbsp;
		<%} else { %> <a href="boardList.bo?page=<%=nowPage-1 %>">[이전]</a>&nbsp;  <%} %>
		
		<!-- 처음페이지~마지막 페이지 번호까지 출력
		(예)nowPage=2일때 startPage=1, endPage=10		nowPage=15일때 startPage=11, endPage=20 -->
		<%for(int i =startPage;i<=endPage;i++) {
			if(i==nowPage){ %> [<%=i %>]&nbsp;
			<%} else {%>
				<a href="boardList.bo?page=<%=i%>">[<%=i %>]</a>&nbsp;	<%} %>
		<%} %>
		<%if(nowPage >=maxPage) { %> [다음]
		<%} else { %>
			<a href="boardList.bo?page=<%=nowPage+1%>">[다음]</a>
		<%} %>
		</section>
		
		<%} else {%>
		<section id="emptyArea">
			등록된 글이 없습니다.
		</section>
		<%}//첫 번째 if문%>
</section>
</body>
</html>