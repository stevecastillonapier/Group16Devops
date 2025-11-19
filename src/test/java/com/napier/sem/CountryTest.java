package com.napier.sem;

import com.napier.sem.model.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Country model class.
 * Verifies instantiation, property accessors (getters/setters), and utility methods.
 */
@DisplayName("Country Model Tests")
class CountryTest {

    // --- Test Data Constants ---
    private static final String CODE = "USA";
    private static final String NAME = "United States";
    private static final String CONTINENT = "North America";
    private static final String REGION = "North America";
    private static final double SURFACE_AREA = 9363520.00;
    private static final Integer INDEP_YEAR = 1776;
    private static final int POPULATION = 300000000;
    private static final Double LIFE_EXPECTANCY = 77.0;
    private static final Double GNP = 8510700.00;
    private static final Double GNP_OLD = 8110700.00;
    private static final String LOCAL_NAME = "United States";
    private static final String GOVERNMENT_FORM = "Federal Republic";
    private static final String HEAD_OF_STATE = "Joe Biden";
    private static final Integer CAPITAL_ID = 3835;
    private static final String CODE2 = "US";
    private static final String CAPITAL_NAME = "Washington D.C.";

    private Country country;

    @BeforeEach
    void setUp() {
        // Initialize with default constructor for setter tests
        country = new Country();
    }

    @Test
    @DisplayName("Should initialize all fields using the full constructor")
    void testFullConstructor() {
        // Instantiate using the full constructor
        Country fullCountry = new Country(
                CODE, NAME, CONTINENT, REGION, SURFACE_AREA, INDEP_YEAR, POPULATION,
                LIFE_EXPECTANCY, GNP, GNP_OLD, LOCAL_NAME, GOVERNMENT_FORM, HEAD_OF_STATE,
                CAPITAL_ID, CODE2, CAPITAL_NAME
        );

        // Verify all fields were set correctly
        assertEquals(CODE, fullCountry.getCode());
        assertEquals(NAME, fullCountry.getName());
        assertEquals(CONTINENT, fullCountry.getContinent());
        assertEquals(REGION, fullCountry.getRegion());
        assertEquals(SURFACE_AREA, fullCountry.getSurfaceArea());
        assertEquals(INDEP_YEAR, fullCountry.getIndepYear());
        assertEquals(POPULATION, fullCountry.getPopulation());
        assertEquals(LIFE_EXPECTANCY, fullCountry.getLifeExpectancy());
        assertEquals(GNP, fullCountry.getGNP());
        assertEquals(GNP_OLD, fullCountry.getGNPOld());
        assertEquals(LOCAL_NAME, fullCountry.getLocalName());
        assertEquals(GOVERNMENT_FORM, fullCountry.getGovernmentForm());
        assertEquals(HEAD_OF_STATE, fullCountry.getHeadOfState());
        assertEquals(CAPITAL_ID, fullCountry.getCapital());
        assertEquals(CODE2, fullCountry.getCode2());
        assertEquals(CAPITAL_NAME, fullCountry.getCapitalName());
    }

    @Test
    @DisplayName("Should set and get all String properties correctly")
    void testStringSettersAndGetters() {
        country.setCode(CODE);
        country.setName(NAME);
        country.setContinent(CONTINENT);
        country.setRegion(REGION);
        country.setLocalName(LOCAL_NAME);
        country.setGovernmentForm(GOVERNMENT_FORM);
        country.setHeadOfState(HEAD_OF_STATE);
        country.setCode2(CODE2);
        country.setCapitalName(CAPITAL_NAME);

        assertEquals(CODE, country.getCode());
        assertEquals(NAME, country.getName());
        assertEquals(CONTINENT, country.getContinent());
        assertEquals(REGION, country.getRegion());
        assertEquals(LOCAL_NAME, country.getLocalName());
        assertEquals(GOVERNMENT_FORM, country.getGovernmentForm());
        assertEquals(HEAD_OF_STATE, country.getHeadOfState());
        assertEquals(CODE2, country.getCode2());
        assertEquals(CAPITAL_NAME, country.getCapitalName());
    }

