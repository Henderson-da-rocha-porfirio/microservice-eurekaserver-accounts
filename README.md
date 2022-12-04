# eureka accounts

## Para a construção do projeto, verificar como foi feito o configserver accounts.

## Construir a imagem:
````
mvn spring-boot:build-image
````

## Para levantar os serviços(precisa ser executado de dentro da pasta docker-compose:
````
docker compose up
````

## Para usar com o Serviço de Descoberta Eureka Server:  
> 1. Pom:

> a. netflix: para a descoberta do serviço   
````
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
		</dependency>
````
> b. openfeing: para estabelecer um balanceamento do lado do client
````
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-openfeign</artifactId>
		</dependency>
````
> 2. application.properties: ver as novas propriedades adicionadas

> 3. Verificar a descoberta do serviço em: http://localhost:8070/
