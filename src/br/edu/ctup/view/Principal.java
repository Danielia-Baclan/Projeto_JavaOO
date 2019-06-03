package br.edu.ctup.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.plaf.synth.SynthSpinnerUI;
import br.edu.ctup.controller.ControllerCliente;
import br.edu.ctup.controller.ControllerRestaurante;
import br.edu.ctup.model.Cardapio;
import br.edu.ctup.model.Cliente;
import br.edu.ctup.model.Endereco;
import br.edu.ctup.model.Pedido;
import br.edu.ctup.model.Item;
import br.edu.ctup.model.Restaurante;

public class Principal {
	
	static Scanner scanner = new Scanner(System.in);
	static ControllerRestaurante controllerRestaurante = new ControllerRestaurante();
	static ControllerCliente controllerCliente = new ControllerCliente();
	static Pedido pedido =  new Pedido();
	static Item item =  new Item(null, null, 0, 0);
	static Cliente cliente = new Cliente();
	static Endereco endereco = new Endereco();
	static Restaurante restaurante = new Restaurante();
	static List<Cliente> listCliente = new ArrayList<Cliente>();
	
	
	public static void main(String[] args){
		
		int op=0;
		
		do{
			op=0;
			do{
				op=0;
				menu();
				try{
					scanner = new Scanner(System.in);
					
					op=scanner.nextInt();
				}catch(InputMismatchException erro){
					System.out.println("Esse campo só permite números!");
				}
			}while(op==0);
			switch(op){
				case 1:
					cadastrarC();
					break;
				case 2:
					logar();
					break;
				case 3:
					System.out.println("Sistema encerrado");
					System.exit(0);
					break;
				default:
						break;
			}
		}while(op>0&&op<3);
		op=-1;
	}
	
	public static void menu(){
		
		System.out.println("##############################");
		System.out.println("      PRAÇA DE ALIMENTAÇÃO    ");
		System.out.println("##############################");
		System.out.println("1 - Cadastrar-se");
		System.out.println("2 - Login");
		System.out.println("3 - Sair");
		
	}
	
	public static void cadastrarC(){
		
		cliente = new Cliente();
		scanner = new Scanner(System.in);
		boolean check=false;
		int loop=0, i=0, num=0;
		long rg=0, cpf=0;
		
		do{
			
			System.out.println("##############################");
			System.out.println("      PRAÇA DE ALIMENTAÇÃO    ");
			System.out.println("##############################");
			System.out.println("Informe seu nome: ");
			cliente.setNome(scanner.nextLine());
			do{
				System.out.println("Informe seu RG: ");
				try{
					scanner = new Scanner(System.in);
					
					rg=scanner.nextLong();
					cliente.setRg(rg);
				}catch(InputMismatchException erro){
					System.out.println("Esse campo só permite números!");
				}
			}while(rg==0);
			
			do{
				System.out.println("Informe seu CPF: ");
				try{
					scanner = new Scanner(System.in);
					
					cpf=scanner.nextLong();
					cliente.setCpf(cpf);
				}catch(InputMismatchException erro){
					System.out.println("Esse campo só permite números!");
				}
			}while(cpf==0);
			
			scanner = new Scanner(System.in);
			System.out.println("Informe seu telefone: ");
			cliente.setTelefone(scanner.nextLine());
			scanner = new Scanner(System.in);
			System.out.println("Informe seu endereço: ");
			System.out.println("Rua: ");
			scanner = new Scanner(System.in);
			endereco.setRua(scanner.nextLine());
			do{
				System.out.println("Número: ");
				try{
					scanner = new Scanner(System.in);
					
					num=scanner.nextInt();
					endereco.setNumC(num);
				}catch(InputMismatchException erro){
					System.out.println("Esse campo só permite números!");
				}
			}while(num==0);
			
			System.out.println("Bairro: ");
			scanner = new Scanner(System.in);
			endereco.setBairro(scanner.nextLine());
			scanner = new Scanner(System.in);
			System.out.println("CEP: ");
			endereco.setCep(scanner.nextLine());
			scanner = new Scanner(System.in);
			System.out.println("Informe um login de usuário: ");
			cliente.setLogin(scanner.nextLine());
			scanner = new Scanner(System.in);
			System.out.println("Informe uma senha de usuário: ");
			cliente.setSenha(scanner.nextLine());
			
			cliente.setEndereco(endereco);
			check=controllerCliente.cadastrarC(cliente);
			
			if(check==true){
				System.out.println("\nLogin ou senha inválidos.");
				System.out.println("Informe um novo login e senha para cadastro.");
				do{
					scanner = new Scanner(System.in);
					System.out.println("Informe um login de usuário: ");
					cliente.setLogin(scanner.nextLine());
					scanner = new Scanner(System.in);
					System.out.println("Informe uma senha de usuário: ");
					cliente.setSenha(scanner.nextLine());
					check=controllerCliente.cadastrarC(cliente);					
				}while(check==true);
				loop=0;
			}
			if(check==false){
				System.out.println("Cadastro realizado com sucesso!");
				loop=1;
			}
			
		}while(loop==0);
		
	}
	
