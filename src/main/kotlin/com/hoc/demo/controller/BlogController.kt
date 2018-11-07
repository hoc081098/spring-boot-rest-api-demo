package com.hoc.demo.controller

import com.hoc.demo.model.BlogEntity
import com.hoc.demo.service.BlogService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping(value = ["/api/v1/blog"])
@RestController
class BlogController(private val blogService: BlogService) {
  @GetMapping(value = [""])
  fun getALlBlogs(
      @RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) page: Int,
      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) size: Int
  ): Page<BlogEntity> {
    return blogService.getAll(page, size, Sort.Direction.ASC, "title")
  }

  companion object {
    const val DEFAULT_PAGE_NUM = "0"
    const val DEFAULT_PAGE_SIZE = "100"
  }
}