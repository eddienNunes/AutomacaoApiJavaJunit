package modulos.produto;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import dataFactory.ProdutDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testes de API Rest do modulo de Produto")
public class ProdutoTest {

    private String token;

    @BeforeEach
    public void beforeEach()
    {
        baseURI="http://165.227.93.41";
        basePath="/lojinha";

        this.token = given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.logarComUsuarioPadrao())
                .when()
                    .post("/v2/login")
                .then()
                     .extract()
                        .path("data.token");
    }

    @Test
    @DisplayName("Verificar se o sistema impede o cadastro de um produto com valor igual a zero")
    public void testValorProdutoIgualZero(){

    //Teste 1: Valor do Produto Igual a Zero

        given()
                .contentType(ContentType.JSON)
                .header("token",this.token)
                .body(ProdutDataFactory.criarProdutoComumComValorIgualA(0.00))
        .when()
                .post("/v2/produtos")

        .then()
                .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);
    }

    @Test
    @DisplayName("Verificar se o sistema impede o cadastro de um produto com valor negativo")
    public void testValorProdutoNegativo()
    {
        //Caso de Teste 2: Valor do Produto Negativo
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutDataFactory.criarProdutoComumComValorIgualA(-10.00))
        .when()
                .post("/v2/produtos")
        .then()
                .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);
    }

    @Test
    @DisplayName("Verificar se o sistema permite o cadastro de um produto com valor válido")
    public void testValorProdutoValido()
    {
        // Caso de Teste 3: Valor do Produto Maior que Zero e Menor que 7.000,00
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutDataFactory.criarProdutoComumComValorIgualA(1000.00))
        .when()
                .post("/v2/produtos")
        .then()
                .assertThat()
                .body("message", equalTo("Produto adicionado com sucesso"))
                .statusCode(201);
    }

    @Test
    @DisplayName("Verificar se o sistema permite o cadastro de um produto com valor no limite superior permitido")
    public void testValorProdutoIgualSeteMil()
    {
        //Caso de Teste 4: Valor do Produto Igual a 7.000,00
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutDataFactory.criarProdutoComumComValorIgualA(7000.00))
        .when()
                .post("/v2/produtos")
        .then()
                .assertThat()
                .body("message", equalTo("Produto adicionado com sucesso"))
                .statusCode(201);
    }

    @Test
    @DisplayName("Verificar se o sistema impede o cadastro de um produto com valor superior ao limite permitido")
    public void testValorProdutoMaiorQueSeteMil(){
       // Caso de Teste 5: Valor do Produto Maior que 7.000,00

        given()
                .contentType(ContentType.JSON)
                .header("token",this.token)
                .body(ProdutDataFactory.criarProdutoComumComValorIgualA(7000.1))
            .when()
                    .post("/v2/produtos")

            .then()
                    .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);

    }
}