	static void logar(){
		
		scanner = new Scanner(System.in);
		int validacao;
		
		String login, senha;
		
		System.out.println("##############################");
		System.out.println("      PRAÇA DE ALIMENTAÇÃO    ");
		System.out.println("##############################");
		System.out.println("Informe seu login: ");
		login=scanner.nextLine();
		System.out.println("Informe sua senha: ");
		senha=scanner.nextLine();
		
		validacao=controllerCliente.validarLogin(login, senha);
		
		if(validacao==1){
			menuCliente(login, senha);
		}
		else if(validacao==2){
			menuAdm();
		}
		else if(validacao==3){
			Cozinha();
		}
		else{
			System.out.println("Erro no sistema.");
		}
		
	}
	
	public static void menuCliente(String login, String senha){
		
		int op=-1;
		cliente = new Cliente();
		
		do{
			
			if(op==-1){
				do{
				System.out.println("##############################");
				System.out.println("        BEM VINDO CLIENTE     ");
				System.out.println("##############################");
				System.out.println("1 - Cardápio");
				System.out.println("2 - Meu pedido");
				System.out.println("3 - Alterar cadastro");
				System.out.println("4 - Sair");
					try{
						scanner = new Scanner(System.in);
						
						op=scanner.nextInt();
					}catch(InputMismatchException erro){
						System.out.println("Esse campo só permite números!");
					}
				}while(op==-1);
			}
			switch(op){
				case 1:
					cardapio(login, senha);
					menuCliente(login, senha);
					break;
				case 2:
					pedidoCliente(login, senha);
					menuCliente(login, senha);
					break;
				case 3:
					alterar(login, senha);
					break;
				case 4:
					int i=-1;
					scanner = new Scanner(System.in);
					do{
						System.out.println("Deseja deslogar?(0 - Sim||1 - Não)");
						try{
							scanner = new Scanner(System.in);
							
							op=scanner.nextInt();
						}catch(InputMismatchException erro){
							System.out.println("Esta resposta só permite números!");
						}
						if(op==0){
							i=1;
							op=1;
						}
						else if(op==1||op==2){
							op=0;
						}
					}while(i==-1);
					scanner = new Scanner(System.in);
					break;
				case 0:
					break;
				default:
					System.out.println("Opção inválida.");
					break;
			}
			scanner = new Scanner(System.in);
		}while(op==0);
		main(null);
		
	}
	
	public static void cardapio(String login, String senha){
		
		List<Restaurante>restaurante = new ArrayList();
		restaurante = controllerRestaurante.listarCardapio();
		pedido = new Pedido();
		
		int i=0, j=0, k=0, op=-1, opp=0, op3=0, op4=0;
		double total=0, pag=0, troco=0;
		
		do{
			System.out.println("##############################");
			System.out.println("      PRAÇA DE ALIMENTAÇÃO    ");
			System.out.println("##############################");
			for(i=0;i<restaurante.size();i++){
				System.out.println(i+1 + " - "+ restaurante.get(i).getNome());
			}
			if(restaurante.size()==0){
				System.out.println("Não há restaurantes cadastrados no momento.");
				main(null);
			}
			System.out.println("\nSelecione o restaurante desejado:\nOu digite 0 para voltar ao Menu:");
			try{
				scanner = new Scanner(System.in);
				
				op=scanner.nextInt();
			}catch(InputMismatchException erro){
				System.out.println("Esse campo só permite números!");
			}
		}while(op==-1);
		
		if(op == 0){
			menuCliente(login, senha);
		}
		else{
			do{
				k=0;
				do{
					System.out.println("##############################");
					System.out.println("      PRAÇA DE ALIMENTAÇÃO    ");
					System.out.println("##############################");
					System.out.println("1 - Refeições");
					System.out.println("2 - Bebidas");
					
					try{
						scanner = new Scanner(System.in);
						
						op3=scanner.nextInt();
					}catch(InputMismatchException erro){
						System.out.println("Esse campo só permite números!");
					}
				}while(op3==0);
				
				System.out.println("##############################");
				System.out.println("           CARDÁPIO           ");
				System.out.println("##############################");
				System.out.println("Cardápio do restaurante "+ restaurante.get(op-1).getNome());
				switch(op3){
					case 1:
						System.out.println("\nRefeições: ");
						for(i=0;i<restaurante.get(op-1).getCardapio().getListItem().size();i++){
								if(restaurante.get(op-1).getCardapio().getListItem().get(i).getTipo()==1){
									k=k+1;
									restaurante.get(op-1).getCardapio().getListItem().get(i).setId(k);
									controllerRestaurante.AdicionarID(op, restaurante.get(op-1));
									System.out.println(restaurante.get(op-1).getCardapio().getListItem().get(i).getId()+ " - " + "Nome: "+restaurante.get(op-1).getCardapio().getListItem().get(i).getNome()+"\nDescrição: "+restaurante.get(op-1).getCardapio().getListItem().get(i).getDescricao());
									System.out.println("Preço: "+restaurante.get(op-1).getCardapio().getListItem().get(i).getPreco());
								}
						}
						break;
					case 2:
						System.out.println("\nBebidas: ");
						for(i=0;i<restaurante.get(op-1).getCardapio().getListItem().size();i++){
							if(restaurante.get(op-1).getCardapio().getListItem().get(i).getTipo()==2){
								k=k+1;
								restaurante.get(op-1).getCardapio().getListItem().get(i).setId(k);
								System.out.println(restaurante.get(op-1).getCardapio().getListItem().get(i).getId()+ " - " + "Nome: "+restaurante.get(op-1).getCardapio().getListItem().get(i).getNome()+"\nDescrição: "+restaurante.get(op-1).getCardapio().getListItem().get(i).getDescricao());
								System.out.println("Preço: "+restaurante.get(op-1).getCardapio().getListItem().get(i).getPreco());
							}
						}
						break;
				}
				do{
					System.out.println("Deseja fazer pedido?(1 - Sim||2 - Não)");
					try{
						scanner = new Scanner(System.in);
						
						opp=scanner.nextInt();
					}catch(InputMismatchException erro){
						System.out.println("Esta resposta só permite números!");
					}
				}while(opp==0);
				
				if(opp==1){
					opp=0;
					do{
						System.out.println("\nFavor informar seu pedido: ");
						try{
							scanner = new Scanner(System.in);
							
							opp=scanner.nextInt();
						}catch(InputMismatchException erro){
							System.out.println("Esse campo só permite números!");
						}
					}while(opp==0);
			
					if(total>0){
						System.out.println("Subtotal: R$ "+total);
					}
					
					do{
						System.out.println("Deseja incluir outro item?(1 - Sim||2 - Não)");
						try{
							scanner = new Scanner(System.in);
							
							op4=scanner.nextInt();
						}catch(InputMismatchException erro){
							System.out.println("Esta resposta só permite números!");
						}
					}while(op4==0);
					
					pedido=controllerRestaurante.caixa(op, opp, op3, login, senha, pedido);
				}
				else if(opp==2){
					cardapio(login, senha);
				}
			}while(op4==1);
			if(op4==2){
				total=controllerRestaurante.caixa2(login, senha, pedido);
				Caixa(login, senha, total);
			}
		}
	}
	
