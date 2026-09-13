/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sisaudcom.crud.javaswing.repository;

import com.sisaudcom.crud.javaswing.connection.Conexao;
import com.sisaudcom.crud.javaswing.model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author neros
 */
public class FuncionarioRepository {

    public void salvar(Funcionario funcionario) throws SQLException {

        String sql = """
                INSERT INTO funcionarios
                (nome, data_admissao, salario, status)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setDate(2, toSqlDate(funcionario.getDataDeAdmissao()));
            stmt.setDouble(3, funcionario.getSalario());
            stmt.setBoolean(4, funcionario.isStatus());

            stmt.executeUpdate();
        }
    }

    public List<Funcionario> listarTodos() throws SQLException {

        String sql = """
                SELECT id, nome, data_admissao, salario, status
                FROM funcionarios
                ORDER BY id
                """;

        List<Funcionario> funcionarios = new ArrayList<>();

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                funcionarios.add(mapear(rs));
            }
        }

        return funcionarios;
    }

    public Optional<Funcionario> buscarPorId(int id) throws SQLException {

        String sql = """
                SELECT id, nome, data_admissao, salario, status
                FROM funcionarios
                WHERE id = ?
                """;

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapear(rs));
                }
            }
        }

        return Optional.empty();
    }

    public void atualizar(Funcionario funcionario) throws SQLException {

        String sql = """
                UPDATE funcionarios
                SET nome = ?,
                    data_admissao = ?,
                    salario = ?,
                    status = ?
                WHERE id = ?
                """;

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setDate(2, toSqlDate(funcionario.getDataDeAdmissao()));
            stmt.setDouble(3, funcionario.getSalario());
            stmt.setBoolean(4, funcionario.isStatus());
            stmt.setInt(5, funcionario.getId());

            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {

        String sql = "DELETE FROM funcionarios WHERE id = ?";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Funcionario mapear(ResultSet rs) throws SQLException {

        Funcionario funcionario = new Funcionario();

        funcionario.setId(rs.getInt("id"));
        funcionario.setNome(rs.getString("nome"));
        funcionario.setDataDeAdmissao(rs.getDate("data_admissao"));
        funcionario.setSalario(rs.getDouble("salario"));
        funcionario.setStatus(rs.getBoolean("status"));

        return funcionario;
    }

    private java.sql.Date toSqlDate(java.util.Date data) {
        return new java.sql.Date(data.getTime());
    }
}
