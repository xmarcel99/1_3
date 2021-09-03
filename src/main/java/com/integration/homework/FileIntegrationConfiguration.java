package com.integration.homework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;

import java.io.File;

@Configuration
public class FileIntegrationConfiguration {

    @Bean
    IntegrationFlow fileIntegrationFlow(
            FileReadingMessageSource fileAdapter,
            FileRecognitionService transformer,
            FileWritingMessageHandler outputFileHandler) {

        return IntegrationFlows.from(fileAdapter, config -> config.poller(Pollers.fixedDelay(100)))
                .transform(transformer, "recognizeAndSaveFileName")
                .handle(outputFileHandler)
                .get();
    }

    @Bean
    FileReadingMessageSource fileAdapter() {
        FileReadingMessageSource fileSource = new FileReadingMessageSource();
        fileSource.setDirectory(new File("data/input"));

        return fileSource;
    }

    @Bean
    FileRecognitionService transformer() {
        return new FileRecognitionService();
    }

    @Bean
    FileWritingMessageHandler outputFileAdapter() {
        File directory = new File("data/output");
        FileWritingMessageHandler handler = new FileWritingMessageHandler(directory);
        handler.setExpectReply(false);
        handler.setFileNameGenerator(new FileName());
        handler.setAppendNewLine(true);

        return handler;
    }
}

