package br.com.asoft.nfereader.adapters.out.persistence.mapper

import br.com.asoft.nfereader.adapters.out.persistence.model.projection.*
import br.com.asoft.nfereader.model.*

object DocumentoFiscalMapper {

    fun DocumentoFiscalHeader.toDto() =
        DocumentoFiscalHeaderDTO(
            tipoDocumento = DocumentoFiscalHeaderDTO.TipoDocumento.valueOf(this.tipoDocumento),
            id = this.id,
            naturezaOperacao = this.natOp,
            numeroDocumento = this.nnf,
            razaoSocial = this.razaoSocial,
            chaveAcesso = this.chNFe,
            uf = this.uf,
            emissao = this.dhEmi,
            valorNotaFiscal = this.vnf,
            totalPedidos = this.totalPedidos,
            cpfCnpjDestinatario = this.cnpjDest
        )

    fun DocumentoFiscalHeaderProjection.toDomain() =
        DocumentoFiscalHeader(
            tipoDocumento = this.tipoDocumento,
            id = this.id,
            natOp = this.natOp,
            nnf = this.nnf,
            razaoSocial = this.razaoSocial,
            chNFe = this.chNFe,
            uf = this.uf,
            dhEmi = this.dhEmi,
            vnf = this.vnf,
            totalPedidos = this.totalPedidos,
            cnpjDest = this.cnpjDest
        )

    fun BasicAXChartInfoProjection.toDomain() =
        BasicAXChartInfo(name, total)

    fun TotalImpostoNFeProjection.toDomain() =
        NFeTotalICMS(
            pfcpstret,
            vbc,
            vbcst,
            vcofins,
            vdesc,
            vfcp,
            vfcpstret,
            vfrete,
            vicms,
            vicmsdeson,
            vicmsufdest,
            vicmsufremet,
            vii,
            vipi,
            vipidevol,
            vnf,
            voutro,
            vpis,
            vprod,
            vst,
            vseg,
            vtottrib
        )

    fun PedidosDiaADiaProjection.toDomain() =
        PedidosDiaADia(dataEmissao, total)

    fun TotaisEstatisticaProdutoProjection.toDomain() =
        TotaisEstatisticaProduto(volume, faturamento, frequencia)
}

