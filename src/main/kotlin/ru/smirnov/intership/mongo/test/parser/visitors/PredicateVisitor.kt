package ru.smirnov.intership.mongo.test.parser.visitors

import ru.smirnov.intership.mongo.test.MongoSQLGrammarBaseVisitor
import ru.smirnov.intership.mongo.test.MongoSQLGrammarParser
import ru.smirnov.intership.mongo.test.parser.ParserException
import ru.smirnov.intership.mongo.test.queries.*

class PredicateVisitor : MongoSQLGrammarBaseVisitor<Predicate>() {
    override fun visitLeftPredicate(ctx: MongoSQLGrammarParser.LeftPredicateContext): Predicate {
        val operator = ctx.booleanBinaryOperator().let {
            when {
                it.EQUAL() != null -> Equality
                it.LESS() != null -> Less
                it.GREATER() != null -> Greater
                it.NOT_EQUAL() != null -> Inequality
                else -> throw ParserException("Operator ${it.text} is not supported")
            }
        }
        val operand = ctx.operand().accept(OperandVisitor())
        return Predicate(ctx.column.text, operand, operator)
    }

    override fun visitRightPredicate(ctx: MongoSQLGrammarParser.RightPredicateContext): Predicate {
        val operator = ctx.booleanBinaryOperator().let {
            when {
                it.EQUAL() != null -> Equality
                it.LESS() != null -> Greater
                it.GREATER() != null -> Less
                it.NOT_EQUAL() != null -> Inequality
                else -> throw ParserException("Operator ${it.text} is not supported")
            }
        }
        val operand = ctx.operand().accept(OperandVisitor())
        return Predicate(ctx.column.text, operand, operator)
    }
}

