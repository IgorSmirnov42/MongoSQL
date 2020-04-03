package ru.smirnov.intership.mongo.test.queries

class SelectQuery(
    private val tableName: String,
    private val columns: Columns,
    private val where: Where,
    private val offset: Int?,
    private val limit: Int?
) {
    fun toMongo(): String {
        val insideFind = if (columns == AllColumns) {
            where.toMongo()
        } else {
            "${where.toMongo()}, ${columns.toMongo()}"
        }
        val skipStatement = offset?.let { ".skip($offset)" } ?: ""
        val limitStatement = limit?.let { ".limit($limit)" } ?: ""
        return "db.${tableName}.find($insideFind)$skipStatement$limitStatement"
    }
}
