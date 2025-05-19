package br.com.asoft.nfereader.adapters.`in`.rest.controller

import br.com.asoft.nfereader.adapters.out.persistence.mapper.NFeMapper.toDto
import br.com.asoft.nfereader.application.port.`in`.service.NFeItemServicePort
import br.com.asoft.nfereader.application.port.`in`.service.NFeServicePort
import br.com.asoft.nfereader.application.port.`in`.service.SecurityUtilPort
import br.com.asoft.nfereader.application.port.`in`.service.UserConfigurationServicePort
import br.com.asoft.nfereader.controller.NfeApi
import br.com.asoft.nfereader.model.NFeCompletaDTO
import br.com.asoft.nfereader.model.ProdutoListagemNotaDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class NFeController(
    private val nFeServicePort: NFeServicePort,
    private val nFeItemServicePort: NFeItemServicePort,
    private val securityUtil: SecurityUtilPort,
    private val userConfigurationService: UserConfigurationServicePort,
) : NfeApi {
    override fun getByCodProd(cProd: String): ResponseEntity<List<ProdutoListagemNotaDTO>> {
        val list = this.nFeServicePort.getCabecalhoNFePorProduto(
            identityId = securityUtil.getIdentityId(),
            pCodigo = cProd,
            pCpfCnpj = null,
            natOperacaoList = userConfigurationService.getNFeProcessaveis()
        )
        return ResponseEntity.ok(list.map { it.toDto() })
    }

    override fun getByCpfCnpj(@PathVariable("cpfCnpj") cpfCnpj: String): ResponseEntity<List<ProdutoListagemNotaDTO>> {
        val list = this.nFeServicePort.getCabecalhoNFePorProduto(
            identityId = securityUtil.getIdentityId(),
            pCodigo = null,
            pCpfCnpj = cpfCnpj,
            natOperacaoList = userConfigurationService.getNFeProcessaveis()
        )
        return ResponseEntity.ok(list.map { it.toDto() })
    }

    override fun getByNFeId(id: Long): ResponseEntity<NFeCompletaDTO> {
        val nFeById = this.nFeServicePort.getNFeById(
            identityId = securityUtil.getIdentityId(),
            id = id
        )
        val itemList = this.nFeItemServicePort.findByNfeId(identityId = securityUtil.getIdentityId(), nfeId = id)
        return ResponseEntity.ok(nFeById.toDto(itemList))
    }

}