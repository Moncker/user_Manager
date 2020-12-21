package cloudAppi.userManager.dto;

import cloudAppi.userManager.modelo.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserDto {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private Integer id;
    private String name;
    private String email;

    private String birthDate;
    private AddressDto address;

    public UserDto(){

    }

    public UserDto(Integer id, String name, String email, String birthDate, AddressDto address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
    }

    public LocalDateTime getSubmissionDateConverted(String timezone) throws ParseException {
        return (LocalDateTime) formatter.parse(birthDate);
    }

    public void setSubmissionDate(LocalDateTime birthDate) {
        this.birthDate = formatter.format(birthDate);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }
}
