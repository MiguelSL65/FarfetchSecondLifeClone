package com.luxclusifmiguel.challenge.backend.dto;

import com.luxclusifmiguel.challenge.backend.model.Customer;

import javax.validation.constraints.*;

/**
 * The {@link com.luxclusifmiguel.challenge.backend.model.Customer} data transfer object
 */
public class CustomerDto {

    private Integer id;

    @NotNull(message = "First name is mandatory!")
    @NotBlank(message = "First name is mandatory!")
    @Size(min = 3, max = 64)
    private String firstName;

    @NotNull(message = "Last name is mandatory!")
    @NotBlank(message = "Last name is mandatory!")
    @Size(min = 3, max = 64)
    private String lastName;

    @Email
    @NotBlank(message = "Email is mandatory!")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]*$", message = "Phone number contains invalid characters")
    @Size(min = 9, max = 16)
    private String phone;

    @NotNull(message = "Country is mandatory!")
    @NotBlank(message = "Country is mandatory!")
    @Size(min = 3, max = 64)
    private String country;

    /**
     * Gets the id of the customer DTO
     *
     * @return the customer DTO id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id of the customer DTO
     *
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *  Gets the first name of the customer DTO
     *
     * @return customer's DTO first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *  Sets the customer DTO first name
     *
     * @param firstName the name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *  Gets the customer DTO last name
     *
     * @return customer's DTO last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *  Sets the customer DTO last name
     *
     * @param lastName the name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *  Gets the customer DTO email
     *
     * @return customer DTO email
     */
    public String getEmail() {
        return email;
    }

    /**
     *  Sets the customer DTO email
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *  Get the customer DTO phone number
     *
     * @return customer DTO phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     *  Sets the customer DTO phone number
     *
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *  Gets the customer DTO country
     *
     * @return customer DTO country
     */
    public String getCountry() {
        return country;
    }

    /**
     *  Sets the customer DTO country
     *
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
