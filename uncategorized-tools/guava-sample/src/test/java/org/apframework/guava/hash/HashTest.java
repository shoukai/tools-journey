package org.apframework.guava.hash;

import com.google.common.base.Charsets;
import com.google.common.hash.*;


/**
 * FROM https://www.jianshu.com/p/f3f24efd3272
 *
 * e4d7f1b4ed2e42d15898f4b27b019da4
 * 09ca7e4eaa6e8ae9c7d261167129184883644d07dfba7cbfbc4c8a2e08360d5b
 * 8710339dcb6814d0d9d2290ef422285c9322b7163951f9a0ca8f883d3305286f44139aa374848e4174f5aada663027e4548637b6d19894aec4fb6c46a139fbf9
 * 3a72abff
 * d2d2d8f839e862ea41823e2541fd21ed
 * --
 * c3c3a6aa2f7ecf63dccb216a3877194d
 */
public class HashTest {

    public static void normal() {
        // 各种算法对应的hash code
        String input = "hello, world";
        // MD5
        System.out.println(Hashing.md5().hashBytes(input.getBytes()).toString());
        // sha256
        System.out.println(Hashing.sha256().hashBytes(input.getBytes()).toString());
        // sha512
        System.out.println(Hashing.sha512().hashBytes(input.getBytes()).toString());
        // crc32
        System.out.println(Hashing.crc32().hashBytes(input.getBytes()).toString());
        // MD5
        System.out.println(Hashing.md5().hashUnencodedChars(input).toString());
    }

    public static void clazz() {
        // 需要hash的对象
        Person person = new Person(27, "wu", "xing", 1990);

        Funnel<Person> personFunnel = (Funnel<Person>) (person1, into) -> into
                .putInt(person1.id)
                .putString(person1.firstName, Charsets.UTF_8)
                .putString(person1.lastName, Charsets.UTF_8)
                .putInt(person1.birthYear);

        // md5算法
        HashFunction hf = Hashing.md5();
        HashCode hc = hf.newHasher()
                .putString("abc", Charsets.UTF_8)
                .putObject(person, personFunnel)
                .hash();
        System.out.println(hc.toString());
    }

    public static void main(String[] args) {
        normal();
        System.out.println("--");
        clazz();
    }
}

class Person {
    int id;
    String firstName;
    String lastName;
    int birthYear;

    public Person(int id, String firstName, String lastName, int birthYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }
}