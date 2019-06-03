package br.edu.ctup.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente{
	
	private String nome, login, senha, telefone;
	private long cpf, rg;
	private Endereco endereco;
	private List<Pedido> listPedido = new ArrayList<Pedido>();
	
	public List<Pedido> getListPedido() {
		return listPedido;
	}
	public void setListPedido(List<Pedido> listPedido) {
		this.listPedido = listPedido;
	}
	public void addPedido(Pedido Pedido) {
		listPedido.add(Pedido);
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public long getCpf() {
		return cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	public long getRg() {
		return rg;
	}
	public void setRg(long rg) {
		this.rg = rg;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
}
