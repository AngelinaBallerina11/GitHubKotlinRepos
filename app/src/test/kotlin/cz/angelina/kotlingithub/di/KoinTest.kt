package cz.angelina.kotlingithub.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Test
import org.koin.dsl.koinApplication
import org.koin.test.check.checkModules

@ExperimentalCoroutinesApi
internal class KoinTest {

    @Test
    fun `check a Koin module`() {
        koinApplication {
            modules(mainModule)
        }.checkModules()
    }
}
