package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String SQL = "CREATE TABLE IF NOT EXISTS qwe (id BIGINT NOT NULL AUTO_INCREMENT," +
                "name VARCHAR (45)NOT NULL, lastname VARCHAR (45) NOT NULL,age TINYINT NOT NULL, PRIMARY KEY (id))";
        Connection connection = Util.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
            System.out.println("Стол сделан");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Connection connection= Util.getConnection();;
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate("DROP TABLE IF EXISTS qwe");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getConnection();
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO qwe(name, lastname, age) VALUE (?, ?, ?)")){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User : "  + name + " был(а) добавлен(а) в базу");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        Connection connection = Util.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM qwe WHERE ID = id LIMIT 1;")) {
            preparedStatement.executeUpdate();
            System.out.println("User c id - " + id + " удален из базы данных");
        } catch (SQLException e) {
            System.out.println("User не удален");
        }

    }

    public List<User> getAllUsers() {

        List<User> user = new ArrayList<>();
        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            String SQL="SELECT * FROM qwe ";
            ResultSet resultSet = statement.executeQuery(SQL);
            while(resultSet.next()) {
                User userd = new User();
                userd.setId(resultSet.getLong("id"));
                userd.setName(resultSet.getString("name"));
                userd.setLastName(resultSet.getString("LastName"));
                userd.setAge(resultSet.getByte("age"));
                user.add(userd);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public void cleanUsersTable() {
        Connection connection = Util.getConnection();
        try {
            String SQL = "TRUNCATE TABLE qwe";
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
            System.out.println("Таблица стерта");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
