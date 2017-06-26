/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.*;
import java.util.List;
import model.Item;
import model.Os;

/**
 *
 * @author Will
 */
public class OsControl {

    private OsDAO osdao;

    public OsControl() {
        osdao = new OsDAO();
    }

    public boolean salvar(Os os) {
        return osdao.salvar(os);

    }

    public boolean alterar(Os os) {
        return osdao.alterarOs(os);

    }

    public boolean salvaritensos(Item i) {
        return osdao.salvaritemos(i);

    }

    public boolean alteraritensos(Item i) {
        return osdao.alterarIos(i);

    }

    public List<Os> listar() {
        return osdao.listar();
    }

    public List<Item> listarios(int codos) {
        return osdao.listarios(codos);
    }

    public List<Os> listaospesq(String descricao, int codpessoa, String status, String dtai, String dtaf, String dtfi, String dtff, int codos) {
        return osdao.pesqos(descricao, codpessoa, status, dtai, dtaf, dtfi, dtff, codos);
    }
    
    public boolean removeritensos(int idios) {
        return osdao.excluir(idios);

    }

}
