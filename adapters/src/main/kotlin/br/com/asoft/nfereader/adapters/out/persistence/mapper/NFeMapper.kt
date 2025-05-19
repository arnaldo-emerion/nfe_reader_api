package br.com.asoft.nfereader.adapters.out.persistence.mapper

import br.com.asoft.nfereader.adapters.out.persistence.mapper.NFeItemMapper.toDTO
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.AnalisysTotalProjection
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.NFeCompletaProjection
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.ProdutoListagemNotaProjection
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.RankingProjection
import br.com.asoft.nfereader.model.*

object NFeMapper {

    fun ProdutoListagemNotaProjection.toDomain() =
        ProdutoListagemNota(
            id,
            naturezaOperacao,
            numeroNotaFiscal,
            razaoSocial,
            chaveAcesso,
            uf,
            municipio,
            valorNFe,
            emissao,
            cnpjDestinatario,
            valorUnitario,
            quantidade,
            valorTotal,
            tipoDocumento
        )

    fun ProdutoListagemNota.toDto() =
        ProdutoListagemNotaDTO(
            id,
            naturezaOperacao,
            numeroNotaFiscal,
            razaoSocial,
            chaveAcesso,
            uf,
            municipio,
            valorNFe,
            emissao,
            cnpjDestinatario,
            valorUnitario,
            quantidade,
            valorTotal,
            tipoDocumento
        )

    fun RankingProjection.toDto() =
        Ranking(destinatarioId, count)

    fun AnalisysTotalProjection.toModel() =
        AnalisysTotal(
            totalNotasProcessadas,
            totalCupomFiscalProcessados,
            clienteCount,
            produtoCount,
            valorTotalProcessado
        )

    fun AnalisysTotal.toDto() =
        AnalisysTotalDTO(
            totalNotasProcessadas,
            totalCupomFiscalProcessados,
            clienteCount,
            produtoCount,
            valorTotalProcessado
        )

    fun NFeCompletaProjection.toModel(): NFeCompleta {
        val transportadora: Transportadora? = if (null != this.transportadoraId)
            Transportadora(
                id = transportadoraId!!,
                userCreate = "",
                cnpj = transportadoraCpfCnpj,
                razaoSocial = transportadoraRazaoSocial,
                ie = transportadoraInscricaoEstadual,
                uf = transportadoraUf,
                municipio = transportadoraMunicipio,
                endereco = transportadoraEndereco
            ) else null
        return NFeCompleta(
            id,
            naturezaOperacao,
            tipo,
            chaveAcesso,
            numeroNotaFiscal,
            dataEmissao,
            Emitente(
                id = emitenteId,
                userCreate = "",
                cnpj = emitenteCpfCnpj,
                razaoSocial = emitenteRazaoSocial,
                nomeFantasia = emitenteNomeFantasia,
                ie = emitenteInscricaoEstadual,
                crt = crt,
                uf = emitenteUf,
                municipio = emitenteMunicipio,
                bairro = emitenteBairro,
                telefone = emitenteTelefone,
                logradouro = emitenteLogradouro,
                cep = emitenteCep,
                cPais = emitenteCodigoPais,
                xPais = emitenteNomePais
            ),
            Destinatario(
                id = destinatarioId,
                cpfCnpj = destinatarioCpfCnpj,
                razaoSocial = destinatarioRazaoSocial,
                ie = destinatarioInscricaoEstadual,
                uf = destinatarioUf,
                municipio = destinatarioMunicipio,
                bairro = destinatarioBairro,
                telefone = destinatarioTelefone,
                logradouro = destinatarioLogradouro,
                numero = destinatarioNumero,
                cep = destinatarioCep,
                cPais = destinatarioCodigoPais,
                xPais = destinatarioNomePais,
                indIEDest = destinatarioIndIEDest
            ),
            transportadora = transportadora,
            NFeTotalICMS(
                valorNotaFiscal = valorNotaFiscal,
                valorBaseCalculo = valorBaseCalculo,
                valorIcms = valorIcms,
                valorBaseCalculoST = valorBaseCalculoST,
                valorST = valorST,
                valorProdutos = valorProdutos,
                valorFrete = valorFrete,
                valorSeguro = valorSeguro,
                valorDesconto = valorDesconto,
                valorImpostoImportacao = valorImpostoImportacao,
                valorIpi = valorIpi,
                valorCofins = valorCofins,
                valorOutros = valorOutros,
                valorIcmsDesonerado = valorIcmsDesonerado,
                valorFcp = valorFcp,
                valorIcmsUfDestinatario = valorIcmsUfDestinatario,
                valorIcmsUfRemetente = valorIcmsUfRemetente,
                valorFcpSTRetido = valorFcpStRetido,
                percentualFcpSTRetido = percentualFcpSTRetido,
                valorIpiDevolucao = valorIpiDevolucao,
                valorTotalTributos = valorTotalTributos,
                valorPis = valorPis
            )
        )
    }

