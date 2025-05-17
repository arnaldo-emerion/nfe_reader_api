package br.com.asoft.nfereader.adapters.out.persistence.mapper

import br.com.asoft.nfereader.adapters.out.persistence.model.nfe.HistoricoProcessamentoEntity
import br.com.asoft.nfereader.model.HistoricoProcessamento
import br.com.asoft.nfereader.model.HistoricoProcessamentoDTO

object HistoricoProcessamentoMapper {

    fun HistoricoProcessamento.toDto() =
        HistoricoProcessamentoDTO(
            nomeArquivo = nomeArquivo,
            dataProcessamento = dataProcessamento,
            processadaCorretamente = processadaCorretamente,
            motivo = motivo
        )

    fun HistoricoProcessamentoEntity.toModel() =
        HistoricoProcessamento(
            nomeArquivo,
            dataProcessamento,
            processadaCorretamente,
            motivo
        )
}