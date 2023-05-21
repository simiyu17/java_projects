package com.daycare.service

import com.daycare.domain.Kid
import com.daycare.domain.KidsRepository
import com.daycare.exception.KidNotFound
import org.springframework.stereotype.Service

@Service
class KidServiceImpl(private val kidRepo: KidsRepository): KidService {
    override fun saveKid(kid: Kid): Kid {
        return this.kidRepo.save(kid)
    }

    override fun findKidById(kidId: Long): Kid {
        return this.kidRepo.findById(kidId).orElseThrow { KidNotFound(kidId) }
    }

    override fun findAllKids(): List<Kid> {
        return this.kidRepo.findAll();
    }

    override fun removeKid(kidId: Long) {
        this.findKidById(kidId)
        this.kidRepo.deleteById(kidId)
    }
}