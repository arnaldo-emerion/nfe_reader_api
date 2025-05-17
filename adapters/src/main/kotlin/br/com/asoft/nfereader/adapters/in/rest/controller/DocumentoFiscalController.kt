package br.com.asoft.nfereader.adapters.`in`.rest.controller

import br.com.asoft.nfereader.application.port.`in`.service.DocumentoFiscalServicePort
import br.com.asoft.nfereader.application.port.`in`.service.SecurityUtilPort
import br.com.asoft.nfereader.application.port.`in`.service.UserConfigurationServicePort
import br.com.asoft.nfereader.model.DocumentoFiscalHeader
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("documento-fiscal")
class DocumentoFiscalController(
    private val documentoFiscalService: DocumentoFiscalServicePort,
    private val userConfigurationService: UserConfigurationServicePort,
    private val securityUtil: SecurityUtilPort
) {
    @GetMapping("cabecalho")
    fun getAllCabecalho(): List<DocumentoFiscalHeader> {
        return documentoFiscalService.getDocumentoFiscalHeader(
            securityUtil.getIdentityId(),
            null,
            null,
            natOpByUserList
        )
    }

    private val natOpByUserList: List<String>
        get() = userConfigurationService.getConfigurationForUser().getNFeProcessaveis()
}
