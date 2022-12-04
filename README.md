# contas
## Configurar no application.properties(nome, profile:
````
spring.application.name=contasconfigserver
spring.profiles.active=prod
spring.config.import=optional:configserver:http://localhost:8071/

management.endpoints.web.exposure.include=*
````
## Criar uma classe ContasServiceConfig, com as devidas anotações, para buscar o nome, o prefixo(prefixos que ficam nas properties da pasta config, ex: accounts, cards e etc) e o profile do microserviço:
````
@Configuration
@ConfigurationProperties(prefix = "accountsconfigserver")
````
## Prestar a atenção para que a leitura do config server(vide classe AccountsServiceConfig) funcione:
> a. Olhar os nomes das properties, se estão no mesmo nome da aplicação.

> b. Verificar os prefixos que estão dentro das properties. Ex: accounts.algumacoisa

> c. Não se preocupar com o "-" de build-version. A Spring o transforma.

> d. Não se preocupar com os sufixos: hostname e etc. Os valores são mapeados assim mesmo.

> e. A lista de arrays deve ter o mesmo nome que foi colocado na properties correspondente.

## Criar a model properties

## Testando no Postman:
````
http://localhost:8080/account/properties
````
### Resposta:
````
{
    "msg": "Bem vindos(as) ao Tuyo Accounts Prod application",
    "buildVersion": "1",
    "mailDetails": {
        "hostName": "prod-accountsconfigserver@tuyosistema.com",
        "port": "9010",
        "from": "prod-accountsconfigserver@tuyosistema.com",
        "subject": "Detalhes da Conta de Tuyo Bank Prod Environment"
    },
    "activeBranches": [
        "Hyderabad",
        "Paris",
        "NewYork"
    ]
}
````

## Necessário criar uma pasta docker-compose, com arquivos docker-compose.yml para cada profile/ambiente: default, dev e prod.
### Isso permite correr todas as docker image com o ambiente/profile que seja necessário através do docker-compose.yml e subir o docker-compose próximo.
### Dá um controle total externo das imagens docker, onde é possível passar a atual que será necessária o seu uso.
### docker-compose.yml( Entendendo o que fica nos Services):
> a. ports: O Docker sempre dará preferência ao que está no docker-compose.yml. Inclusive as portas. Caso contrário, ele vai procurar as propriedades mencionadas dentro da docker image.
> 
> b. networks: esta será compartilhada por todos os serviços. E todos os containers se comunicarão através dela.
> Devido a isso, o fato de todos os containers correrem em ambientes isolados, os networks farão a ponte entre esses.
> c. depends on: dependência do serviço que deverá ser iniciado primeiro. Mas assim que ele ligar, os outros serviços
> também subirão. Para um delay disso verificar abaixo.
> d. deploy: cria um delay para o que estiver no depends on. Por exemplo, se um microserviço 'dependente' não iniciar,
> a razão pode ser uma falha ao se conectar no configserver. E se o 'deployment(implantação)' do serviço falhar,
> pode-se definir uma política de reinício todas as vezes que isso acontecer.
> e. enviroment: colocar o profile/ambiente que objetiva-se conectar ao configserver. Em 'SPRING_CONFIG_IMPORT',
> tem que colocar o mesmo nome que é colocado abaixo de services:
````
services:

  configserver:
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