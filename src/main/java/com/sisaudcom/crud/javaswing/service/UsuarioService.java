/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sisaudcom.crud.javaswing.service;

import com.sisaudcom.crud.javaswing.model.Usuario;
import com.sisaudcom.crud.javaswing.repository.UsuarioRepository;
import com.sisaudcom.crud.javaswing.util.Criptografia;

import java.sql.SQLException;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepository();
    }

    public void cadastrar(Usuario usuario, String confirmarSenha)
            throws SQLException {

        validarCadastro(usuario);

        if (!usuario.getSenha().equals(confirmarSenha)) {
            throw new IllegalArgumentException(
                    "As senhas não coincidem."
            );
        }

        if (!usuario.getNome().matches("[a-zA-ZÀ-ÿ ]+")) {
            throw new IllegalArgumentException(
                    "O nome deve conter apenas letras e espaços."
            );
        }

        if (usuarioRepository.buscarPorEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Já existe um usuário cadastrado com este e-mail.");
        }

        String senhaCriptografada
                = Criptografia.sha256(usuario.getSenha());

        usuario.setSenha(senhaCriptografada);

        usuarioRepository.salvar(usuario);
    }

    public Usuario login(String email, String senha) throws SQLException {

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "Informe o e-mail."
            );
        }

        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException(
                    "Informe a senha."
            );
        }

        Usuario usuario = usuarioRepository.buscarPorEmail(email.trim())
                .orElseThrow(() -> new IllegalArgumentException("E-mail ou senha inválidos."));

        String senhaCriptografada
                = Criptografia.sha256(senha);

        if (!senhaCriptografada.equals(usuario.getSenha())) {
            throw new IllegalArgumentException(
                    "E-mail ou senha inválidos."
            );
        }

        return usuario;
    }

    private void validarCadastro(Usuario usuario) {

        if (usuario == null) {
            throw new IllegalArgumentException(
                    "Usuário inválido."
            );
        }

        if (usuario.getNome() == null
                || usuario.getNome().isBlank()) {

            throw new IllegalArgumentException(
                    "Informe o nome."
            );
        }

        if (!usuario.getNome().matches("[a-zA-ZÀ-ÿ ]+")) {
            throw new IllegalArgumentException(
                    "O nome deve conter apenas letras e espaços."
            );
        }

        if (usuario.getEmail() == null
                || usuario.getEmail().isBlank()) {

            throw new IllegalArgumentException(
                    "Informe o e-mail."
            );
        }

        String email = usuario.getEmail().trim();

        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException(
                    "Informe um e-mail válido."
            );
        }

        usuario.setEmail(email);

        if (usuario.getSenha() == null
                || usuario.getSenha().isBlank()) {

            throw new IllegalArgumentException(
                    "Informe a senha."
            );
        }
    }
}
