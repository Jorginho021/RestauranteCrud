package DAO;

import DTO.ClienteDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClienteDAO {

    // Cadastrar um cliente
    public void cadastrar(ClienteDTO c) {
        String sql = "INSERT INTO clientes (nome, telefone, email) VALUES (?, ?, ?)";
        try (Connection conexao = bancoDAO.conectar();
                PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setString(1, c.getNome());
            pst.setString(2, c.getTelefone());
            pst.setString(3, c.getEmail());
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    // Atualizar cliente pelo ID
    public void atualizar(ClienteDTO c) {
        String sql = "UPDATE clientes SET nome = ?, telefone = ?, email = ? WHERE id_cliente = ?";
        try (Connection conexao = bancoDAO.conectar();
                PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setString(1, c.getNome());
            pst.setString(2, c.getTelefone());
            pst.setString(3, c.getEmail());
            pst.setInt(4, c.getIdCliente());

            int linhas = pst.executeUpdate();

            if (linhas > 0) {
                JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    // Deletar cliente pelo ID
    public void deletar(int idCliente) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        try (Connection conexao = bancoDAO.conectar();
                PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setInt(1, idCliente);
            int linhas = pst.executeUpdate();

            if (linhas > 0) {
                JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar cliente: " + e.getMessage());
        }
    }

    // Listar todos os clientes
    public ArrayList<ClienteDTO> listarTodos() {
        ArrayList<ClienteDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection conexao = bancoDAO.conectar();
                PreparedStatement pst = conexao.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                ClienteDTO c = new ClienteDTO();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                lista.add(c);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes: " + e.getMessage());
        }

        return lista;
    }

    // Buscar cliente pelo nome
    public ClienteDTO buscarPorNome(String nome) {
        ClienteDTO c = null;
        String sql = "SELECT * FROM clientes WHERE nome = ?";

        try (Connection conexao = bancoDAO.conectar();
                PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setString(1, nome);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    c = new ClienteDTO();
                    c.setIdCliente(rs.getInt("id_cliente"));
                    c.setNome(rs.getString("nome"));
                    c.setTelefone(rs.getString("telefone"));
                    c.setEmail(rs.getString("email"));
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar cliente: " + e.getMessage());
        }

        return c;
    }

    public void atualizar(String nomeAntigo, ClienteDTO c) {
        String sql = "UPDATE clientes SET nome = ?, telefone = ?, email = ? WHERE nome = ?";
        try (Connection conexao = bancoDAO.conectar();
                PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setString(1, c.getNome());
            pst.setString(2, c.getTelefone());
            pst.setString(3, c.getEmail());
            pst.setString(4, nomeAntigo);

            int linhas = pst.executeUpdate();

            if (linhas > 0) {
                JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente: " + e.getMessage());
        }
    }

// Deletar cliente pelo nome
    public void deletar(String nome) {
        String sql = "DELETE FROM clientes WHERE nome = ?";
        try (Connection conexao = bancoDAO.conectar();
                PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setString(1, nome);
            int linhas = pst.executeUpdate();

            if (linhas > 0) {
                JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar cliente: " + e.getMessage());
        }
    }
}
