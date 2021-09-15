//페이징 처리 관련 정보를 저장하는 클래스
package vo;

public class PageInfo {
	private int page;//해당 페이지 번호
	private int maxPage;//총 페이지 수
	private int startPage;//현재 페이지에 보여줄 시작 페이지 수
	private int endPage;//현재 페이지에 보여줄 마지막 페이지 수
	private int listCount;//게시판의 총 글의 개수
	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	
}
