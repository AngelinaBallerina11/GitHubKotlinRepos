package cz.angelina.kotlingithub.model

/**
 * Class to hold the error information
 * @property message text error message. It could have app specific or UseCase specific information
 * @property throwable a specific exception object
 */
open class ErrorResult(open var message: String? = null, open var throwable: Throwable? = null)
