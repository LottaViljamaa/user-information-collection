# Henkilötietojärjestelmä - taustapalvelu

Read the readme in English: [![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/LottaViljamaa/user-information-collection-rest/blob/main/README.eng.md)

## Huomio
Älä tallenna järjestelmään omia tai muiden henkilöiden tietoja!
#### 
Tämän sovelluksen kehitystyö on vielä kesken. Alla kuitenkin tiedot nykyisestä sovelluksesta.

### Yleistä
- Yksinkertainen ohjelmisto henkilötietojen tallentamiseksi ilman tietokantayhteyttä
- Sovelluksen backend on toteutettu Java + Spring Boot + Maven
- Sovelluksessa on käytössä Unit5 yksikkötestit
- "UserInformation" -kansiossa on logiikka tietojen tallentamiselle ja validoimiselle.
- "UserInformationService" -luokassa on toteutettu tietojen tallentamisen, hakemisen, muokkaamisen ja poistamisen logiikka.
- "UserInformationController" -luokassa käsitellään HTTP-pyyntöjä ja pyyntejön virhetilanteita

### Ohjeet sovelluksen avaamiselle
1. Kloonaa repositorio
2. Suorita maven clean ja install
3. Käynnistä sovellus

## Swagger ui
- Swagger-käyttöliittymän avulla on mahdollista visualisoida sovelluksen backendin toimintoja

1. Käynnistä sovellus
2. Avaa selaimessa: http://localhost:8090/swagger-ui/index.html#/
3. Testaa sovelluksen toimintoja

Esimerkkirunko käyttäjätietojen lisäämistä varten: 

```JSON
{
  "basicInformation": {
    "firstName": "Matti",
    "surname": "Meikäläinen",
    "personalIdentityCode": "010101",
    "citizenship": "Suomi",
    "gender": "Mies",
    "fullName": "Matti Meikäläinen"
  },
  "contactInformation": {
    "personalIdentityCode": "010101",
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
Kehityskohteita: 
- Koodin jäsentely
- Unit -testien lisääminen HTTP -pyynnöille


