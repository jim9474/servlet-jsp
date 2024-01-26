package service;

import java.io.IOException;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WriteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. num , pageNum, writer ,  email , subject , passwd , content   Get
		int num = Integer.parseInt(request.getParameter("num"));
		int ref = Integer.parseInt(request.getParameter("ref"));
		int re_level = Integer.parseInt(request.getParameter("re_level"));
		int re_step = Integer.parseInt(request.getParameter("re_step"));
		String pageNum = request.getParameter("pageNum");
        System.out.println("WriteProAction pageNum->"+pageNum);

		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String passwd = request.getParameter("passwd");
		String content = request.getParameter("content");
		// 2. Board board 생성하고 DTO Value Setting
		try {
			Board board = new Board();
			board.setNum(num);
			board.setWriter(writer);
			board.setEmail(email);
			board.setSubject(subject);
			board.setPasswd(passwd);
			board.setContent(content);
			board.setRef(ref);
			board.setRe_level(re_level);
			board.setRe_step(re_step);
			board.setIp(request.getRemoteAddr());
			
			// 3. BoardDao bd Instance
			BoardDao bd = BoardDao.getInstance();
			int result = bd.insert(board);
			// 4. request 객체에 result, num , pageNum
			request.setAttribute("result", result);
			request.setAttribute("num", num);
			request.setAttribute("pageNum", pageNum);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		 	        

		return "writePro.jsp";
	}

}
