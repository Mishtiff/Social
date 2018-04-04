package methods;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UsersInfo {
	
	public static void gatherUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String username = request.getParameter("un");
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean userExists = false;
		PrintWriter out = null;
	    out = response.getWriter();
		
		//connect to Database
		Connection db = DatabaseConnection.ConnectDB();
		if (db == null) {
	    	out.print("Failure");
		    out.flush();
		    out.close();
			return;
		}
		
		try {
			pst = db.prepareStatement(ApplicationConstants.GATHER_USER_INFO);
			pst.setString(1, username.toLowerCase());
			rs = pst.executeQuery();
			
			if (rs.next()) { userExists = true; }
		}
		catch(Exception e){ e.printStackTrace(); }
		finally{ SQLCloser.close(rs); SQLCloser.close(pst); }
		
		if(userExists){ 
			sendUserInfo(username, db, out);
		}
		else{
	    	out.print("Failure");
		    out.flush();
		    out.close();
		    return;
		}
	}
	
	
	public static void sendUserInfo(String username, Connection db, PrintWriter out){
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = db.prepareStatement(ApplicationConstants.GATHER_USER_INFO);
			pst.setString(1, username.toLowerCase());
			rs = pst.executeQuery();
			
			if (rs.next()) {
				out.print("userProfile");
				out.print(";first," + rs.getString("firstname"));
				out.print(";last," + rs.getString("lastname"));
				out.print(";phone," + rs.getString("phonenumber"));
				out.print(";birthday," + rs.getLong("birthday"));
				out.print(";sex," + rs.getString("sex"));
				out.print(";rep," + rs.getInt("reputation"));
				out.print(";ec," + rs.getInt("events_created"));
				out.print(";ej," + rs.getInt("events_joined"));
				out.print(";ea," + rs.getInt("events_attended"));
				out.print(";em," + rs.getInt("events_missed"));
			    out.flush();
			    out.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			SQLCloser.close(rs);
			SQLCloser.close(pst);
			SQLCloser.close(db);
		}
	}
	
	
	public void saveUserImage(HttpServletRequest request, HttpServletResponse response){
		String filename ="WEB-INF/classes/wasdev/sample/methods/UserImages/";

		String username = request.getParameter("un");
		//byte[] picture = Byte.parseByte(request.getParameter("pic"));
		
		File myFile = new File(filename + username + ".txt");
		FileWriter fw = null;
		try {
			fw = new FileWriter(myFile,true);
			fw.write("\n");
			//fw.write(message);
			fw.write("\n");
			fw.flush();
		} catch (IOException e) {
			System.err.println(e);
		} finally{
			try {
				fw.close();
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}
}
