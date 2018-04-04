package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import methods.Events;
import methods.Login;
import methods.UsersInfo;


@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String requestType = request.getParameter("requestType");
		response.setContentType("text/plain");
		PrintWriter out = null;
		
		try{
			switch (requestType) {
				case "login":
					Login.Attempt(request, response);
					
					break;
				default: 
				    out = response.getWriter();
				    
			    	out.print("OK");
				    out.flush();
				    out.close();
				    break;
			}
		}
		catch(IOException e){
			System.out.println(e);
		}
	}
  
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestType = request.getParameter("requestType");
		PrintWriter out = null;

		try{
			switch (requestType) {
				case "login": Login.Attempt(request, response); break;
				case "gatherUserInfo": UsersInfo.gatherUserInfo(request, response); break;
				case "GatherEvents": Events.GatherEvents(request, response); break;
				
				default: 
					response.setContentType("text/plain");
				    out = response.getWriter();
				    
			    	out.print("OK");
				    out.flush();
				    out.close();
				    break;
			}
		}
		catch(IOException e){
			System.out.println(e);
		}
	}
}