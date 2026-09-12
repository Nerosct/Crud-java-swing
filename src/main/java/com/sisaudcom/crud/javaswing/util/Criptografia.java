package com.sisaudcom.crud.javaswing.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {

    public static String sha256(String texto) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(
                    texto.getBytes(StandardCharsets.UTF_8)
            );

            StringBuilder hexadecimal = new StringBuilder();

            for (byte b : hash) {
                hexadecimal.append(String.format("%02x", b));
            }

            return hexadecimal.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar SHA-256", e);
        }
    }
}