	public static void pedidoCliente(String login, String senha){
		
		cliente = new Cliente();
		int i=0, op=0, ver=0;
		
		cliente=controllerCliente.listarDados(login, senha);
		
		System.out.println("##############################");
		System.out.println("           MEU PEDIDO         ");
		System.out.println("##############################");
		for(i=0;i<cliente.getListPedido().size();i++){
			if(cliente.getListPedido().get(i).getSenha()>=0&&cliente.getListPedido().get(i).getSenha()<=999&&cliente.getListPedido().get(i).getStatus().equals("Retirada pendente")){
				System.out.println("Pedido esperando retirada\nSenha: "+cliente.getListPedido().get(i).getSenha());
				ver=1;
				break;
			}
		}
		if(ver == 1){
			do{
				System.out.println("Deseja realizar retirada?(1 - Sim||2 - Não)");
				try{
					scanner = new Scanner(System.in);
					
					op=scanner.nextInt();
				}catch(InputMismatchException erro){
					System.out.println("Esta resposta só permite números!");
				}
			}while(op==0);
			
			if(op==1){
				cliente.getListPedido().get(i).setStatus("Pedido finalizado");
				controllerCliente.finalizarPed(i, cliente);
			}
			else{
				menuCliente(login, senha);
			}
		}
		else{
			System.out.println("Não há pedidos para retirada com senha.");
		}
		
	}
	
