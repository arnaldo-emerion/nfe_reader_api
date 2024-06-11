package br.com.asoft.nfereader.adapters.out.persistence.repository

import br.com.asoft.nfereader.adapters.out.persistence.model.nfe.ProdutoEntity
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.PedidosDiaADiaProjection
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.ProdutoCabecalhoProjection
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.ProdutoCurvaABCProjection
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.TotaisEstatisticaProdutoProjection
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProdutoRepository : PagingAndSortingRepository<ProdutoEntity, Long> {
    fun findByUserCreate(userCreate: String): List<ProdutoEntity>

    @Query(
        nativeQuery = true,
        value = """
            with datasource as (select
                'NOTA_FISCAL' as tipoDocumento, p.id as id, p.codigo, p.descricao, p.unidade, sum(ni.quantidade) as volume, p.ncm,
                sum(ni.valor_total) as faturamento,
                count(distinct(ni.nfe_id)) as frequencia,
                (sum(ni.valor_total) / sum(ni.quantidade)) as precoMedio,
                sum(ni.valor_total) / count(distinct(ni.nfe_id)) as ticketMedio
                from nfe_reader.nfe_item ni
                left join nfe_reader.produto p on p.id = ni.produto_id
                left join nfe_reader.nfe nfe on nfe.id = ni.nfe_id
                where 1 = 1
                   and (cast(:pCodigo as varchar(20))  is null or p.codigo = cast(:pCodigo as varchar(20)) )
                   and (cast(:identityId as varchar(64)) is null or ni.user_create = cast(:identityId as varchar(64)))
                   and (nfe.nat_operacao in (:natOperacaoList))
                group  by p.id, p.codigo, p.descricao, p.unidade

                union all
                
                select 
                'CUPOM_FISCAL' as tipoDocumento, cpm.id, cpm.codigo, cpm.descricao, 'UN', sum(cim.quantidade) as volume, cpm.ncm,
                sum(cim.valor_total_item) as faturamento,
                count(distinct(cim.cupom_fiscal_id)) as frequencia,
                (sum(cim.valor_total_item) / sum(cim.quantidade)) as precoMedio,
                sum(cim.valor_total_item) / count(distinct(cim.cupom_fiscal_id)) as ticketMedio
                from nfe_reader.cfe_item_model cim 
                left join nfe_reader.cfe_produto_model cpm on cpm.id = cim.c_fe_produto_id
                left join nfe_reader.cupom_fiscal_model cfm on cfm.id = cim.cupom_fiscal_id 
                where 1 = 1
                   and (cast(:pCodigo as varchar(20))  is null or cpm.codigo = cast(:pCodigo as varchar(20)) )
                   and (cast(:identityId as varchar(64)) is null or cpm.user_create = cast(:identityId as varchar(64)))
                   and (cfm.nat_operacao in (:natOperacaoList))
                group by cpm.id, cpm.codigo, cpm.descricao)
            select * from datasource
        """
    )
    fun getEstatisticaProduto(
        @Param("identityId") identityId: String,
        @Param("pCodigo") codigo: String?,
        @Param("natOperacaoList") natOperacaoList: List<String>,
        pageRequest: Pageable
    ): List<ProdutoCurvaABCProjection>

    @Query(
        nativeQuery = true,
        value = """
            select
                'NOTA_FISCAL' as tipoDocumento, p.codigo, p.descricao, p.ean, p.unidade, p.ncm
            from
                nfe_reader.produto p
            where 1 = 1
                and p.user_create = :identityId
                and (:codigo is null or :codigo = p.codigo)
            union all
            select
                'CUPOM_FISCAL' as tipoDocumento, cpm.codigo, cpm.descricao, '', '', cpm.ncm
            from
                nfe_reader.cfe_produto_model cpm 
            where 1 = 1
                and cpm.user_create = :identityId
                and (:codigo is null or :codigo = cpm.codigo)
            order by 1
        """
    )
    fun getProdutoByUserCreateAndCodigo(
        @Param("identityId") identityId: String,
        @Param("codigo") codigo: String? = null
    ): List<ProdutoCabecalhoProjection>


    @Query(
        nativeQuery = true,
        value = """
            with datasource as(
                select nfe.data_emissao as dataEmissao, sum(ni.quantidade) as total from nfe_reader.nfe_item ni
                left join nfe_reader.nfe nfe on nfe.id = ni.nfe_id
                 where 1 = 1
                    and nfe.data_emissao is not null
                    and ( cast(:identityId as varchar(64)) is null or nfe.user_create = cast(:identityId as varchar(64)))
                    and (nfe.nat_operacao in (:natOperacaoList))
                group by nfe.data_emissao
                union all
                select cast(cfm.data_emissao as date), sum(cim.quantidade) from nfe_reader.cfe_item_model cim 
                left join nfe_reader.cupom_fiscal_model cfm on cfm.id = cim.cupom_fiscal_id
                 where 1 = 1
                    and cfm.data_emissao is not null
                    and ( cast(:identityId as varchar(64)) is null or cfm.user_create = cast(:identityId as varchar(64)))
                    and (cfm.nat_operacao in (:natOperacaoList))
                group by cast(cfm.data_emissao as date)
            )
            select dataemissao, sum(total) as total from datasource
            group by dataemissao
            order by 1
        """
    )
    fun getQtdProdutosDiaADia(
        @Param("identityId") identityId: String,
        @Param("natOperacaoList") natOperacaoList: List<String>
    ): List<PedidosDiaADiaProjection>

    @Query(
        nativeQuery = true,
        value = """
            with datasource as (
            select 
            'NOTA_FISCAL' as tipoDocumento,
            sum(ni.quantidade) as volume, 
            sum(ni.valor_total) as faturamento, 
            count(distinct(ni.nfe_id)) as frequencia
            from nfe_reader.nfe_item ni
             where 1 = 1
               and ( cast(:identityId as varchar(64)) is null or ni.user_create = cast(:identityId as varchar(64))) 
             
            union all
             
            select
                'CUPOM_FISCAL' as tipoDocumento,
                coalesce(sum(cim.quantidade),
                0) as volume,
                coalesce(sum(cim.valor_total_item),
                0) as faturamento,
                count(distinct(cim.cupom_fiscal_id)) as frequencia
            from
                nfe_reader.cfe_item_model cim
            where
                1 = 1
                and ( cast(:identityId as varchar(64)) is null
                    or cim.user_create = cast(:identityId as varchar(64)))
             )
             select
                sum(volume) as volume,
                sum(faturamento) as faturamento,
                sum(frequencia) as frequencia
            from
                datasource
        """
    )
    fun getTotaisEstatisticaProduto(@Param("identityId") identityId: String): TotaisEstatisticaProdutoProjection
}
