package in.rapps.servlets.authentication;

import in.rapps.models.User;
import in.rapps.utils.JsonBuilder;
import in.rapps.utils.UserService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/log_in")
public class LoginServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String userName = request.getParameter("user");
		String password = request.getParameter("password");

		try {
			User user = UserService.getUserByEmail(userName);
			
			if(password != null && password.equals(user.getPassword())) {
				String userData = JsonBuilder.getUserJson(user);
				out.print(userData);
			}
			else {
				out.println(false);
			}
		}
		catch (Exception e) {
			out.print(false);
		}

	}
}