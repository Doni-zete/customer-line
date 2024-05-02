package br.com.foodtruck.pagamentos.controller;

import br.com.foodtruck.pagamentos.dto.PagamentoDto;
import br.com.foodtruck.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;


@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

  @Autowired
  private PagamentoService service;

  @GetMapping
  public Page<PagamentoDto> listar(@PageableDefault(size = 10) Pageable paginacao) {
    return service.obterTodos(paginacao);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PagamentoDto> detalhar(@PathVariable @NotNull Long id) {
    PagamentoDto pagamentoDto = service.obterPorId(id);
    return ResponseEntity.ok(pagamentoDto);
  }

  @PostMapping
  public ResponseEntity<PagamentoDto> cadastrar(
    @RequestBody @Valid PagamentoDto pagamentoDto, UriComponentsBuilder uriBuilder) {
    PagamentoDto pagamento = service.criarPagamento(pagamentoDto);
    URI endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.getId()).toUri();
    return ResponseEntity.created(endereco).body(pagamento);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PagamentoDto> atualizar(
    @PathVariable @NotNull Long id, @RequestBody @Valid PagamentoDto pagamentoDto) {
    PagamentoDto atualizado = service.atualizarPagamento(id, pagamentoDto);
    return ResponseEntity.ok(atualizado);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<PagamentoDto> remover(@PathVariable @NotNull Long id) {
    service.excluirPagamento(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}/confirmar")
  public void confirmarPagamento(@PathVariable @NotNull Long id){
    service.confirmarPagamento(id);
  }

}
