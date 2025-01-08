package tn.home.student.dto.response;

public class StudentResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long addressId;

    public StudentResponse(Long id, String firstName, String lastName, String email, Long addressId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressId = addressId;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Long getAddressId() {
        return addressId;
    }
}
