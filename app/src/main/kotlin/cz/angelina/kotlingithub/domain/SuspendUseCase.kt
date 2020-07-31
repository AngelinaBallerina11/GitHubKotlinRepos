package cz.angelina.kotlingithub.domain

interface SuspendUseCase<in I, out O> {
    suspend operator fun invoke(input: I): O
}

suspend operator fun <O> SuspendUseCase<Unit, O>.invoke(): O = invoke(Unit)
