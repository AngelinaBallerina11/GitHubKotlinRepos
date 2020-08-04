package cz.angelina.kotlingithub.domain

/**
 * Abstract UseCase with input and output parameters
 */
interface SuspendUseCase<in I, out O> {
    suspend operator fun invoke(input: I): O
}

/**
 * Utility function in order not to pass Unit parameter
 */
suspend operator fun <O> SuspendUseCase<Unit, O>.invoke(): O = invoke(Unit)
