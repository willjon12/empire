/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.PessoaDAO;
import model.Pessoa;
import java.util.List;

/**
 *
 * @author will
 */
public class PessoaControl {

    private PessoaDAO pessoaDAO;

//    private Usuario usuario;
//    private Produto produto;
    public PessoaControl() {
        pessoaDAO = new PessoaDAO();
    }

    public boolean salvar(Boolean pfisica, String nome, String CPF, String celular, String nascimento,
            String endereco, String apefan, String sexo, String enumero, String bairro, String complemento,
            String CEP, String cidade, String email, String UF, String datacad, String rgie,
            String emissorrg, String telefone, String OBS) {
        boolean retorno;

        Pessoa p = new Pessoa();

        p.setPfisica(pfisica);
        p.setNome(nome);
        p.setCPF(CPF);
        p.setCelular(celular);
        p.setNascimento(nascimento);
        p.setEndereco(endereco);
        p.setApelido(apefan);
        p.setSexo(sexo);
        p.setEnumero(enumero);
        p.setBairro(bairro);
        p.setComplemento(complemento);
        p.setCEP(CEP);
        p.setCidade(cidade);
        p.setEmail(email);
        p.setUF(UF);
        p.setDatacadastro(datacad);
        p.setRG(rgie);
        p.setEmissorRG(emissorrg);
        p.setTelefone(telefone);
        p.setDatacadastro(datacad);
        p.setOBS(OBS);

        retorno = pessoaDAO.salvar(p);

        return retorno;
    }

    public List<Pessoa> listar() {
        return pessoaDAO.listar();

    }

    public Pessoa getPessoaById(int codpessoa) {
        return pessoaDAO.getPessoaById(codpessoa);

    }

    public List<Pessoa> getPessoaByNome(String pessoa) {
        return pessoaDAO.listar2(pessoa);
    }

    public List<Pessoa> getPessoaByCPF(String cpf) {
        return pessoaDAO.listarcpf(cpf);
    }

    public boolean alterar(int codigo, Boolean pfisica, String nome, String CPF, String celular, String nascimento,
            String endereco, String apefan, String sexo, String enumero, String bairro, String complemento,
            String CEP, String cidade, String email, String UF, String datacad, String rgie,
            String emissorrg, String telefone, String OBS) {
        boolean retorno;

        Pessoa p = new Pessoa();

        p.setCodpessoa(codigo);
        p.setPfisica(pfisica);
        p.setNome(nome);
        p.setCPF(CPF);
        p.setCelular(celular);
        p.setNascimento(nascimento);
        p.setEndereco(endereco);
        p.setApelido(apefan);
        p.setSexo(sexo);
        p.setEnumero(enumero);
        p.setBairro(bairro);
        p.setComplemento(complemento);
        p.setCEP(CEP);
        p.setCidade(cidade);
        p.setEmail(email);
        p.setUF(UF);
        p.setDatacadastro(datacad);
        p.setRG(rgie);
        p.setEmissorRG(emissorrg);
        p.setTelefone(telefone);
        p.setDatacadastro(datacad);
        p.setOBS(OBS);

        retorno = pessoaDAO.alterar(p);

        return retorno;

    }

    public boolean excluir(int codigo) {

        boolean retorno = pessoaDAO.excluir(codigo);
        return retorno;
    }

}
