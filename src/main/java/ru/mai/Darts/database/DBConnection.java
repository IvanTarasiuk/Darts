package ru.mai.Darts.database;

import java.sql.*;

public class DBConnection {

    private String url = "jdbc:postgresql://localhost:5433/postgres";
    private String user = "postgres";
    private String password = "adminqwerty";

    public String getAnswerFromDB(int index) {
        String answer = "";
        String select = """
                select answer
                from questions_and_answers
                where id = ?
                """;
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(select);
            statement.setInt(1, index + 1);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            answer = resultSet.getString("answer");
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return answer;
    }

    public String getQuestionFromDB(int index) {
        String question = "";
        String select = """
                select questions_and_answers.question
                from questions_and_answers
                where id = ?
                """;
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(select);
            statement.setInt(1, index + 1);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            question = resultSet.getString("question");
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return question;
    }

    public Boolean getMarkerFromDB(int index) {
        boolean marker = false;
        String select = """
                select usage_marker
                from questions_and_answers
                where id = ?
                """;
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(select);
            statement.setInt(1, index + 1);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            marker = resultSet.getBoolean("usage_marker");
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return marker;
    }

    public void updateQuestionInDB(int index) {
        String update = """
            update questions_and_answers
            set question = ''
            where id = ?
            """;
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setInt(1, index + 1);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void updateMarkerInDB(int index) {
        String update =
                """
            update questions_and_answers
            set usage_marker = false
            where id = ?
            """;
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setInt(1, index + 1);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}