/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sisaudcom.crud.javaswing.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author neros
 */
public class InicializadorBanco {

    public static void inicializar() throws SQLException {

        String sqlUsuarios = """
                CREATE TABLE IF NOT EXISTS usuarios (
                    id SERIAL PRIMARY KEY,
                    nome VARCHAR(100) NOT NULL,
                    email VARCHAR(150) NOT NULL UNIQUE,
                    senha VARCHAR(64) NOT NULL
                )
                """;

        String sqlFuncionarios = """
                CREATE TABLE IF NOT EXISTS funcionarios (
                    id SERIAL PRIMARY KEY,
                    nome VARCHAR(100) NOT NULL,
                    data_admissao DATE NOT NULL,
                    salario NUMERIC(12, 2) NOT NULL,
                    status BOOLEAN NOT NULL
                )
                """;

        try (Connection conexao = Conexao.conectar(); Statement stmt = conexao.createStatement()) {

            stmt.executeUpdate(sqlUsuarios);
            stmt.executeUpdate(sqlFuncionarios);
        }
    }
}
