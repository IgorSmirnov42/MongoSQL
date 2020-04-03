package ru.smirnov.intership.mongo.test.queries

sealed class Operator {
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
