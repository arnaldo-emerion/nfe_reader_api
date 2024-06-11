package br.com.asoft.nfereader.adapters.out.persistence.mapper

import br.com.asoft.nfereader.adapters.out.persistence.model.nfe.DestinatarioEntity
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.AnaliseQualitativaClienteProjection
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.DestinatarioCabecalhoProjection
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.EstatisticaDestinatarioProjection
import br.com.asoft.nfereader.model.AnaliseQualitativaCliente
import br.com.asoft.nfereader.model.Destinatario
import br.com.asoft.nfereader.model.DestinatarioCabecalho
import br.com.asoft.nfereader.model.EstatisticaDestinatario

object DestinatarioMapper {

    fun AnaliseQualitativaClienteProjection.toDomain() =
        AnaliseQualitativaCliente(
            cnpj, razaoSocial, sum, min, max, avg, count, tipoDocumento
        )

    fun DestinatarioCabecalhoProjection.toDomain() =
        DestinatarioCabecalho(tipoDocumento, id, nome, cnpj, uf, municipio, telefone, cep)

    fun DestinatarioEntity.toDomain() =
        Destinatario(
            this.id!!,
            cnpj,
            razaoSocial,
            ie,
            uf,
            municipio,
            bairro,
            telefone,
            cep,
            logradouro,
            numero,
            cPais,
            xPais,
            indIEDest
        )

    fun EstatisticaDestinatarioProjection.toDomain() =
        EstatisticaDestinatario(
            userCreate,
            razaoSocial,
            cpfCnpj,
            menorCompra,
            maiorCompra,
            totalPedidos,
            valorTotalCompras,
            valorMedioPedido,
            dataUltimaCompra,
            quantidadeMediaItensPorPedido,
            participacaoCliente
        )
}