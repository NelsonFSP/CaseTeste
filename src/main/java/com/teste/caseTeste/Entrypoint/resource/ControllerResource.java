package com.teste.caseTeste.Entrypoint.resource;

import com.teste.caseTeste.Entrypoint.dto.Cliente;
import com.teste.caseTeste.Entrypoint.dto.Transacao;
import com.teste.caseTeste.core.usecase.RegraUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value  = "/api")
public class ControllerResource {
    private final RegraUseCase regraUseCase;

    @Autowired
    public ControllerResource(RegraUseCase regraUseCase) {
        this.regraUseCase = regraUseCase;
    }

    @PostMapping(path = "/cadastro/cliente")
    public ResponseEntity<Cliente> cadastraCliente(@RequestBody Cliente cliente) {
        var retorno = regraUseCase.cadastroCliente(cliente);
        return new ResponseEntity<>(retorno, HttpStatus.CREATED);
    }

    @GetMapping(path="/clientes")
    public ResponseEntity<List<Cliente>> listarClientes(){
        var retorno = regraUseCase.buscarClientes();
        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

    @GetMapping(path="/cliente/{numConta}")
    public ResponseEntity<Cliente> cliente(@PathVariable("numConta") String numConta){
        var retorno = regraUseCase.buscarCliente(numConta);
        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

    @PostMapping(path="/transacao", produces = { "application/json" })
    public ResponseEntity<Transacao> transacao(@RequestBody Transacao transacao){
        var retorno = regraUseCase.realizarTransacao(transacao);
        return new ResponseEntity<>(retorno, HttpStatus.CREATED);
    }

    @GetMapping(path="/transacoes/{numConta}")
    public ResponseEntity<List<Transacao>> listarTransacao(@PathVariable("numConta") String numConta){
        var retorno = regraUseCase.listarTransacoes(numConta);
        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }
}