    fun NFeCompleta.toDto(itemList: List<NFeItemModel>): NFeCompletaDTO {
        val transportadora: TransportadoraDTO? = if (null != transportadora?.id)
            TransportadoraDTO(
                id = transportadora!!.id,
                cnpj = transportadora!!.cnpj,
                razaoSocial = transportadora!!.razaoSocial,
                ie = transportadora!!.ie,
                uf = transportadora!!.uf,
                municipio = transportadora!!.municipio,
                endereco = transportadora!!.endereco
            ) else null
        return NFeCompletaDTO(
            id = id,
            naturezaOPeracao = naturezaOperacao,
            tipo = tipo,
            chaveAcesso = chaveAcesso,
            numeroNotaFiscal = numeroNotaFiscal,
            dataNotaFiscal = dataEmissao,
            emitente = EmitenteDTO(
                id = emitente.id,
                cpfCnpj = emitente.cnpj,
                razaoSocial = emitente.razaoSocial,
                nomeFantasia = emitente.nomeFantasia,
                inscricaoEstadual = emitente.ie,
                crt = emitente.crt,
                uf = emitente.uf,
                municipio = emitente.municipio,
                bairro = emitente.bairro,
                telefone = emitente.telefone,
                logradouro = emitente.logradouro,
                cep = emitente.cep,
                cPais = emitente.cPais,
                xPais = emitente.xPais
            ),
            destinatario = DestinatarioDTO(
                id = destinatario.id,
                cpfCnpj = destinatario.cpfCnpj,
                razaoSocial = destinatario.razaoSocial,
                ie = destinatario.ie,
                uf = destinatario.uf,
                municipio = destinatario.municipio,
                bairro = destinatario.bairro,
                telefone = destinatario.telefone,
                logradouro = destinatario.logradouro,
                numero = destinatario.numero,
                cep = destinatario.cep,
                cPais = destinatario.cPais,
                xPais = destinatario.xPais,
                indIEDest = destinatario.indIEDest
            ),
            transportadora = transportadora,
            totalIcms = NFeTotalICMSDTO(
                valorNotaFiscal = totalICMSDTO.valorNotaFiscal,
                valorBaseCalculo = totalICMSDTO.valorBaseCalculo,
                valorIcms = totalICMSDTO.valorIcms,
                valorBaseCalculoST = totalICMSDTO.valorBaseCalculoST,
                valorST = totalICMSDTO.valorST,
                valorProdutos = totalICMSDTO.valorProdutos,
                valorFrete = totalICMSDTO.valorFrete,
                valorSeguro = totalICMSDTO.valorSeguro,
                valorDesconto = totalICMSDTO.valorDesconto,
                valorImpostoImportacao = totalICMSDTO.valorImpostoImportacao,
                valorIpi = totalICMSDTO.valorIpi,
                valorCofins = totalICMSDTO.valorCofins,
                valorOutros = totalICMSDTO.valorOutros,
                valorIcmsDesonerado = totalICMSDTO.valorIcmsDesonerado,
                valorFcp = totalICMSDTO.valorFcpSTRetido,
                valorIcmsUfDestinatario = totalICMSDTO.valorIcmsUfDestinatario,
                valorIcmsUfRemetente = totalICMSDTO.valorIcmsUfRemetente,
                valorFcpSTRetido = totalICMSDTO.valorFcp,
                percentualFcpSTRetido = totalICMSDTO.percentualFcpSTRetido,
                valorIpiDevolucao = totalICMSDTO.valorIpiDevolucao,
                valorTotalTributos = totalICMSDTO.valorTotalTributos,
                valorPis = totalICMSDTO.valorPis,
            ),
            items = itemList.map { it.toDTO() }
        )
    }
}