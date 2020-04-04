package com.topsail.batch.demo.example8.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;

/**
 * @author Steven
 * @date 2020-04-05
 */
@Slf4j
public class MyChunkListener {

    @BeforeChunk
    public void beforeChunk(ChunkContext chunkContext) {
        log.info("chunk before running: {}", chunkContext.getStepContext().getStepName());
    }

    @AfterChunk
    public void afterChunk(ChunkContext chunkContext) {
        log.info("chunk after running: {}", chunkContext.getStepContext().getStepName());
    }

}
