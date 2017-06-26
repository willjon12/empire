/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.OsDAO;
import java.util.List;
import dao.TituloDAO;
import model.Item;
import model.Os;
import model.Titulo;

/**
 *
 * @author Will
 */
public class TituloControl {

    private TituloDAO titulodao;

    public TituloControl() {
        titulodao = new TituloDAO();
    }

    public boolean salvar(Titulo t) {
        return titulodao.salvar(t);

    }

    public List<Titulo> listar(int codp) {
        return titulodao.listar(codp);
    }

    public List<Titulo> listartos(int codp,int codos) {
        return titulodao.listartos(codp,codos);
    }
     public List<Titulo> listatspesq(String tipo, int codpessoa, int baixado, String dtei, String dtef, 
            String dtvi, String dtvf, String descobs,String dtbi,String dtbf) {
        return titulodao.pesqts(tipo, codpessoa, baixado, dtei, dtef, dtvi, dtvf, descobs,dtbi,dtbf);
    }

    public boolean removertitulo(int doc, int idclifor) {
        return titulodao.excluir(doc,idclifor);

    }

    public boolean alterar(Titulo titulo) {
        return titulodao.alterar(titulo);

    }

}
