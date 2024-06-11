package br.com.asoft.nfereader.adapters.out.persistence.adapter

import br.com.asoft.nfereader.adapters.out.persistence.mapper.DestinatarioMapper.toDomain
import br.com.asoft.nfereader.adapters.out.persistence.repository.DestinatarioRepository
import br.com.asoft.nfereader.application.port.out.persistence.DestinatarioPersistencePort
import br.com.asoft.nfereader.model.AnaliseQualitativaCliente
import br.com.asoft.nfereader.model.Destinatario
import br.com.asoft.nfereader.model.DestinatarioCabecalho
import br.com.asoft.nfereader.model.EstatisticaDestinatario
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DestinatarioPersistenceAdapter(private val destinatarioRepository: DestinatarioRepository) :
    DestinatarioPersistencePort {
    override fun getAnalizeQualitativa(
        identityId: String,
        startDate: LocalDate?,
        endDate: LocalDate?,
        natOperacaoList: List<String>
    ): List<AnaliseQualitativaCliente> {
        return this.destinatarioRepository.getAnalizeQualitativa(identityId, startDate, endDate, natOperacaoList)
            .map { it.toDomain() }
    }

    override fun getDestinatarioCabecalhoByUserCreate(identityId: String): List<DestinatarioCabecalho> {
        return this.destinatarioRepository.getDestinatarioCabecalhoByUserCreate(identityId)
            .map { it.toDomain() }
    }

    override fun findByIdAndUserCreate(id: Long, userCreate: String): Destinatario? {
        return this.destinatarioRepository.findByIdAndUserCreate(id, userCreate)?.toDomain()
    }

    override fun getEstatisticaDestinatario(identityId: String, cpfCnpj: String): EstatisticaDestinatario {
        return this.destinatarioRepository.getEstatisticaDestinatario(
            identityId = identityId,
            cpfCnpj = cpfCnpj
        ).toDomain()
    }

    override fun findByUserCreateAndCnpj(userCreate: String, cnpj: String): Destinatario? {
        return this.destinatarioRepository.findByUserCreateAndCnpj(
            userCreate = userCreate,
            cnpj = cnpj
        )?.toDomain()
    }
}