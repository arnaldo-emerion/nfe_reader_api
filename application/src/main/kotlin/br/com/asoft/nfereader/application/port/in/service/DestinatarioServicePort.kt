package br.com.asoft.nfereader.application.port.`in`.service

import br.com.asoft.nfereader.model.AnaliseQualitativaCliente
import br.com.asoft.nfereader.model.Destinatario
import br.com.asoft.nfereader.model.DestinatarioCabecalho
import br.com.asoft.nfereader.model.EstatisticaDestinatario
import java.time.LocalDate
import kotlin.reflect.KProperty1

interface DestinatarioServicePort {
    fun getCurvaDeClientesPorCriterio(
        identityId: String,
        comparingBy: KProperty1<AnaliseQualitativaCliente, Double>,
        natOperacaoList: List<String>,
        startDate: LocalDate? = null,
        endDate: LocalDate? = null,
    ): List<AnaliseQualitativaCliente>

    fun getDestinatarioCabecalhoByUserCreate(identityId: String): List<DestinatarioCabecalho>
    fun getByIdAndUserCreate(id: Long, userCreate: String): Destinatario
    fun getEstatisticaDestinatario(
        identityId: String,
        cpfCnpj: String,
        natOperacaoList: List<String>,
    ): EstatisticaDestinatario
}