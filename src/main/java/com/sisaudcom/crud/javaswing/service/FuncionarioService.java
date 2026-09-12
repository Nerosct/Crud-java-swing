/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sisaudcom.crud.javaswing.service;

import com.sisaudcom.crud.javaswing.repository.FuncionarioRepository;
import com.sisaudcom.crud.javaswing.model.Funcionario;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author neros
 */
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService() {
        this.funcionarioRepository = new FuncionarioRepository();
    }

    public void cadastrar(Funcionario funcionario) throws SQLException {
        validarCadastro(funcionario);
        funcionarioRepository.salvar(funcionario);
    }

    public List<Funcionario> listarTodos() throws SQLException {
        return funcionarioRepository.listarTodos();
    }

    private void validarCadastro(Funcionario funcionario) {

        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário inválido.");
        }
        if (funcionario.getNome() == null || funcionario.getNome().isBlank()) {
            throw new IllegalArgumentException("Informe o nome do funcionário.");
        }

        if (!funcionario.getNome().matches("[a-zA-ZÀ-ÿ ]+")) {
            throw new IllegalArgumentException(
                    "O nome deve conter apenas letras e espaços."
            );
        }
        
        if (funcionario.getDataDeAdmissao() == null) {
            throw new IllegalArgumentException("Informe a data de admissão.");
        }
        if (funcionario.getDataDeAdmissao().after(new Date())) {
            throw new IllegalArgumentException("A data de admissão não pode ser futura.");
        }
        if (funcionario.getSalario() <= 0) {
            throw new IllegalArgumentException("O salário deve ser maior que zero.");
        }

    }

}
