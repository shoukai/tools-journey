package org.apframework.guava.limiter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

public class BloomFilterTest {
    private static int size = 1000000;

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size);

    public static void main(String[] args) {
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }

        List<Integer> missList = new ArrayList<Integer>(1000);
        for (int i = 0; i < size; i++) {
            if (!bloomFilter.mightContain(i)) {
                missList.add(i);
            }
        }
        System.out.println("有坏人逃脱的数量：" + missList.size());

        List<Integer> list = new ArrayList<Integer>(1000);
        for (int i = size + 10000; i < size + 20000; i++) {
            if (bloomFilter.mightContain(i)) {
                list.add(i);
            }
        }
        System.out.println("有误伤的数量：" + list.size());
    }
}
