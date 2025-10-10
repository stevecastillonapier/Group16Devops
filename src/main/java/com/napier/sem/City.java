package com.napier.sem;

public class City {
    /**
     * City ID (Primary Key)
     */
    public int ID;

    /**
     * Name of the city
     */
    public String Name;

    /**
     * 3-letter country code that references the country table
     */
    public String CountryCode;

    /**
     * District or region the city belongs to
     */
    public String District;

    /**
     * Population of the city
     */
    public int Population;

    public City(int ID, String name, String countryCode, String district, int population) {
        this.ID = ID;
        Name = name;
        CountryCode = countryCode;
        District = district;
        Population = population;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public String getDistrict() {
        return District;
    }

    public int getPopulation() {
        return Population;
    }

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
