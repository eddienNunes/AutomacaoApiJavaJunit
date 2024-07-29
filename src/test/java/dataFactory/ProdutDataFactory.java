package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutDataFactory {
    public static ProdutoPojo criarProdutoComumComValorIgualA(double valor)
    {
        ProdutoPojo produto = new ProdutoPojo();
        produto.setProdutoNome("Play 10");
        produto.setProdutoValor(0.00);
        List<String> corLista = new ArrayList<>();
        corLista.add("Preto");
        corLista.add("Branco");
        produto.setProdutoCores(corLista);
        List<ComponentePojo> componentes = new ArrayList<>();
        ComponentePojo componentepj = new ComponentePojo();
        componentepj.setComponenteNome("Controle");
        componentepj.setComponenteQuantidade(2);
        componentes.add(componentepj);
        produto.setComponentes(componentes);

        return produto;
    }

}
