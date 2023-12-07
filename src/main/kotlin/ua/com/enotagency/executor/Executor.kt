package ua.com.enotagency.executor

import jakarta.annotation.PreDestroy
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object Executor {
    private val executorService: ExecutorService = Executors.newFixedThreadPool(3)

    fun execute(task: () -> Unit) {
        executorService.execute { task() }
    }

    @PreDestroy
    fun shutdown() {
        executorService.shutdown()
    }
}