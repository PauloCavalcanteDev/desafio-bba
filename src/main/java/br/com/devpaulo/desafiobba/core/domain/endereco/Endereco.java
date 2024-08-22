package br.com.devpaulo.desafiobba.core.domain.endereco;

import br.com.devpaulo.desafiobba.core.domain.cliente.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.UUID;


@Entity(name = "endereco")
@Table(name = "tb_endereco")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "endereco_id")
    private UUID Id;
    @ManyToOne
    @JoinColumn(name = "cpf")
    @NonNull
    private Cliente cliente;
    @Column(name = "cep", length = 8)
    @NonNull
    private String cep;
    @Column(name = "logadouro")
    @NonNull
    private String logradouro;
    @Column(name = "complemento")
    @NonNull
    private String complemento;
    @Column(name = "bairro")
    @NonNull
    private String bairro;
    @NonNull
    @Column(name = "cidade")
    private String localidade;
    @Column(name = "uf")
    @NonNull
    private String uf;
}
