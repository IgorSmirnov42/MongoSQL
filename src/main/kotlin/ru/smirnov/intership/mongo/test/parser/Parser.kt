package ru.smirnov.intership.mongo.test.parser

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import ru.smirnov.intership.mongo.test.MongoSQLGrammarLexer
import ru.smirnov.intership.mongo.test.MongoSQLGrammarParser
import ru.smirnov.intership.mongo.test.parser.visitors.SelectQueryVisitor
import ru.smirnov.intership.mongo.test.queries.SelectQuery

/**
 * Class for parsing SQL queries.
 */
class Parser {
    /**
     * Parses select query.
     *
     * @throws ParserException if input is invalid select query
     */
    fun parseSelectQuery(input: String): SelectQuery {
        try {
            val lexer = MongoSQLGrammarLexer(CharStreams.fromString(input))
            val parser = MongoSQLGrammarParser(CommonTokenStream(lexer))
            return parser.select().accept(SelectQueryVisitor())
        } catch (exception: RuntimeException) {
            throw ParserException(exception.message ?: "").apply { initCause(exception) }
        }
    }
}
