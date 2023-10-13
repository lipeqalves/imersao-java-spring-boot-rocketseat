package br.com.filipealves.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data // usando a lib lombok para adicionar os getters e setters
@Entity(name = "tb_users") // indentificação deuma tabela de usuario
public class UserModel {

    @Id // informa que é uma chave primaria
    @GeneratedValue(generator = "UUID")
    private UUID id;// gera um indentificador unico

    @Column(unique = true)
    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // getters setters

    // public void setUsername(String username) {
    // this.username = username;
    // }

    // public String getUsername() {
    // return username;
    // }

    // public void setName(String name) {
    // this.name = name;
    // }

    // public String getName() {
    // return name;
    // }

    // public void setPassword(String password) {
    // this.password = password;
    // }

    // public String getPassword() {
    // return password;
    // }

}
