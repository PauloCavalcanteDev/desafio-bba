package br.com.devpaulo.desafiobba.core.domain.cliente;

import br.com.devpaulo.desafiobba.core.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "cliente")
@Table(name = "tb_cliente")
public class Cliente implements Serializable {

    @Id
    @Column(name = "cpf", length = 11)
    @NonNull
    private String cpf;
    @NonNull
    @Column(name = "nome")
    private String nome;
    @NonNull
    @OneToMany(mappedBy = "cliente")
    private List<Endereco> enderecos;

}
