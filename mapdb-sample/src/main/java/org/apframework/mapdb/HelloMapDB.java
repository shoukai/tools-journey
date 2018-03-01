package org.apframework.mapdb;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.util.concurrent.ConcurrentMap;

public class HelloMapDB {

    public static void main(String[] args) {
        DB db = DBMaker.memoryDB().make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        map.put("something", "here");
        System.out.println(map.get("something"));
    }
}
