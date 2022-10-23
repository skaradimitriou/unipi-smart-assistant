package com.stathis.smartassistant.util

import com.google.firebase.firestore.QuerySnapshot
import java.io.IOException

/**
 * This file contains the extension functions that are used across the app for queries to firestore
 */


/**
 * Helper fun to transform firestore results to a list of a certain object.
 *
 * Usage:
 *
 * val documents = firestore.collection("XXX").get().await()
 * val list = documents.toListOf<Foo>()
 *
 * list is now either a list of foo objects or an empty list
 */
inline fun <reified T> QuerySnapshot.toListOf(): List<T> {
    return try {
        val list = mutableListOf<T>()
        documents.forEach { document ->
            val model = document.toObject(T::class.java)
            model?.let { list.add(it) }
        }
        list
    } catch (ioException: IOException) {
        listOf()
    }
}