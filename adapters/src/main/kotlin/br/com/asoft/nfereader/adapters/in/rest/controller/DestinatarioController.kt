package br.com.asoft.nfereader.adapters.`in`.rest.controller

import br.com.asoft.nfereader.adapters.`in`.rest.mapper.AnaliseClienteMapper.toDto
import br.com.asoft.nfereader.application.port.`in`.service.DestinatarioServicePort
import br.com.asoft.nfereader.application.port.`in`.service.SecurityUtilPort
import br.com.asoft.nfereader.controller.DestinatarioApi
import br.com.asoft.nfereader.model.DestinatarioCabecalhoDTO
import br.com.asoft.nfereader.model.DestinatarioDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class DestinatarioController(
    private val destinatarioService: DestinatarioServicePort,
    private val securityUtil: SecurityUtilPort
) : DestinatarioApi {
    override fun getDestinatariosCabecalho(): ResponseEntity<List<DestinatarioCabecalhoDTO>> {
        val list =
            this.destinatarioService.getDestinatarioCabecalhoByUserCreate(securityUtil.getIdentityId())
        return ResponseEntity.ok(list.map { it.toDto() })
    }

    override fun getDestinatarioById(id: Long): ResponseEntity<DestinatarioDTO> {
        val destinatario = this.destinatarioService.getByIdAndUserCreate(
            id = id,
            userCreate = securityUtil.getIdentityId()
        )
        return ResponseEntity.ok(destinatario.toDto());
    }
}