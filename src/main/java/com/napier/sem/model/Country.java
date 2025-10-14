package com.napier.sem.model;

/**
 * Represents a Country entity, serving as a Data Transfer Object (DTO) and Model
 * to hold information about a country from the database.
 */
public class Country {

    //Country Code (Primary Key, usually 3 letters).
    public String Code;

    //Name of the country.
    public String Name;

    //Continent the country belongs to.
    public String Continent;

    //Region within the continent.
    public String Region;

    //Total surface area of the country.
    public double SurfaceArea;

    //Year of independence.
    public Integer IndepYear;

    //Total population of the country.
    public int Population;
    
    //Life Expectancy.
    public Double LifeExpectancy;

    //Gross National Product (GNP).
    public Double GNP;

    //Old Gross National Product (GNP)
    public Double GNPOld;

    //Local name of the country.
    public String LocalName;

    //Type of government form
    public String GovernmentForm;

    //Name of the Head of State.
    public String HeadOfState;

    //ID of the capital city
    public Integer Capital;

    //Secondary country code
    public String Code2;

    //The name of the capital city
    public String CapitalName;

    /**
     * Full constructor for initializing all fields.
     */
    public Country(String code, String name, String continent, String region, double surfaceArea, Integer indepYear, int population, Double lifeExpectancy, Double GNP, Double GNPOld, String localName, String governmentForm, String headOfState, Integer capital, String code2, String capitalName) {
        Code = code;
        Name = name;
        Continent = continent;
        Region = region;
        SurfaceArea = surfaceArea;
        IndepYear = indepYear;
        Population = population;
        LifeExpectancy = lifeExpectancy;
        this.GNP = GNP;
        this.GNPOld = GNPOld;
        LocalName = localName;
        GovernmentForm = governmentForm;
        HeadOfState = headOfState;
        Capital = capital;
        Code2 = code2;
        CapitalName = capitalName;
    }

    /**
     * Default no-argument constructor.
     */
    public Country() {
    }

    public String getCode() {
        return Code;
    }

    public String getName() {
        return Name;
    }

    public String getContinent() {
        return Continent;
    }

    public String getRegion() {
        return Region;
    }

    public double getSurfaceArea() {
        return SurfaceArea;
    }

    public Integer getIndepYear() {
        return IndepYear;
    }

    public int getPopulation() {
        return Population;
    }

    public Double getLifeExpectancy() {
        return LifeExpectancy;
    }

    public Double getGNP() {
        return GNP;
    }

    public Double getGNPOld() {
        return GNPOld;
    }

    public String getLocalName() {
        return LocalName;
    }

    public String getGovernmentForm() {
        return GovernmentForm;
    }

    public String getHeadOfState() {
        return HeadOfState;
    }

    public Integer getCapital() {
        return Capital;
    }

    public String getCode2() {
        return Code2;
    }

    public String getCapitalName() {
        return CapitalName;
    }

    /**
     * Provides a detailed string representation of the Country object for logging and debugging.
     * @return A formatted string showing all field values.
     */
    @Override
    public String toString() {
        return "Country{" +
                "Code='" + Code + '\'' +
                ", Name='" + Name + '\'' +
                ", Continent='" + Continent + '\'' +
                ", Region='" + Region + '\'' +
                ", SurfaceArea=" + SurfaceArea +
                ", IndepYear=" + IndepYear +
                ", Population=" + Population +
                ", LifeExpectancy=" + LifeExpectancy +
                ", GNP=" + GNP +
                ", GNPOld=" + GNPOld +
                ", LocalName='" + LocalName + '\'' +
                ", GovernmentForm='" + GovernmentForm + '\'' +
                ", HeadOfState='" + HeadOfState + '\'' +
                ", Capital=" + Capital +
                ", Code2='" + Code2 + '\'' +
                ", CapitalName='" + CapitalName + '\'' +
                '}';
    }
}
