package ru.smirnov.intership.mongo.test

import ru.smirnov.intership.mongo.test.parser.Parser
import ru.smirnov.intership.mongo.test.parser.ParserException

class SQLToMongo {
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
