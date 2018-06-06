import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.softtek.javaweb.connection.DriverManagerDatabase;

public class DatabaseTest {

	public static void main(String[] args) {
		System.out.println("############## start");
		Connection connection = DriverManagerDatabase.getConnection();
		System.out.println(connection.toString());
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("SELECT * FROM user_role");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
