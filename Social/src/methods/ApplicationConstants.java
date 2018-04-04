package methods;

public class ApplicationConstants {
	
	private ApplicationConstants(){}
	
	static final String ATTEMPT_LOGIN = "SELECT username FROM login WHERE LOWER(username) = ? and password = ?";
	static final String GATHER_USER_INFO = "select * FROM users WHERE LOWER(username) = ?";
	static final String GATHER_EVENTS = "select * FROM event";
	
	
	
	
	
	
	
	
	
	
	
	
	
}
