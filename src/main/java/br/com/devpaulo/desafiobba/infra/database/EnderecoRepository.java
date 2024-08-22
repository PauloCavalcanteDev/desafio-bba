package br.com.devpaulo.desafiobba.infra.database;

import br.com.devpaulo.desafiobba.core.domain.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
}
