package br.com.devpaulo.desafiobba.core.domain.cliente;

import br.com.devpaulo.desafiobba.core.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "cliente")
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @Column(name = "cpf", length = 11)
    private String cpf;
    @Column(name = "nome")
    private String nome;
    @OneToMany(mappedBy = "cliente")
    private List<Endereco> enderecos;

}
