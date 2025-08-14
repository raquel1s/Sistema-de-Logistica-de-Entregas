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

    private static void listarEntregas() {
        for(EntregasLista e : EntregaDAO.listarEntregas()){
            System.out.println(e);
        }
    }

    private static void atualizarStatusEntrega() {
        listarEntregas();

        System.out.println("Digite o id da entrega: ");
        int id = SC.nextInt();

    }

    private static void registrarEventoEntrega() {
        listarEntregas();

        System.out.println("Digite o id da entrega: ");
        int id = SC.nextInt();

        System.out.println("Digite a descrição do histórico da entrega: ");
        String descricao = SC.nextLine();

        HistoricoEntrega historico = new HistoricoEntrega(id, LocalDate.now(), descricao);
        HistoricoEntregaDAO.registrarEventoEntrega(historico);
    }

    private static void atribuirPedidoMotorista() {
        for(Pedido p : PedidoDAO.listarPedido()){
            System.out.println(p);
        }

        System.out.println("Digite o id do pedido: ");
        int id = SC.nextInt();

        for(Motorista m : MotoristaDAO.listarMotoristas()){
            System.out.println(m);
        }

        System.out.println("Digite o id do motorista: ");
        int motoristaId = SC.nextInt();

        Entrega entrega = new Entrega(id, motoristaId, LocalDate.now());
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