	public static void alterar(String login, String senha){
		
		scanner=new Scanner(System.in);
		cliente = new Cliente();
		endereco = new Endereco();
		int op=1, opp=0, validar=0, num=0;
		long cpf=0, rg=0;
		
		System.out.println("##############################");
		System.out.println("     MEUS DADOS CADASTRAIS    ");
		System.out.println("##############################");
		cliente = controllerCliente.listarDados(login, senha);
		if(op==1){
			do{
				System.out.println("\nPor favor confira os dados abaixo: ");
				System.out.println("\nNome: "+cliente.getNome()+"\nCPF: "+cliente.getCpf()+"\nRG: "+cliente.getRg()+"\nTelefone: "+cliente.getTelefone());
				System.out.println("Endereço: "+cliente.getEndereco().getRua()+", "+cliente.getEndereco().getNumC()+" - "+cliente.getEndereco().getBairro()+"\n"+cliente.getEndereco().getCep());
				System.out.println("\nLogin: "+cliente.getLogin()+"\nSenha: "+cliente.getSenha());
				do{
					System.out.println("\n\nDeseja fazer alguma alteração?(1 - Sim||2 - Não)");
					try{
						scanner = new Scanner(System.in);
						
						op=scanner.nextInt();
					}catch(InputMismatchException erro){
						System.out.println("Esta resposta só permite números!");
						op=0;
					}
				}while(op==0);
				
				if(op==1){
					opp=0;
					do{
						System.out.println("##############################");
						System.out.println("     MEUS DADOS CADASTRAIS    ");
						System.out.println("##############################");
						System.out.println("1 - Nome");
						System.out.println("2 - CPF");
						System.out.println("3 - RG");
						System.out.println("4 - Telefone");
						System.out.println("5 - Endereço");
						System.out.println("6 - Login");
						System.out.println("7 - Senha");
						try{
							scanner = new Scanner(System.in);
							
							opp=scanner.nextInt();
						}catch(InputMismatchException erro){
							System.out.println("Esse campo só permite números!");
						}
					}while(opp==0);
					scanner = new Scanner(System.in);
					if(opp==1){
						scanner = new Scanner(System.in);
						System.out.println("\nInforme o nome: ");
						cliente.setNome(scanner.nextLine());
						validar=controllerCliente.alterarDados(cliente, login, senha, opp);
						scanner = new Scanner(System.in);
					}
					else if(opp==2){
						scanner = new Scanner(System.in);
						do{
							System.out.println("\nInforme seu CPF: ");
							try{
								scanner = new Scanner(System.in);
								
								cpf=scanner.nextLong();
								cliente.setCpf(cpf);
							}catch(InputMismatchException erro){
								System.out.println("Esse campo só permite números!");
							}
						}while(cpf==0);
						validar=controllerCliente.alterarDados(cliente, login, senha, opp);
						scanner = new Scanner(System.in);
					}
					else if(opp==3){
						scanner = new Scanner(System.in);
						do{
							System.out.println("\nInforme seu RG: ");
							try{
								scanner = new Scanner(System.in);
								
								rg=scanner.nextLong();
								cliente.setRg(rg);
							}catch(InputMismatchException erro){
								System.out.println("Esse campo só permite números!");
							}
						}while(rg==0);
						validar=controllerCliente.alterarDados(cliente, login, senha, opp);
						scanner = new Scanner(System.in);
					}
					else if(opp==4){
						scanner = new Scanner(System.in);
						System.out.println("\nInforme seu telefone: ");
						cliente.setTelefone(scanner.nextLine());
						validar=controllerCliente.alterarDados(cliente, login, senha, opp);
						scanner = new Scanner(System.in);
					}
					else if(opp==5){
						scanner = new Scanner(System.in);
						System.out.println("Informe seu endereço: ");
						System.out.println("Rua: ");
						scanner = new Scanner(System.in);
						endereco.setRua(scanner.nextLine());
						do{
							System.out.println("Número: ");
							try{
								scanner = new Scanner(System.in);
								
								num=scanner.nextInt();
								endereco.setNumC(num);
							}catch(InputMismatchException erro){
								System.out.println("Esse campo só permite números!");
							}
						}while(num==0);
						
						System.out.println("Bairro: ");
						scanner = new Scanner(System.in);
						endereco.setBairro(scanner.nextLine());
						scanner = new Scanner(System.in);
						System.out.println("CEP: ");
						endereco.setCep(scanner.nextLine());
						scanner = new Scanner(System.in);
						cliente.setEndereco(endereco);
						validar=controllerCliente.alterarDados(cliente, login, senha, opp);
						scanner = new Scanner(System.in);
					}
					else if(opp==6){
						scanner = new Scanner(System.in);
						System.out.println("Informe um login de usuário: ");
						scanner = new Scanner(System.in);
						cliente.setLogin(scanner.nextLine());
						validar=controllerCliente.alterarDados(cliente, login, senha, opp);
						String login1=cliente.getLogin();
						validar=controllerCliente.validarLogin(login1, senha);
						if(validar==1){
							System.out.println("\nDados atualizados com sucesso!Faça login novamente.");
						}
						else{
							System.out.println("\nNão foi possível atualizar os dados. Tente novamente.");
						}
						logar();						
					}
					else if(opp==7){
						scanner = new Scanner(System.in);
						System.out.println("Informe uma senha de usuário: ");
						cliente.setSenha(scanner.nextLine());
						validar=controllerCliente.alterarDados(cliente, login, senha, opp);
						String senha1=cliente.getSenha();
						validar=controllerCliente.validarLogin(login, senha1);
						if(validar==1){
							System.out.println("\nDados atualizados com sucesso!Faça login novamente.");
						}
						else{
							System.out.println("\nNão foi possível atualizar os dados. Tente novamente.");
						}
						scanner = new Scanner(System.in);
						logar();
						scanner = new Scanner(System.in);
					}
					else {
						System.out.println("Opção inválida.");
					}
					if(opp!=6&&opp!=7){
						if(validar==1){
							System.out.println("\nDados atualizados com sucesso!");
						}
						else{
							System.out.println("\nNão foi possível atualizar os dados. Tente novamente.");
						}
					}
					op=0;
					scanner = new Scanner(System.in);
					do{
						System.out.println("\nDeseja fazer mais alguma alteração?(1 - Sim||2 - Não)");
						try{
							scanner = new Scanner(System.in);
							
							op=scanner.nextInt();
						}catch(InputMismatchException erro){
							System.out.println("Esta resposta só permite números!");
						}
					}while(op==0);
				}
				else{
					menuCliente(login, senha);
				}
			}while(op==1);
		}
		scanner = new Scanner(System.in);
		
	}
	
	public static void menuAdm(){
		
		int op=0;
		
		do{
			System.out.println("##############################");
			System.out.println("     BEM VINDO ADMINITRADOR   ");
			System.out.println("##############################");
			System.out.println("1 - Cadastrar Restaurante");
			System.out.println("2 - Configurações");
			System.out.println("3 - Pedidos");
			System.out.println("4 - Sair");
			try{
				scanner = new Scanner(System.in);
				
				op=scanner.nextInt();
			}catch(InputMismatchException erro){
				System.out.println("Esse campo só permite números!");
			}
		}while(op==0);

		switch(op){
			case 1:
				cadastrarRest();
				menuAdm();
				break;
			case 2:
				alterarInfo();
				break;
			case 3:
				pedidos();
				break;
			case 4:
				scanner = new Scanner(System.in);
				op=1;
				main(null);
				break;
		}
		
	}
	
