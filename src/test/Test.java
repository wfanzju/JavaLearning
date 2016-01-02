package test;

import helper.TreeNode;

import java.util.*;

import static test.OuterClass.*;

/**
 * Created by fan on 9/8/15.
 */
public class Test {
    // TODO: a^b mod c; Search Insert Pos; populating next right pointers
    // TODO: gas station
    // TODO: Uber: design messager, anagram, suduku
    public static void main(String[] args) {

        System.out.println(new Integer(2).compareTo(new Integer(3)));
        System.out.println("hello".substring(5).equals(""));
        String str = "hello";
        System.out.println(str.replaceFirst("e", "a"));

        System.out.println(str.replaceFirst("e", "b"));

        System.out.println(new ArrayList<>().iterator().hasNext());

        List<String> l1 = Arrays.asList("1", "2");
        List<String> l2 = Arrays.asList("2", "1");
        List<String> l3 = new ArrayList<>();
        l3.add("1");
        l3.add("2");

        System.out.println(l1.equals(l2));

        System.out.println(l1.hashCode());
        System.out.println(l2.hashCode());
        System.out.println(l3.hashCode());
        System.out.println(l1.equals(l3));

        Queue<Integer> heap =
                new PriorityQueue<>(1, (a, b) -> -a.compareTo(b));
        heap.add(1);
        heap.add(2);
        System.out.println(heap.peek());

        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(1);
        System.out.println(n1.equals(n2));

        System.out.println(Integer.bitCount(-1));

        OuterClass oc = new OuterClass(3);
        OuterClass.InnerClass in = oc.new InnerClass();
        System.out.println(in.b);
        OuterClass.StaticClass sc = new OuterClass.StaticClass();
        System.out.println(sc.c);

        Set<List<Integer>> set = new HashSet<>();
        set.add(Arrays.asList(1, 2));
        set.add(Arrays.asList(1, 2));
        System.out.println(set);

        System.out.println(Integer.MAX_VALUE);
        System.out.println(1 << 31);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.remove(1);
        System.out.println(list);

        Person p1 = new Person("a", 1);
        Person p2 = new Person("a", 2);
        Set<Person> pSet = new HashSet<>();
        pSet.add(p1);
        pSet.add(p2);
        System.out.println(pSet.contains(p1));
        System.out.println(pSet.contains(p2));
        System.out.println(pSet.size());

        Person p = new Person("haha", 1);
        System.out.println(p.name);

        System.out.println(new Random().nextInt(0));
    }

    private static class Person {
        static String name = "hehe";
        int age;

        static {
            name = "xixi";
        }

        public Person(String name, int age) {
            //this.name = name;
            this.age = age;
        }

        @Override
        public int hashCode(){
            return name.hashCode();
        }

        @Override
        public boolean equals(Object person) {
            return this.name.equals(((Person)person).name);
        }
        @Override
        public String toString() {
            return name + age;
        }
    }
}
