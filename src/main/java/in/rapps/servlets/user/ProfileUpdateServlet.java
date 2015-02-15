package in.rapps.servlets.user;

import in.rapps.models.Profile;
import in.rapps.models.User;
import in.rapps.utils.JsonBuilder;
import in.rapps.utils.UserService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet to update profile of client
@WebServlet("/updateProfile")
public class ProfileUpdateServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = null;

		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// New Profile object
		Profile profile = new Profile();
		
		// Populating profile object with data received from request
		String userName = request.getParameter("user");
		profile.setFirstName(request.getParameter("firstName"));
		profile.setLastName(request.getParameter("lastName"));
		profile.setFullName(request.getParameter("fullName"));
		profile.setPhone(request.getParameter("phone"));
		profile.setAddressLine1(request.getParameter("addressLine1"));
		profile.setAddressLine2(request.getParameter("addressLine2"));
		profile.setLandMark(request.getParameter("landMark"));
		profile.setCity(request.getParameter("city"));
		profile.setState(request.getParameter("state"));
		profile.setCountry(request.getParameter("country"));
		profile.setZipCode(Integer.parseInt(request.getParameter("zipCode")));


		try {
			// Update user details with data in profile object and return user details in user object
			User user = UserService.updateProfile(userName, profile);

			// Building JSON from user object so as to send in response
			String userData = JsonBuilder.getUserJson(user);
			
			// Send the response
			out.print(userData);
		}
		catch (Exception e) {
			
			// If update of profile fails, send status as false
			out.print(false);
		}



	}
}