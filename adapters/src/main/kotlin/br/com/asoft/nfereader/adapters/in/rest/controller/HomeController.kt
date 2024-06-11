package br.com.asoft.nfereader.adapters.`in`.rest.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class HomeController {

    @GetMapping
    fun ping(): String {
        return "Ping is working..."
    }

    @GetMapping("free")
    fun pingSecured(): String {

        return "Ping SECURED is working..."
    }
}