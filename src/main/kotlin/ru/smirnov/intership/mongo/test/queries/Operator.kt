package ru.smirnov.intership.mongo.test.queries

/**
 * Operators in query.
 */
sealed class Operator {
    /**
     * Returns Mongo alias for this operator.
     *
     * For example, for Equality it is "$eq"
     */
    abstract fun toMongo(): String
}

object Equality : Operator() {
    override fun toMongo(): String = "\$eq"
}

object Greater : Operator() {
    override fun toMongo(): String = "\$gt"
}

object Inequality : Operator() {
    override fun toMongo(): String = "\$ne"
}

object Less : Operator() {
    override fun toMongo(): String = "\$lt"
}
