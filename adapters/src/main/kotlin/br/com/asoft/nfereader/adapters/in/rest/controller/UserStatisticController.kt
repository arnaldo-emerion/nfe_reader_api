package br.com.asoft.nfereader.adapters.`in`.rest.controller

import br.com.asoft.nfereader.application.port.`in`.service.SecurityUtilPort
import br.com.asoft.nfereader.application.port.`in`.service.UserConfigurationServicePort
import br.com.asoft.nfereader.model.UserStatistic
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user-statistic")
class UserStatisticController(
    private val userConfigurationService: UserConfigurationServicePort,
    private val securityUtil: SecurityUtilPort
) {

    @GetMapping
    fun getUserStatistic(): UserStatistic {
        return this.userConfigurationService.getUserStatistic(securityUtil.getIdentityId())
    }
}
