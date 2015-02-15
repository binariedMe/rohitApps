package in.rapps.servlets.message;
import in.rapps.models.Message;
import in.rapps.utils.JsonBuilder;
import in.rapps.utils.MessageService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// Servlet to supply chat messages to client
@WebServlet("/getMessage")
public class MessageRetrieverServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = null;
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		// Populating local variables with request data
		String userName = request.getParameter("user");
		String friendEmail = request.getParameter("friend");
		
		// Populating an arraylist with chat messages
		List<Message> messages = MessageService.getMessageList(userName, friendEmail);
		
		//  Build JSON of chat messages 
		String messageResponse = JsonBuilder.getObjectJson(messages);
		
		if(out != null) {
			// Send chat messages if database read is successful, send false otherwise
			out.print(messageResponse);
		}
		
		
	}
}