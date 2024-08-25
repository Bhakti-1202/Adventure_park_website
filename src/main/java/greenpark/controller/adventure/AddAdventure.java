package greenpark.controller.adventure;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import greenpark.dao.AdventureDao;
import greenpark.dto.Admin;
import greenpark.dto.Adventures;

@WebServlet("/addAdventures")
public class AddAdventure extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		HttpSession session = req.getSession() ;
		Admin admin = (Admin) session.getAttribute("admin") ;
		
		if(admin != null)
		{
		
			String adventure = req.getParameter("name");
			long price = Long.parseLong(req.getParameter("price"));
			
			Adventures adventures = new Adventures();
			adventures.setAdventures(adventure);
			adventures.setPrice(price);
			
			AdventureDao dao = new AdventureDao();
			Adventures adventuresdb = new Adventures();
			PrintWriter printWriter = resp.getWriter() ;
			RequestDispatcher dispatcher ;
			try {
				adventuresdb = dao.addAdventure(adventures);
				if (adventuresdb != null) 
				{
	//				printWriter.print("<h1>Adventure added successfully..!</h1>");
					dispatcher = req.getRequestDispatcher("ViewAdventures.jsp") ;
					dispatcher.include(req, resp) ;
				}
				else {
	//					 printWriter.print("<h1>Failed to add adventure please try again </h1>");
							dispatcher = req.getRequestDispatcher("ViewAdventures.jsp") ;
							dispatcher.include(req, resp) ;
					 
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp") ;
			dispatcher.forward(req, resp);
		}
	}
}
