package com.daycare.domain

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "kids")
data class Kid(
        val firstName: String,
        val lastName: String,
        val gender: String,
        val birthDate: LocalDate,
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long?=null,
)
