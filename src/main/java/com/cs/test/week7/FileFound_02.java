package com.cs.test.week7;

import java.io.File;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class FileFound_02 {
            public static void main(String[] args) {
                String searchword = "wamp" ;
                System.out.println("Andrey3k");
                // Get Search word from arguments
//                if( args.length > 0 ) {
//                    searchword = args[0];
//                    System.out.println("____ args.length " + searchword);
//                }
                // Get Disk Drives
                File[] rootDrive = File.listRoots();
                for(File sysDrive : rootDrive){
                    System.out.println("Drive : " + sysDrive);
                    System.out.println("Drive getAbsolutePath: " + sysDrive.getAbsolutePath());

                    // Scanning D Disk
                    if( sysDrive.getAbsolutePath().startsWith("D:") ) {
                        System.out.println("sysDrive.getAbsolutePath(): " + sysDrive.getAbsolutePath());

                        if( searchword != null ) {
                            ForkJoinPool forkJoinPool = new ForkJoinPool() ;

                            List<File> directories = forkJoinPool.invoke(new ScanningDirectory( new File( sysDrive.getAbsolutePath() ), searchword) );

                            for (int i = 0; i < directories.size(); i++) {
                                File file = (File) directories.get(i);
                                System.out.println(file.getAbsolutePath());
                            }
                        }
                    }
                }
            }
        }


        