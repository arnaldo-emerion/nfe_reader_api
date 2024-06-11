package br.com.asoft.nfereader.adapters.out.persistence.repository

import br.com.asoft.nfereader.adapters.out.persistence.model.nfe.DestinatarioEntity
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.AnaliseQualitativaClienteProjection
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.DestinatarioCabecalhoProjection
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.EstatisticaDestinatarioProjection
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface DestinatarioRepository : PagingAndSortingRepository<DestinatarioEntity, Long> {
    fun findByUserCreate(userCreate: String): List<DestinatarioEntity>
    fun findByUserCreateAndCnpj(userCreate: String, cnpj: String): DestinatarioEntity?
    fun findByIdAndUserCreate(id: Long, userCreate: String): DestinatarioEntity?

    @Query(
        nativeQuery = true,
        value = """
            select 'NOTA_FISCAL' as tipoDocumento, d.cnpj, d.razao_social as razaoSocial, sum(nt.vnf), min(nt.vnf), max(nt.vnf), avg(nt.vnf), count(nfe.id) from nfe_reader.nfe_totalicms nt
            left join nfe_reader.nfe nfe on nfe.id = nt.nfe_id
            left join nfe_reader.destinatario d on d.id = nfe.destinatario_id
            where 1 = 1
               and ( cast(cast(:startDate as varchar(10)) as date) is null or nfe.data_emissao >= cast(cast(:startDate as varchar(10)) as date))
               and ( cast(cast(:endDate as varchar(10)) as date) is null or nfe.data_emissao <= cast(cast(:endDate as varchar(10)) as date))
               and ( cast(:identityId as varchar(64)) is null or nt.user_create = cast(:identityId as varchar(64)))
               and (nfe.nat_operacao in (:natOperacaoList))
            group by d.cnpj, d.razao_social
            
            union all
            
            select 'CUPOM_FISCAL' as tipoDocumento, coalesce(cdm.cpf,'CUPOM_FISCAL'), coalesce(cdm.cpf, 'CUPOM_FISCAL'), sum(citm.v_prod), min(citm.v_prod), max(v_prod), avg(citm.v_prod), count(citm.id) from nfe_reader.cfe_icms_total_model citm
            left join nfe_reader.cupom_fiscal_model cfm on cfm.id = citm.cupom_fiscal_id 
            left join nfe_reader.cfe_destinatario_model cdm on cdm.id = cfm.destinatario_id 
            where 1 = 1
               and ( cast(cast(:startDate as varchar(10)) as date) is null or cfm.data_emissao >= cast(cast(:startDate as varchar(10)) as date))
               and ( cast(cast(:endDate as varchar(10)) as date) is null or cfm.data_emissao <= cast(cast(:endDate as varchar(10)) as date))
               and ( cast(:identityId as varchar(64)) is null or cfm.user_create = cast(:identityId as varchar(64)))
               and (cfm.nat_operacao in (:natOperacaoList))
            group by cdm.cpf 
        """
    )
    fun getAnalizeQualitativa(
        @Param("identityId") identityId: String,
        @Param("startDate") startDate: LocalDate?,
        @Param("endDate") endDate: LocalDate?,
        @Param("natOperacaoList") natOperacaoList: List<String>
    ): List<AnaliseQualitativaClienteProjection>

    @Query(
        nativeQuery = true,
        value = """
            select 
                'NOTA_FISCAL' as tipoDocumento, d.id, d.razao_social as nome, d.cnpj, d.uf, d.municipio, d.telefone, d.cep 
            from nfe_reader.destinatario d
            where 1 = 1
                and ( d.user_create = :identityId )
            union all
            select 
                'CUPOM_FISCAL' as tipoDocumento, cdm.id, cdm.cpf, cdm.cpf, '', '', '', ''
            from nfe_reader.cfe_destinatario_model cdm
            where 1 = 1
                and ( cdm.user_create = :identityId )
            order by 1
        """
    )
    fun getDestinatarioCabecalhoByUserCreate(@Param("identityId") identityId: String): List<DestinatarioCabecalhoProjection>

    @Query(
        nativeQuery = true,
        value = """
            with totals as (
            select
                sum(ni.quantidade) as volume,
                sum(ni.valor_total) as faturamento,
                count(distinct(ni.nfe_id)) as frequencia
            from
                nfe_reader.nfe_item ni
            where
                1 = 1
                and ( cast(:identityId as varchar(64)) is null
                    or ni.user_create = cast(:identityId as varchar(64))) 
            ),
            
            userStatistics as (
            select
                nfe.user_create as userCreate,
                dest.razao_social as razaoSocial,
                dest.cnpj as cpfCnpj,
                min(total.vnf) as menorCompra,
                max(total.vnf) as maiorCompra,
                count(nfe.id) as totalPedidos,
                sum(total.vnf) as valorTotalCompras,
                avg(total.vnf) as valorMedioPedido,
                max(nfe.data_emissao) as dataUltimaCompra,
                (
                select
                    cast(count(ni.id) as decimal) / cast(count(distinct(n.id))as decimal)
                from
                    nfe_reader.nfe_item ni
                left join nfe_reader.nfe n on
                    n.id = ni.nfe_id
                left join nfe_reader.destinatario dest on
                    dest.id = n.destinatario_id
                where
                    dest.cnpj = :cpfCnpj) as quantidadeMediaItensPorPedido
            from
                nfe_reader.nfe nfe
            left join nfe_reader.nfe_totalicms total on
                total.nfe_id = nfe.id
            left join nfe_reader.destinatario dest on
                dest.id = nfe.destinatario_id
            where
                1 = 1
                and dest.cnpj = :cpfCnpj
                and ( cast(:identityId as varchar(64)) is null
                    or nfe.user_create = cast(:identityId as varchar(64)))
            group by
                nfe.user_create ,
                dest.razao_social,
                dest.cnpj
            )
            
            select 
                us.*,
                (us.valorTotalCompras / t.faturamento) * 100 as participacaoCliente
            from
                userStatistics us,
                totals t
        """
    )
    fun getEstatisticaDestinatario(
        @Param("identityId") identityId: String,
        @Param("cpfCnpj") cpfCnpj: String
    ): EstatisticaDestinatarioProjection
}
