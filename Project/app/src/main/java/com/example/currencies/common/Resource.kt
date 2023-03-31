package com.example.currencies.common

/**
* A sealed class representing the different states of a resource. A resource can be in one of three states:
* [Success], [Error], or [Loading].
* @param T The type of data wrapped by this resource.
* @property data The data wrapped by this resource. This can be null in the case of [Loading] or [Error].
* @property message The error message associated with this resource. This can be null in the case of [Success] or [Loading].
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
