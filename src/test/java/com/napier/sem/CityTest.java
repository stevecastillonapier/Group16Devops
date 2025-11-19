package com.napier.sem;

import com.napier.sem.model.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the City model class.
 * These tests verify the instantiation and property accessors (getters) of City objects
 * after the constructor assignment bug has been fixed.
 */
@DisplayName("City Model Tests")
class CityTest {

    private final int TEST_ID = 1;
    private final String TEST_NAME = "Edinburgh";
    private final String TEST_COUNTRY_CODE = "GBR";
    private final String TEST_DISTRICT = "Scotland";
    private final int TEST_POPULATION = 500000;

    private City city;

    @BeforeEach
    void setUp() {
        // Instantiate the City object using the corrected constructor
        city = new City(
                TEST_ID,
                TEST_NAME,
                TEST_COUNTRY_CODE,
                TEST_DISTRICT,
                TEST_POPULATION
        );
    }

    // =======================================================================
    // Test for Correct Initialization (All tests below now PASS)
    // =======================================================================

    @Test
    @DisplayName("Should correctly initialize all fields via constructor")
    void testFullConstructorInitialization() {
        // Assertions verifying that all fields were correctly assigned using 'this.'
        assertEquals(TEST_ID, city.getID(), "The ID should be assigned correctly.");
        assertEquals(TEST_NAME, city.getName(), "The Name should match the set value.");
        assertEquals(TEST_COUNTRY_CODE, city.getCountryCode(), "The Country Code should match the set value.");
        assertEquals(TEST_DISTRICT, city.getDistrict(), "The District should match the set value.");
        assertEquals(TEST_POPULATION, city.getPopulation(), "The Population should match the set value.");
    }

    @Test
    @DisplayName("Should correctly return the City ID")
    void testGetID() {
        assertEquals(TEST_ID, city.getID(), "The returned ID should match the set value.");
    }

    @Test
    @DisplayName("Should correctly return the City Name")
    void testGetName() {
        assertEquals(TEST_NAME, city.getName(), "The returned Name should match the set value.");
    }

    @Test
    @DisplayName("Should correctly return the Country Code")
    void testGetCountryCode() {
        assertEquals(TEST_COUNTRY_CODE, city.getCountryCode(), "The returned CountryCode should match the set value.");
    }

    @Test
    @DisplayName("Should correctly return the District")
    void testGetDistrict() {
        assertEquals(TEST_DISTRICT, city.getDistrict(), "The returned District should match the set value.");
    }

    @Test
    @DisplayName("Should correctly return the Population")
    void testGetPopulation() {
        assertEquals(TEST_POPULATION, city.getPopulation(), "The returned Population should match the set value.");
    }

    @Test
    @DisplayName("Should return a correctly formatted toString() output")
    void testToStringOutput() {
        String expectedToString = "City{ID=1, Name='Edinburgh', CountryCode='GBR', District='Scotland', Population=500000}";

        // Ensure the toString() method includes all expected fields in the correct format.
        assertEquals(expectedToString, city.toString(), "toString() output format is incorrect.");
    }

}
