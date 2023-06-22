package model;

public class Country {

    private String code;
    private String name;
    private String flagImageUri;
    private int numRegions;
    private String wikiDataId;

    public Country(String code, String name, String flagImageUri, int numRegions, String wikiDataId) {
        this.code = code;
        this.name = name;
        this.flagImageUri = flagImageUri;
        this.wikiDataId = wikiDataId;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getFlagImageUri() {
        return flagImageUri;
    }

    public int getNumRegions() {
        return numRegions;
    }

    public String getWikiDataId() {
        return wikiDataId;
    }

}
