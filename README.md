# Phenolic

## Configure gradle.properties

```
create a file : ~/.gradle/gradle.properties
insert a line : phenolic_db_url = jdbc:h2:file:<YOUR DB PATH>
```

## SWAGGER

To use SWAGGER API documentation:

```
git clone https://gitlab.estig.ipb.pt/DTS/phenolic.git
git checkout develop
run phenolic_main/src/main/java/pt/ipb/phenolic/PhenolicApplication.java
browse : http://localhost:5000/api/v1/swagger-ui.html
```