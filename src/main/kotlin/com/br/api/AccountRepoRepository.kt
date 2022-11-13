package com.br.api

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface AccountRepoRepository : MongoRepository<Account,String> {
    fun findByDocument(documented: String): Optional<Account>
}