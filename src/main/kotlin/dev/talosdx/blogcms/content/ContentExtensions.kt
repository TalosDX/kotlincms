package dev.talosdx.blogcms.content

import dev.talosdx.blogcms.user.toDto
import dev.talosdx.blogcms.user.toEntity

fun Content.toDto() = ContentDto(
    name,
    shortDescription,
    text,
    author.toDto(),
    contentType,
    attachment,
    createdDate,
    modifiedDate,
    id
)

fun ContentDto.toEntity() = Content(
    name,
    shortDescription,
    text,
    author.toEntity(),
    contentType,
    attachment,
    id
)