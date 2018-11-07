package com.hoc.demo.repository

import com.hoc.demo.model.BlogEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BlogRepository : JpaRepository<BlogEntity, Long> {
  fun findByTitleIgnoreCaseContainingOrContentIgnoreCaseContaining(title: String, content: String): List<BlogEntity>
}