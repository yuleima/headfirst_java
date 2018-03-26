package me.azno.study.java8.stream;

import java.util.*;

import static java.util.Comparator.comparing;

/**
 * https://www.ibm.com/developerworks/cn/java/j-java-streams-1-brian-goetz/index.html?ca=drs-
 * 流使用。
 *
 */
public class Stream2 {
    public static void main(String[] args) {
        // 交易
        List<Txn> txns = Arrays.asList();
        // 输出与年龄超过65岁的买家进行交易的卖家姓名，并按姓名排序。
        // 1 传统方式
        Set<Seller> sellers = new HashSet<>();
        // 1.1 遍历交易列表
        for (Txn t : txns) {
            // 1.1.1 筛选卖家年纪65岁以上的
            if (t.getBuyer().getAge() >= 65)
                // 1.1.2 卖家保存到临时变量
                sellers.add(t.getSeller());
        }
        // 1.2 排序
        // 1.2.1 排序的临时列表
        List<Seller> sorted = new ArrayList<>(sellers);
        // 1.2.2 按姓名排序
        Collections.sort(sorted, new Comparator<Seller>() {
            public int compare(Seller a, Seller b) {
                return a.getName().compareTo(b.getName());
            }
        });
        // 1.3 输出
        for (Seller s : sorted)
            System.out.println(s.getName());

        // 2 流方式
        txns.stream()
                // 2.1 过滤交易中买家年纪大于65
                .filter(t -> t.getBuyer().getAge() >= 65)
                // 2.2 映射，从交易变为卖家，获得卖家
                .map(Txn::getSeller)
                // 2.3 distinct 去重
                .distinct()
                // 2.4 排序，有直接的方法
                .sorted(comparing(Seller::getName))
                // 2.5 映射，从卖家变为名字，获得卖家的名字
                .map(Seller::getName)
                // 结束，输出
                .forEach(System.out::println);
    }

    private class Buyer {
        private String name;
        private short age;

        public String getName() {
            return name;
        }

        public short getAge() {
            return age;
        }
    }
    private class Seller {
        private String name;
        private short age;

        public String getName() {
            return name;
        }

        public short getAge() {
            return age;
        }
    }

    private class Txn {
        private Buyer buyer;
        private Seller seller;

        public Buyer getBuyer() {
            return buyer;
        }

        public Seller getSeller() {
            return seller;
        }
    }
}
