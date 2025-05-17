package br.com.asoft.nfereader.application.port.out.persistence

import br.com.asoft.nfereader.model.HistoricoProcessamento

interface HistoricoProcessamentoPersistencePort {
    fun getAllErros(
        userCreate: String
    ): List<HistoricoProcessamento>
}