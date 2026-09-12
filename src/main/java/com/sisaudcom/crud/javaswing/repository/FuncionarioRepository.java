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
import java.util.Date;
import java.util.List;
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

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());

            stmt.setDate(
                    2,
                    new java.sql.Date(
                            funcionario.getDataDeAdmissao().getTime()
                    )
            );

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

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Funcionario funcionario = new Funcionario();

                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setDataDeAdmissao(
                        rs.getDate("data_admissao")
                );
                funcionario.setSalario(
                        rs.getDouble("salario")
                );
                funcionario.setStatus(
                        rs.getBoolean("status")
                );

                funcionarios.add(funcionario);
            }
        }

        return funcionarios;
    }
}
