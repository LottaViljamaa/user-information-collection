package user.information.collection.userInformation;

public class BasicInformation {

    public static final String ILLEGAL_NAME_FORMAT = "Name can only contain letters and the '-' character.";
    public static final String ILLEGAL_ID_FORMAT = "Invalid personal identity code.";
    public static final String FIELD_EMPTY = "Field can not be empty.";

    private String firstName;
    private String surname;
    private String personalIdentityCode;
    private String citizenship;
    private String gender;

    public BasicInformation(String firstName, String surname, String personalIdentityCode, String citizenship, String gender) {
        setFirstName(firstName);
        setSurname(surname);
        setPersonalIdentityCode(personalIdentityCode);
        setCitizenship(citizenship);
        setGender(gender);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (isNonEmpty(firstName) && isValidName(firstName)) {
            this.firstName = firstName;
        } else if (!isNonEmpty(firstName)) {
            throw new IllegalArgumentException(FIELD_EMPTY);
        } else {
            throw new IllegalArgumentException(ILLEGAL_NAME_FORMAT);
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (isNonEmpty(surname) && isValidName(surname)) {
            this.surname = surname;
        } else if (!isNonEmpty(surname)) {
            throw new IllegalArgumentException(FIELD_EMPTY);
        } else {
            throw new IllegalArgumentException(ILLEGAL_NAME_FORMAT);
        }
    }

    public String getPersonalIdentityCode() {
        return personalIdentityCode;
    }

    public void setPersonalIdentityCode(String personalIdentityCode) {
        if (isNonEmpty(personalIdentityCode) && isValidPersonalIdentityCode(personalIdentityCode)) {
            this.personalIdentityCode = personalIdentityCode;
        } else if (!isNonEmpty(personalIdentityCode)) {
            throw new IllegalArgumentException(FIELD_EMPTY);
        } else {
            throw new IllegalArgumentException(ILLEGAL_ID_FORMAT);
        }
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        if (isNonEmpty(citizenship)) {
            this.citizenship = citizenship;
        } else {
            throw new IllegalArgumentException(FIELD_EMPTY);
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (isNonEmpty(gender)) {
            this.gender = gender;
        } else {
            throw new IllegalArgumentException(FIELD_EMPTY);
        }
    }

    // Tarkistaa, että kenttä ei ole tyhjä
    private boolean isNonEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    // Tarkistaa, että nimi sisältää vain kirjaimia tai '-' merkin
    private boolean isValidName(String name) {
        return name != null && name.matches("[a-zA-ZåäöÅÄÖ-]+");
    }

    // Tarkistaa, että hetu sisältää: 6 numeroa + "-" + 4 numeroa
    private boolean isValidPersonalIdentityCode(String code) {
        if (code == null) {
            return false;
        }
        return code.matches("\\d{6}-\\d{4}");
    }

    @Override
    public String toString() {
        return "Full Name: " + firstName + " " + surname +
                ", Personal Identity Code: " + personalIdentityCode +
                ", Citizenship: " + citizenship +
                ", Gender: " + gender;
    }
}
