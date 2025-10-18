package DTO;

import java.util.Date;

public class PedidoDTO {
    private int idPedido;
    private int idCliente;
    private Date dataPedido;
    private double valorTotal;

    public PedidoDTO() {
    }

    public PedidoDTO(int idPedido, int idCliente, Date dataPedido, double valorTotal) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Pedido #" + idPedido + " | Cliente: " + idCliente + " | Total: R$ " + valorTotal;
    }
}
