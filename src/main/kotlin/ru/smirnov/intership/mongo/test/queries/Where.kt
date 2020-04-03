package ru.smirnov.intership.mongo.test.queries

class Where(private val predicates: List<Predicate>) {
    fun toMongo(): String {
        val columnToPredicates = predicates.groupBy { it.columnOperand }
        val columnToMongoPredicate = columnToPredicates.mapValues {
            it.value.joinToString(prefix = "{", postfix = "}", separator = ", ", transform = { predicate ->
                "${predicate.operator.toMongo()}: ${predicate.constantOperand.toMongo()}"
            })
        }
        return columnToMongoPredicate.map { "${it.key}: ${it.value}" }
            .joinToString(separator = ", ", prefix = "{", postfix = "}")
    }
}
