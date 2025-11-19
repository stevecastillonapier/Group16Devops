package com.napier.sem;

import com.napier.sem.service.ReportService;
import com.napier.sem.ui.ReportMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Tests the core functionality of the App class, focusing on connection management and main flow logic.
 */
@DisplayName("App Core Tests")
class AppTest {
    private Connection mockConnection;
    private App app;

    // Preserve original System properties to restore after tests
    private String originalDbHost;

    @BeforeEach
    void setUp() throws SQLException {
        // Initialize the App instance
        app = new App();

        // Preserve environment/system properties
        originalDbHost = System.getenv("DB_HOST");

        // Mock the Connection class for use in disconnect test
        mockConnection = mock(Connection.class);
    }

    @AfterEach
    void tearDown() {
        // Restore System properties (especially important if running in CI)
        if (originalDbHost != null) {
            // Restore DB_HOST environment variable if it was set
            // NOTE: Changing environment variables at runtime is difficult; setting System properties is easier.
            // For robust testing of environment variables, a more complex setup is usually required.
        }
    }


    @Test
    @DisplayName("Should attempt to close the connection in disconnect()")
    void testDisconnectClosesConnection() throws Exception {
        // Use reflection to inject the mocked connection into the private 'con' field
        java.lang.reflect.Field field = App.class.getDeclaredField("con");
        field.setAccessible(true);
        field.set(app, mockConnection);

        // Act
        app.disconnect();

        // Assert
        verify(mockConnection, times(1)).close();
    }

    @Test
    @DisplayName("Should handle null connection in disconnect()")
    void testDisconnectHandlesNullConnection() {
        // Act & Assert
        assertDoesNotThrow(() -> app.disconnect(), "Disconnect should not throw an exception when connection is null.");
    }

    @Test
    @DisplayName("Should handle connection close exception in disconnect()")
    void testDisconnectHandlesCloseException() throws Exception {
        // Inject mock connection
        java.lang.reflect.Field field = App.class.getDeclaredField("con");
        field.setAccessible(true);
        field.set(app, mockConnection);

        // Configure mock to throw an exception on close
        doThrow(new SQLException("Mock close failure")).when(mockConnection).close();

        // Act & Assert
        assertDoesNotThrow(() -> app.disconnect(), "Disconnect should catch and handle exceptions on close.");
    }


}
