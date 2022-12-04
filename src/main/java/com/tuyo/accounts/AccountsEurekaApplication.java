package com.tuyo.accounts;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.domain.*;
import org.springframework.cloud.context.config.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.*;
/* 1. @ComponentScan: scaneia todos os beans dentro dos outros pacotes.
* Como podemos ver, ele scaneia o package controller onde fica definido o Rest Controller.
* Enquanto a aplicação está subindo, ela está indo e checando todos os packages e identificando todas as anotações e
* configurações que estão dentro desses, e baseados neles tentará criar os serviços REST.
*  2. @EnableJpaRepositories: ao obter êxito na ação acima, ele precisa também interagir com o database. Por isso
* essa anotação é utilizada para que ela escaneie todas as classes/interfaces dentro do package repository.
*  3. @EntityScan: sendo o JPA uma framework ORM, e esse sendo usado, é necessário ter o package model ou entity,
* onde ficarão as entidades(são as classes responsáveis pelas tabelas correspondentes).
* E esta anotação permitirá o escaneamento .
* @RefreshScope: Invoca o reload às propriedades(properties) que mudaram sem a necessidade de reiniciar o microserviço.
* E é preciso inserir também em application.properties: management.endpoints.web.exposure.include=*
*  */

@SpringBootApplication
@RefreshScope
@ComponentScans({ @ComponentScan("com.tuyo.accounts.controller") })
@EnableJpaRepositories("com.tuyo.accounts.repository")
@EntityScan("com.tuyo.accounts.model")
public class AccountsEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsEurekaApplication.class, args);
	}
}
