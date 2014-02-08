package com.nic.mongodb;

import java.net.UnknownHostException;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoTest {

    public static void main(String[] args) throws UnknownHostException, MongoException {
        DB db = getDB();
        DBCollection persons = db.getCollection("persons");

        // equivalent to > db.persons.find({name: {$ne: 'jack'}}).limit(2);
        DBCursor find = persons.find(new BasicDBObject("name", new BasicDBObject("$ne", "jack"))).limit(2);
        printAll(find);
    }

    public static void printAll(DBCursor cursor) {
        while (cursor.hasNext()) {
            DBObject obj = cursor.next();
            Set<String> keySet = obj.keySet();
            for (String key : keySet) {
                System.out.print("    " + key + ":" + obj.get(key));
            }
            System.out.println();
        }
    }

    public static DB getDB() throws UnknownHostException, MongoException {
        Mongo mongo = new Mongo("192.168.119.130", 27017);
        DB db = mongo.getDB("blah");
        return db;
    }
}
