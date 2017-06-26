/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import javax.swing.JTextField;

/**
 *
 * @author Will
 */
public class Os {

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getUseros() {
        return useros;
    }

    public void setUseros(String useros) {
        this.useros = useros;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCodclifor() {
        return codclifor;
    }

    public void setCodclifor(int codclifor) {
        this.codclifor = codclifor;
    }

    public String getClifor() {
        return clifor;
    }

    public void setClifor(String clifor) {
        this.clifor = clifor;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    int cod;

    public String getDta() {
        return dta;
    }

    public void setDta(String dta) {
        this.dta = dta;
    }

    public String getDtf() {
        return dtf;
    }

    public void setDtf(String dtf) {
        this.dtf = dtf;
    }

    public String getDtg() {
        return dtg;
    }

    public void setDtg(String dtg) {
        this.dtg = dtg;
    }
    String dta;
    String dtf;
    String dtg;
    String useros;
    String descricao;
    String status;
    int codclifor;
    String clifor;
    Double desconto;
    Double subtotal;
    Double total;
    private String obs;

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

}
