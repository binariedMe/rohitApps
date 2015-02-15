package in.rapps.servlets.message;
import in.rapps.utils.MessageService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet to submit message
@WebServlet("/submitMessage")
public class MessageSubmitterServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = null;
		
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// Populating local veriables with request data
		String userName = request.getParameter("user");
		String recepientEmail = request.getParameter("recepient");
		String message = request.getParameter("message");
		
		// Submit the message in database and reply true if successful, false otherwise
		boolean isMessageSubmitted = MessageService.submitMessage(userName, recepientEmail, message);
		
		if(out != null) {
			
			out.print(isMessageSubmitted);
		}
		
	}
}