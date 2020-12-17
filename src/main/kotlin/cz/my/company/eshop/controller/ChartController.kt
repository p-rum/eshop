package cz.my.company.eshop.controller

import cz.my.company.eshop.model.Client
import cz.my.company.eshop.model.ClientUpdate
import cz.my.company.eshop.model.request.CreateClientRequest
import cz.my.company.eshop.model.request.UpdateClientRequest
import cz.my.company.eshop.service.ClientService
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.data.annotation.AccessType
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.annotation.security.PermitAll
import javax.servlet.annotation.HttpConstraint
import javax.servlet.annotation.HttpMethodConstraint
import javax.servlet.annotation.ServletSecurity


import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping


@Controller
class GoogleChartsController {
    @GetMapping("/")
    fun getPieChart(model: Model): String {
        val graphData: MutableMap<String, Int> = HashMap<String, Int>()
        model.addAttribute("chartData", graphData)
        return "google-charts"
    }
}