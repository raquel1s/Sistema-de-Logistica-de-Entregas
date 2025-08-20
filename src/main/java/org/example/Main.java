package org.example;

import org.example.dao.*;
import org.example.model.*;
import org.example.view.Menu;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        inicio();
    }

    public static void inicio(){
        boolean sair = false;

        Menu.exibir();
        int opcao = SC.nextInt();
        SC.nextLine();

        switch (opcao){
            case 1 -> cadastrarCliente();
            case 2 -> cadastrarMotorista();
            case 3 -> criarPedido();
            case 4 -> atribuirPedidoMotorista();
            case 5 -> registrarEventoEntrega();
            case 6 -> atualizarStatusEntrega();
            case 7 -> listarEntregas();
            case 8 -> totalEntregasMotorista();
            case 9 -> clienteMaiorVolume();
            case 0 -> {
                sair = true;
                break;
            }
            default -> System.out.println("Opção Inválida.");
        }

        if(!sair){
            inicio();
        }
    }

    private static void clienteMaiorVolume() {
        System.out.println("=== Clientes com o Maior volume Entregue ===");
        for(ClienteVolume c : PedidoDAO.clienteMaiorVolume()){
            System.out.println(c);
        }
        System.out.println();
    }

    private static void totalEntregasMotorista() {
        System.out.println("=== Total de Entregas por Motorista ===");
        for(EntregasMotorista em : EntregaDAO.totalEntregasMotorista()){
            System.out.println(em);
        }
        System.out.println();
    }

    private static void listarEntregas() {
        for(EntregasLista e : EntregaDAO.listarEntregas()){
            System.out.println(e);
        }
    }

    private static void atualizarStatusEntrega() {
        for(EntregasLista e : EntregaDAO.listarEntregas()){
            if(e.getStatus() == StatusEntrega.EM_ROTA){
                System.out.println(e);
            }
        }

        System.out.println("Digite o id da entrega: ");
        int id = SC.nextInt();

        System.out.println("Digite o Status atual da entrega: ");
        System.out.println("1 - ENTREGUE");
        System.out.println("2 - ATRASADO");
        int opcao = SC.nextInt();

        switch (opcao){
            case 1 -> {
                EntregaDAO.atualizarStatusEntrega(id, StatusEntrega.ENTREGUE, LocalDate.now());
                Entrega entrega = EntregaDAO.buscarEntregaId(id);
                PedidoDAO.atualizarStatusPedido(entrega.getPedidoId(), StatusPedido.ENTREGUE);
            }
            case 2 -> EntregaDAO.atualizarStatusEntrega(id, StatusEntrega.ATRASADA, LocalDate.now());
            default -> System.out.println("Opção Inválida.");
        }
    }

    private static void registrarEventoEntrega() {
        listarEntregas();

        System.out.println("Digite o id da entrega: ");
        int id = SC.nextInt();
        SC.nextLine();

        System.out.println("Digite a descrição do histórico da entrega: ");
        String descricao = SC.nextLine();

        HistoricoEntrega historico = new HistoricoEntrega(id, LocalDate.now(), descricao);
        HistoricoEntregaDAO.registrarEventoEntrega(historico);
    }

    private static void atribuirPedidoMotorista() {
        for(Pedido p : PedidoDAO.listarPedido()){
            if(p.getStatus() == StatusPedido.PENDENTE){
                System.out.println(p);
            }
        }

        System.out.println("Digite o id do pedido: ");
        int id = SC.nextInt();

        for(Motorista m : MotoristaDAO.listarMotoristas()){
            System.out.println(m);
        }

        System.out.println("Digite o id do motorista: ");
        int motoristaId = SC.nextInt();

        Entrega entrega = new Entrega(id, motoristaId, LocalDate.now(), StatusEntrega.EM_ROTA);
        EntregaDAO.atribuirPedido(entrega);
    }

    private static void criarPedido() {
        for(Cliente c : ClienteDAO.listarClientes()){
            System.out.println(c);
        }

        System.out.println("Digite o id do cliente que fez o pedido: ");
        int clienteId = SC.nextInt();

        System.out.println("Digite a quantidade de caixas: ");
        int volumeM3 = SC.nextInt();

        System.out.println("Digite o peso em quilos do pedido: ");
        double peso = SC.nextDouble();

        Pedido pedido = new Pedido(clienteId, LocalDate.now() ,volumeM3, peso, StatusPedido.PENDENTE);
        PedidoDAO.criarPedido(pedido);
    }

    private static void cadastrarMotorista() {
        System.out.println("Digite o nome do motorista: ");
        String nome = SC.nextLine();

        System.out.println("Digite a CNH do motorista: ");
        String cnh = SC.nextLine();

        System.out.println("Digite o veiculo do motorista: ");
        String veiculo = SC.nextLine();

        System.out.println("Digite a cidade base do motorista: ");
        String cidadeBase = SC.nextLine();

        Motorista motorista = new Motorista(nome, cnh, veiculo, cidadeBase);
        MotoristaDAO.cadastrarMotorista(motorista);
    }

    private static void cadastrarCliente() {
        System.out.println("Digite o nome do cliente: ");
        String nome = SC.nextLine();

        System.out.println("Digite o CPF/CNPJ do cliente: ");
        String cpfCnpj = SC.nextLine();
        System.out.println("Digite o endereço do cliente: ");
        String endereco = SC.nextLine();

        System.out.println("Digite a cidade do cliente: ");
        String cidade = SC.nextLine();

        System.out.println("Digite o estado do cliente: ");
        String estado = SC.nextLine();

        Cliente cliente = new Cliente(nome, cpfCnpj, endereco, cidade, estado);
        ClienteDAO.cadastrarCliente(cliente);
    }

}