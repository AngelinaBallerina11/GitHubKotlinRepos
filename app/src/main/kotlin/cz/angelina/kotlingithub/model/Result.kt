package cz.angelina.kotlingithub.model

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
