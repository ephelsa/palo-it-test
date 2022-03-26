package com.github.ephelsa.credijustotest.domain

/**
 * Useful interface to transform any other model in a domain.
 */
interface DomainMapper<Domain> {
    fun toDomain(): Domain
}