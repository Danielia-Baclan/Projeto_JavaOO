package br.edu.ctup.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import br.edu.ctup.model.Cliente;
import br.edu.ctup.model.Pedido;
import br.edu.ctup.model.Restaurante;

public class ControllerCliente {

	static List<Cliente>listCliente = new ArrayList<Cliente>();
	static List<Restaurante> listRest = new ArrayList<Restaurante>();
	static ControllerRestaurante controllerRestaurante = new ControllerRestaurante();
	static Pedido pedido = new Pedido();
	
	public boolean cadastrarC (Cliente cliente) {
		
		boolean check=false;
		int i=0;
		
		if(cliente.getLogin().equals("adm")||cliente.getSenha().equals("adm")) {
			check=true;
		}
		else if(cliente.getLogin().equals("coz")||cliente.getSenha().equals("coz")){
			check=true;
		}
		else if(check==false){
			for(i=0;i<listCliente.size();i++){
				if(cliente.getLogin().equals(listCliente.get(i).getLogin())){
					check=true;
					break;
				}
			}
		}
		
		if(check==false){
			listCliente.add(cliente);
		}
		return check;
	}
	
	public int validarLogin(String login, String senha){
		
		int validacao=0, i=0;
		
		for(i=0;i<listCliente.size();i++){
			if(listCliente.get(i).getLogin().equals(login)&&listCliente.get(i).getSenha().equals(senha)){
				validacao=1;
			}
		}
		if(login.equals("adm")&&senha.equals("adm")){
			validacao=2;
		}
		else if(login.equals("coz")&&senha.equals("coz")){
			validacao=3;
		}
		return validacao;
		
	}
	
	public Cliente listarDados(String login, String senha) {
		int i=0, validacao=-1;
		for(i=0;i<listCliente.size();i++){
			if(listCliente.get(i).getLogin().equals(login)&&listCliente.get(i).getSenha().equals(senha)){
				validacao = i;
				break;
			}
		}
		return listCliente.get(validacao);
	}
	
	public List<Cliente> listarGeral(){
		
		return listCliente;
		
	}
	
	public int alterarDados(Cliente cliente, String login, String senha, int op){
		
		int i=0, validar=0;
		
		for(i=0;i<listCliente.size();i++){
			if(listCliente.get(i).getLogin().equals(login)&&listCliente.get(i).getSenha().equals(senha)){
				if(op==1){
					listCliente.get(i).setNome(cliente.getNome());
					if(listCliente.get(i).getNome().equals(cliente.getNome())){
						validar=1;
					}
				}
				else if(op==2){
					listCliente.get(i).setCpf(cliente.getCpf());
					if(listCliente.get(i).getCpf()==cliente.getCpf()){
						validar=1;
					}
				}
				else if(op==3){
					listCliente.get(i).setRg(cliente.getRg());
					if(listCliente.get(i).getRg()==cliente.getRg()){
						validar=1;
					}
				}
				else if(op==4){
					listCliente.get(i).setTelefone(cliente.getTelefone());
					if(listCliente.get(i).getTelefone().equals(cliente.getTelefone())){
						validar=1;
					}
				}
				else if(op==5){
					listCliente.get(i).setEndereco(cliente.getEndereco());
					if(listCliente.get(i).getEndereco().equals(cliente.getEndereco())){
						validar=1;
					}
				}
				else if(op==6){
					listCliente.get(i).setLogin(cliente.getLogin());
					validar=1;
				}
				else if(op==7){
					listCliente.get(i).setSenha(cliente.getSenha());
					validar=1;
				}
				break;
			}
		}
		return validar;
		
	}
	
	public void att(int i, List<Cliente> listCustomer){
		
		listCliente.get(i).setListPedido(listCustomer.get(i).getListPedido());
		
	}
	
	public void atualizarCozinha(int i, int j){
		listCliente.get(i).getListPedido().get(j).setStatus("Retirada pendente");
	}
	
	public void senhaRetirada(int senha, int i, int j){
		
			listCliente.get(i).getListPedido().get(j).setSenha(senha);
		
	}
	
	public void finalizarPed(int i, Cliente cliente){
		
		int j=0, validacao=-1;
		for(j=0;j<listCliente.size();j++){
			if(listCliente.get(j).getLogin().equals(cliente.getLogin())&&listCliente.get(j).getSenha().equals(cliente.getSenha())){
				listCliente.get(j).getListPedido().get(i).setStatus(cliente.getListPedido().get(i).getStatus());
				break;
			}
		}
		
	}
}
