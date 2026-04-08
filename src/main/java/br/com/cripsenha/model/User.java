package br.com.cripsenha.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;

    @Column(name = "name", nullable = false)
    private String nome;

    @Column(name = "password", nullable = false)
    private String senha;

    @Column(name = "email", nullable = false) // Removido o unique=true aqui
    private String email;

    @Column(name = "phone")
    private String telefone;
}
