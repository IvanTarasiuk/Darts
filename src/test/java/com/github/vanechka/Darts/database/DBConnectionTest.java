package com.github.vanechka.Darts.database;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DBConnectionTest{

    @Test
    public void testGetQuestionFromDB() {
        DBConnection connection = new DBConnection();
        String question = connection.getQuestionFromDB(0);
        assertEquals("Данные, предназначенные для управления конкретными компонентами системы обработки информации в целях реализации определенного алгоритма", question);
    }

    @Test
    public void testGetAnswerFromDB() {
        DBConnection connection = new DBConnection();
        String answer = connection.getAnswerFromDB(1);
        assertEquals("Программное обеспечение", answer);
    }

    @Test
    public void testGetUsageMarkerFromDB() {
        DBConnection connection = new DBConnection();
        boolean usageMarker = connection.getMarkerFromDB(3);
        assertEquals(true, usageMarker);
    }

    @Test
    public void testGetQuestionById_InvalidId() {
        DBConnection connection = new DBConnection();
        String question = connection.getQuestionFromDB(100);
        assertEquals("", question);
    }

    @Test
    public void testGetAnswerFromDB_InvalidId() {
        DBConnection connection = new DBConnection();
        String answer = connection.getAnswerFromDB(100);
        assertEquals("", answer);
    }

    @Test
    public void testGetUsageMarkerById_InvalidId() {
        DBConnection connection = new DBConnection();
        boolean usageMarker = connection.getMarkerFromDB(100);
        assertEquals(false, usageMarker);
    }
}