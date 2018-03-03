package org.apframework.mapdb;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.util.concurrent.ConcurrentMap;

public class HelloMapFileDB {
    public static void main(String[] args) {
        DB db = DBMaker
                .fileDB("file.db.tmpfile")
                .fileMmapEnable()
                .make();
        ConcurrentMap<String,Long> map = db
                .hashMap("map", Serializer.STRING, Serializer.LONG)
                .createOrOpen();
        map.put("something", 111L);

        System.out.println(map.get("something"));

        db.close();
    }
}
