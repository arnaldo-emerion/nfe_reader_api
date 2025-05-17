package br.com.asoft.nfereader.application.service

import br.com.asoft.nfereader.application.port.`in`.service.HistoricoProcessamentoServicePort
import br.com.asoft.nfereader.application.port.out.persistence.HistoricoProcessamentoPersistencePort
import br.com.asoft.nfereader.model.HistoricoProcessamento
import org.springframework.stereotype.Service

@Service
class HistoricoProcessamentoService(private val historicoProcessamentoPersistence: HistoricoProcessamentoPersistencePort) :
    HistoricoProcessamentoServicePort {
    override fun getAllErros(userCreate: String): List<HistoricoProcessamento> {
        return this.historicoProcessamentoPersistence.getAllErros(userCreate)
    }

}