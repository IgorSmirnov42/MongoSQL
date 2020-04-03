package ru.smirnov.intership.mongo.test.queries

data class Predicate(val columnOperand: String,
                val constantOperand: Operand,
                val operator: Operator)
