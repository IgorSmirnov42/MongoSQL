package ru.smirnov.intership.mongo.test.queries

sealed class Columns {
    abstract fun toMongo(): String
}

class ConcreteColumns(private val columns: List<String>) : Columns() {
    override fun toMongo(): String {
        return columns.joinToString(separator = ", ", prefix = "{", postfix = "}", transform = { "$it: 1" })
    }
}

object AllColumns: Columns() {
    override fun toMongo(): String {
        return "{}"
    }
}
