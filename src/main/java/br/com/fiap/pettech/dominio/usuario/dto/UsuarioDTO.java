package br.com.fiap.pettech.dominio.usuario.dto;

import br.com.fiap.pettech.dominio.usuario.entity.Usuario;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class UsuarioDTO {

    private UUID id;

    @NotBlank(message = "Informe o nome do usuário.")
    private String username;

    @NotBlank(message = "Informe a senha do usuário.")
    private String password;

    public UsuarioDTO() {
    }

    public UsuarioDTO(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UsuarioDTO(Usuario entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.password = entity.getPassword();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
