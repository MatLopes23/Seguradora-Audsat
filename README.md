# Projeto SEGURADORA

### Documentação

REST API que fornece serviços para o cálculo de orçamento de seguro de carros.


### Open API Documentation
```
http://localhost:8080/swagger-ui/index.html
```

### Deploy

Para criar a imagem através do gradle:
```
./gradlew bootBuildImage --imageName=seguradora-api:v1.0
```

Para subir a aplicação pelo Docker:
```
docker run -p 8080:8080 seguradora-api:v1.0
```
