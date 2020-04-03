package ru.smirnov.intership.mongo.test.parser.visitors

import ru.smirnov.intership.mongo.test.MongoSQLGrammarBaseVisitor
import ru.smirnov.intership.mongo.test.MongoSQLGrammarParser
import ru.smirnov.intership.mongo.test.queries.AllColumns
import ru.smirnov.intership.mongo.test.queries.Columns
import ru.smirnov.intership.mongo.test.queries.ConcreteColumns

class ColumnsVisitor : MongoSQLGrammarBaseVisitor<Columns>() {
    override fun visitAllColumns(ctx: MongoSQLGrammarParser.AllColumnsContext): Columns {
        return AllColumns
    }

    override fun visitSpecificColumns(ctx: MongoSQLGrammarParser.SpecificColumnsContext): Columns {
        val columnsList = ctx.columnsList.map { it.text }.toList()
        return ConcreteColumns(columnsList)
    }
}
