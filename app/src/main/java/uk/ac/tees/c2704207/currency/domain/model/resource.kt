package uk.ac.tees.c2704207.currency.domain.model


sealed class resource<T>(val data : T? = null, val message: String? = null){

    class Success<T>(data: T? = null) : resource<T>(data)
    class Error<T>(message: String, data: T?= null):resource<T>(data, message)

}