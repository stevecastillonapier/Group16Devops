package com.napier.sem.model;
/**
 * Represents a City entity, serving as a Data Transfer Object (DTO) and Model
 * to hold information about a City from the database.
 */
public class City {

    //City ID (Primary Key)
    private int id;

    //Name of the city
    private String name;

    //3-letter country code that references the country table
    private String countryCode;

    //District or region the city belongs to
    private String district;

    //Population of the city
    private int population;

    /**
     * Constructor for the City model.
     * @param ID City's unique identifier.
     * @param name City's name.
     * @param countryCode The 3-letter code of the country it belongs to.
     * @param district The district/region within the country.
     * @param population The total population.
     */
    public City(int ID, String name, String countryCode, String district, int population) {
        this.id = ID;
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
    }

    /**
     * Gets the city ID.
     * @return The ID.
     */
    public int getID() {
        return id;
    }

    /**
     * Gets the city name.
     * @return The Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the country code.
     * @return The CountryCode.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Gets the district name.
     * @return The District.
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Gets the city population.
     * @return The Population.
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Provides a string representation of the City object for logging and debugging.
     * @return A formatted string showing all field values.
     */
    @Override
    public String toString() {
        return "City{" +
                "ID=" + id +
                ", Name='" + name + '\'' +
                ", CountryCode='" + countryCode + '\'' +
                ", District='" + district + '\'' +
                ", Population=" + population +
                '}';
    }
}
