package util.extensions

import androidx.lifecycle.LiveData

fun <T> LiveData<T?>.valueOr(default: T): T = value ?: default
