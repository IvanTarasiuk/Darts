package com.github.vanechka.Darts.database;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public final class DBConnectionTest {
    @Test
    @DisplayName("getAnswerFromDB: index = 388 (mutated from -2147483260)")
    @Timeout(value = 1000L, unit = TimeUnit.MILLISECONDS)
    public void testGetAnswerFromDB() {
        DBConnection dBConnection = new DBConnection();

        assertTimeoutPreemptively(Duration.ofMillis(1000L), () -> dBConnection.getAnswerFromDB(388));
    }

    @Test
    @DisplayName("getAnswerFromDB: index = -2147483647 (mutated from positive)")
    public void testGetAnswerFromDB1() {
        DBConnection dBConnection = new DBConnection();

        String actual = dBConnection.getAnswerFromDB(-2147483647);

        String expected = "";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("getAnswerFromDB: getAnswerFromDB: index = -2147483647 (mutated from positive)")
    public void testGetAnswerFromDB6() {
        DBConnection dBConnection = new DBConnection();

        String actual = dBConnection.getAnswerFromDB(-2147483647);

        String expected = "";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("getAnswerFromDB: getAnswerFromDB: index = 388 (mutated from -2147483260)")
    @Timeout(value = 1000L, unit = TimeUnit.MILLISECONDS)
    public void testGetAnswerFromDB7() {
        DBConnection dBConnection = new DBConnection();

        assertTimeoutPreemptively(Duration.ofMillis(1000L), () -> dBConnection.getAnswerFromDB(388));
    }

    @Test
    @DisplayName("getMarkerFromDB: index = min (mutated from zero)")
    public void testGetMarkerFromDBWithCornerCase() {
        DBConnection dBConnection = new DBConnection();

        Boolean actual = dBConnection.getMarkerFromDB(Integer.MIN_VALUE);

        Boolean expected = false;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("getMarkerFromDB: getMarkerFromDB: index = min (mutated from zero)")
    public void testGetMarkerFromDBWithCornerCase1() {
        DBConnection dBConnection = new DBConnection();

        Boolean actual = dBConnection.getMarkerFromDB(Integer.MIN_VALUE);

        Boolean expected = false;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("getQuestionFromDB: index = -2147483647 (mutated from positive)")
    public void testGetQuestionFromDB() {
        DBConnection dBConnection = new DBConnection();

        String actual = dBConnection.getQuestionFromDB(-2147483647);

        String expected = "";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("getQuestionFromDB: getQuestionFromDB: index = -2147483647 (mutated from positive)")
    public void testGetQuestionFromDB5() {
        DBConnection dBConnection = new DBConnection();

        String actual = dBConnection.getQuestionFromDB(-2147483647);

        String expected = "";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("updateMarkerInDB: index = -2147483647 (mutated from positive)")
    public void testUpdateMarkerInDB() {
        DBConnection dBConnection = new DBConnection();

        dBConnection.updateMarkerInDB(-2147483647);
    }

    @Test
    @DisplayName("updateMarkerInDB: updateMarkerInDB: index = -2147483647 (mutated from positive)")
    public void testUpdateMarkerInDB4() {
        DBConnection dBConnection = new DBConnection();

        dBConnection.updateMarkerInDB(-2147483647);
    }

    @Test
    @DisplayName("updateQuestionInDB: index = -2147483647 (mutated from positive)")
    public void testUpdateQuestionInDB() {
        DBConnection dBConnection = new DBConnection();

        dBConnection.updateQuestionInDB(-2147483647);
    }

    @Test
    @DisplayName("updateQuestionInDB: updateQuestionInDB: index = -2147483647 (mutated from positive)")
    public void testUpdateQuestionInDB4() {
        DBConnection dBConnection = new DBConnection();

        dBConnection.updateQuestionInDB(-2147483647);
    }
}