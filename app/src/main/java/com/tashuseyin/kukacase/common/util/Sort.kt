package com.tashuseyin.kukacase.common.util

enum class Sort(val value: String?) {
    ASC("asc"),
    DESC("desc"),
    NONE(null)
}

fun Sort.updateSort(): Sort {
    return when(this) {
        Sort.ASC -> Sort.DESC
        Sort.DESC -> Sort.ASC
        Sort.NONE -> Sort.ASC
    }
}