package dev.talosdx.blogcms.content

import dev.talosdx.blogcms.exception.AlreadyExistsException
import dev.talosdx.blogcms.exception.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ContentService(
    private val repository: ContentRepository,
) {

    fun publishContent(content: ContentDto) =
        if (!repository.existsByNameIgnoreCase(content.name))
            repository.save(content.toEntity())
        else
            throw AlreadyExistsException("Content with than name=${content.name} already exists!")

    fun updateContent(contentIn: ContentDto) =
        if (repository.existsById(contentIn.id!!)) {
            val editableContent = repository.getById(contentIn.id!!)

            editableContent.name = contentIn.name
            editableContent.shortDescription = contentIn.shortDescription
            editableContent.text = contentIn.text
            editableContent.contentType = contentIn.contentType
            editableContent.attachment = contentIn.attachment
            repository.save(editableContent)
        } else
            throw NotFoundException("Content with id=${contentIn.id}")

    fun getContent(id: Long): ContentDto =
        repository.getById(id).toDto()

    fun getContentByName(name: String): ContentDto =
        repository.findByNameContainsIgnoreCase(name).toDto()

    fun deleteContent(contentId: Long) = repository.deleteById(contentId)

    fun deleteContent(content: ContentDto) = content.id?.let { repository.deleteById(it) }
}

