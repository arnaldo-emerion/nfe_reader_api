package br.com.asoft.nfereader.adapters.`in`.rest.mapper

import br.com.asoft.nfereader.model.TotalImpostoNFe
import br.com.asoft.nfereader.model.TotalImpostoNFeDTO

object TotalImpostoMapper {
    fun TotalImpostoNFe.toDto() =
        TotalImpostoNFeDTO(
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
}