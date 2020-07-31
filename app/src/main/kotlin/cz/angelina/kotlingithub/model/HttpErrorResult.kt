package cz.angelina.kotlingithub.model

data class HttpErrorResult(
    val code: Int,
    val errorMessage: String? = null,
    val apiThrowable: Throwable? = null
) : ErrorResult(errorMessage, apiThrowable)
