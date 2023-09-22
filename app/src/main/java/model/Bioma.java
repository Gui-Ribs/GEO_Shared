package model;

public class Bioma {
    private String name;
    private String clima;
    private String image;
    private String description;

    public Bioma(String name, String clima, String image, String description) {
        this.name = name;
        this.clima = clima;
        this.image = image;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getClima() {
        return clima;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}
