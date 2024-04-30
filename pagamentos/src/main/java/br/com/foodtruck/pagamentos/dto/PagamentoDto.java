package br.com.foodtruck.pagamentos.dto;

import br.com.foodtruck.pagamentos.util.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PagamentoDto {
  private Long id;
  private BigDecimal valor;
  private String nome;
  private String numero;
  private String expiracao;
  private String codigo;
  private Status status;
  private Long formaDePagamentoId;
  private Long pedidoId;
}
