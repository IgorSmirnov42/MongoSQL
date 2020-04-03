package ru.smirnov.intership.mongo.test.queries

sealed class Operand(protected val value: String) {
    abstract fun toMongo(): String
}

class NumberOperand(value: String) : Operand(value) {
    override fun toMongo(): String = value
}

class StringOperand(value: String) : Operand(value) {
    override fun toMongo(): String = "\"$value\""
}