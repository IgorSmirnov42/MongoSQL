package ru.smirnov.intership.mongo.test.parser.visitors

import ru.smirnov.intership.mongo.test.MongoSQLGrammarBaseVisitor
import ru.smirnov.intership.mongo.test.MongoSQLGrammarParser
import ru.smirnov.intership.mongo.test.queries.SelectQuery

class SelectQueryVisitor : MongoSQLGrammarBaseVisitor<SelectQuery>() {
    override fun visitSelect(ctx: MongoSQLGrammarParser.SelectContext): SelectQuery {
        val tableName = ctx.table.text
        val columns = ctx.columns().accept(ColumnsVisitor())
        val where = ctx.where().accept(WhereVisitor())
        val offset = ctx.offset()?.accept(OffsetVisitor())
        val limit = ctx.limit()?.accept(LimitVisitor())

        return SelectQuery(tableName, columns, where, offset, limit)
    }
}