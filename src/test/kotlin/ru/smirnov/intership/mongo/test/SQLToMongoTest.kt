package ru.smirnov.intership.mongo.test

import org.junit.Assert.*
import org.junit.Test
import ru.smirnov.intership.mongo.test.parser.ParserException

class SQLToMongoTest {
    @Test
    fun shouldConvertSimpleSelect() {
        val result = SQLToMongo().convert("SELECT * FROM someTable")
        assertEquals("db.someTable.find({})", result)
    }

    @Test
    fun shouldProjectColumns() {
        val result = SQLToMongo().convert("SELECT name, surname FROM collection")
        assertEquals("db.collection.find({}, {name: 1, surname: 1})", result)
    }

    @Test
    fun shouldConvertOffsetAndLimit() {
        val result = SQLToMongo().convert("SELECT * FROM collection OFFSET 5 LIMIT 10")
        assertEquals("db.collection.find({}).skip(5).limit(10)", result)
    }

    @Test
    fun shouldConvertSimpleWhereQuery() {
        val result = SQLToMongo().convert("SELECT * FROM customers WHERE age > 22")
        assertEquals("db.customers.find({age: {\$gt: 22}})", result)
    }

    @Test
    fun shouldConvertWhereQueryWithManyPredicates() {
        val result = SQLToMongo().convert("SELECT * FROM customers WHERE age > 22 AND name = 'Vasya'")
        assertEquals("db.customers.find({age: {\$gt: 22}, name: {\$eq: \"Vasya\"}})", result)
    }

    @Test
    fun shouldConvertQueryWithWhereAndColumns() {
        val result = SQLToMongo().convert("SELECT name, surname FROM collection WHERE age = 42")
        assertEquals("db.collection.find({age: {\$eq: 42}}, {name: 1, surname: 1})", result)
    }

    @Test(expected = ParserException::class)
    fun shouldThrowOnIllegalInput() {
        SQLToMongo().convert("42")
    }

    @Test(expected = ParserException::class)
    fun shouldThrowOnIllegalInputAfterTheQuery() {
        SQLToMongo().convert("SELECT * FROM someTable 42")
    }

    @Test(expected = ParserException::class)
    fun shouldThrowOnIllegalTableName() {
        SQLToMongo().convert("SELECT * FROM 0")
    }

    @Test(expected = ParserException::class)
    fun shouldThrowOnIllegalColumnName() {
        SQLToMongo().convert("SELECT 2 FROM table")
    }

    @Test(expected = ParserException::class)
    fun shouldThrowOnExpressionWithoutColumnNameInWhere() {
        SQLToMongo().convert("SELECT * FROM table WHERE 3 > 2")
    }

    @Test(expected = ParserException::class)
    fun shouldThrowOnExpressionWithTwoColumnNamesInWhere() {
        SQLToMongo().convert("SELECT * FROM table WHERE a > b")
    }

    @Test(expected = ParserException::class)
    fun shouldThrowOnExpressionWithTwoStringLiteralsInWhere() {
        SQLToMongo().convert("SELECT * FROM table WHERE 'a' > 'b'")
    }

    @Test(expected = ParserException::class)
    fun shouldThrowOnTooBigIntegerInOffset() {
        SQLToMongo().convert("SELECT * FROM table OFFSET 5000000000000")
    }

    @Test(expected = ParserException::class)
    fun shouldThrowOnTooBigIntegerInLimit() {
        SQLToMongo().convert("SELECT * FROM table LIMIT 5000000000000")
    }

    @Test
    fun shouldSupportCaseInsensitiveKeywordsInput() {
        val result = SQLToMongo().convert("SeLeCt * FroM someTable")
        assertEquals("db.someTable.find({})", result)
    }

    @Test
    fun shouldSupportAllBinaryOperators() {
        val result = SQLToMongo().convert(
            "SELECT * FROM customers WHERE age > 22 AND name = 'Vasya' AND surname <> \"Petrov\" AND height < 190"
        )
        assertEquals(
            "db.customers.find({age: {\$gt: 22}, name: {\$eq: \"Vasya\"}, surname: {\$ne: \"Petrov\"}, height: {\$lt: 190}})", result)
    }

    @Test
    fun shouldSupportColumnsOnTheRightSideOfBinaryOperators() {
        val result = SQLToMongo().convert(
            "SELECT * FROM customers WHERE 22 < age AND 'Vasya' = name AND \"Petrov\" <> surname AND 190 > height"
        )
        assertEquals(
            "db.customers.find({age: {\$gt: 22}, name: {\$eq: \"Vasya\"}, surname: {\$ne: \"Petrov\"}, height: {\$lt: 190}})", result)
    }

    @Test
    fun shouldSupportPredicatesInBraces() {
        val result = SQLToMongo().convert(
            "SELECT * FROM customers WHERE (22 < age) AND ('Vasya' = name) AND (\"Petrov\" <> surname) AND (190 > height)"
        )
        assertEquals(
            "db.customers.find({age: {\$gt: 22}, name: {\$eq: \"Vasya\"}, surname: {\$ne: \"Petrov\"}, height: {\$lt: 190}})", result)
    }
}
