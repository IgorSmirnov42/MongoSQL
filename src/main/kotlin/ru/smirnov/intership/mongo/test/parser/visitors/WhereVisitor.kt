package ru.smirnov.intership.mongo.test.parser.visitors

import ru.smirnov.intership.mongo.test.MongoSQLGrammarBaseVisitor
import ru.smirnov.intership.mongo.test.MongoSQLGrammarParser
import ru.smirnov.intership.mongo.test.queries.Where

class WhereVisitor : MongoSQLGrammarBaseVisitor<Where>() {
    override fun visitWhere(ctx: MongoSQLGrammarParser.WhereContext): Where {
        val predicates = ctx.predicates
            .map { it.accept(PredicateVisitor()) }
        return Where(predicates)
    }
}
