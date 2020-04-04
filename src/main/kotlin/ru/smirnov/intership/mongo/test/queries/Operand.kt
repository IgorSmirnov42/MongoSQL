package ru.smirnov.intership.mongo.test.queries

/**
 * Class for storing constant operand from query.
 */
sealed class Operand(protected val value: String) {
    /**
     * Returns operand as it has to be substituted to a query.
     *
     * For example, string operand has to be substituted in quotes
     */
    abstract fun toMongo(): String
}

/**
 * Integer or float operand.
 */
class NumberOperand(value: String) : Operand(value) {
    override fun toMongo(): String = value
}

/**
 * String operand.
 *
 * Has to be stored without quotes
 */
class StringOperand(value: String) : Operand(value) {
    /** Substituted with double quotes */
    override fun toMongo(): String = "\"$value\""
}