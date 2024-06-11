package br.com.asoft.nfereader.application.port.out.persistence

import br.com.asoft.nfereader.model.AnaliseQualitativaCliente
import br.com.asoft.nfereader.model.Destinatario
import br.com.asoft.nfereader.model.DestinatarioCabecalho
import br.com.asoft.nfereader.model.EstatisticaDestinatario
import java.time.LocalDate

interface DestinatarioPersistencePort {
    fun getAnalizeQualitativa(
        identityId: String,
        startDate: LocalDate?,
        endDate: LocalDate?,
        natOperacaoList: List<String>
    ): List<AnaliseQualitativaCliente>

    fun getDestinatarioCabecalhoByUserCreate(identityId: String): List<DestinatarioCabecalho>
    fun findByIdAndUserCreate(id: Long, userCreate: String): Destinatario?
    fun getEstatisticaDestinatario(
        identityId: String,
        cpfCnpj: String
    ): EstatisticaDestinatario

    fun findByUserCreateAndCnpj(userCreate: String, cnpj: String): Destinatario?
}