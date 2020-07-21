package com.nash.strings;

import org.junit.Test;

public class ReflectionsTest {

    static class TestObject {
        private String f1;
        private TestObject2 f2;

        public String getF1() {
            return f1;
        }

        public void setF1(String f1) {
            this.f1 = f1;
        }

        public TestObject2 getF2() {
            return f2;
        }

        public void setF2(TestObject2 f2) {
            this.f2 = f2;
        }
    }

    static class TestObject2 {
        private int t;
        private String h;
        private TestObject3 testObject3;

        public int getT() {
            return t;
        }

        public void setT(int t) {
            this.t = t;
        }

        public String getH() {
            return h;
        }

        public void setH(String h) {
            this.h = h;
        }

        public TestObject3 getTestObject3() {
            return testObject3;
        }

        public void setTestObject3(TestObject3 testObject3) {
            this.testObject3 = testObject3;
        }
    }

    static class TestObject3 {
        private int r;

        public int getR() {
            return r;
        }

        public void setR(int r) {
            this.r = r;
        }
    }

    @Test
    public void testAsString() {

        TestObject3 testObject3 = new TestObject3();
        testObject3.setR(999);

        TestObject2 testObject2 = new TestObject2();
        testObject2.setT(27);
        testObject2.setH("hello world");
        testObject2.setTestObject3(null);

        TestObject testObject = new TestObject();
        testObject.setF1("hola amigo");
        testObject.setF2(testObject2);

        System.out.println(Reflections.asString(testObject));
    }

}
