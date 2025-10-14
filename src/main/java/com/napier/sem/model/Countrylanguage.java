package com.napier.sem.model;
/**
 * Represents a CountryLanguage entity, and used as a data model that captures information
 * about a language spoken in a specific country.
 */
public class Countrylanguage {

    //3-letter code referencing the Country table
    public String countryCode;

    //The name of the language spoken in the country.
    public String language;

    //Indicates whether the language is official in the country ('T' for True, 'F' for False).
    public String isOfficial;

    //The percentage of the country's population that speaks this language.
    public double percentage;

    /**
     * Full constructor for initializing all fields.
     * @param countryCode The country's code.
     * @param language The name of the language.
     * @param isOfficial Flag indicating if the language is official ('T'/'F').
     * @param percentage The speaker percentage.
     */
    public Countrylanguage(String countryCode, String language, String isOfficial, double percentage) {
        this.countryCode = countryCode;
        this.language = language;
        this.isOfficial = isOfficial;
        this.percentage = percentage;
    }

    /**
     * Default no-argument constructor.
     */
    public Countrylanguage() {
    }

    /**
     * Gets the country code.
     * @return The 3-letter country code.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Gets the language name.
     * @return The language name.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Gets the official language flag ('T' or 'F').
     *  @return 'T' if official, 'F' otherwise.
     * */
    public String getIsOfficial() {
        return isOfficial;
    }

    /**
     * Gets the speaker percentage.
     * @return The percentage of the population speaking this language.
     */
    public double getPercentage() {
        return percentage;
    }

    /**
     * Provides a string representation of the Countrylanguage object for logging and debugging.
     * @return A formatted string showing all field values.
     */
    @Override
    public String toString() {
        return "Countrylanguage{" +
                "countryCode='" + countryCode + '\'' +
                ", language='" + language + '\'' +
                ", isOfficial='" + isOfficial + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}
