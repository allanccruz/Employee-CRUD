# <center> Employee CRUD - Java </center>

###### <center>API de gerenciamento de funcionários</center>

## Tecnologias utilizadas:

- Java 11
- Spring Boot
- Git
- Swagger
- Flyway
- Docker Compose
- MySQL
- JUnit
- Maven

## Como executar a API:

1. Primeiramente certifique-se de ter o Docker Compose instalado em sua máquina para poder utilizar seus
   comandos;

2. Após clonar a aplicação, renomeie o arquivo **example.env** que está no diretório **docker/config** para apenas *
   *.env** e altere os dados da mesma forma como demonstrado a seguir:
   ~~~
    MYSQL_DATABASE=nome-do-seu-banco-de-dados
    MYSQL_USER=seu-usuário
    MYSQL_PASSWORD=sua-senha
    MYSQL_ROOT_PASSWORD=sua-senha-root
   ~~~
3. Renomeie também o arquivo **application-dev-example.yml** que está no diretório **src/main/resources** para apenas *
   *application-dev.yml** e troque as variáveis para os mesmos valores que foram adicionados no arquivo **.env** citado
   acima;
4. Abra o terminal no diretório **docker** e digite o seguinte comando para subir o banco de dados que será utilizado
   pela API:
   ~~~
   docker-compose up -d
   ~~~
5. Agora, abra o terminal dentro da pasta raíz do projeto e digite o seguinte comando para iniciar a aplicação (por
   padrão do Spring Boot, a aplicação irá rodar na porta 8080):
   ~~~
   ./mvnw spring-boot:run 
   ~~~

## Recursos da API:

Abaixo serão apresentados os recursos que a API fornece e seus respectivos endpoints. Contudo, ela foi documentada
utilizando o Swagger e você poderá acessar e testar os endpoints de forma simplificada utilizando o link a seguir:
http://localhost:8080/swagger-ui/index.html#/

Com a aplicação rodando você poderá:

- Criar funcionário;
- Buscar um funcinário pelo seu id;
- Atualizar funcionároio;
- Deletar funcionário.
