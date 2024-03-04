package com.grandel.storj.dto;

public class UtenteDTO {
    private Long id;
    private String username;
    private String password;
    private boolean statoPagamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public boolean isStatoPagamento() {
        return statoPagamento;
    }

    public void setStatoPagamento(boolean statoPagamento) {
        this.statoPagamento = statoPagamento;
    }
}
