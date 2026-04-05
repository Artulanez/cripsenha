package br.com.cripsenha.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String nome;
    private String senha;
    private String email;
    private String telefone;
}
