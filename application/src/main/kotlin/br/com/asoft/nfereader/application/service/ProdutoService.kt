package br.com.asoft.nfereader.application.service

import br.com.asoft.nfereader.application.port.`in`.service.ProdutoServicePort
import br.com.asoft.nfereader.application.port.`in`.service.SecurityUtilPort
import br.com.asoft.nfereader.application.port.`in`.service.UserConfigurationServicePort
import br.com.asoft.nfereader.application.port.out.persistence.ProdutoPersistencePort
import br.com.asoft.nfereader.model.PedidosDiaADia
import br.com.asoft.nfereader.model.Produto
import br.com.asoft.nfereader.model.ProdutoCurvaABC
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class ProdutoService(
    private val produtoPersistence: ProdutoPersistencePort,
    private val securityUtil: SecurityUtilPort,
    private val userConfigurationService: UserConfigurationServicePort
) : ProdutoServicePort {

    override fun getEstatisticaProdutoByVolume(): List<ProdutoCurvaABC> {
        val pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "volume"))
        return this.getEstatisticaProduto(pageRequest)
    }

    override fun getEstatisticaProdutoByFaturamento(): List<ProdutoCurvaABC> {
        val pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "faturamento"))
        return this.getEstatisticaProduto(pageRequest)
    }

    override fun getEstatisticaProdutoByFrequencia(): List<ProdutoCurvaABC> {
        val pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "frequencia"))
        return this.getEstatisticaProduto(pageRequest)
    }

    override fun getProdutoByUserCreateAndCodigo(codigo: String?): List<Produto> {
        return this.produtoPersistence.getProdutoByUserCreateAndCodigo(
            identityId = securityUtil.getIdentityId(),
            codigo = codigo
        )
    }

    override fun getQtdProdutosDiaADia(): List<PedidosDiaADia> {
        return this.produtoPersistence.getQtdProdutosDiaADia(
            identityId = securityUtil.getIdentityId(),
            natOperacaoList = userConfigurationService.getNFeProcessaveis()
        )
    }

    override fun getByCodigoAndUserCreate(codigo: String): Produto? {
        TODO("Not yet implemented")
    }

    override fun getTotaisEstatisticaProduto(codigo: String): ProdutoCurvaABC {
        val totaisProduto =
            this.produtoPersistence.getTotaisEstatisticaProduto(identityId = securityUtil.getIdentityId())
        val curvaABC = this.produtoPersistence.getEstatisticaProduto(
            identityId = securityUtil.getIdentityId(),
            codigo = codigo,
            natOperacaoList = userConfigurationService.getNFeProcessaveis(),
            pageRequest = Pageable.unpaged()
        ).first()

        curvaABC.partVolume = (curvaABC.volume / totaisProduto.volume) * 100
        curvaABC.partFaturamento = (curvaABC.faturamento / totaisProduto.faturamento) * 100
        curvaABC.partFrequencia = (curvaABC.frequencia / totaisProduto.frequencia) * 100

        return curvaABC
    }

    private fun getEstatisticaProduto(
        pageRequest: Pageable
    ): List<ProdutoCurvaABC> {
        return this.produtoPersistence.getEstatisticaProduto(
            identityId = securityUtil.getIdentityId(),
            codigo = null,
            natOperacaoList = userConfigurationService.getNFeProcessaveis(),
            pageRequest = pageRequest
        )
    }
}