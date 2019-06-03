package br.edu.ctup.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurante{
	
	private String nome;
	private long cnpj;
	private Cardapio cardapio;
	
	public String getNome(){
		return nome;
	}
	public void setNome(String nome){
		this.nome = nome;
	}
	public long getCnpj(){
		return cnpj;
	}
	public void setCnpj(long cnpj){
		this.cnpj = cnpj;
	}
	public Cardapio getCardapio() {
		return cardapio;
	}
	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	
}
