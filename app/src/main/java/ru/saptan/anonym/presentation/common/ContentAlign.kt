package ru.saptan.anonym.presentation.common


enum class ContentAlign(var type: Int) {
    LEFT(1), CENTER(2), RIGHT(3);

    companion object {
        fun fromType(type: Int): ContentAlign {
            values().forEach {
                if (it.type == type) return it
            }

            throw IllegalArgumentException()
        }
    }

}