package com.sisaudcom.crud.javaswing.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    
    // Não é recomendado colocar as variáveis de conexão e senhas no código, fiz dessa forma para facilitar a avaliação
    // num outro cenário, criaria variáveis de ambiente
    private static final String URL = "jdbc:postgresql://localhost:5432/app-db";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
