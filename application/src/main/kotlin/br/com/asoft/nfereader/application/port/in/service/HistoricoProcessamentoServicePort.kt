package br.com.asoft.nfereader.application.port.`in`.service

import br.com.asoft.nfereader.model.HistoricoProcessamento

interface HistoricoProcessamentoServicePort {
    fun getAllErros(
        userCreate: String
    ): List<HistoricoProcessamento>
}