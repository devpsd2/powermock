package com.luc.powermock

class CollaboratorForPartialMocking {

    private fun privateMethod(): String {
        return "Hello Baeldung!"
    }

    fun privateMethodCaller(): String {
        return privateMethod() + " Welcome to the Java world."
    }
}