package org.apframework.jexl.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Shoukai Huang
 * @Date: 2018/11/6 15:06
 */
public class ComparatorSample {

    public static void main(String[] args) {

        List<Developer> result = new ArrayList<>();
        result.add(new Developer(1, 0,0,100L));
        result.add(new Developer(2, 0,1,101L));
        result.add(new Developer(3, 0,1,102L));
        result.add(new Developer(4, 1,0,100L));
        result.add(new Developer(5, 1,1,100L));
        System.out.println(result.stream().map(p->p.getId()).collect(Collectors.toList()));
        result.sort(
                (o1, o2) -> {
                    if (o2.getLevelFirst() != o1.getLevelFirst()) {
                        return o2.getLevelFirst() - o1.getLevelFirst();
                    }
                    if (o2.getLevelSecond() != o1.getLevelSecond()) {
                        return o2.getLevelSecond() - o1.getLevelSecond();
                    }
                    return (int) (o2.getTime() - o1.getTime());
                }
        );
        System.out.println(result.stream().map(p->p.getId()).collect(Collectors.toList()));
    }


    private static class Developer {

        public Developer(int id, int levelFirst, int levelSecond, long time) {
            this.id = id;
            this.levelFirst = levelFirst;
            this.levelSecond = levelSecond;
            this.time = time;
        }

        private int id;
        // IS SPECIAL
        private int levelFirst;
        // IS SPECIAL
        private int levelSecond;
        private long time;

        public int getLevelFirst() {
            return levelFirst;
        }

        public void setLevelFirst(int levelFirst) {
            this.levelFirst = levelFirst;
        }

        public int getLevelSecond() {
            return levelSecond;
        }

        public void setLevelSecond(int levelSecond) {
            this.levelSecond = levelSecond;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "Developer{" +
                    "levelFirst=" + levelFirst +
                    ", levelSecond=" + levelSecond +
                    ", time=" + time +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
