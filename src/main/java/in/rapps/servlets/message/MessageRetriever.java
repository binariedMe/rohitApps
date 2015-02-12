package in.rapps.servlets.message;
import in.rapps.models.Message;
import in.rapps.utils.JsonBuilder;
import in.rapps.utils.MessageService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getMessage")
public class MessageRetriever extends HttpServlet {

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
		String friendEmail = request.getParameter("friend");
		
		List<Message> messages = MessageService.getMessageList(userName, friendEmail);
		
		String messageResponse = JsonBuilder.getObjectJson(messages);
		
		if(out != null) {
			out.print(messageResponse);
		}
		
		
	}
}