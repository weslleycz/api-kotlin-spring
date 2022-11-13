package com.br.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException
import java.util.*

@RestController
@RequestMapping
class AccountController(val repository: AccountRepoRepository) {
    @PostMapping()
    fun create(@RequestBody account: Account): ResponseEntity<Account> {
        val accoutSave = repository.save(account)
        return ResponseEntity.ok(accoutSave)
    }
    
    @GetMapping("/get")
    fun read() = ResponseEntity.ok(repository.findAll())
    
    @PutMapping("/{document}")
    fun update(@PathVariable document:String, @RequestBody account:Account): ResponseEntity<Account>{
       val accountDBOptional = repository.findByDocument(document) 
       val accountDB = accountDBOptional.orElseThrow{ RuntimeException("Account document: $document not found!") }
       val save = repository.save(accountDB.copy(name = account.name, balance = account.balance))
       return  ResponseEntity.ok(save)
    }
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:String): ResponseEntity<Optional<Account>> {
        val entity = repository.findById(id)
        repository.deleteById(id)
        return ResponseEntity.ok(entity)
    }
}

