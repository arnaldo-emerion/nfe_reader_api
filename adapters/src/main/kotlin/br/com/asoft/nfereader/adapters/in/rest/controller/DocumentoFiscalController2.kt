package br.com.asoft.nfereader.adapters.`in`.rest.controller

import br.com.asoft.nfereader.adapters.out.persistence.mapper.DocumentoFiscalMapper.toDto
import br.com.asoft.nfereader.application.port.`in`.service.DocumentoFiscalServicePort
import br.com.asoft.nfereader.application.port.`in`.service.SecurityUtilPort
import br.com.asoft.nfereader.application.port.`in`.service.UserConfigurationServicePort
import br.com.asoft.nfereader.controller.DocumentoFiscalApi
import br.com.asoft.nfereader.model.DocumentoFiscalHeaderDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class DocumentoFiscalController2(
    private val documentoFiscalService: DocumentoFiscalServicePort,
    private val userConfigurationService: UserConfigurationServicePort,
    private val securityUtil: SecurityUtilPort
) : DocumentoFiscalApi {

    override fun getAllDocumentoFiscalHeader(): ResponseEntity<List<DocumentoFiscalHeaderDTO>> {
        val list = documentoFiscalService.getDocumentoFiscalHeader(
            securityUtil.getIdentityId(),
            null,
            null,
            natOpByUserList
        )
        return ResponseEntity.ok(list.map { it.toDto() })
    }

    override fun getAllDocumentoFiscalHeaderFiltered(
        startDate: LocalDate,
        endtDate: LocalDate,
        estado: String
    ): ResponseEntity<List<DocumentoFiscalHeaderDTO>> {
        var list = documentoFiscalService.getDocumentoFiscalHeader(
            securityUtil.getIdentityId(),
            startDate,
            endtDate,
            natOpByUserList
        )
        if("TODOS" != estado){
            list = list.filter { item -> item.uf.uppercase() == estado.uppercase() }
        }
        return ResponseEntity.ok(list.map { it.toDto() })
    }

    private val natOpByUserList: List<String>
        get() = userConfigurationService.getConfigurationForUser().getNFeProcessaveis()
}