package model;

public class Country {

    private String code;
    private String name;
    private String flagImageUri;
    private int numRegions;
    private String wikiDataId;
    private String callingCode;
    private String Capital;

    public Country(String code, String name, String flagImageUri, int numRegions, String wikiDataId, String callingCode, String Capital) {
        this.code = code;
        this.name = name;
        this.flagImageUri = flagImageUri;
        this.wikiDataId = wikiDataId;
        this.callingCode = callingCode;
        this.Capital = Capital;
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

    public String getCallingCode() {
        return callingCode;
    }

    public String getCapital() {
        return Capital;
    }

}