	public static void cadastrarRest(){
		
		scanner = new Scanner(System.in);
		restaurante = new Restaurante();
		item = new Item(null, null, 0, 0);
		int op=1, tipo=0;
		long cnpj=0;
		float preco=0;

		System.out.println("##############################");
		System.out.println("      PRAÇA DE ALIMENTAÇÃO    ");
		System.out.println("##############################");
		System.out.println("Informe o nome do restaurante: ");
		restaurante.setNome(scanner.nextLine());
		
		do{
			System.out.println("Informe o CNPJ do restaurante: ");
			try{
				scanner = new Scanner(System.in);
				
				cnpj=scanner.nextLong();
				restaurante.setCnpj(cnpj);
			}catch(InputMismatchException erro){
				System.out.println("Esse campo só permite números!");
			}
		}while(cnpj==0);
		scanner = new Scanner(System.in);
		do{
			item = new Item(null, null, 0, 0);
			scanner = new Scanner(System.in);
			System.out.println("\nFavor informar o cardápio do restaurante: ");
			System.out.println("Nome do item: ");
			item.setNome(scanner.nextLine());
			scanner = new Scanner(System.in);
			System.out.println("Informe a descrição do item: ");
			item.setDescricao(scanner.nextLine());
			do{
				System.out.println("Informe o preço do item: ");
				try{
					scanner = new Scanner(System.in);
					
					preco=scanner.nextFloat();
					item.setPreco(preco);
				}catch(InputMismatchException erro){
					System.out.println("Esse campo só permite números!");
				}
			}while(preco==0);

			do{
				System.out.println("Informe o tipo do item(1 - Comidas||2 - Bebidas)");
				try{
					scanner = new Scanner(System.in);
					
					tipo=scanner.nextInt();
					item.setTipo(tipo);
				}catch(InputMismatchException erro){
					System.out.println("Esse campo só permite números!");
				}
			}while(tipo==0);
			
			controllerRestaurante.AdicionarItemCardapio(item);
			do{
				System.out.println("Deseja cadastrar outro item?(1 - Sim||2 - Não)");
				try{
					scanner = new Scanner(System.in);
					
					op=scanner.nextInt();
				}catch(InputMismatchException erro){
					System.out.println("Esta resposta só permite números!");
					op=0;
				}
			}while(op==0);
			
			
			if (op==2){
				controllerRestaurante.cadastrarR(restaurante);
				item= new Item(null, null, op, op);
				restaurante=new Restaurante();
			}
		}while(op==1);
		
	}
	
