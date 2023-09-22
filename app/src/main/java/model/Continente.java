package model;

public class Continente {

    private String code;
    private String name;

    private float latitude;
    private float longitude;
    private String bioma;
    private String image;

    public Continente(String code, String name, float latitude, float longitude, String bioma, String image) {
        this.code = code;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.bioma = bioma;
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getImage() {
        return image;
    }

    public String getBioma() {
        return bioma;
    }


}
