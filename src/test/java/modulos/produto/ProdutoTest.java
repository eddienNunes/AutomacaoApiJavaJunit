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


        //obter o token do usuario
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
    @DisplayName("Validar o que o valor do produto igual a 0.00 não seja permitido")
    public void TestValidarLimiteZeradoProibidosValorProduto(){

        //Inserir um produto com valor 0,00 r validar a mensagem de erro
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
    @DisplayName("Validar o que o valor do produto maior que 7.000,00 não seja permitido")
    public void TestValidarLimiteMaiorQueSeteMil(){
        // configurandoos dados da api
               //Inserir um produto com valor 0,00 r validar a mensagem de erro
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

