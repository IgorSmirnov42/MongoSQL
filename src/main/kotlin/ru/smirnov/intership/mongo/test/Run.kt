package ru.smirnov.intership.mongo.test

import ru.smirnov.intership.mongo.test.parser.Parser
import ru.smirnov.intership.mongo.test.parser.ParserException

/**
 * Class for converting SQL queries to Mongo queries
 */
class SQLToMongo {
    /**
     * Converts SQL Select query to Mongo query
     *
     * @throws ParserException if select query is invalid
     */
    fun convert(input: String): String {
        val selectQuery = Parser().parseSelectQuery(input)
        return selectQuery.toMongo()
    }
}

fun main() {
    try {
        println(SQLToMongo().convert(readLine()!!))
    } catch (parserException: ParserException) {
        println("SYNTAX ERROR")
    }
}
