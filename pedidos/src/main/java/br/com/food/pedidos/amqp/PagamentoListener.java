package br.com.food.pedidos.amqp;

import br.com.food.pedidos.dto.PagamentoDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PagamentoListener {

  @RabbitListener(queues = "pagamentos.concluido")
  public void recebeMensagem(@Payload PagamentoDto pagamentoDto) {
    String mensagem = """
      Dados do pagamento: %s
      Valor R$: %s
      Nome: %s
      Número: %s
      Expiração: %s
      Número do pedido: %s
      Status: %s
      """.formatted(
      pagamentoDto.getId(),
      pagamentoDto.getValor(),
      pagamentoDto.getNome(),
      pagamentoDto.getNumero(),
      pagamentoDto.getExpiracao(),
      pagamentoDto.getPedidoId(),
      pagamentoDto.getStatus());

    System.out.println("Recebi a mensagem " + mensagem);
  }
}
