package cloudAppi.userManager.dto;

import javax.persistence.Column;

public class AddressDto {

    private Integer id;
    private String street;
    private String state;

    private String city;
    private String country;
    private String zip;

    public AddressDto(){

    }

    public AddressDto(Integer id, String street, String state, String city, String country, String zip) {
        this.id = id;
        this.street = street;
        this.state = state;
        this.city = city;
        this.country = country;
        this.zip = zip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
