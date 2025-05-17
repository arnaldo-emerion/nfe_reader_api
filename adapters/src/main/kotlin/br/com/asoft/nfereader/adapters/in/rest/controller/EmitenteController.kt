package br.com.asoft.nfereader.adapters.`in`.rest.controller

import br.com.asoft.nfereader.adapters.out.persistence.mapper.EmitenteMapper.toDto
import br.com.asoft.nfereader.application.port.`in`.service.EmitenteServicePort
import br.com.asoft.nfereader.application.port.`in`.service.SecurityUtilPort
import br.com.asoft.nfereader.controller.EmitenteApi
import br.com.asoft.nfereader.model.EmitenteDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class EmitenteController(
    private val emitenteService: EmitenteServicePort,
    private val securityUtil: SecurityUtilPort
) : EmitenteApi {
    override fun getAllEmitent(): ResponseEntity<List<EmitenteDTO>> {
        val list = this.emitenteService.getAll(
            securityUtil.getIdentityId()
        )
        return ResponseEntity.ok(list.map { it.toDto() })
    }
}