	public static void alterarInfo(){
		
		scanner=new Scanner(System.in);
		restaurante = new Restaurante();
		Cardapio cardapio = new Cardapio();
		item = new Item(null, null, 0, 0);
		
		int op=1, opp=0, oppp=0, op4=1, op5=0, validar=0, tipo=0;
		long cnpj=0;
		float preco=0;
		
		do{
			restaurante = new Restaurante();
			scanner=new Scanner(System.in);
			validar=0;
			op=1;
			
			do{
				System.out.println("##############################");
				System.out.println("     GERENCIAR RESTAURANTES   ");
				System.out.println("##############################");
				System.out.println("1 - Atualizar dados do restaurante");
				System.out.println("2 - Atualizar cardápio");
				System.out.println("3 - Adicionar item ao cardápio");
				System.out.println("4 - Sair");
				try{
					scanner = new Scanner(System.in);
					
					opp=scanner.nextInt();
				}catch(InputMismatchException erro){
					System.out.println("Esse campo só permite números!");
				}
			}while(opp==0);
			
			switch(opp){
				case 1:
					do{
						System.out.println("Informe o CNPJ do restaurante: ");
						try{
							scanner = new Scanner(System.in);
							
							cnpj=scanner.nextLong();
						}catch(InputMismatchException erro){
							System.out.println("Esse campo só permite números!");
						}
					}while(cnpj==0);
					
					try{
						restaurante=controllerRestaurante.listarDados(cnpj);
					}catch(ArrayIndexOutOfBoundsException erro){
						System.out.println("Não há restaurantes cadastrados!");
						alterarInfo();
						opp=0;
					}
					
					do{
						System.out.println("\nFavor confirir os dados: ");
						System.out.println("\nNome: "+restaurante.getNome()+"\nCNPJ: "+restaurante.getCnpj());
						op=0;
						do{
							System.out.println("\n\nDeseja fazer alguma alteração?(1 - Sim||2 - Não)");
							try{
								scanner = new Scanner(System.in);
								
								op=scanner.nextInt();
							}catch(InputMismatchException erro){
								System.out.println("Esta resposta só permite números!");
								op=0;
							}
						}while(op==0);
						
						if(op==1){
							do{
								System.out.println("\nDigite o campo que deseja modificar: ");
								System.out.println("1 - Nome");
								System.out.println("2 - CNPJ");
								try{
									scanner = new Scanner(System.in);
									
									oppp=scanner.nextInt();
								}catch(InputMismatchException erro){
									System.out.println("Esse campo só permite números!");
								}
							}while(oppp==0);
							scanner = new Scanner(System.in);
							if(oppp==1){
								System.out.println("Informe o nome do restaurante: ");
								scanner = new Scanner(System.in);
								restaurante.setNome(scanner.nextLine());
								validar=controllerRestaurante.alterarInfo(restaurante, oppp, cnpj);
							}
							else if(oppp==2){
								cnpj=0;
								do{
									System.out.println("Informe o CNPJ do restaurante: ");
									try{
										scanner = new Scanner(System.in);
										
										cnpj=scanner.nextLong();
										restaurante.setCnpj(cnpj);
									}catch(InputMismatchException erro){
										System.out.println("Esse campo só permite números!");
									}
								}while(cnpj==0);
								validar=controllerRestaurante.alterarInfo(restaurante, oppp, cnpj);
							}
							else{
								System.out.println("Opção inválida.");
							}
							if(validar==1){
								System.out.println("Dados atualizados com sucesso!");
							}
							else{
								System.out.println("Não foi possível atualizar o cadastro, tente novamente.");
							}
							op=0;
							do{
								System.out.println("Deseja fazer mais alguma alteração?(1 - Sim||2 - Não)");
								try{
									scanner = new Scanner(System.in);
									
									op=scanner.nextInt();
								}catch(InputMismatchException erro){
									System.out.println("Esta resposta só permite números!");
								}
							}while(op==0);
						}
					}while(op==1);
					break;
				case 2:
					cnpj=0;
					do{
						System.out.println("Informe o CNPJ do restaurante: ");
						try{
							scanner = new Scanner(System.in);
							
							cnpj=scanner.nextLong();
						}catch(InputMismatchException erro){
							System.out.println("Esse campo só permite números!");
						}
					}while(cnpj==0);
					int verificar=controllerRestaurante.verificarCNPJ(cnpj);
					if(verificar==1){
						restaurante=controllerRestaurante.listarDados(cnpj);
						do{
							System.out.println("\nFavor confirir os dados: ");
							System.out.println("\nNome: "+restaurante.getNome()+"\nCNPJ: "+restaurante.getCnpj());
							System.out.println("\nCardapio:");
							for(int i=0;i<restaurante.getCardapio().getListItem().size(); i++){
								System.out.println(i+1+ " - " + "Nome: "+restaurante.getCardapio().getListItem().get(i).getNome()+"\nDescrição: "+restaurante.getCardapio().getListItem().get(i).getDescricao());
								System.out.println("Preço: "+restaurante.getCardapio().getListItem().get(i).getPreco());
								System.out.println("Tipo: "+restaurante.getCardapio().getListItem().get(i).getTipo());
							}
							op=0;
							scanner = new Scanner(System.in);
							do{
								System.out.println("\nDeseja fazer alguma alteração?(1 - Sim||2 - Não)");
								try{
									scanner = new Scanner(System.in);
									
									op=scanner.nextInt();
								}catch(InputMismatchException erro){
									System.out.println("Esta resposta só permite números!");
									op=0;
								}
							}while(op==0);
							
							if(op==1){
								do{
									System.out.println("Informe o item a ser modificado: ");
									try{
										scanner = new Scanner(System.in);
										
										op5=scanner.nextInt();
									}catch(InputMismatchException erro){
										System.out.println("Esse campo só permite números!");
									}
								}while(op5==0);
								if(op5<=restaurante.getCardapio().getListItem().size()){
									scanner = new Scanner(System.in);
									oppp=0;
									do{
										System.out.println("\nDigite o campo que deseja modificar: ");
										System.out.println("1 - Nome");
										System.out.println("2 - Descrição");
										System.out.println("3 - Preço");
										System.out.println("4 - Tipo");
										try{
											scanner = new Scanner(System.in);
											
											oppp=scanner.nextInt();
										}catch(InputMismatchException erro){
											System.out.println("Esse campo só permite números!");
										}
									}while(oppp==0);
									scanner = new Scanner(System.in);
									if(oppp==1){
										System.out.println("Informe o nome do item: ");
										item.setNome(scanner.nextLine());
										validar=controllerRestaurante.alterarItem(item, oppp, cnpj, op5);
									}
									else if(oppp==2){
										scanner=new Scanner(System.in);
										System.out.println("Informe a descrição do item: ");
										item.setDescricao(scanner.nextLine());
										validar=controllerRestaurante.alterarItem(item, oppp, cnpj, op5);
									}
									else if(oppp==3){
										do{
											System.out.println("Informe o preço do item: ");
											try{
												scanner = new Scanner(System.in);
												
												preco=scanner.nextFloat();
												item.setPreco(preco);
											}catch(InputMismatchException erro){
												System.out.println("Esse campo só permite números!");
											}
										}while(preco==0);
										validar=controllerRestaurante.alterarItem(item, oppp, cnpj, op5);
										scanner = new Scanner(System.in);
									}
									else if(oppp==4){
										do{
											System.out.println("Informe o tipo do item(1 - Comidas||2 - Bebidas): ");
											try{
												scanner = new Scanner(System.in);
												
												tipo=scanner.nextInt();
												item.setTipo(tipo);
											}catch(InputMismatchException erro){
												System.out.println("Esse campo só permite números!");
											}
										}while(tipo==0);
										validar=controllerRestaurante.alterarItem(item, oppp, cnpj, op5);
										scanner = new Scanner(System.in);
									}
									else{
										System.out.println("Opção inválida.");
									}
									if(validar==1){
										System.out.println("Dados atualizados com sucesso!");
									}
									else{
										System.out.println("Não foi possível atualizar o cadastro, tente novamente.");
									}
									op=0;
									do{
										System.out.println("Deseja fazer mais alguma alteração?(1 - Sim||2 - Não)");
										try{
											scanner = new Scanner(System.in);
											
											op=scanner.nextInt();
										}catch(InputMismatchException erro){
											System.out.println("Esta resposta só permite números!");
										}
									}while(op==0);
									scanner = new Scanner(System.in);
								}
								else{
									System.out.println("Opção inválida.");
								}
							}
							else{
								menuAdm();
							}
						}while(op==1);
					}
					else{
						System.out.println("CNPJ inválido.");
					}
					break;
				case 3:
					cnpj=0;
					do{
						System.out.println("Informe o CNPJ do restaurante: ");
						try{
							scanner = new Scanner(System.in);
							
							cnpj=scanner.nextLong();
						}catch(InputMismatchException erro){
							System.out.println("Esse campo só permite números!");
						}
					}while(cnpj==0);
					
					try{
						restaurante=controllerRestaurante.listarDados(cnpj);
					}catch(ArrayIndexOutOfBoundsException erro){
						System.out.println("Não há restaurantes cadastrados!");
						alterarInfo();
					}
					do{
						System.out.println("\nFavor confirir os dados: ");
						System.out.println("\nNome: "+restaurante.getNome()+"\nCNPJ: "+restaurante.getCnpj());
						System.out.println("\nCardapio:");
						for(int i=0;i<restaurante.getCardapio().getListItem().size(); i++){
							System.out.println(i+1+ " - " + "Nome: "+restaurante.getCardapio().getListItem().get(i).getNome()+"\nDescrição: "+restaurante.getCardapio().getListItem().get(i).getDescricao());
							System.out.println("Preço: "+restaurante.getCardapio().getListItem().get(i).getPreco());
							System.out.println("Tipo: "+restaurante.getCardapio().getListItem().get(i).getTipo());
						}
						op=0;
						do{
							System.out.println("Deseja acrescentar um novo item ao cardápio?(1 - Sim||2 - Não)");
							try{
								scanner = new Scanner(System.in);
								
								op=scanner.nextInt();
							}catch(InputMismatchException erro){
								System.out.println("Esta resposta só permite números!");
							}
						}while(op==0);
						scanner = new Scanner(System.in);
					
						if(op==1){
							do{
								item = new Item(null, null, 0, 0);
								scanner = new Scanner(System.in);
								
								System.out.println("\nFavor informar o cardápio do restaurante: ");
								System.out.println("Nome do item: ");
								item.setNome(scanner.nextLine());
								scanner = new Scanner(System.in);
								System.out.println("Informe a descrição do item: ");
								item.setDescricao(scanner.nextLine());
								preco=0;
								do{
									System.out.println("Informe o preço do item: ");
									try{
										scanner = new Scanner(System.in);
										
										preco=scanner.nextFloat();
										item.setPreco(preco);
									}catch(InputMismatchException erro){
										System.out.println("Esse campo só permite números!");
									}
								}while(preco==0);
								scanner = new Scanner(System.in);
								tipo=0;
								do{
									System.out.println("Informe o tipo do item(1 - Comidas||2 - Bebidas)");
									try{
										scanner = new Scanner(System.in);
										
										tipo=scanner.nextInt();
										item.setTipo(tipo);
									}catch(InputMismatchException erro){
										System.out.println("Esse campo só permite números!");
									}
								}while(tipo==0);
								scanner = new Scanner(System.in);
								validar=controllerRestaurante.AdicionarNovoItem(cnpj, item);
								if(validar==1){
									System.out.println("Cadastro realizado com sucesso!");
								}
								else{
									System.out.println("Não foi possível adicionar item ao cardápio.");
								}
								op=0;
								do{
									System.out.println("Deseja cadastrar outro item?(1 - Sim||2 - Não)");
									try{
										scanner = new Scanner(System.in);
										
										op=scanner.nextInt();
									}catch(InputMismatchException erro){
										System.out.println("Esta resposta só permite números!");
									}
								}while(op==0);
								scanner = new Scanner(System.in);
							}while(op==1);
							scanner = new Scanner(System.in);
						}
						else{
							menuAdm();
						}
					}while(op==1);
					break;
				case 4:
					menuAdm();
					op4=0;
					opp=0;
					break;
				default:
					op4=0;
					break;
			}
		}while(op4==1);
		
	}
	
