package br.com.asoft.nfereader.adapters.out.persistence.repository

import br.com.asoft.nfereader.adapters.out.persistence.model.nfe.NFeEntity
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.AnalisysTotalProjection
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.ProdutoListagemNotaProjection
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.RankingProjection
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface NFeRepository : PagingAndSortingRepository<NFeEntity, Long> {

    @Query(
        nativeQuery = true,
        value = """
            select distinct 
                'NOTA_FISCAL' as tipoDocumento,
                nfe.id,  nfe.nat_operacao as naturezaOperacao, nfe.nnf as numeroNotaFiscal, nfe.data_emissao as emissao, nfe.chavenfe as chaveAcesso,
                d.razao_social as razaoSocial, d.uf as uf, d.municipio as municipio, d.cnpj as cnpjDestinatario,
                nt.vnf as valorNFe,
                ni.valor_unitario as valorUnitario, 
                coalesce(ni.quantidade,0)  as quantidade, 
                coalesce(ni.valor_total,0)  as valorTotal 
            from nfe_reader.nfe nfe
            left join nfe_reader.destinatario d on d.id = nfe.destinatario_id 
            left join nfe_reader.nfe_totalicms nt on nt.nfe_id = nfe.id
            left join nfe_reader.nfe_item ni on ni.nfe_id = nfe.id
            left join nfe_reader.produto p on p.id = ni.produto_id 
            where 1 = 1
                and (:pCodigo is null or p.codigo = :pCodigo)
                and (:pCpfCnpj is null or d.cnpj = :pCpfCnpj)
                and ( cast(:identityId as varchar(64)) is null or nfe.user_create = cast(:identityId as varchar(64))) 
                and (nfe.nat_operacao in (:natOperacaoList)) 
            
            union all
            
            select 
                'CUPOM_FISCAL' as tipoDocumento,
                cfm.id, 'VENDAS', cfm.num_cupom_fiscal, cfm.data_emissao, cfm.chavecfe,
                'CPF: ' || cdm.cpf, cfm.uf,  '', 'CPF: ' || cdm.cpf,
                citm.vcfe,
                cim.valor_unitario,
                coalesce(cim.quantidade,0), 
                coalesce(cim.valor_total_item) 
            from nfe_reader.cupom_fiscal_model cfm
            left join nfe_reader.cfe_destinatario_model cdm on cdm.id = cfm.destinatario_id
            left join nfe_reader.cfe_icms_total_model citm on citm.cupom_fiscal_id = cfm.id
            left join nfe_reader.cfe_item_model cim on cim.cupom_fiscal_id = cfm.id 
            left join nfe_reader.cfe_produto_model cpm on cpm.id  = cim.c_fe_produto_id 
            where 1 = 1
                and (:pCodigo is null or cpm.codigo = :pCodigo)
                and (:pCpfCnpj is null or cdm.cpf = :pCpfCnpj)
                and ( cast(:identityId as varchar(64)) is null or cfm.user_create = cast(:identityId as varchar(64))) 
                and (cfm.nat_operacao in (:natOperacaoList)) 
        """
    )
    fun getCabecalhoNFePorProduto(
        @Param("identityId") identityId: String,
        @Param("pCodigo") pCodigo: String?,
        @Param("pCpfCnpj") pCpfCnpj: String?,
        @Param("natOperacaoList") natOperacaoList: List<String>
    ): List<ProdutoListagemNotaProjection>

    @Query(
        nativeQuery = true,
        value = """
            select n.destinatario_id as destinatarioId, sum(tot.vnf) as count from nfe_reader.nfe_totalicms tot
            left join nfe_reader.nfe n on n.id = tot.nfe_id
             where 1 = 1
               and ( cast(:identityId as varchar(64)) is null or n.user_create = cast(:identityId as varchar(64))) 
               and (n.nat_operacao in (:natOperacaoList))
            group by n.destinatario_id
            order by 2 desc
        """
    )
    fun getRankingFaturamento(
        @Param("identityId") identityId: String,
        @Param("natOperacaoList") natOperacaoList: List<String?>?
    ): List<RankingProjection>

    @Query(
        nativeQuery = true,
        value = """
            select n.destinatario_id as destinatarioId, count(1) from nfe_reader.nfe n
            where 1 = 1
                and ( cast(:identityId as varchar(64)) is null or n.user_create = cast(:identityId as varchar(64)))
                and (n.nat_operacao in (:natOperacaoList))
            group by n.destinatario_id
            order by 2 desc
        """
    )
    fun getRankingQtdPedidos(
        @Param("identityId") identityId: String,
        @Param("natOperacaoList") natOperacaoList: List<String?>?
    ): List<RankingProjection>

    @Query(
        nativeQuery = true,
        value = """
            select
                count(1) totalNotasProcessadas,
                (
                select
                    count(1)
                from
                    nfe_reader.cupom_fiscal_model cfm
                where
                    1 = 1
                    and ( cfm.user_create = cast(:identityId as varchar(64)))
                    and cfm.nat_operacao in (:natOperacaoList)
                ) as totalCupomFiscalProcessados,
                (
                select
                    count(distinct(d.id))
                from
                    nfe_reader.destinatario d
                left join nfe_reader.nfe n2 on
                    n2.destinatario_id = d.id
                    and n2.user_create = d.user_create
                where
                    1 = 1
                    and (d.user_create = cast(:identityId as varchar(64)))
                        and n2.nat_operacao in (:natOperacaoList)
            ) as clienteCount,
                (
            with datasource as (
                select
                    count(distinct(p.id)) as total
                from
                    nfe_reader.produto p
                join nfe_reader.nfe_item ni on
                    ni.produto_id = p.id
                    and ni.user_create = p.user_create
                join nfe_reader.nfe n2 on
                    n2.id = ni.nfe_id
                where
                    1 = 1
                    and ( p.user_create = cast(:identityId as varchar(64)))
                        and n2.nat_operacao in (:natOperacaoList)
                union all
                    select
                        count(distinct(cpm.id))
                    from
                        nfe_reader.cfe_produto_model cpm
                    left join nfe_reader.cfe_item_model cim on
                        cim.c_fe_produto_id = cpm.id
                    left join nfe_reader.cupom_fiscal_model cfm on
                        cfm.id = cim.cupom_fiscal_id
                    where
                        1 = 1
                        and ( cpm.user_create = cast(:identityId as varchar(64)))
                            and cfm.nat_operacao in (:natOperacaoList)
            )
                select
                    sum(total) as count
                from
                    datasource
            ) as produtoCount,
                (
            with datasource as(
                select
                    coalesce(sum(nt.vnf),
                    0) as total
                from
                    nfe_reader.nfe_totalicms nt
                left join nfe_reader.nfe n2 on
                    n2.id = nt.nfe_id
                    and n2.user_create = nt.user_create
                where
                    1 = 1
                    and ( nt.user_create = cast(:identityId as varchar(64)))
                        and n2.nat_operacao in (:natOperacaoList)
                union all
                    select
                        coalesce(sum(citm.vcfe),
                        0)
                    from
                        nfe_reader.cfe_icms_total_model citm
                    left join nfe_reader.cupom_fiscal_model cfm on
                        cfm.id = citm.cupom_fiscal_id
                        and cfm.user_create = cfm.user_create
                    where
                        1 = 1
                        and ( cfm.user_create = cast(:identityId as varchar(64)))
                            and cfm.nat_operacao in (:natOperacaoList)
            )
                select
                    sum(total) as total
                from
                    datasource
            ) as valorTotalProcessado
            from
                nfe_reader.nfe n
            where
                1 = 1
                and ( n.user_create = cast(:identityId as varchar(64)))
                and n.nat_operacao in (:natOperacaoList)
        """
    )
    fun getAllTotals(
        @Param("identityId") identityId: String,
        @Param("natOperacaoList") natOperacaoList: List<String>
    ): AnalisysTotalProjection

}