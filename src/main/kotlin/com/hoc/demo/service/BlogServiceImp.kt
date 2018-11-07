package com.hoc.demo.service

import com.hoc.demo.model.BlogEntity
import com.hoc.demo.repository.BlogRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class BlogServiceImp(private val blogRepository: BlogRepository) : BlogService {
  override fun getAll(
      page: Int,
      size: Int,
      direction: Sort.Direction,
      properties: String
  ) = blogRepository.findAll(PageRequest.of(page, size, direction, properties))

  override fun findById(id: Long) = blogRepository.findById(id)

  override fun create(blogEntity: BlogEntity) = blogRepository.save(blogEntity)

  override fun update(blogEntity: BlogEntity) = blogRepository.save(blogEntity)

  override fun delete(id: Long) = blogRepository.deleteById(id)

  override fun searchByTitleOrContent(title: String, content: String) =
      blogRepository.findByTitleIgnoreCaseContainingOrContentIgnoreCaseContaining(title,  content)

}
