package com.daycare.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface KidsRepository: JpaRepository<Kid, Long> {
}