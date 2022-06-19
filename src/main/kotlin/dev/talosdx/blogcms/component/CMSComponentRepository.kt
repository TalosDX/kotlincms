package dev.talosdx.blogcms.component

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CMSComponentRepository : JpaRepository<CMSComponent, String> {

}