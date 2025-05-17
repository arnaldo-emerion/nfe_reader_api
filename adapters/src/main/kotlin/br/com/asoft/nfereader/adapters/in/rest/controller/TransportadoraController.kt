package br.com.asoft.nfereader.adapters.`in`.rest.controller

import br.com.asoft.nfereader.adapters.`in`.rest.mapper.TransportadoraMapper.toDto
import br.com.asoft.nfereader.application.port.`in`.service.SecurityUtilPort
import br.com.asoft.nfereader.application.port.`in`.service.TransportadoraServicePort
import br.com.asoft.nfereader.controller.TransportadoraApi
import br.com.asoft.nfereader.model.TransportadoraDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class TransportadoraController(
    private val transportadoraService: TransportadoraServicePort,
    private val securityUtil: SecurityUtilPort
) : TransportadoraApi {
    override fun getAllCabecalho(): ResponseEntity<List<TransportadoraDTO>> {
        val list = this.transportadoraService.getAll(securityUtil.getIdentityId())
        return ResponseEntity.ok(list.map { it.toDto() })
    }
}