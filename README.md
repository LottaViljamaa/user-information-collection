# Henkilötietojärjestelmä
### Yleistä
- Yksinkertainen ohjelmisto henkilötietojen tallentamiseksi ilman tietokantayhteyttä
- Sovelluksen backend on toteutettu Java + Spring Boot + Maven
- Sovelluksessa on käytössä Unit5 yksikkötestit
- "UserInformation" -kansiossa on logiikka tietojen tallentamiselle ja validoimiselle.
- "UserInformationService" -luokassa on toteutettu tietojen tallentamisen, hakemisen, muokkaamisen ja poistamisen logiikka.
- "UserInformationController" -luokassa käsitellään HTTP-pyyntöjä niiden virhetilanteita

### Ohjeet sovelluksen avaamiselle
1. Kloonaa repositorio: git clone https://github.com/LottaViljamaa/user-information-collection.git
2. Suorita maven clean ja install
3. Käynnistä sovellus

## Swagger ui
- Swagger-käyttöliittymän avulla on mahdollista visualisoida sovelluksen backendin toimintoja

1. Käynnistä sovellus
2. Avaa selaimessa: http://localhost:8090/swagger-ui/index.html#/
3. Testaa sovelluksen toimintoja

Esimerkkirunko käyttäjätietojen lisäämistä varten: 

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

![image](https://github.com/user-attachments/assets/710d24af-bb42-4184-876c-7269139be1e1)


