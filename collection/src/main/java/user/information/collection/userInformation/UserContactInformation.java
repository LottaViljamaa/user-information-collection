package user.information.collection.userInformation;

public class UserContactInformation {

    public static final String ILLEGAL_EMAIL_FORMAT = "Invalid email address.";
    public static final String ILLEGAL_PHONE_NUMBER = "Invalid phone number.";
    public static final String ILLEGAL_POSTAL_CODE = "Invalid postal code.";
    public static final String FIELD_EMPTY = "Field can not be empty.";

    private String personalIdentityCode;
    private String email;
    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String postalCode;

    public UserContactInformation(String personalIdentityCode, String email, String phoneNumber, String streetAddress, String city, String postalCode) {
        setPersonalIdentityCode(personalIdentityCode);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setStreetAddress(streetAddress);
        setCity(city);
        setPostalCode(postalCode);
    }

    public String getPersonalIdentityCode() {
        return personalIdentityCode;
    }

    public void setPersonalIdentityCode(String personalIdentityCode) {
        if (isNonEmpty(personalIdentityCode)) {
            this.personalIdentityCode = personalIdentityCode;
        } else {
            throw new IllegalArgumentException(FIELD_EMPTY);
        }
    }

    public String getEmail() {
        return email;
    }

    //Validointi sähköpostiosoitteelle: Täytyy sisältää "@"-merkki ja sen jälkeen "."-merkki
    public void setEmail(String email) {
        if (isNonEmpty(email)) {
            if (!email.contains("@") || email.indexOf('.', email.indexOf('@')) == -1) {
                throw new IllegalArgumentException(ILLEGAL_EMAIL_FORMAT);
            }
            this.email = email;
        } else {
            throw new IllegalArgumentException(FIELD_EMPTY);
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (isNonEmpty(phoneNumber)) {
            if (phoneNumber.length() > 20 || !isValidPhoneNumber(phoneNumber)) {
                throw new IllegalArgumentException(ILLEGAL_PHONE_NUMBER);
            }
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException(FIELD_EMPTY);
        }
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        if (isNonEmpty(streetAddress)) {
            this.streetAddress = streetAddress;
        } else {
            throw new IllegalArgumentException(FIELD_EMPTY);
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (isNonEmpty(city)) {
            this.city = city;
        } else {
            throw new IllegalArgumentException(FIELD_EMPTY);
        }
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        if (isNonEmpty(postalCode)) {
            if (postalCode.length() != 5 || !isAllDigits(postalCode)) {
                throw new IllegalArgumentException(ILLEGAL_POSTAL_CODE);
            }
            this.postalCode = postalCode;
        } else {
            throw new IllegalArgumentException(FIELD_EMPTY);
        }
    }

    // Tarkistaa, että kenttä ei ole tyhjä
    private boolean isNonEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    // Puhelinnumeron validointi: hyväksytään vain numerot, '+', ja '-' -merkit
    private boolean isValidPhoneNumber(String phoneNumber) {
        for (char c : phoneNumber.toCharArray()) {
            if (!Character.isDigit(c) && c != '+' && c != '-') {
                return false;
            }
        }
        return true;
    }

    private boolean isAllDigits(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserContactInformation{" +
                "personalIdentityCode='" + personalIdentityCode + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
