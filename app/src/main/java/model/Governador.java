package model;

public class Governador {

    private String name;
    private String policy;
    private String provincia;
    private int age;
    private String image;

    private String nationality;

    public Governador(String name, String policy, String provincia, int age, String image, String nationality) {
        this.name = name;
        this.policy = policy;
        this.provincia = provincia;
        this. age = age;
        this.image = image;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public String getPolicy() {
        return policy;
    }

    public String getProvincia() {
        return provincia;
    }

    public int getAge() {
        return age;
    }

    public String getImage() {
        return image;
    }

    public String getNationality() {
        return nationality;
    }
}
