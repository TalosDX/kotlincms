package dev.talosdx.blogcms.attachment

import org.springframework.data.jpa.repository.JpaRepository

interface AttachmentRepository : JpaRepository<Attachment, Long> {}