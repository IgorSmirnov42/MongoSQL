package ru.smirnov.intership.mongo.test.queries

/**
 * Class for storing predicates from query.
 *
 * Predicates stored here must have column name as left operand and constant as right operand
 */
data class Predicate(val columnOperand: String,
                val constantOperand: Operand,
                val operator: Operator)
