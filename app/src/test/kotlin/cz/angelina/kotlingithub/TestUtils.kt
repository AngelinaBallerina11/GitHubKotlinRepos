package cz.angelina.kotlingithub

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest

/**
 * Use for testing suspending function or blocking functions which send values to the flow.
 * We can wait for the function to complete in `job2`
 * [runBlockingTest] is used to speed up execution of the [launch] blocks.
 * Otherwise if [runBlocking] is used the [runBlocking] is complete
 * before the coroutines even launch.
 */
@ExperimentalCoroutinesApi
fun <T> runCollectingSuspend(
    flow: Flow<T>,
    block: suspend () -> Unit
): List<T> {
    val result = mutableListOf<T>()
    runBlockingTest {
        val job = launch {
            flow.collect {
                result.add(it)
            }
        }
        val job2 = launch {
            block()
        }
        job2.invokeOnCompletion {
            job.cancel()
        }
    }
    return result.toList()
}

/**
 * Use for testing functions which launch a new coroutine inside,
 * i.e. we can not wait for the function to complete. [runBlocking]
 * slows down [launch] and delay gives enough time to collect the emitted values.
 */
suspend fun <T> runCollectingLaunch(
    flow: Flow<T>
): List<T> {
    val result = mutableListOf<T>()
    runBlocking {
        val job1 = launch {
            flow.collect { result.add(it) }
        }
        delay(200L)
        job1.cancel()
    }
    return result.toList()
}
