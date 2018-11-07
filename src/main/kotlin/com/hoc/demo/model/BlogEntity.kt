package com.hoc.demo.model

import javax.persistence.*

@Table(name = "blog")
@Entity
data class BlogEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val title: String,
    val content: String
)