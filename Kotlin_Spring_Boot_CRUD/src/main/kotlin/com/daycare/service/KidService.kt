package com.daycare.service

import com.daycare.domain.Kid

interface KidService {

    fun saveKid(kid: Kid): Kid

    fun findKidById(kidId: Long): Kid

    fun findAllKids(): List<Kid>

    fun removeKid(kidId: Long)
}