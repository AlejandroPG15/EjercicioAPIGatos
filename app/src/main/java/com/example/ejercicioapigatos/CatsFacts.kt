package com.example.ejercicioapigatos

data class CatsFacts (
    var fact : String,
)

{
    override fun toString(): String {
        return "Fact: $fact\n"
    }
}