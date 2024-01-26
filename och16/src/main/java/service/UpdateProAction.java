package service;

import java.io.IOException;
import java.sql.SQLException;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		// 1. num , pageNum, writer ,  email , subject , passwd , content   Get
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String passwd = request.getParameter("passwd");
		String content = request.getParameter("content");
		
		
		
		// 3. BoardDao bd Instance
		BoardDao bd = BoardDao.getInstance();
		int result;
		try {
			// 2. Board board 생성하고 DTO Value Setting
			Board board = new Board();
			board.setNum(num);
			board.setContent(content);
			board.setEmail(email);
			board.setSubject(subject);
			board.setWriter(writer);
			board.setPasswd(passwd);
			board.setIp(request.getRemoteAddr());	// 클라이언트의 아이피 주소를 반환함
			result = bd.update(board);
			// 4. request 객체에 result, num , pageNum 
			request.setAttribute("result", result);
			request.setAttribute("num", num);
			request.setAttribute("pageNum", pageNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 5.updatePro.jsp Return
		return "updatePro.jsp";
	}

}
