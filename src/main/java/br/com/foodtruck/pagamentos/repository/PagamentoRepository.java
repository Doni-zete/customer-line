package br.com.foodtruck.pagamentos.repository;

import br.com.foodtruck.pagamentos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
