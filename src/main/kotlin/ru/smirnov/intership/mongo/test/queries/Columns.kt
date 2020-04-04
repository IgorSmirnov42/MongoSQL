package ru.smirnov.intership.mongo.test.queries

/**
 * List of columns in a query
 */
sealed class Columns {
    /**
     * Returns columns as they have to be substituted in Mongo query.
     */
    abstract fun toMongo(): String
}

/**
 * Concrete list of columns listed in query.
 */
class ConcreteColumns(private val columns: List<String>) : Columns() {
    /**
     * Returns columns as they have to be substituted in Mongo query.
     *
     * For example, if there were columns a, b, c, it will return "{a: 1, b: 1, c: 1}"
     */
    override fun toMongo(): String {
        return columns.joinToString(separator = ", ", prefix = "{", postfix = "}", transform = { "$it: 1" })
    }
}

object AllColumns: Columns() {
    override fun toMongo(): String {
        return "{}"
    }
}
