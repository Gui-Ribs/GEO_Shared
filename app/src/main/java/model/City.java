package model;

public class City {

    private String name;
    private String country;
    private String country_code;
    private String region;
    private long latitude;
    private long longitude;

    public City(String name, String country, String country_code,
                String region, long latitude, long longitude) {
        this.name = name;
        this.country = country;
        this.country_code = country_code;
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCountry_code() {
        return country_code;
    }

    public String getRegion() {
        return region;
    }

    public long getLatitude() {
        return latitude;
    }

    public long getLongitude() {
        return longitude;
    }

}