	public static void pedidos(){
		
		listCliente = new ArrayList<Cliente>();
		List<Restaurante> listRest = new ArrayList<Restaurante>();
		scanner = new Scanner(System.in);
		int i=0, j=0, k=0, op=0;
		
		listCliente=controllerCliente.listarGeral();
		listRest=controllerRestaurante.listarCardapio();
		do{
			try{
				scanner = new Scanner(System.in);
				
				System.out.println("##############################");
				System.out.println("       GERENCIAR PEDIDOS      ");
				System.out.println("##############################");
				System.out.println("1 - Pedidos em aberto");
				System.out.println("2 - Itens mais pedidos");
				op=scanner.nextInt();
			}catch(InputMismatchException erro){
				System.out.println("Esse campo só permite números!");
				op=0;
			}
		}while(op==0);
		scanner = new Scanner(System.in);
		if(op==1){
			for(i=0;i<listCliente.size();i++){
				for(j=0;j<listCliente.get(i).getListPedido().size();j++){
					if(listCliente.get(i).getListPedido().get(j).getStatus().equals("Preparo pendente")){
						System.out.println("Pedidos em aberto");
						System.out.println("Cliente: "+listCliente.get(i).getNome()+"\nPedido: "+listCliente.get(i).getListPedido().get(j).getStatus()+"\nData: "+listCliente.get(i).getListPedido().get(j).getDia()+"/"+
										   listCliente.get(i).getListPedido().get(j).getMes()+"/"+listCliente.get(i).getListPedido().get(j).getAno());
					}
				}
			}
		}
		else if(op==2){
			for(i=0;i<listRest.size();i++){
				for(j=0;j<listRest.get(i).getCardapio().getListItem().size();j++){
						if(listRest.get(i).getCardapio().getListItem().get(j).getVenda()>5){
							System.out.println("Itens mais pedidos");
							System.out.println("Restaurante: "+listRest.get(i).getNome());
							System.out.println("Item: "+listRest.get(i).getCardapio().getListItem().get(j).getNome()+
											   "\nDescrição: "+listRest.get(i).getCardapio().getListItem().get(j).getDescricao()+
											   "\nTipo: "+listRest.get(i).getCardapio().getListItem().get(j).getTipo());
						}
				}
			}
		}
		menuAdm();
	}
	
