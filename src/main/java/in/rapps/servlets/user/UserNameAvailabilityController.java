package in.rapps.servlets.user;


import in.rapps.utils.UserService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/isUserNameAvailable")
public class UserNameAvailabilityController extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = null;
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String userName = request.getParameter("userName");
		
		boolean isAvailable = UserService.isUserNameAvailable(userName);
		
		if(out != null) {
			out.print(isAvailable);
		}
		
	}
}