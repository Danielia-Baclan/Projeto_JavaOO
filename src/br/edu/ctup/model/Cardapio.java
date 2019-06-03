package br.edu.ctup.model;

import java.util.ArrayList;
import java.util.List;

public class Cardapio{
	
	private List<Item> listItem = new ArrayList<Item>();

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
