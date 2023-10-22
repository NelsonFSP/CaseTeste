# Case teste - Itaú

## API para case de testes para vaga de Analista de engenharia PL.


## Ferramentas utilizadas

- Java 11+
- Maven 3+
- Banco de dados inMemory (H2)

## Para buildar a aplicação

```
mvn clean install
```

## Run da aplicação
A aplicação rodará no endereço http://localhost:8080
```
mvn spring-boot:run
```

## Para configurar o banco de dados é necessário acessar o console do H2
O console pode ser acessado por meio do endereço http://localhost:8080/h2

Para isso utilizaremos o user e password configurados no arquivo application.properties

```
#Source H2
spring.datasource.username=sa
spring.datasource.password=123456
```

## Dentro do console do H2 no campo disponível para executar queries SQL execute as queries abaixo

```
create table tb_cliente(
id Integer,
nome varchar,
numero_conta varchar,
saldo double)

create table tb_transacao(
id Integer,
num_conta_origem varchar,
num_conta_destino varchar,
status_transacao varchar,
valor double,
data_transacao varchar)
```

## Para realizar os testes integrados da aplicação utilize a collection insomnia presente na raiz do projeto
O arquivo está presente no caminho testes_integrados/Testes_integrados

## Débitos tecnicos
- Implementação de tratamento de exceções
- integração com banco de dados mais robusto
- Melhoria da estrutura de banco de dados
- Reforçar as validações de tipagem de objetos