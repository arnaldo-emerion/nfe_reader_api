package br.com.asoft.nfereader.adapters.out.persistence.repository

import br.com.asoft.nfereader.adapters.out.persistence.model.nfe.NFeEntity
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.BasicAXChartInfoProjection
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.DocumentoFiscalHeaderProjection
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.PedidosDiaADiaProjection
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.TotalImpostoNFeProjection
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.Date

@Repository
interface DocumentoFiscalRepository : PagingAndSortingRepository<NFeEntity, Long> {

    @Query(
        nativeQuery = true,
        value = """
            select distinct
                'NOTA_FISCAL' as tipoDocumento,
                nfe.id,
                nfe.nat_operacao as natOp,
                nfe.nnf,
                d.razao_social as razaoSocial,
                nfe.chavenfe as chNFe,
                d.uf as uf,
                nfe.data_emissao as dhEmi,
                nt.vnf,
                d.cnpj as cnpjDest,
                (select count(1) from nfe_reader.nfe n where n.destinatario_id = nfe.destinatario_id) as totalPedidos
            from nfe_reader.nfe nfe
            left join nfe_reader.destinatario d on d.id = nfe.destinatario_id
            left join nfe_reader.nfe_totalicms nt on nt.nfe_id = nfe.id
            where 1 = 1
               and ( cast(cast(:startDate as varchar(10)) as date) is null or nfe.data_emissao >= cast(cast(:startDate as varchar(10)) as date))
               and ( cast(cast(:endDate as varchar(10)) as date) is null or nfe.data_emissao <= cast(cast(:endDate as varchar(10)) as date))
               and ( cast(:identityId as varchar(64)) is null or nfe.user_create = cast(:identityId as varchar(64)))
               and (nfe.nat_operacao in (:natOperacaoList))
            union all
            select
                'CUPOM_FISCAL',
                cf.id ,
                'VENDA',
                cf.num_cupom_fiscal,
                'CPF: ' || cfd.cpf,
                cf.chavecfe,
                cf.uf,
                cf.data_emissao,
                citm.vcfe,
                coalesce(cfd.cpf, ''),
                (select count(1) from nfe_reader.cupom_fiscal_model cfe where cf.destinatario_id = cfe.destinatario_id) as totalPedidos
            from nfe_reader.cupom_fiscal_model cf 
            left join nfe_reader.cfe_destinatario_model cfd on cfd.id = cf.destinatario_id
            left join nfe_reader.cfe_icms_total_model citm on citm.cupom_fiscal_id = cf.id
            where 1 = 1
               and ( cast(cast(:startDate as varchar(10)) as date) is null or cf.data_emissao >= cast(cast(:startDate as varchar(10)) as date))
               and ( cast(cast(:endDate as varchar(10)) as date) is null or cf.data_emissao <= cast(cast(:endDate as varchar(10)) as date))
               and ( cast(:identityId as varchar(64)) is null or cf.user_create = cast(:identityId as varchar(64)))
            order by 3
        """
    )
    fun getDocumentoFiscalHeader(
        @Param("identityId") identityId: String,
        @Param("startDate") startDate: LocalDate?,
        @Param("endDate") endDate: LocalDate?,
        @Param("natOperacaoList") natOperacaoList: List<String>
    ): MutableList<DocumentoFiscalHeaderProjection>

    @Query(
        nativeQuery = true,
        value = """
            with datasource as (
                select extract(year from nfe.data_emissao) as yearName, sum(nt.vnf) as total from nfe_reader.nfe_totalicms nt
                left join nfe_reader.nfe nfe on nfe.id = nt.nfe_id
                where 1 = 1
                    and nfe.data_emissao is not null
                    and ( cast(:identityId as varchar(64)) is null or nfe.user_create = cast(:identityId as varchar(64)))
                    and (nfe.nat_operacao in (:natOperacaoList))
                group by extract(year from nfe.data_emissao)
                union all
                select extract(year from cfm.data_emissao) as yearName, sum(citm.v_prod) as total
                from nfe_reader.cfe_icms_total_model citm 
                left join nfe_reader.cupom_fiscal_model cfm on cfm.id = citm.cupom_fiscal_id
                where 1 = 1
                    and ( cast(:identityId as varchar(64)) is null or cfm.user_create = cast(:identityId as varchar(64)))
                    and (cfm.nat_operacao in (:natOperacaoList))
                group by extract(year from cfm.data_emissao)
            )
            select yearName as name, sum(total) as total from datasource 
            group by yearName
            order by yearName desc
        """
    )
    fun getFaturamentoAnualGroupedByYear(
        @Param("identityId") identityId: String,
        @Param("natOperacaoList") natOperacaoList: List<String>
    ): List<BasicAXChartInfoProjection>

