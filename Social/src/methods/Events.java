package methods;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Events {
	
	public static void GatherEvents(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
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
			pst = db.prepareStatement(ApplicationConstants.GATHER_EVENTS);
			rs = pst.executeQuery();
			

			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<events>");
			
			while (rs.next()) {

				sb.append("<event>");
				sb.append("<name>").append(rs.getString("event_name")).append("</name>");
				sb.append("<category>").append(rs.getString("event_category")).append("</category>");
		        sb.append("<type>").append(rs.getString("event_type")).append("</type>");
		        sb.append("<text>").append(rs.getString("event_text")).append("</text>");
		        sb.append("<creator>").append(rs.getString("event_creator")).append("</creator>");
		        sb.append("<date_created>").append(String.valueOf(rs.getLong("event_date_created"))).append("</date_created>");
		        sb.append("<date_planned>").append(String.valueOf(rs.getLong("event_date_planned"))).append("</date_planned>");
		        sb.append("<max>").append(String.valueOf(rs.getInt("event_max"))).append("</max>");
		        sb.append("</event>");
                
            }
			sb.append("</events>");
			
			out.print(sb.toString());
		    out.flush();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			SQLCloser.close(rs);
			SQLCloser.close(pst);
			SQLCloser.close(db);
		    out.close();
		}

	    return;
	}
}
