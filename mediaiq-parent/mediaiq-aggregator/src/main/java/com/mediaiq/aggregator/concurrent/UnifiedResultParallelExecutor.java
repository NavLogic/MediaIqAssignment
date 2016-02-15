package com.mediaiq.aggregator.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UnifiedResultParallelExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnifiedResultParallelExecutor.class);
    private ExecutorService executor = Executors.newFixedThreadPool(10);
    private List<Future<Object>> futures = new ArrayList<>();
    private List<Exception> fetchTimeCollectiveExceptions = new ArrayList<>();

    public void submit(Callable<Object> task) throws Exception {
        futures.add(executor.submit(task));
    }

    public List<Object> getUnifiedResult() throws Exception {
        executor.shutdown();
        List<Object> completedResults = new ArrayList<>();
        for (Future<Object> future : futures) {
            try {
                completedResults.add(future.get());
            } catch (InterruptedException | ExecutionException | RuntimeException e) {
                fetchTimeCollectiveExceptions.add(e);
            }
        }

        fetchTimeCollectiveExceptions.forEach(ex -> {
            LOGGER.error("Error in one of the tasks", ex);
        });

        if (fetchTimeCollectiveExceptions.isEmpty() || futures.size() != fetchTimeCollectiveExceptions.size())
            return completedResults;
        else
            return null;
    }
}