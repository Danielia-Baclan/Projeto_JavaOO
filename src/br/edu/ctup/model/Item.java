package br.edu.ctup.model;

public class Item{
	
	private String nome, descricao;
	private double preco;
	private int tipo, venda=0, id;
	
	
	public Item(String nome, String descricao, float preco, int tipo){
		this.nome=nome;
		this.descricao=descricao;
		this.preco=preco;
		this.tipo=tipo;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVenda() {
		return venda;
	}
	public void setVenda(int venda) {
		this.venda = venda;
	}
	public int getTipo(){
		return tipo;
	}
	public void setTipo(int tipo){
		this.tipo = tipo;
	}
	public String getNome(){
		return nome;
	}
	public void setNome(String nome){
		this.nome = nome;
	}
	public String getDescricao(){
		return descricao;
	}
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	public double getPreco(){
		return preco;
	}
	public void setPreco(double preco){
		this.preco = preco;
	}
	
}
