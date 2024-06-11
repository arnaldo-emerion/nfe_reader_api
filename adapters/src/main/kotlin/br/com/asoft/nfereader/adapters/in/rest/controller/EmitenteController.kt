package br.com.asoft.nfereader.adapters.`in`.rest.controller

import br.com.asoft.nfereader.application.port.`in`.service.EmitenteServicePort
import br.com.asoft.nfereader.application.port.`in`.service.SecurityUtilPort
import br.com.asoft.nfereader.model.Emitente
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("emitente")
class EmitenteController(
    private val emitenteService: EmitenteServicePort,
    private val securityUtil: SecurityUtilPort
) {
    @GetMapping
    fun getAll(): List<Emitente> {
        return emitenteService.getAll(
            securityUtil.getIdentityId()
        )
    }

}
