package br.edu.ctup.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido{
	
	private List<Item> listItem = new ArrayList<Item>();
	private String status;
	private int dia, mes, ano, senha=-1;
	private double total=0;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getSenha() {
		return senha;
	}
	public void setSenha(int senha) {
		this.senha = senha;
	}
	public List<Item> getListItem(){
		return listItem;
	}
	public void setListItem(List<Item> listItem){
		this.listItem = listItem;
	}
	public void addItem(Item item){
		listItem.add(item);
	}
	
}
