package cz.angelina.kotlingithub.model

/**
 * Result obtained from the Use Case. It can be either [Success] and hold the data,
 * or it is an [Error] and has an [ErrorResult] which has error details.
 */
sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()

    data class Error<out T : Any>(val error: ErrorResult) : Result<T>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$data]"
            is Error -> "Error[exception=${error.throwable}"
        }
    }
}
