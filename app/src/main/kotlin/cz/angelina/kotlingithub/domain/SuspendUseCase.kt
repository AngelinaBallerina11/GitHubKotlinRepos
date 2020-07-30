package cz.angelina.kotlingithub.domain

interface SuspendUseCase<in I, out O> {
    suspend operator fun invoke(input: I): O
}
