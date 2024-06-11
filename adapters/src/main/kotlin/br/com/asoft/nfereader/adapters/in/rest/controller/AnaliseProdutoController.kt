package br.com.asoft.nfereader.adapters.`in`.rest.controller

import br.com.asoft.nfereader.adapters.`in`.rest.mapper.AnaliseProdutoMapper.toDto
import br.com.asoft.nfereader.adapters.`in`.rest.mapper.CurvaABCMapper.toDto
import br.com.asoft.nfereader.application.port.`in`.service.ProdutoServicePort
import br.com.asoft.nfereader.controller.AnaliseProdutosApi
import br.com.asoft.nfereader.model.PedidosDiaADiaDTO
import br.com.asoft.nfereader.model.ProdutoCurvaABCDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AnaliseProdutoController(private val produtoService: ProdutoServicePort) : AnaliseProdutosApi {
    override fun getEstatisticaProdutoByVolume(): ResponseEntity<List<ProdutoCurvaABCDTO>> {
        val list = this.produtoService.getEstatisticaProdutoByVolume()
        return ResponseEntity.ok(list.map { it.toDto() })
    }

    override fun getEstatisticaProdutoByFaturamento(): ResponseEntity<List<ProdutoCurvaABCDTO>> {
        val list = this.produtoService.getEstatisticaProdutoByFaturamento()
        return ResponseEntity.ok(list.map { it.toDto() })
    }

    override fun getEstatisticaProdutoByFrequencia(): ResponseEntity<List<ProdutoCurvaABCDTO>> {
        val list = this.produtoService.getEstatisticaProdutoByFrequencia()
        return ResponseEntity.ok(list.map { it.toDto() })
    }

    override fun getQtdProdutoDiaADia(): ResponseEntity<List<PedidosDiaADiaDTO>> {
        val list = this.produtoService.getQtdProdutosDiaADia()
        return ResponseEntity.ok(list.map { it.toDto() })
    }

    override fun getTotaisEstatisticaProduto(codigo: String): ResponseEntity<ProdutoCurvaABCDTO> {
        val produtoCurvaABC = this.produtoService.getTotaisEstatisticaProduto(codigo = codigo)
        return ResponseEntity.ok(produtoCurvaABC.toDto())
    }
}