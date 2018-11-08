package com.hoc.demo.controller

import com.hoc.demo.model.BlogEntity
import com.hoc.demo.service.BlogService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RequestMapping(value = ["/api/v1/blog"])
@RestController
class BlogController(private val blogService: BlogService) {

  /*@PostConstruct
  fun initData() {
    blogService.deleteAll()
    (1..100).map { BlogEntity(title = "Title $it", content = "Content $it ".repeat(Random().nextInt(20))) }
        .forEach { blogService.create(it) }
  }*/

  @GetMapping(value = [""])
  @ResponseStatus(HttpStatus.OK)
  fun getAllBlogs(
      @RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) page: Int,
      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) size: Int
  ): Page<BlogEntity> {
    return blogService.getAll(page, size, Sort.Direction.ASC, "title")
  }

  @PostMapping(value = [""])
  @ResponseStatus(HttpStatus.CREATED)
  fun createBlog(
      @RequestBody map: Map<String, String>,
      request: HttpServletRequest,
      response: HttpServletResponse
  ): BlogEntity {
    println(map)
    return blogService.create(
        BlogEntity(
            title = map["title"] ?: "",
            content = map["content"] ?: ""
        )
    ).also {
      response.setHeader(
          "Location",
          request.requestURL.append('/').append(it.id).toString()
      )
    }
  }

  @GetMapping(value = ["/{id}"])
  fun getBlog(@PathVariable(name = "id", required = true) id: Long): ResponseEntity<BlogEntity> {
    println(id)
    return blogService.findById(id)
        .map { ResponseEntity.ok(it) }
        .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
  }

  companion object {
    const val DEFAULT_PAGE_NUM = "0"
    const val DEFAULT_PAGE_SIZE = "100"
  }
}