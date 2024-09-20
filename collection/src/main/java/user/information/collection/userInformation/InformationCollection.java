package user.information.collection.userInformation;

public class InformationCollection {

    private BasicInformation basicInformation;
    private ContactInformation contactInformation;

    public InformationCollection(BasicInformation basicInformation, ContactInformation contactInformation) {
        this.basicInformation = basicInformation;
        this.contactInformation = contactInformation;
    }

    public BasicInformation getBasicInformation() {
        return basicInformation;
    }

    public void setBasicInformation(BasicInformation basicInformation) {
        this.basicInformation = basicInformation;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
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
