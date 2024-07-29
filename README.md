
# Automação de API com JUnit

Este repositório contém a automação de testes de API REST para um software denominado Lojinha. Os subtópicos abaixo descrevem algumas das decisões tomadas na estruturação do projeto.

## Tecnologias Utilizadas

-   Java - Jdk 22
    (https://www.oracle.com/br/java/technologies/downloads/#jdk22-windows)
-   JUnit
    https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api/5.11.0-M2
-   RestAssured
- https://mvnrepository.com/artifact/io.rest-assured/rest-assured/5.3.2
-   Maven
    https://maven.apache.org/download.cgi

## Testes automatizados
### Caso de Teste 1: Valor do Produto Igual a Zero

**Descrição:** Verificar se o sistema impede o cadastro de um produto com valor igual a zero.

- **Dado** que o valor do produto é igual a R$ 0,00
- **Quando** tento cadastrar o produto
- **Então** o sistema deve rejeitar o cadastro e exibir uma mensagem de erro indicando que o valor do produto deve ser maior que zero.

### Caso de Teste 2: Valor do Produto Negativo

**Descrição:** Verificar se o sistema impede o cadastro de um produto com valor negativo.

- **Dado** que o valor do produto é igual a R$ -10,00
- **Quando** tento cadastrar o produto
- **Então** o sistema deve rejeitar o cadastro e exibir uma mensagem de erro indicando que o valor do produto deve ser maior que zero.

### Caso de Teste 3: Valor do Produto Maior que Zero e Menor que 7.000,00

**Descrição:** Verificar se o sistema permite o cadastro de um produto com valor válido.

- **Dado** que o valor do produto é igual a R$ 1.000,00
- **Quando** tento cadastrar o produto
- **Então** o sistema deve aceitar o cadastro e confirmar que o produto foi cadastrado com sucesso.

### Caso de Teste 4: Valor do Produto Igual a 7.000,00

**Descrição:** Verificar se o sistema permite o cadastro de um produto com valor no limite superior permitido.

- **Dado** que o valor do produto é igual a R$ 7.000,00
- **Quando** tento cadastrar o produto
- **Então** o sistema deve aceitar o cadastro e confirmar que o produto foi cadastrado com sucesso.

### Caso de Teste 5: Valor do Produto Maior que 7.000,00

**Descrição:** Verificar se o sistema impede o cadastro de um produto com valor superior ao limite permitido.

- **Dado** que o valor do produto é igual a R$ 7.001,00
- **Quando** tento cadastrar o produto
- **Então** o sistema deve rejeitar o cadastro e exibir uma mensagem de erro indicando que o valor do produto não deve ultrapassar R$ 7.000,00.