package com.hoc.demo.model

import javax.persistence.*

@Table(name = "blog")
@Entity
data class BlogEntity(
    val title: String,
    val content: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
)