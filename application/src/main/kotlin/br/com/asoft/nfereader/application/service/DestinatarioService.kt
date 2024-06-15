package br.com.asoft.nfereader.application.service

import br.com.asoft.nfereader.application.port.`in`.service.DestinatarioServicePort
import br.com.asoft.nfereader.application.port.out.persistence.DestinatarioPersistencePort
import br.com.asoft.nfereader.application.port.out.persistence.NFePersistencePort
import br.com.asoft.nfereader.model.*
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDate
import kotlin.reflect.KProperty1

@Service
class DestinatarioService(
    private val destinatarioPersistence: DestinatarioPersistencePort,
    private val nfePersistencePort: NFePersistencePort
) : DestinatarioServicePort {

    override fun getCurvaDeClientesPorCriterio(
        identityId: String,
        comparingBy: KProperty1<AnaliseQualitativaCliente, Double>,
        natOperacaoList: List<String>,
        startDate: LocalDate?,
        endDate: LocalDate?,
    ): List<AnaliseQualitativaCliente> {
        val analizeQualitativaList = this.getAnalizeQualitativa(identityId, startDate, endDate, natOperacaoList)
        val list = analizeQualitativaList.sortedBy(comparingBy).reversed()

        return if (list.size > 20) list.subList(0, 20) else list
    }

    override fun getDestinatarioCabecalhoByUserCreate(identityId: String): List<DestinatarioCabecalho> {
        return this.destinatarioPersistence.getDestinatarioCabecalhoByUserCreate(identityId)
    }

    private fun getAnalizeQualitativa(
        identityId: String,
        startDate: LocalDate?,
        endDate: LocalDate?,
        natOperacaoList: List<String>
    ): List<AnaliseQualitativaCliente> {
        return this.destinatarioPersistence.getAnalizeQualitativa(identityId, startDate, endDate, natOperacaoList)
    }

    override fun getByIdAndUserCreate(id: Long, userCreate: String): Destinatario {
        return this.destinatarioPersistence.findByIdAndUserCreate(id, userCreate) ?: throw NotFoundException()
    }

    override fun getEstatisticaDestinatario(
        identityId: String,
        cpfCnpj: String,
        natOperacaoList: List<String>
    ): EstatisticaDestinatario {
        val destinatario = this.destinatarioPersistence.findByUserCreateAndCnpj(
            userCreate = identityId,
            cnpj = cpfCnpj
        ) ?: throw RuntimeException("Destinatario nao encontrado")

        val estatistica = this.destinatarioPersistence.getEstatisticaDestinatario(
            identityId = identityId,
            cpfCnpj = cpfCnpj
        )

        val rankingFaturamento = this.nfePersistencePort.getRankingFaturamento(
            identityId = identityId,
            natOperacaoList = natOperacaoList
        )

        val rankingQtdPedidos = this.nfePersistencePort.getRankingQtdPedidos(
            identityId = identityId,
            natOperacaoList = natOperacaoList
        )

        val posicaoFaturamento = getRanking(destinatario = destinatario, rankingList = rankingFaturamento)
        val posicaoQtdPedidos = getRanking(destinatario = destinatario, rankingList = rankingQtdPedidos)

        estatistica.rankingQtdPedidos = posicaoFaturamento
        estatistica.rankingFaturamento = posicaoQtdPedidos

        return estatistica
    }

    fun getRanking(destinatario: Destinatario, rankingList: List<Ranking>): Int {
        var aux = 0
        var lastValue = 0
        for (rankingDTO in rankingList) {
            if (lastValue > rankingDTO.count || lastValue < rankingDTO.count) {
                aux++
            }
            if (rankingDTO.destinatarioId == destinatario.id) {
                break
            }
            lastValue = rankingDTO.count
        }

        return aux
    }
}