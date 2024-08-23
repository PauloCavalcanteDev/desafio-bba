package br.com.devpaulo.desafiobba.core.domain.cliente;

import br.com.devpaulo.desafiobba.core.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.List;


@Entity(name = "cliente")
@Table(name = "tb_cliente")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Cliente {

    @Id
    @Column(name = "cpf", length = 11)
    @NonNull
    private String cpf;
    @NonNull
    @Column(name = "nome")
    private String nome;
    @OneToMany(mappedBy = "cliente")
    private List<Endereco> enderecos;

}
