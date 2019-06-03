package br.edu.ctup.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import br.edu.ctup.model.Cardapio;
import br.edu.ctup.model.Cliente;
import br.edu.ctup.model.Item;
import br.edu.ctup.model.Pedido;
import br.edu.ctup.model.Restaurante;

public class ControllerRestaurante {

	static List<Restaurante>listRest = new ArrayList<Restaurante>();
	static List<Cliente>listCliente = new ArrayList<Cliente>();
	private ControllerCliente controllerCliente = new ControllerCliente();
	private Cardapio cardapio = new Cardapio();
	private Pedido pedido = new Pedido();
	private Item item;
	
	int id=0, id1=0;
	
	public void cadastrarR(Restaurante restaurante){
		
		restaurante.setCardapio(cardapio);
		listRest.add(restaurante);
		cardapio=new Cardapio();
		
	}
	
	public int AdicionarNovoItem(long cnpj, Item item){
		
		int i=0, validacao=0;
		
		for(i=0;i<listRest.size();i++){
			if(listRest.get(i).getCnpj()==cnpj){
				listRest.get(i).getCardapio().addItem(item);
				validacao = 1;
				break;
			}
		}
		return validacao;
		
	}
	
	public void AdicionarItemCardapio(Item item){
		
		/*int i=0;
		
		if(item.getTipo()==1){
			id=id+1;
			item.setId(id);
		}
			else if(item.getTipo()==2){
			id1=id1+1;
			item.setId(id1);
		}*/
		cardapio.addItem(item);
		
	}
	
	public void AdicionarID(int l, Restaurante restaurante){
		
		int i=0;
		
		for(i=0;i<listRest.get(l-1).getCardapio().getListItem().size();i++){
			listRest.get(l-1).getCardapio().getListItem().get(i).setId(restaurante.getCardapio().getListItem().get(i).getId());
		}
		
	}
	
	public int verificarCNPJ(long cnpj){
		
		int i=0, j=0;
		
		for(i=0;i<listRest.size();i++){
			if(cnpj==listRest.get(i).getCnpj()){
				j=1;
			}
		}
		
		return j;
	}
	
	public Restaurante listarDados(long cnpj){
		
		int i=0, validacao=-1;
		
		for(i=0;i<listRest.size();i++){
			if(listRest.get(i).getCnpj()==cnpj){
				validacao = i;
				break;
			}
		}
		return listRest.get(validacao);
	}
	
	public List<Restaurante> listarCardapio(){
		
		return listRest;
		
	}
	
	public int alterarInfo(Restaurante restaurante, int op, long cnpj){
		
		int i=0, validar=0;
		
		for(i=0;i<listRest.size();i++){
			if(op==1){
				if(listRest.get(i).getCnpj()==cnpj){
					listRest.get(i).setNome(restaurante.getNome());
					validar=1;
				}
			}
			else if(op==2){
				if(listRest.get(i).getCnpj()==cnpj){
					listRest.get(i).setCnpj(restaurante.getCnpj());
					validar=1;
				}
			}
		}
		return validar;
		
	}
	
	public int alterarItem(Item item, int op, long cnpj, int posicao) {
		
		int i=0, validar=0;
		
		posicao=posicao-1;
		for(i=0; i<listRest.size();i++){
			if(listRest.get(i).getCnpj()==cnpj){
				if(op==1){
					listRest.get(i).getCardapio().getListItem().get(posicao).setNome(item.getNome());
					validar=1;
				}
				else if(op==2){
					listRest.get(i).getCardapio().getListItem().get(posicao).setDescricao(item.getDescricao());
					validar=1;
				}
				else if(op==3){
					listRest.get(i).getCardapio().getListItem().get(posicao).setPreco(item.getPreco());
					validar=1;
				}
				else if(op==4){
					listRest.get(i).getCardapio().getListItem().get(posicao).setTipo(item.getTipo());
					validar=1;
				}
			}
		}
		return validar;
	}
	
	public Pedido carrinho(String login, String senha){
		
		pedido = new Pedido();
		
		int i=0;
		
		for(i=0;i<listCliente.size();i++){
			if(listCliente.get(i).getLogin().equals(login)&&listCliente.get(i).getSenha().equals(senha)){
				pedido=listCliente.get(i).getListPedido().get(listCliente.get(i).getListPedido().size()-1);
				break;
			}
		}
		return pedido;
		
	}
	
	public Pedido caixa(int op, int opp, int op3, String login, String senha, Pedido pedido){
		
		int i=0, j=0;
		double total=0;
	
		for(i=0;i<listRest.get(op-1).getCardapio().getListItem().size();i++){
			if(listRest.get(op-1).getCardapio().getListItem().get(i).getTipo()==op3){
				if(listRest.get(op-1).getCardapio().getListItem().get(i).getId()==opp){
					pedido.addItem(listRest.get(op-1).getCardapio().getListItem().get(i));					
					if(pedido.getTotal()==0){
						pedido.setTotal(listRest.get(op-1).getCardapio().getListItem().get(i).getPreco());
					}
					else{
						pedido.setTotal(pedido.getTotal()+listRest.get(op-1).getCardapio().getListItem().get(i).getPreco());
					}
					for(j=0;j<pedido.getListItem().size();j++){
						if(pedido.getListItem().get(j).getVenda()==0){
							pedido.getListItem().get(j).setVenda(1);
						}
						else if(pedido.getListItem().get(j).getVenda()>0){
							pedido.getListItem().get(j).setVenda(pedido.getListItem().get(j).getVenda()+1);
						}
					}
						break;
				}
			}	
		}
		return pedido;
		
	}
	
	public double caixa2(String login, String senha, Pedido pedido){
		
		listCliente = new ArrayList<Cliente>();
		//Random random = new Random();
		//Calendar hora = Calendar.getInstance();
		int j=0, dia=9, mes=5, ano=2019;
		double total=0;
		
		listCliente=controllerCliente.listarGeral();
		
		pedido.setDia(dia);
		pedido.setMes(mes);
		pedido.setAno(ano);
		pedido.setStatus("Pagamento pendente");
		for(j=0;j<listCliente.size();j++){
			if(listCliente.get(j).getLogin().equals(login)&&listCliente.get(j).getSenha().equals(senha)){
				total=pedido.getTotal();
				listCliente.get(j).addPedido(pedido);
				controllerCliente.att(j, listCliente);
				break;
			}
		}
		return total;
		
	}
	
	public double financeiroD(double total, double pag, String login, String senha){
		
		listCliente = new ArrayList<Cliente>();
		double troco=0;
		int j=0;
		
		listCliente=controllerCliente.listarGeral();
		
		troco=pag-total;
		for(j=0;j<listCliente.size();j++){
			if(listCliente.get(j).getLogin().equals(login)&&listCliente.get(j).getSenha().equals(senha)){
				listCliente.get(j).getListPedido().get(listCliente.get(j).getListPedido().size()-1).setStatus("Preparo pendente");
				break;
			}
		}
		return troco;
		
	}
	
	public void financeiroC(String login, String senha){
		
		listCliente = new ArrayList<Cliente>();
		int j=0;
		
		listCliente=controllerCliente.listarGeral();
		
		for(j=0;j<listCliente.size();j++){
			if(listCliente.get(j).getLogin().equals(login)&&listCliente.get(j).getSenha().equals(senha)){
				listCliente.get(j).getListPedido().get(listCliente.get(j).getListPedido().size()-1).setStatus("Preparo pendente");
				break;
			}
		}
	}

	public void atualizarCozinha(int i, int j){
		listCliente.get(i).getListPedido().get(j).setStatus("Retirada pendente");
	}
		
}

