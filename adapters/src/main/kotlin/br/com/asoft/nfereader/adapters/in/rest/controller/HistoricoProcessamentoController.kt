package br.com.asoft.nfereader.adapters.`in`.rest.controller

import br.com.asoft.nfereader.adapters.out.persistence.mapper.HistoricoProcessamentoMapper.toDto
import br.com.asoft.nfereader.application.port.`in`.service.HistoricoProcessamentoServicePort
import br.com.asoft.nfereader.application.port.`in`.service.SecurityUtilPort
import br.com.asoft.nfereader.controller.HistoricoProcessamentoApi
import br.com.asoft.nfereader.model.HistoricoProcessamentoDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class HistoricoProcessamentoController(
    private val historicoProcessamentoService: HistoricoProcessamentoServicePort,
    private val securityUtil: SecurityUtilPort
) : HistoricoProcessamentoApi {
    override fun getAllErros(): ResponseEntity<List<HistoricoProcessamentoDTO>> {
        val list = this.historicoProcessamentoService.getAllErros(securityUtil.getIdentityId())
        return ResponseEntity.ok(list.map { it.toDto() })
    }

}