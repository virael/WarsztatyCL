package warsztatyCL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

// może być problem z users_id zamiast user_id

public class Solution {
	private int id;
	private Date created;
	private Date updated;
	private String description;
	private int exercise_id;
	private int user_id;

	public Solution(Date created, Date updated, String descreption, int exercise_id, int user_id) {
		this.created = created;
		this.updated = updated;
		this.description = descreption;
		this.exercise_id = exercise_id;
		this.user_id = user_id;
	}

	public Solution(Date created, int exercise_id, int user_id) {
		this.created = created;
		this.exercise_id = exercise_id;
		this.user_id = user_id;
	}

	public Solution() {
	}

	public void saveToDB(Connection conn) throws SQLException {
		if (this.id == 0) {
			String sql = "INSERT INTO Solution(created, updated, descreption, exercise_id, users_id) VALUES (?, ?, ?, ?, ?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns);
			java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
			preparedStatement.setDate(1, sqlDate);
			preparedStatement.setDate(2, null);
			preparedStatement.setString(3, this.description);
			preparedStatement.setInt(4, this.exercise_id);
			preparedStatement.setInt(5, this.user_id);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		} else {
			String sql = "UPDATE Solution SET updated=Now(), description = ? where exercise_id = ? AND users_id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, this.description);
			preparedStatement.setInt(2, this.id);
			preparedStatement.setInt(3, this.id);
			preparedStatement.setInt(4, this.user_id);
			preparedStatement.executeUpdate();
		}
	}

	static public Solution loadUserById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM Solution where id=?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.created = resultSet.getDate("created");
			loadedSolution.updated = resultSet.getDate("updated");
			loadedSolution.description = resultSet.getString("description");
			loadedSolution.exercise_id = resultSet.getInt("exercise_id");
			loadedSolution.user_id = resultSet.getInt("users_id");
			return loadedSolution;
		}
		return null;
	}

	static public Solution[] loadAllGroups(Connection conn) throws SQLException {
		ArrayList<Solution> solution = new ArrayList<Solution>();
		String sql = "SELECT * FROM Solution";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.created = resultSet.getDate("created");
			loadedSolution.updated = resultSet.getDate("updated");
			loadedSolution.description = resultSet.getString("description");
			loadedSolution.exercise_id = resultSet.getInt("exercise_id");
			loadedSolution.user_id = resultSet.getInt("users_id");
			solution.add(loadedSolution);
		}
		Solution[] uArray = new Solution[solution.size()];
		uArray = solution.toArray(uArray);
		return uArray;
	}

	public void delete(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql = "DELETE FROM Solution WHERE id= ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
			this.id = 0;
		}
	}

	static public Solution[] loadAllByExerciseId(Connection conn, int id) throws SQLException {
		ArrayList<Solution> exerciseSolution = new ArrayList<Solution>();
		String sql = "SELECT * FROM Solution where exercise_id=?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Solution loadedExerciseSolution = new Solution();
			loadedExerciseSolution.id = resultSet.getInt("id");
			loadedExerciseSolution.created = resultSet.getDate("created");
			loadedExerciseSolution.updated = resultSet.getDate("updated");
			loadedExerciseSolution.description = resultSet.getString("description");
			loadedExerciseSolution.exercise_id = resultSet.getInt("exercise_id");
			loadedExerciseSolution.user_id = resultSet.getInt("users_id");
			exerciseSolution.add(loadedExerciseSolution);
		}
		Solution[] uArray = new Solution[exerciseSolution.size()];
		uArray = exerciseSolution.toArray(uArray);
		return uArray;
	}

	static public Solution[] loadAllByUserId(Connection conn, int id) throws SQLException {
		ArrayList<Solution> userSolution = new ArrayList<Solution>();
		String sql = "SELECT * FROM Solution where users_id=?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Solution loadedUserSolution = new Solution();
			loadedUserSolution.id = resultSet.getInt("id");
			loadedUserSolution.created = resultSet.getDate("created");
			loadedUserSolution.updated = resultSet.getDate("updated");
			loadedUserSolution.description = resultSet.getString("description");
			loadedUserSolution.exercise_id = resultSet.getInt("exercise_id");
			loadedUserSolution.user_id = resultSet.getInt("users_id");
			userSolution.add(loadedUserSolution);
		}
		Solution[] uArray = new Solution[userSolution.size()];
		uArray = userSolution.toArray(uArray);
		return uArray;
	}

	static public Solution[] loadAllByGroupId(Connection conn, int id) throws SQLException {
		ArrayList<Solution> exerciseSolution = new ArrayList<Solution>();
		String sql = "SELECT * FROM Solution LEFT JOIN Users ON Solution.users_id=Users.id WHERE Users.person_group_id=?;";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Solution loadedExerciseSolution = new Solution();
			loadedExerciseSolution.id = resultSet.getInt("id");
			loadedExerciseSolution.created = resultSet.getDate("created");
			loadedExerciseSolution.updated = resultSet.getDate("updated");
			loadedExerciseSolution.description = resultSet.getString("description");
			loadedExerciseSolution.exercise_id = resultSet.getInt("exercise_id");
			loadedExerciseSolution.user_id = resultSet.getInt("users_id");
			exerciseSolution.add(loadedExerciseSolution);
		}
		Solution[] uArray = new Solution[exerciseSolution.size()];
		uArray = exerciseSolution.toArray(uArray);
		return uArray;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getDescreption() {
		return description;
	}

	public void setDescreption(String descreption) {
		this.description = descreption;
	}

	public int getExercise_id() {
		return exercise_id;
	}

	public void setExercise_id(int exercise_id) {
		this.exercise_id = exercise_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
