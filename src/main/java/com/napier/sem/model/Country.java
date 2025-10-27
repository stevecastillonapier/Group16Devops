package com.napier.sem.model;

/**
 * Represents a Country entity, serving as a Data Transfer Object (DTO) and Model
 * to hold information about a country from the database.
 */
public class Country {

    //Country Code (Primary Key, usually 3 letters).
    private String code;

    //Name of the country.
    private String name;

    //Continent the country belongs to.
    private String continent;

    //Region within the continent.
    private String region;

    //Total surface area of the country.
    private double surfaceArea;

    //Year of independence.
    private Integer indepYear;

    //Total population of the country.
    private int population;
    
    //Life Expectancy.
    private Double lifeExpectancy;

    //Gross National Product (GNP).
    private Double gNP;

    //Old Gross National Product (GNP)
    private Double gNPOld;

    //Local name of the country.
    private String localName;

    //Type of government form
    private String governmentForm;

    //Name of the Head of State.
    private String headOfState;

    //ID of the capital city
    private Integer capital;

    //Secondary country code
    private String code2;

    //The name of the capital city
    private String capitalName;

    /**
     * Full constructor for initializing all fields.
     */
    public Country(String code, String name, String continent, String region, double surfaceArea, Integer indepYear, int population, Double lifeExpectancy, Double GNP, Double GNPOld, String localName, String governmentForm, String headOfState, Integer capital, String code2, String capitalName) {
        code = code;
        name = name;
        continent = continent;
        region = region;
        surfaceArea = surfaceArea;
        indepYear = indepYear;
        population = population;
        lifeExpectancy = lifeExpectancy;
        this.gNP = GNP;
        this.gNPOld = GNPOld;
        localName = localName;
        governmentForm = governmentForm;
        headOfState = headOfState;
        capital = capital;
        code2 = code2;
        capitalName = capitalName;
    }

    /**
     * Default no-argument constructor.
     */
    public Country() {
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public String getRegion() {
        return region;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public Integer getIndepYear() {
        return indepYear;
    }

    public int getPopulation() {
        return population;
    }

    public Double getLifeExpectancy() {
        return lifeExpectancy;
    }

    public Double getGNP() {
        return gNP;
    }

    public Double getGNPOld() {
        return gNPOld;
    }

    public String getLocalName() {
        return localName;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public Integer getCapital() {
        return capital;
    }

    public String getCode2() {
        return code2;
    }

    public String getCapitalName() {
        return capitalName;
    }

    /**
     * Provides a detailed string representation of the Country object for logging and debugging.
     * @return A formatted string showing all field values.
     */
    @Override
    public String toString() {
        return "Country{" +
                "Code='" + code + '\'' +
                ", Name='" + name + '\'' +
                ", Continent='" + continent + '\'' +
                ", Region='" + region + '\'' +
                ", SurfaceArea=" + surfaceArea +
                ", IndepYear=" + indepYear +
                ", Population=" + population +
                ", LifeExpectancy=" + lifeExpectancy +
                ", GNP=" + gNP +
                ", GNPOld=" + gNPOld +
                ", LocalName='" + localName + '\'' +
                ", GovernmentForm='" + governmentForm + '\'' +
                ", HeadOfState='" + headOfState + '\'' +
                ", Capital=" + capital +
                ", Code2='" + code2 + '\'' +
                ", CapitalName='" + capitalName + '\'' +
                '}';
    }
}
