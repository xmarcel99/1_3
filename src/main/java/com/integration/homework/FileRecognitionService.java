package com.integration.homework;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Iterator;

public class FileRecognitionService {

    public String recognizeAndSaveFileName()  {
        StringBuilder result = new StringBuilder();
        Iterator it = FileUtils.iterateFiles(new File("data/input"), null, false);
        while(it.hasNext()){
            result.append(((File) it.next()).getName()).append("\n");
        }
        return result.toString();
    }
}
