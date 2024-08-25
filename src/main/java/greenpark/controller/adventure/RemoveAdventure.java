package greenpark.controller.adventure;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import greenpark.dao.AdventureDao;

@WebServlet("/deleteAdventure")
public class RemoveAdventure extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int adventureId = Integer.parseInt(req.getParameter("id"));
		
		AdventureDao dao = new AdventureDao();
		String adventuresdb;
		
		PrintWriter printWriter = resp.getWriter() ;
		RequestDispatcher dispatcher ;
		try {
			adventuresdb = dao.deleteAdventure(adventureId);
			if (adventuresdb.isEmpty()) 
			{
//				printWriter.print("<h1>Failed to remove adventure please try again </h1>");
				dispatcher = req.getRequestDispatcher("ViewAdventures.jsp") ;
				dispatcher.include(req, resp) ;
				
				
			}
			else {
//				printWriter.print("<h1>Adventure removed successfully..!</h1>");
				dispatcher = req.getRequestDispatcher("ViewAdventures.jsp") ;
				dispatcher.include(req, resp) ;
				 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
