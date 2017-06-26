/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;



/**
 *
 * @author Will
 */
public class Item {

    public int getCodproduto() {
        return codproduto;
    }

    public void setCodproduto(int codproduto) {
        this.codproduto = codproduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDatacad() {
        return datacad;
    }

    public void setDatacad(String datacad) {
        this.datacad = datacad;
    }


    int codproduto;
    String descricao;
    String datacad;

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

    public Double getVenda() {
        return venda;
    }

    public void setVenda(Double venda) {
        this.venda = venda;
    }

    public Double getLucro() {
        return lucro;
    }

    public void setLucro(Double lucro) {
        this.lucro = lucro;
    }

    public Double getTotalitem() {
        return totalitem;
    }

    public void setTotalitem(Double totalitem) {
        this.totalitem = totalitem;
    }

    public Double getQtd() {
        return qtd;
    }

    public void setQtd(Double qtd) {
        this.qtd = qtd;
    }


    Double custo;
    Double venda;
    Double lucro;
    Double totalitem;
    Double qtd;

    public int getCodos() {
        return codos;
    }

    public void setCodos(int codos) {
        this.codos = codos;
    }
    int codos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    int id;

    public boolean isFechado() {
        return fechado;
    }

    public void setFechado(boolean fechado) {
        this.fechado = fechado;
    }
    boolean fechado;
    


    public String getUN() {
        return UN;
    }

    public void setUN(String UN) {
        this.UN = UN;
    }
    String UN;

    public int getSi() {
        return si;
    }

    public void setSi(int si) {
        this.si = si;
    }
    int si;
            
    
}
