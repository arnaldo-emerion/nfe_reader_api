package br.com.asoft.nfereader.adapters.out.persistence.adapter

import br.com.asoft.nfereader.adapters.out.persistence.mapper.HistoricoProcessamentoMapper.toModel
import br.com.asoft.nfereader.adapters.out.persistence.repository.HistoricoProcessamentoRepository
import br.com.asoft.nfereader.application.port.out.persistence.HistoricoProcessamentoPersistencePort
import br.com.asoft.nfereader.model.HistoricoProcessamento
import org.springframework.stereotype.Service

@Service
class HistoricoProcessamentoPersistenceAdapter(private val historicoProcessamentoRepository: HistoricoProcessamentoRepository) :
    HistoricoProcessamentoPersistencePort {
    override fun getAllErros(userCreate: String): List<HistoricoProcessamento> {
        return this.historicoProcessamentoRepository.findByUserCreateAndProcessadaCorretamenteFalseOrderByDataProcessamentoDesc(
            userCreate = userCreate
        ).map { it.toModel() }
    }
}