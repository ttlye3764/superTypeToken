package com.supertypetoken;

public class Generic {

    public static void main(String[] args) {


        // 1. 제네릭에 클래스를 넣어서 생성하는 것을 parameterized type 라고 부름
        // 2. runtime 시에는 type erasure 에 의해서 String 은 Object 타입으로 읽힘
        GenericClass<String> s = new GenericClass<>();

        // 2-1. 이떄 value 타입은 Object 타입
        // 그럼 어떻게 String 값을 바로 넣을 수가 있을까 -> 컴파일러가 미리 체크를 하고 코드안에 캐스팅 (String) 하는것을 묵시적으로 넣어줌
        // 그렇기 자동으로 컴파일러가 체크해서 캐스팅해주기때문에 명시적으로 (String) 하는것보다 type safe 하게 개발 가능하다.

        // 밑에 코드 두줄이 강제 캐스팅 코드인데, 컴파일 타임에서는 에러가 나지 않지만, 런타임에서는 에러가 발생하기 때문에 완전 type safe하지 않다.
//        Object o  = "String";
//        Integer i = (Integer) o;

        s.value = "String";
        s.set("String");

        System.out.println(s.getClass());

        GenericClass<Integer> i = new GenericClass<>();
        i.value = 10;
        i.set(10);
    }

    // T : 타입 파라미터 (type Parameter ) 혹은 타입 변수라고 함.
    static class GenericClass<T> {
        T value;
        void set(T t) {}
        T get() {
            return null;
        }
    }
}
