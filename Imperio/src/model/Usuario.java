/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Willian
 */
public class Usuario {

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    int codusuario;

    public int getCodusuario() {
        return codusuario;
    }

    public void setCodusuario(int codusuario) {
        this.codusuario = codusuario;
    }

    public boolean isPessoas() {
        return pessoas;
    }

    public void setPessoas(boolean pessoas) {
        this.pessoas = pessoas;
    }

    public boolean isUsuarios() {
        return usuarios;
    }

    public void setUsuarios(boolean usuarios) {
        this.usuarios = usuarios;
    }

    public boolean isProdutos() {
        return produtos;
    }

    public void setProdutos(boolean produtos) {
        this.produtos = produtos;
    }

    public boolean isApagar() {
        return apagar;
    }

    public void setApagar(boolean apagar) {
        this.apagar = apagar;
    }

    public boolean isAreceber() {
        return areceber;
    }

    public void setAreceber(boolean areceber) {
        this.areceber = areceber;
    }

    public boolean isOs() {
        return os;
    }

    public void setOs(boolean os) {
        this.os = os;
    }
    
    String nome;
    String senha;
    boolean pessoas;
    boolean usuarios;
    boolean produtos;
    boolean apagar;
    boolean areceber;
    boolean os;

}