    @Test
    @DisplayName("Should set and get all numeric properties correctly")
    void testNumericSettersAndGetters() {
        country.setSurfaceArea(SURFACE_AREA);
        country.setIndepYear(INDEP_YEAR);
        country.setPopulation(POPULATION);
        country.setLifeExpectancy(LIFE_EXPECTANCY);
        country.setGNP(GNP);
        country.setGNPOld(GNP_OLD);
        country.setCapital(CAPITAL_ID);

        assertEquals(SURFACE_AREA, country.getSurfaceArea());
        assertEquals(INDEP_YEAR, country.getIndepYear());
        assertEquals(POPULATION, country.getPopulation());
        assertEquals(LIFE_EXPECTANCY, country.getLifeExpectancy());
        assertEquals(GNP, country.getGNP());
        assertEquals(GNP_OLD, country.getGNPOld());
        assertEquals(CAPITAL_ID, country.getCapital());
    }

    @Test
    @DisplayName("Should handle null values for optional Double/Integer fields")
    void testNullValues() {
        country.setIndepYear(null);
        country.setLifeExpectancy(null);
        country.setGNP(null);
        country.setGNPOld(null);
        country.setCapital(null);

        assertNull(country.getIndepYear());
        assertNull(country.getLifeExpectancy());
        assertNull(country.getGNP());
        assertNull(country.getGNPOld());
        assertNull(country.getCapital());
    }

    @Test
    @DisplayName("Should generate a detailed toString representation")
    void testToString() {
        Country fullCountry = new Country(
                CODE, NAME, CONTINENT, REGION, SURFACE_AREA, INDEP_YEAR, POPULATION,
                LIFE_EXPECTANCY, GNP, GNP_OLD, LOCAL_NAME, GOVERNMENT_FORM, HEAD_OF_STATE,
                CAPITAL_ID, CODE2, CAPITAL_NAME
        );

        String result = fullCountry.toString();

        assertTrue(result.contains("Country{"));
        assertTrue(result.contains("Code='USA'"));
        assertTrue(result.contains("Name='United States'"));
        assertTrue(result.contains("Population=300000000"));
        assertTrue(result.contains("GovernmentForm='Federal Republic'"));
        assertTrue(result.contains("CapitalName='Washington D.C.'"));
    }

    @Test
    @DisplayName("Should implement equals based on Code")
    void testEquals() {
        Country country1 = new Country(CODE, NAME, CONTINENT, REGION, 0, 0, 0, 0.0, 0.0, 0.0, null, null, null, null, null, null);
        Country country2 = new Country(CODE, "Different Name", "Different Continent", null, 0, 0, 0, 0.0, 0.0, 0.0, null, null, null, null, null, null);
        Country country3 = new Country("CAN", "Canada", CONTINENT, REGION, 0, 0, 0, 0.0, 0.0, 0.0, null, null, null, null, null, null);

        // Should be equal if Code is the same
        assertEquals(country1, country2);

        // Should be unequal if Code is different
        assertNotEquals(country1, country3);
        assertNotEquals(country1, null);
    }

    @Test
    @DisplayName("Should implement hashCode consistently based on Code")
    void testHashCode() {
        Country country1 = new Country(CODE, NAME, CONTINENT, REGION, 0, 0, 0, 0.0, 0.0, 0.0, null, null, null, null, null, null);
        Country country2 = new Country(CODE, "Different Name", "Different Continent", null, 0, 0, 0, 0.0, 0.0, 0.0, null, null, null, null, null, null);
        Country country3 = new Country("CAN", "Canada", CONTINENT, REGION, 0, 0, 0, 0.0, 0.0, 0.0, null, null, null, null, null, null);

        // Hash codes should be equal if Code is the same
        assertEquals(country1.hashCode(), country2.hashCode());

        // Hash codes should be different if Code is different
        assertNotEquals(country1.hashCode(), country3.hashCode());
    }

}
