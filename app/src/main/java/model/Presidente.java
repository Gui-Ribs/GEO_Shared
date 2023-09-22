package model;

public class Presidente {
    private String name;
    private String country;
    private String policy;
    private int age;
    private String nationality;
    private String image;


    public Presidente(String name, String country, String policy, int age, String nationality, String image) {
        this.name = name;
        this.country = country;
        this.policy = policy;
        this.age = age;
        this.nationality = nationality;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getPolicy() {
        return policy;
    }

    public int getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
    }

    public String getImage() {
        return image;
    }

}
