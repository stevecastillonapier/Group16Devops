package com.napier.sem.model;

public class Country {

    public String Code;
    public String Name;
    public String Continent;
    public String Region;
    public double SurfaceArea;
    public Integer IndepYear;
    public int Population;
    public Double LifeExpectancy;
    public Double GNP;
    public Double GNPOld;
    public String LocalName;
    public String GovernmentForm;
    public String HeadOfState;
    public Integer Capital;
    public String Code2;
    public String CapitalName;

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
