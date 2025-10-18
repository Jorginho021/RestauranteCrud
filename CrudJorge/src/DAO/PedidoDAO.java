package DAO;

import DTO.PedidoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PedidoDAO {

    // Cadastrar pedido
    public void cadastrar(PedidoDTO p) {
        String sql = "INSERT INTO pedidos (id_cliente, data_pedido, valor_total) VALUES (?, ?, ?)";
        try (Connection conexao = bancoDAO.conectar();
             PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setInt(1, p.getIdCliente());
            pst.setDate(2, new Date(p.getDataPedido().getTime()));
            pst.setDouble(3, p.getValorTotal());
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar pedido: " + e.getMessage());
        }
    }

    // Atualizar pedido
    public void atualizar(PedidoDTO p) {
        String sql = "UPDATE pedidos SET id_cliente = ?, data_pedido = ?, valor_total = ? WHERE id_pedido = ?";
        try (Connection conexao = bancoDAO.conectar();
             PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setInt(1, p.getIdCliente());
            pst.setDate(2, new Date(p.getDataPedido().getTime()));
            pst.setDouble(3, p.getValorTotal());
            pst.setInt(4, p.getIdPedido());

            int linhas = pst.executeUpdate();

            if (linhas > 0) {
                JOptionPane.showMessageDialog(null, "Pedido atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Pedido não encontrado!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar pedido: " + e.getMessage());
        }
    }

    // Deletar pedido
    public void deletar(int idPedido) {
        String sql = "DELETE FROM pedidos WHERE id_pedido = ?";
        try (Connection conexao = bancoDAO.conectar();
             PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setInt(1, idPedido);
            int linhas = pst.executeUpdate();

            if (linhas > 0) {
                JOptionPane.showMessageDialog(null, "Pedido deletado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Pedido não encontrado!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar pedido: " + e.getMessage());
        }
    }

    // Listar todos os pedidos
    public ArrayList<PedidoDTO> listarTodos() {
        ArrayList<PedidoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";

        try (Connection conexao = bancoDAO.conectar();
             PreparedStatement pst = conexao.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                PedidoDTO p = new PedidoDTO();
                p.setIdPedido(rs.getInt("id_pedido"));
                p.setIdCliente(rs.getInt("id_cliente"));
                p.setDataPedido(rs.getDate("data_pedido"));
                p.setValorTotal(rs.getDouble("valor_total"));
                lista.add(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar pedidos: " + e.getMessage());
        }

        return lista;
    }

    // Buscar pedido por ID
    public PedidoDTO buscarPorId(int idPedido) {
        PedidoDTO p = null;
        String sql = "SELECT * FROM pedidos WHERE id_pedido = ?";

        try (Connection conexao = bancoDAO.conectar();
             PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setInt(1, idPedido);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    p = new PedidoDTO();
                    p.setIdPedido(rs.getInt("id_pedido"));
                    p.setIdCliente(rs.getInt("id_cliente"));
                    p.setDataPedido(rs.getDate("data_pedido"));
                    p.setValorTotal(rs.getDouble("valor_total"));
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar pedido: " + e.getMessage());
        }

        return p;
    }
}
