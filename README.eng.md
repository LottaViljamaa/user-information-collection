# User Information Application 

Open the readme in Finnish language: [![fin](https://img.shields.io/badge/lang-fin-red.svg)](https://github.com/LottaViljamaa/user-information-collection-rest/blob/main/README.md)

## Caution
Do not store your own or others' personal information in this application!
#### 
This application is still under development.
### General information
- This is an simple application to save users informations without API
- The backend of the application is developed with Java + Spring Boot + Maven + Unit5 tests
- The "UserInformation" calss handles the logic for data storage and validation
- The "UserInformationService" class handles logic for storing, retrieving, updating, and deleting data
- The "UserInformationController" class manages HTTP requests and handles errors in requests

### Instructions to Launch the Application
1. Clone the repository
2. Run Maven clean and install
3. Start the application
   
## Swagger ui

- The Swagger interface allows you to visualize backend operations of the application
- 
1. Start the application
2. Open in a browser: http://localhost:8090/swagger-ui/index.html#/
3. Test the application's functions

Example structure for adding user information: 

```JSON
{
  "basicInformation": {
    "firstName": "Matti",
    "surname": "Meikäläinen",
    "personalIdentityCode": "010101-1234",
    "citizenship": "Suomi",
    "gender": "Mies",
    "fullName": "Matti Meikäläinen"
  },
  "contactInformation": {
    "personalIdentityCode": "010101-1234",
    "email": "matti.meikalainen@esimerkki.com",
    "phoneNumber": "040-123456",
    "streetAddress": "Esimerkkikatu",
    "city": "Esimerkki",
    "postalCode": "00000"
  }
}
```
![image](https://github.com/user-attachments/assets/1f4ca9d2-9f97-4c27-bc3e-c9d8fa7ad0a5)

####
Areas for Development:
- Code structuring
- Adding unit tests for HTTP requests


