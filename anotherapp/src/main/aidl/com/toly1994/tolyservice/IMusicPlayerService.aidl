// IMusicPlayerService.aidl
package com.toly1994.tolyservice;

// Declare any non-default types here with import statements

interface IMusicPlayerService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void stop();
    void pause();
    void start();
    void prev();
    void next();
    void release();
    boolean isPlaying();
    void seek(int pre_100);



    //加in
    void create(in List<String> filePaths);
}
