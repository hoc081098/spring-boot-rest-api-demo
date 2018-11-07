package com.hoc.demo.service

import com.hoc.demo.model.BlogEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import java.util.*

interface BlogService {
  fun getAll(
      page: Int,
      size: Int,
      direction: Sort.Direction,
      properties: String
  ): Page<BlogEntity>

  fun findById(id: Long): Optional<BlogEntity>

  fun create(blogEntity: BlogEntity): BlogEntity

  fun update(blogEntity: BlogEntity): BlogEntity

  fun delete(id: Long)

  fun searchByTitleOrContent(title: String, content: String): List<BlogEntity>
}