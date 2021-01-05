# Phenolic

## SWAGGER

To use SWAGGER API documentation:

```
git clone https://gitlab.estig.ipb.pt/DTS/phenolic.git
git checkout develop
edit phenolic_main/src/main/resources/application-development.properties (change spring.datasource.url = <YOUR URL>)
run phenolic_main/src/main/java/pt/ipb/phenolic/PhenolicApplication.java
browse : http://localhost:5000/api/v1/swagger-ui.html
```