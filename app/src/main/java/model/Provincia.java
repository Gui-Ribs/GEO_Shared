package model;

public class Provincia {

    private String code;
    private String name;
    private String country;
    private float longitude;
    private float latitude;
    private int population;
    private String bioma;
    private String image;
    private String ddd;

    public Provincia(String code, String name, String country, float longitude, float latitude,
                     int population, String bioma, String image, String ddd) {
        this.code = code;
        this.name = name;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
        this. population = population;
        this.bioma = bioma;
        this.image = image;
        this.ddd = ddd;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public int getPopulation() {
        return population;
    }

    public String getBioma() {
        return bioma;
    }

    public String getImage() {
        return image;
    }

    public String getDdd() {
        return ddd;
    }
}
