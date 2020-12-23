package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BoardBean;
import dao.PageDAO;
import dao.PageMaker;
import service.BoardMainService;
import service.BoardPageService;
import vo.ActionForward;

public class BoardMainAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		String pageNum = request.getParameter("pageNum");
		
		BoardMainService service = new BoardMainService();
		BoardPageService service2 = new BoardPageService();
		
		ArrayList<BoardBean> boardList = service.getBoardList(pageNum);
		PageMaker pageMaker = service2.getPageMaker(pageNum);
		
		request.setAttribute("page", pageMaker);
		request.setAttribute("boardList", boardList);
		
		forward = new ActionForward();
		forward.setPath("main.jsp");
		return forward;
	}

}