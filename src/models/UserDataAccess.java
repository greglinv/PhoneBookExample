package models;

import java.sql.*;
import myutils.*;
import models.*;

public class UserDataAccess {

	private Connection connect;
	public static int currentUserId;

	public UserDataAccess() {
		try {
			this.connect = Util.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean registerUser(User user) {

		try {
			String query = "INSERT INTO tb_users (username, password) VALUES (?, ?)";
			PreparedStatement stm = connect.prepareStatement(query);

			stm.setString(1, user.getUsername());
			stm.setString(2, Util.hashPassword(user.getPassword()));

			int row = stm.executeUpdate();

			if (row > 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			return false;
		}
	}

	public boolean loginUser(String username, String password) {

		String hashPaswword = Util.hashPassword(password);

		try {
			String query = "SELECT * FROM tb_users WHERE username = ? AND password = ?";
			PreparedStatement stm = connect.prepareStatement(query);
			stm.setString(1, username);
			stm.setString(2, hashPaswword);

			ResultSet results = stm.executeQuery();

			while (results.next()) {
				currentUserId = results.getInt("id");
				return true;
			}
			return false;
		}

		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
