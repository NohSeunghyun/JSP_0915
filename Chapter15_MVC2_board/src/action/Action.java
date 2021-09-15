/* 2.Action클래스들의 구격을 정의한 Action 인터페이스 설계
 * 
 * 이 인터페이스를 구현한 클래스에서 추상메서드를 반드시 재정의해줘야함
 * 컨트롤러에서 요청이 파악되면 해당 요청을 처리하는 각 Action클래스를 사용해서 요청 처리
 * 각 요청에 해당하는 Action클래스 객체들을 다형성으로 이용해서 동일한 타입(Action)으로 참조하기 위해
 */

package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public interface Action {
	//추상메서드 : 반드시 자식클래스에서 재정의해야함
	public ActionForward exeute(HttpServletRequest request,HttpServletResponse response) throws Exception;
}
