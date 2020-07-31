package cz.angelina.kotlingithub.infrastructure

import cz.angelina.kotlingithub.model.ErrorResult
import cz.angelina.kotlingithub.model.Result

/**
 * Wrap a suspending [call] in try/catch. In case an exception is thrown, a [Result.Error] is
 * created.
 */
suspend fun <T : Any> safeCall(errorMessage: String = "", call: suspend () -> Result<T>): Result<T> {
    return try {
        call()
    } catch (e: Throwable) {
        Result.Error(ErrorResult(errorMessage, e))
    }
}
