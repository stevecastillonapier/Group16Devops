package com.napier.sem;

import com.napier.sem.model.Countrylanguage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Countrylanguage model class.
 * Verifies instantiation, property accessors (getters/setters), and utility methods.
 */

@DisplayName("Countrylanguage Model Tests")
class CountrylanguageTest {
    // --- Test Data Constants ---
    private static final String COUNTRY_CODE = "GBR";
    private static final String LANGUAGE = "English";
    private static final String IS_OFFICIAL = "T";
    private static final double PERCENTAGE = 98.0;

    private Countrylanguage lang;

    @BeforeEach
    void setUp() {
        // Initialize with default constructor for setter tests
        lang = new Countrylanguage();
    }

    // --- Constructor & Getters ---

    @Test
    @DisplayName("Should initialize all fields using the full constructor")
    void testFullConstructorInitialization() {
        // Instantiate using the full constructor
        Countrylanguage fullLang = new Countrylanguage(
                COUNTRY_CODE, LANGUAGE, IS_OFFICIAL, PERCENTAGE
        );

        // Verify all fields were set correctly
        assertEquals(COUNTRY_CODE, fullLang.getCountryCode());
        assertEquals(LANGUAGE, fullLang.getLanguage());
        assertEquals(IS_OFFICIAL, fullLang.getIsOfficial());
        assertEquals(PERCENTAGE, fullLang.getPercentage(), 0.001); // Use delta for double comparison
    }

    @Test
    @DisplayName("Should correctly return the Country Code")
    void testGetCountryCode() {
        lang.setCountryCode(COUNTRY_CODE);
        assertEquals(COUNTRY_CODE, lang.getCountryCode());
    }

    @Test
    @DisplayName("Should correctly return the Language Name")
    void testGetLanguage() {
        lang.setLanguage(LANGUAGE);
        assertEquals(LANGUAGE, lang.getLanguage());
    }

    @Test
    @DisplayName("Should correctly return the Official Flag")
    void testGetIsOfficial() {
        lang.setIsOfficial(IS_OFFICIAL);
        assertEquals(IS_OFFICIAL, lang.getIsOfficial());
    }

    @Test
    @DisplayName("Should correctly return the Percentage")
    void testGetPercentage() {
        lang.setPercentage(PERCENTAGE);
        assertEquals(PERCENTAGE, lang.getPercentage(), 0.001);
    }

    // --- Setters ---

    @Test
    @DisplayName("Should set and get all properties correctly using setters")
    void testSetters() {
        lang.setCountryCode("FRA");
        lang.setLanguage("French");
        lang.setIsOfficial("F");
        lang.setPercentage(85.5);

        assertEquals("FRA", lang.getCountryCode());
        assertEquals("French", lang.getLanguage());
        assertEquals("F", lang.getIsOfficial());
        assertEquals(85.5, lang.getPercentage(), 0.001);
    }

    // --- Utility Methods ---

    @Test
    @DisplayName("Should generate a correct toString representation")
    void testToString() {
        Countrylanguage fullLang = new Countrylanguage(
                COUNTRY_CODE, LANGUAGE, IS_OFFICIAL, PERCENTAGE
        );
        String result = fullLang.toString();

        assertTrue(result.contains("Countrylanguage{"));
        assertTrue(result.contains("countryCode='GBR'"));
        assertTrue(result.contains("language='English'"));
        assertTrue(result.contains("isOfficial='T'"));
        assertTrue(result.contains("percentage=98.0"));
    }

    @Test
    @DisplayName("Should implement equals based on Country Code and Language")
    void testEquals() {
        Countrylanguage lang1 = new Countrylanguage(COUNTRY_CODE, LANGUAGE, IS_OFFICIAL, PERCENTAGE);
        Countrylanguage lang2 = new Countrylanguage(COUNTRY_CODE, LANGUAGE, "F", 50.0); // Different official status/percentage
        Countrylanguage lang3 = new Countrylanguage("FRA", LANGUAGE, IS_OFFICIAL, PERCENTAGE); // Different country code
        Countrylanguage lang4 = new Countrylanguage(COUNTRY_CODE, "Welsh", IS_OFFICIAL, PERCENTAGE); // Different language

        // Should be equal if CountryCode and Language are the same
        assertEquals(lang1, lang2, "Should be equal despite different percentage/official status.");

        // Should be unequal if Country Code is different
        assertNotEquals(lang1, lang3, "Should not be equal due to different country code.");

        // Should be unequal if Language is different
        assertNotEquals(lang1, lang4, "Should not be equal due to different language.");

        // Null check
        assertNotEquals(lang1, null);
    }

    @Test
    @DisplayName("Should implement hashCode consistently based on Country Code and Language")
    void testHashCode() {
        Countrylanguage lang1 = new Countrylanguage(COUNTRY_CODE, LANGUAGE, IS_OFFICIAL, PERCENTAGE);
        Countrylanguage lang2 = new Countrylanguage(COUNTRY_CODE, LANGUAGE, "F", 50.0);

        // Hash codes should be equal if primary keys are the same
        assertEquals(lang1.hashCode(), lang2.hashCode());

        // Hash codes should be different if primary keys are different
        Countrylanguage lang3 = new Countrylanguage("FRA", LANGUAGE, IS_OFFICIAL, PERCENTAGE);
        assertNotEquals(lang1.hashCode(), lang3.hashCode());
    }

}
