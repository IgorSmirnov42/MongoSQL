package ru.smirnov.intership.mongo.test.parser.visitors

import ru.smirnov.intership.mongo.test.MongoSQLGrammarBaseVisitor
import ru.smirnov.intership.mongo.test.MongoSQLGrammarParser
import ru.smirnov.intership.mongo.test.queries.NumberOperand
import ru.smirnov.intership.mongo.test.queries.Operand
import ru.smirnov.intership.mongo.test.queries.StringOperand

class OperandVisitor : MongoSQLGrammarBaseVisitor<Operand>() {
    override fun visitNumberOperand(ctx: MongoSQLGrammarParser.NumberOperandContext): Operand {
        return NumberOperand(ctx.text)
    }

    override fun visitStringOperand(ctx: MongoSQLGrammarParser.StringOperandContext): Operand {
        return StringOperand(ctx.text.substring(1 until ctx.text.lastIndex))
    }
}