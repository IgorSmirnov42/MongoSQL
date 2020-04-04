package ru.smirnov.intership.mongo.test.queries

/**
 * Where statement for query.
 *
 * Contains list of predicates. Statement for object is true iff every predicate is true on this object
 */
class Where(private val predicates: List<Predicate>) {
    /**
     * Converts where statement to Mongo format.
     *
     * For example, if statement was "WHERE a > 5 AND x <> 3
     * it will be converted to {a : {$gt: 5}, x: {$ne: 3}}
     */
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