    @Query(
        nativeQuery = true,
        value = """
            with datasource as (
                select extract(year from nfe.data_emissao) || '-' || extract(MONTH from nfe.data_emissao) as yearName, sum(nt.vnf) as total from nfe_reader.nfe_totalicms nt
                left join nfe_reader.nfe nfe on nfe.id = nt.nfe_id
                where 1 = 1
                    and nfe.data_emissao is not null
                    and ( cast(:identityId as varchar(64)) is null or nfe.user_create = cast(:identityId as varchar(64)))
                    and (nfe.nat_operacao in (:natOperacaoList))
                group by extract(year from nfe.data_emissao), extract(MONTH from nfe.data_emissao)
                union all
                select extract(year from cfm.data_emissao) || '-' || extract(MONTH from cfm.data_emissao) as yearName, sum(citm.v_prod) as total
                from nfe_reader.cfe_icms_total_model citm 
                left join nfe_reader.cupom_fiscal_model cfm on cfm.id = citm.cupom_fiscal_id
                where 1 = 1
                    and ( cast(:identityId as varchar(64)) is null or cfm.user_create = cast(:identityId as varchar(64)))
                    and (cfm.nat_operacao in (:natOperacaoList))
                group by extract(year from cfm.data_emissao), extract(MONTH from cfm.data_emissao)
            )
            select yearName as name, sum(total) as total from datasource 
            group by yearName
            order by yearName desc

        """
    )
    fun getFaturamentoAnualGrouppedByYearAndMonth(
        @Param("identityId") identityId: String,
        @Param("natOperacaoList") natOperacaoList: List<String>
    ): List<BasicAXChartInfoProjection>

    @Query(
        nativeQuery = true,
        value = """
            with datasource as (
                select 'NOTA_FISCAL' as tipoDocumento, d.uf as name, sum(nt.vnf) as total from nfe_reader.nfe_totalicms nt
                left join nfe_reader.nfe n on n.id = nt.nfe_id
                left join nfe_reader.destinatario d on d.id = n.destinatario_id
                 where 1 = 1
                   and ( cast(cast(:startDate as varchar(10)) as date) is null or n.data_emissao >= cast(cast(:startDate as varchar(10)) as date))
                   and ( cast(cast(:endDate as varchar(10)) as date) is null or n.data_emissao <= cast(cast(:endDate as varchar(10)) as date))
                   and ( cast(:identityId as varchar(64)) is null or n.user_create = cast(:identityId as varchar(64)))
                   and (n.nat_operacao in (:natOperacaoList))
                group by d.uf
                union all
                select 'CUPOM_FISCAL' as tipoDocumento, cfm.uf, sum(citm.vcfe) 
                from nfe_reader.cupom_fiscal_model cfm 
                left join nfe_reader.cfe_icms_total_model citm on citm.cupom_fiscal_id = cfm.id
                 where 1 = 1
                   and ( cast(cast(:startDate as varchar(10)) as date) is null or cfm.data_emissao >= cast(cast(:startDate as varchar(10)) as date))
                   and ( cast(cast(:endDate as varchar(10)) as date) is null or cfm.data_emissao <= cast(cast(:endDate as varchar(10)) as date))
                   and ( cast(:identityId as varchar(64)) is null or cfm.user_create = cast(:identityId as varchar(64)))
                   and ( cfm.nat_operacao in (:natOperacaoList))
                group by cfm.uf
                order by 2 desc
            )
            select name, sum(total) as total from datasource
            group by name
            order by 2 desc
        """
    )
    fun getDistVendasEstadoValorTotal(
        @Param("identityId") identityId: String,
        @Param("startDate") startDate: LocalDate?,
        @Param("endDate") endDate: LocalDate?,
        @Param("natOperacaoList") natOperacaoList: List<String>
    ): List<BasicAXChartInfoProjection>

