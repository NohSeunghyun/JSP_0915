drop table board;

/*주석 달기위해 쓰이는 용도*/
create table board(
board_num int, /*글번호(오라클 number)*/
board_name varchar(20) not null, /*글 작성자(오라클 varchar2)*/
board_pass varchar(15) not null, /*글 비밀번호*/
board_subject varchar(50) not null, /*글 제목*/
board_content varchar(2000) not null,/*글 내용*/
board_file varchar(50) not null,/*첨부 파일
--------------------------------------------------*/
board_re_ref int not null, /*관련글 번호*/
board_re_lev int not null, /*답글 레벨*/
board_re_seq int not null, /*관련글 중 출력순서
--------------------------------------------------*/
board_readcount int default 0, /*조회수*/
board_date date, /*작성일
--------------------------------------------------*/
primary key(board_num)/*기본키*/
);

--board_re_ref : 같은 수는 같은 그룹을 의미
--(원글 board_re_ref 숫자가 3이면 답변글 모두 board_re_ref 숫자가 3이 되어 원글과 답변글을 하나

--board_re_rev : 얼마만큼 안쪽으로 들어가 글이 시작될 것인지 결정해 주는 값
--답변레벨로 0이 아니면 [답변글]이다. (0이면 원글임)
--답변레벨로 0이 아니면 즉, [답변글]이면 '답변레벨*2'하여 답변레벨이 하나 증가할 때마다
--'공백(&nbsp;)을 2번씩' 더 출력하여 들여쓰기한 후 답변글이 출력되도록 로직처리함

--board_re_seq : 원글에서 답변글이 몇 번째 아래에 놓일 것인지 위치를 결정해 주는 값

/*insertArticle*/
select * from board;
select ifnull(max(board_num),0)+1 /*nvl(max(board_num),0)+1*/ from board;
select * from board order by Board_re_ref desc, Board_re_seq asc limit ?,10;
/*selecListCount*/
select count(*) from board;

create table board(
board_num int,
board_name varchar(20) not null,
board_pass varchar(15) not null,
board_subject varchar(50) not null,
board_content varchar(2000) not null,
board_file varchar(50) not null,
board_re_ref int not null,
board_re_lev int not null,
board_re_seq int not null,
board_readcount int default 0,
board_date date,
primary key(board_num)
);

