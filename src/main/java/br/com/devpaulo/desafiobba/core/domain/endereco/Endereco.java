package br.com.devpaulo.desafiobba.core.domain.endereco;

import br.com.devpaulo.desafiobba.core.domain.cliente.Cliente;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


@Data
@Entity(name = "endereco")
@Table(name = "tb_endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "endereco_id")
    private UUID Id;
    @ManyToOne
    @JoinColumn(name = "cpf")
    private Cliente cliente;
    @Column(name = "cep", length = 8)
    private String cep;
    @Column(name = "logadouro")
    private String logradouro;
    @Column(name = "complemento")
    private String complemento;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "localidade")
    private String localidade;
    @Column(name = "uf")
    private String uf;
    @Column(name = "numero")
    private Integer numero;

}