    @Query(
        nativeQuery = true,
        value = """
            with datasource as (
                select 'NOTA_FISCAL' as tipoDocumento, d.uf as name, count(n.id) as total from nfe_reader.nfe_totalicms nt
                left join nfe_reader.nfe n on n.id = nt.nfe_id
                left join nfe_reader.destinatario d on d.id = n.destinatario_id
                 where 1 = 1
                   and ( cast(cast(:startDate as varchar(10)) as date) is null or n.data_emissao >= cast(cast(:startDate as varchar(10)) as date))
                   and ( cast(cast(:endDate as varchar(10)) as date) is null or n.data_emissao <= cast(cast(:endDate as varchar(10)) as date))
                   and ( cast(:identityId as varchar(64)) is null or n.user_create = cast(:identityId as varchar(64)))
                   and (n.nat_operacao in (:natOperacaoList))
                group by d.uf
                union all
                select 'CUPOM_FISCAL' as tipoDocumento, cfm.uf, count(cfm.id) from nfe_reader.cupom_fiscal_model cfm 
                 where 1 = 1
                   and ( cast(cast(:startDate as varchar(10)) as date) is null or cfm.data_emissao >= cast(cast(:startDate as varchar(10)) as date))
                   and ( cast(cast(:endDate as varchar(10)) as date) is null or cfm.data_emissao <= cast(cast(:endDate as varchar(10)) as date))
                   and ( cast(:identityId as varchar(64)) is null or cfm.user_create = cast(:identityId as varchar(64)))
                   and ( cfm.nat_operacao in (:natOperacaoList))
                group by cfm.uf
            )
            select name, sum(total) as total from datasource
            group by name
            order by 2 desc

        """
    )
    fun getDistVendasEstadoFrequencia(
        @Param("identityId") identityId: String,
        @Param("startDate") startDate: LocalDate?,
        @Param("endDate") endDate: LocalDate?,
        @Param("natOperacaoList") natOperacaoList: List<String>
    ): List<BasicAXChartInfoProjection>

    @Query(
        nativeQuery = true,
        value = """
            select
                 coalesce(sum(pfcpstret), 0) as pfcpstret,
                 coalesce(sum(vbc), 0) as vbc,
                 coalesce(sum(vbcst), 0) as vbcst,
                 coalesce(sum(vcofins), 0) as vcofins,
                 coalesce(sum(v_desc), 0) as vdesc,
                 coalesce(sum(vfcp), 0) as vfcp,
                 coalesce(sum(vfcpstret), 0) as vfcpstret,
                 coalesce(sum(v_frete), 0) as vfrete,
                 coalesce(sum(vicms), 0) as vicms,
                 coalesce(sum(vicmsdeson), 0) as vicmsdeson,
                 coalesce(sum(vicmsufdest), 0) as vicmsufdest,
                 coalesce(sum(vicmsufremet), 0) as vicmsufremet,
                 coalesce(sum(vii), 0) as vii,
                 coalesce(sum(vipi), 0) as vipi,
                 coalesce(sum(vipidevol), 0) as vipidevol,
                 coalesce(sum(vnf), 0) as vnf,
                 coalesce(sum(v_outro), 0) as voutro,
                 coalesce(sum(vpis), 0) as vpis,
                 coalesce(sum(v_prod), 0) as vprod,
                 coalesce(sum(vst), 0) as vst,
                 coalesce(sum(v_seg), 0) as vseg,
                 coalesce(sum(v_tot_trib), 0) as vtottrib 
             from nfe_reader.nfe_totalicms nt
             left join nfe_reader.nfe n on n.id = nt.nfe_id
             left join nfe_reader.destinatario d on d.id = n.destinatario_id
             where 1 = 1
               and ( cast(cast(:startDate as varchar(10)) as date) is null or n.data_emissao >= cast(cast(:startDate as varchar(10)) as date))
               and ( cast(cast(:endDate as varchar(10)) as date) is null or n.data_emissao <= cast(cast(:endDate as varchar(10)) as date))
               and ( cast(:identityId as varchar(64)) is null or n.user_create = cast(:identityId as varchar(64)))
               and (n.nat_operacao in (:natOperacaoList))
        """
    )
    fun getTotaisImpostos(
        @Param("identityId") identityId: String,
        @Param("startDate") startDate: LocalDate?,
        @Param("endDate") endDate: LocalDate?,
        @Param("natOperacaoList") natOperacaoList: List<String>
    ): TotalImpostoNFeProjection


    @Query(
        nativeQuery = true,
        value = """
                with datasource as (
                    select nfe.data_emissao as dataEmissao, count(1) as total from nfe_reader.nfe nfe
                    where 1 = 1
                        and nfe.data_emissao is not null
                        and ( cast(:identityId as varchar(64)) is null or nfe.user_create = cast(:identityId as varchar(64)))
                        and (nfe.nat_operacao in (:natOperacaoList))
                    group by nfe.data_emissao
                    union all
                    select cast(cfm.data_emissao as date), count(1) from nfe_reader.cupom_fiscal_model cfm 
                    where 1 = 1
                        and cfm.data_emissao is not null
                        and ( cast(:identityId as varchar(64)) is null or cfm.user_create = cast(:identityId as varchar(64)))
                        and ( cfm.nat_operacao in (:natOperacaoList))
                    group by cast(cfm.data_emissao as date)
                )
                select dataemissao, sum(total) as total from datasource
                group by dataemissao
                order by 1
        """
    )
    fun getQtdPedidosDiaADia(
        @Param("identityId") identityId: String,
        @Param("natOperacaoList") natOperacaoList: List<String>
    ): List<PedidosDiaADiaProjection>
}