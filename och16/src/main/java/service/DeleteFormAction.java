package service;

import java.io.IOException;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("DeleteFormAction start...");
		// 1. num, pageNum Get
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		try {
			// 2. BoardDao bd Instance
			BoardDao bd = BoardDao.getInstance();
			// 3. Board board = bd.select(num);
			Board board = bd.select(num);
			// 4. request 객체에 board, num, pageNum
			request.setAttribute("board", board);
			request.setAttribute("num", num);
			request.setAttribute("pageNum", pageNum);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "deleteForm.jsp";
	}

}
