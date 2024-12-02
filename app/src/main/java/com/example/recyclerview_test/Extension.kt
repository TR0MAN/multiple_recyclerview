package com.example.recyclerview_test


fun List<Item>.toMultiMode(): List<Item> {
    return this.map {
        Item(
            id = it.id,
            name = it.name,
            career = it.career,
            isMultiMode = true
        )
    }
}

fun List<Item>.toSingleMode(): List<Item> {
    return this.map {
        Item(
            id = it.id,
            name = it.name,
            career = it.career,
            isMultiMode = false
        )
    }
}
