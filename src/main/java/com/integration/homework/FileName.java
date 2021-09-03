package com.integration.homework;

import org.springframework.integration.file.FileNameGenerator;
import org.springframework.messaging.Message;

public class FileName implements FileNameGenerator {
    @Override
    public String generateFileName(Message<?> message) {
        return "outputFile";
    }
}