	public static void Caixa(String login, String senha, double total){
		
		int op=0, opp=0, i=0;
		double troco=0, pag=0;
		
		do{
			System.out.println("##############################");
			System.out.println("        FINALIZAR PEDIDO      ");
			System.out.println("##############################");
			System.out.println("1 - Carrinho");
			System.out.println("2 - Caixa");
			System.out.println("Informe o que deseja visualizar: ");
			try{
				scanner = new Scanner(System.in);
				
				op=scanner.nextInt();
			}catch(InputMismatchException erro){
				System.out.println("Esse campo só permite números!");
			}
		}while(op==0);
		scanner = new Scanner(System.in);
		switch(op){
			case 1:
				pedido=controllerRestaurante.carrinho(login, senha);
				for(i=0;i<pedido.getListItem().size();i++){
					System.out.println("Item: "+pedido.getListItem().get(i).getNome()+"\nPreço: R$ "+pedido.getListItem().get(i).getPreco());
				}
				break;
			case 2:
				System.out.println("Total do pedido: R$ "+total);
				do{
					System.out.println("Favor informar método de pagamento: (1 - Dinheiro||2 - Cartão)");
					try{
						scanner = new Scanner(System.in);
						
						opp=scanner.nextInt();
					}catch(InputMismatchException erro){
						System.out.println("Esse campo só permite números!");
					}
				}while(opp==0);
				scanner = new Scanner(System.in);				
				if(opp==1){
					do{
						do{
							System.out.println("Informe o valor do pagamento: ");
							try{
								scanner = new Scanner(System.in);
								
								pag=scanner.nextDouble();
							}catch(InputMismatchException erro){
								System.out.println("Favor digite um valor de pagamento válido!");
							}
						}while(pag==0);
						scanner = new Scanner(System.in);
						if(pag>=total){
							troco=controllerRestaurante.financeiroD(total, pag, login, senha);
						}
						if(pag<total){
							System.out.println("Valor de pagamento insuficiente.");
						}
					}while(pag<total);
					System.out.println(troco);
					if(troco==0){
						System.out.println("Obrigado pela preferência!");
					}
					else if(troco>0){
						System.out.println("Seu troco é de: R$ "+troco);
					}
				}
				else if(opp==2){
					controllerRestaurante.financeiroC(login, senha);
					System.out.println("Obrigado pela preferência!");
				}
				break;
		}
		if(op==1){
			Caixa(login, senha, total);
		}
		
	}
	
	public static void Cozinha(){
		
		scanner = new Scanner(System.in);
		listCliente = new ArrayList<Cliente>();
		Random random = new Random();
		int i=0, j=0, k=0, op=0, ver=0, senha; 

		listCliente=controllerCliente.listarGeral();
		System.out.println("##############################");
		System.out.println("      PRAÇA DE ALIMENTAÇÃO    ");
		System.out.println("##############################");
		System.out.println("Pedidos em aberto");
		for(i=0;i<listCliente.size();i++){
			for(j=0;j<listCliente.get(i).getListPedido().size();j++){
				if(listCliente.get(i).getListPedido().get(j).getStatus().equals("Preparo pendente")){
					System.out.println("\nPedido: "+listCliente.get(i).getListPedido().get(j).getStatus()+"\nData: "+listCliente.get(i).getListPedido().get(j).getDia()+"/"+
									   listCliente.get(i).getListPedido().get(j).getMes()+"/"+listCliente.get(i).getListPedido().get(j).getAno());
					for(k=0;k<listCliente.get(i).getListPedido().get(j).getListItem().size();k++){
							System.out.println("produto: "+listCliente.get(i).getListPedido().get(j).getListItem().get(k).getNome());
					}	
				}	
			}
		}
		
		op=0;
		do{
			System.out.println("Pedido atual concluído? (1 - Sim|| 2 - Não)");
			try{
				scanner = new Scanner(System.in);
				
				op = scanner.nextInt();
			}catch(InputMismatchException erro){
				System.out.println("Favor digite um valor de pagamento válido!");
			}
		}while(op==0);
		scanner = new Scanner(System.in);
		if(op == 1){
			for(i=0;i<listCliente.size();i++){
				for(j=0;j<listCliente.get(i).getListPedido().size();j++){
					if(listCliente.get(i).getListPedido().get(j).getStatus().equals("Preparo pendente")){
						controllerCliente.atualizarCozinha(i, j);
						controllerRestaurante.atualizarCozinha(i, j);
						senha= random.nextInt(999);
						controllerCliente.senhaRetirada(senha, i, j);
						ver=1;
						break;
					}
				}
				if(ver==1){
				break;
				}
			}
			Cozinha();
		}
		else if(op == 2){
			main(null);
		}
	}
}
