package br.com.asoft.nfereader.adapters.`in`.rest.mapper

import br.com.asoft.nfereader.model.Transportadora
import br.com.asoft.nfereader.model.TransportadoraCabecalhoDTO

object TransportadoraMapper {
    fun Transportadora.toDto(): TransportadoraCabecalhoDTO {
        return TransportadoraCabecalhoDTO(
            id = this.id,
            cnpj = this.cnpj,
            ie = this.ie,
            municipio = this.municipio,
            uf = this.uf,
            razaoSocial = this.razaoSocial
        )
    }
}