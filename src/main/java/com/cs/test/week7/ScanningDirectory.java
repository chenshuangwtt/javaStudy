package com.cs.test.week7;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ScanningDirectory extends RecursiveTask<List<File>> {
            File file;
            String searchword = "";
            public ScanningDirectory(File file, String searchword) {
                this.file = file;
                this.searchword = searchword;
            }
            @Override
            protected List<File> compute() {
                //
                List<RecursiveTask<List<File>>> forks = new LinkedList<>(); 
                List files = new ArrayList();

                 if (file.isDirectory()) {
                     for (File childFile : file.listFiles()) {
                         ScanningDirectory task = new ScanningDirectory(childFile, searchword);
                         forks.add(task);
                         task.fork();
                     }
                     for (RecursiveTask<List<File>> task : forks) {
                         files.addAll(task.join()); 
                     }
                 }
                 return files ;
            }
        }