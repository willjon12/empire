/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.*;
import java.math.BigDecimal;
import java.util.List;
import model.*;

/**
 *
 * @author will
 */
public class ItemControl {

    public ItemDAO produtoDAO;

    public ItemControl() {
        produtoDAO = new ItemDAO();
    }

    public boolean salvar(String descricao, String datacad, Double custo, 
            Double venda, Double lucro,String UN) {
        boolean retorno;

        Item prod = new Item();
        prod.setDescricao(descricao);
        prod.setDatacad(datacad);
        prod.setCusto(custo);
        prod.setVenda(venda);
        prod.setLucro(lucro);
        prod.setUN(UN);
        
        

        retorno = produtoDAO.salvar(prod);

        return retorno;
    }
        public boolean salvar2(Item i) {
        boolean retorno = produtoDAO.salvar(i);

        return retorno;
    }

    public List<Item> listar() {
        return produtoDAO.listar();

    }

    public List<Item> listar2(String produto) {
        return produtoDAO.listar2(produto);

    }

    public Item getProdutoById(int codprod) {
        return produtoDAO.getProdutoById(codprod);

    }


    public boolean alterar(int cod,String descricao, String datacad, Double custo, 
            Double venda, Double lucro,String UN) {
        boolean retorno;

        Item item = new Item();
        item.setDescricao(descricao);
        item.setDatacad(datacad);
        item.setCusto(custo);
        item.setVenda(venda);
        item.setLucro(lucro);
        item.setUN(UN);
        item.setCodproduto(cod);
        
        

        retorno = produtoDAO.alterar(item);

        return retorno;
    }

    public boolean excluir(int codigo) {

       boolean retorno = produtoDAO.excluir(codigo);
        return retorno;
    }

}
