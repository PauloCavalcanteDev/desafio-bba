package br.com.devpaulo.desafiobba.infra.database;

import br.com.devpaulo.desafiobba.core.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
