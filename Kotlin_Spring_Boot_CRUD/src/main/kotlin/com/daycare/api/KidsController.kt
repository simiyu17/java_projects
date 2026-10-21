package com.daycare.api

import com.daycare.domain.Kid
import com.daycare.service.KidService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kids")
class KidsController(private val kidService: KidService) {

    @PostMapping("")
    fun addKid(@RequestBody kid: Kid): ResponseEntity<Kid> = ResponseEntity(this.kidService.saveKid(kid), HttpStatus.CREATED)

    @GetMapping("")
    fun getAllKids(): ResponseEntity<List<Kid>> = ResponseEntity(this.kidService.findAllKids(), HttpStatus.OK)

    @GetMapping("/{kid-id}")
    fun findKidById(@PathVariable("kid-id")  kidId: Long): ResponseEntity<Kid> = ResponseEntity(this.kidService.findKidById(kidId), HttpStatus.OK)

    @DeleteMapping("/{kid-id}")
    fun deleteKidById(@PathVariable("kid-id")  kidId: Long) {
        this.kidService.removeKid(kidId)
    }
}