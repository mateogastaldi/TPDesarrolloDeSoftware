/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memory;

import DAO.PedidosDAO;
import exceptions.Pedido.PedidoNoEncontradoException;
import exceptions.cliente.ClienteNoEncontradoException;
import tp.Pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PedidosMemory implements PedidosDAO {
    //UTILIZAMOS PATRON SINGLETON PARA LA CREACION DEL MEMORY
    private static PedidosMemory PEDIDOMEMORY_INTANCE;
    private List<Pedido> pedidos;

    //constructores
    private PedidosMemory(){
        pedidos = new ArrayList<>();
    }
    private PedidosMemory(List<Pedido> p){
        setPedidos(p);
    }

    //getters
    @Override
    public List<Pedido> getPedido(){
        return this.pedidos;
    }
    public static PedidosMemory getInstance(){
        if(PEDIDOMEMORY_INTANCE == null){
            PEDIDOMEMORY_INTANCE = new PedidosMemory();
        }
        return PEDIDOMEMORY_INTANCE;
    }

    //setters
    private void setPedidos(List<Pedido> p){
        this.pedidos = p;
    }

    //metodos
    @Override
    public void addPedido(Pedido v){
        this.pedidos.add(v);
    }

    public Pedido filtrarPedidoPorId(int id) throws PedidoNoEncontradoException{
        Pedido pedido = pedidos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if(pedido == null){
            throw new PedidoNoEncontradoException("No se encontro pedido con el id" + id);
        }
        return pedido;
    }
    public List<Pedido> filtrarPorNombreCliente(String nombre) throws PedidoNoEncontradoException{
        List<Pedido> pedidosFiltrados = this.pedidos.stream()
                .filter(ped -> ped.getCliente().getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());
        if(pedidosFiltrados.isEmpty()){
            throw new PedidoNoEncontradoException("No se encontro pedido con el nombre de cliente" + nombre);
        }
        return pedidosFiltrados;
    }


    public List<Pedido> filtrarPorIdCliente(int id) throws PedidoNoEncontradoException{
        List<Pedido> pedidosFiltrados = this.pedidos.stream()
                .filter(ped -> ped.getCliente().getId() == (id))
                .collect(Collectors.toList());
        if(pedidosFiltrados.isEmpty()){
            throw new PedidoNoEncontradoException("No se encontro pedido con el id de cliente" + id);
        }
        return pedidosFiltrados;
    }

    public void eliminarPedido(int id) throws PedidoNoEncontradoException {
        Pedido pedido = pedidos.stream().filter(c -> c.getId() == id).findFirst().orElse(null);

        if(pedido == null){
            ClienteNoEncontradoException exc = new ClienteNoEncontradoException("Cliente no encontrado");
        }
        else {
            pedidos.remove(pedido);
        }
    }

    public List<Pedido> filtrarPedidoPorVendedor(String nombre) throws PedidoNoEncontradoException{
        List<Pedido> pedidosFiltrados = this.pedidos.stream().filter(p-> p.getVendedor().getNombre().equalsIgnoreCase(nombre)).collect(Collectors.toList());
        if(pedidosFiltrados.isEmpty()){
            throw new PedidoNoEncontradoException("Pedido no encontrado");
        }
        return pedidosFiltrados;
    }


}
