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
import greenpark.dto.Adventures;

@WebServlet("/editAdventure")
public class UpdateAdventure extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int adventureId = Integer.parseInt(req.getParameter("id"));
		String adventureName = req.getParameter("name");
		double adventurePrice = Double.parseDouble(req.getParameter("price"));
		Adventures adventures = new Adventures();
		
		adventures.setAdventures(adventureName);
		adventures.setPrice(adventurePrice);
		
		AdventureDao adventureDao = new AdventureDao();
		PrintWriter printWriter = resp.getWriter() ;
		RequestDispatcher dispatcher ;
		Adventures adventuresdb= new Adventures();
		try {
			adventuresdb = adventureDao.updateAdventure(adventureId, adventures);
			if (adventuresdb != null) 
			{
//				printWriter.print("<h1>Adventure updated successfully..!</h1>");
				dispatcher = req.getRequestDispatcher("ViewAdventures.jsp") ;
				dispatcher.include(req, resp) ;
			}
			else {
//					printWriter.print("<h1>Failed to update adventure please try again </h1>");
					dispatcher = req.getRequestDispatcher("ViewAdventures.jsp") ;
					dispatcher.include(req, resp) ;
				 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
