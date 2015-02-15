package in.rapps.utils;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import in.rapps.models.User;

// JSON builder class
public class JsonBuilder {
	
	//volatile static ObjectMapper objMapper = new ObjectMapper();
	
	static ObjectMapper objMapper = new ObjectMapper();
	
	
	// Build JSON for user object
	public static String getUserJson(User user) {
		try {
			return objMapper.writeValueAsString(user);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	// Build JSON for user list object
	public static String getUsersJson(List<User> users) {
		try {
			return objMapper.writeValueAsString(users);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	// Build JSON for any given object
	public static String getObjectJson(Object obj) {
		try {
			return objMapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
