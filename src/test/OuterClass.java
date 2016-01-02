package test;

/**
 * Created by fan on 11/6/15.
 */
public class OuterClass {
    int a;
    InnerClass in;
    StaticClass sc;

    class InnerClass {
        int b;
    }

    static class StaticClass {
        protected int c;
    }

    interface Interf {
        public void hello();
    }

    OuterClass(int a) {
        this.a = a;
        this.in = new InnerClass();
        this.sc = new StaticClass();
    }

    void test() {
        Interf anonymousClass = new Interf() {
            @Override
            public void hello() {
                System.out.println("wfHello!");
            }
        };
    }
}
