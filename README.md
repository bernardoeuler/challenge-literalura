# Literalura

Catálogo de livros desenvolvido como desafio proposto pela plataforma **Alura**, com o objetivo de treinar o uso do padrão **MVC** (Model-View-Controller) utilizando as tecnologias: **Spring Boot**, **JPA** e **PostgreSQL**.

## 📝 Funcionalidades

- Buscar por livros na API Gutendex  
- Salvar os livros obtidos na base de dados  
- Consultar livros presentes no banco  
- Consultar autores presentes no banco  
- Filtrar autores vivos em um determinado ano  
- Buscar livros por idioma

## 🛠 Tecnologias utilizadas

- Java
- Spring Boot  
- Spring Data JPA
- Hibernate
- PostgreSQL
- Jackson
- Maven

## 🚀 Pré-requisitos

- Java 17+
- PostgreSQL  
- Maven  

## 📥 Como executar o projeto

1. Clone o repositório:  
  ```bash
  git clone https://github.com/bernardoeuler/challenge-literalura.git
  cd challenge-literalura
  ```
2. Configure o banco de dados PostgreSQL no arquivo application.properties ou application.yml.
3. Compile o projeto:
  ```bash
  mvn clean install
  ```
5. Execute a aplicação:
  ```bash
  mvn spring-boot:run
  ```
