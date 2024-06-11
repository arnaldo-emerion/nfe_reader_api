package br.com.asoft.nfereader.adapters.`in`.rest.controller

import br.com.asoft.nfereader.adapters.`in`.rest.mapper.AnaliseProdutoMapper.toDto
import br.com.asoft.nfereader.application.port.`in`.service.ProdutoServicePort
import br.com.asoft.nfereader.controller.ProdutosApi
import br.com.asoft.nfereader.model.ProdutoDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class ProdutoController(private val produtoService: ProdutoServicePort) : ProdutosApi {
    override fun getProdutosCabecalho(): ResponseEntity<List<ProdutoDTO>> {
        val list = this.produtoService.getProdutoByUserCreateAndCodigo()
        return ResponseEntity.ok(list.map { it.toDto() })
    }

    override fun getProdutos(codigo: String): ResponseEntity<ProdutoDTO> {
        val list = this.produtoService.getProdutoByUserCreateAndCodigo(codigo = codigo)
        return ResponseEntity.ok(list.map { it.toDto() }.first())
    }
}