package dev.talosdx.blogcms.mail

import mu.KotlinLogging
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class MailService(
    val mailSender: JavaMailSender,
    val mailRepository: MailRepository,
) {
    private val log = KotlinLogging.logger {}

    fun sendMail(mail: MailDto) {
        val message = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true)

        helper.setFrom("noreply@talosdx.dev")
        helper.setTo(mail.toEmails.toTypedArray())
        helper.setSubject(mail.header)
        helper.setText(mail.body)
        mailSender.send(message)


        mail.isSent = true
        mailRepository.save(mail.toEntity())
    }
}