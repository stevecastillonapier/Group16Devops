package com.napier.sem.model;
/**
 * Represents a City entity, serving as a Data Transfer Object (DTO) and Model
 * to hold information about a City from the database.
 */
public class City {

    //City ID (Primary Key)
    public int ID;

    //Name of the city
    public String Name;

    //3-letter country code that references the country table
    public String CountryCode;

    //District or region the city belongs to
    public String District;

    //Population of the city
    public int Population;

    /**
     * Constructor for the City model.
     * @param ID City's unique identifier.
     * @param name City's name.
     * @param countryCode The 3-letter code of the country it belongs to.
     * @param district The district/region within the country.
     * @param population The total population.
     */
    public City(int ID, String name, String countryCode, String district, int population) {
        this.ID = ID;
        Name = name;
        CountryCode = countryCode;
        District = district;
        Population = population;
    }

    /**
     * Gets the city ID.
     * @return The ID.
     */
    public int getID() {
        return ID;
    }

    /**
     * Gets the city name.
     * @return The Name.
     */
    public String getName() {
        return Name;
    }

    /**
     * Gets the country code.
     * @return The CountryCode.
     */
    public String getCountryCode() {
        return CountryCode;
    }

    /**
     * Gets the district name.
     * @return The District.
     */
    public String getDistrict() {
        return District;
    }

    /**
     * Gets the city population.
     * @return The Population.
     */
    public int getPopulation() {
        return Population;
    }

    /**
     * Provides a string representation of the City object for logging and debugging.
     * @return A formatted string showing all field values.
     */
    @Override
    public String toString() {
        return "City{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", CountryCode='" + CountryCode + '\'' +
                ", District='" + District + '\'' +
                ", Population=" + Population +
                '}';
    }
}
