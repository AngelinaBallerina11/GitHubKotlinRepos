package cz.angelina.kotlingithub

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest

/**
 * Use for testing suspending function,
 * i.e. we can wait for the function to complete in `job2`
 * [runBlockingTest] is used to speed up execution of coroutines,
 * otherwise if [runBlocking] is used the [runBlocking] is complete
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
            flow.collect { result.add(it) }
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
 * i.e. we can not wait for the function to complete. Thus, we wait
 * for a certain [takeCount] number of emitted values and then complete
 * the job `job1`.
 */
fun <T> runCollectingLaunch(
    flow: Flow<T>,
    takeCount: Int = 3,
    block: () -> Unit
): List<T> {
    val result = mutableListOf<T>()
    runBlocking {
        val job1 = launch {
            flow
                .take(takeCount)
                .collect { result.add(it) }
        }
        launch {
            block()
        }
        job1.join()
    }
    return result.toList()
}
