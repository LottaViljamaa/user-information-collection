package user.information.collection.userInformation;

public class UserInformationCollection {

    private UserBasicInformation basicInformation;
    private UserContactInformation contactInformation;

    public UserInformationCollection(UserBasicInformation basicInformation, UserContactInformation contactInformation) {
        this.basicInformation = basicInformation;
        this.contactInformation = contactInformation;
    }

    public UserBasicInformation getBasicInformation() {
        return basicInformation;
    }

    public void setBasicInformation(UserBasicInformation basicInformation) {
        this.basicInformation = basicInformation;
    }

    public UserContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(UserContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "basicInformation=" + basicInformation +
                ", contactInformation=" + contactInformation +
                '}';
    }
}
