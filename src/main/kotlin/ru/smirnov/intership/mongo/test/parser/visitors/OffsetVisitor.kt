package ru.smirnov.intership.mongo.test.parser.visitors

import ru.smirnov.intership.mongo.test.MongoSQLGrammarBaseVisitor
import ru.smirnov.intership.mongo.test.MongoSQLGrammarParser
import ru.smirnov.intership.mongo.test.parser.ParserException

class OffsetVisitor : MongoSQLGrammarBaseVisitor<Int>() {
    override fun visitOffset(ctx: MongoSQLGrammarParser.OffsetContext): Int {
        return ctx.INTEGER().text.toIntOrNull()
            ?: throw ParserException("Too big integer in offset: ${ctx.OFFSET().text}")
    }
}