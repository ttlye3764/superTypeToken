package com.supertypetoken;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeSafeMap {

    static class TypeSafeMapClass {
        Map<Class<?>, Object> map = new HashMap<>();

        // 와일드카드 문법
        Map<Class<?>, Object> typeSafeMap = new HashMap<>();

        <T> void put(Class<T> clazz, T value) {
            map.put(clazz, value);
        }

        <T> T get(Class<T> clazz) {
            return clazz.cast(map.get(clazz));
        }

        // 강제 캐스팅하는 위험한 코드
//        void run () {
//            map.put("a", "a");
//            map.put("b", 1);
//            Integer i = (Integer) map.get("b");
//            String s = (String) map.get("a");
//        }
    }

    // type token
    // 말그대로 타입을 키값으로 값을 넣어서 type safe하게 구현
    // 그러나 한계가 존재, map은 같은 List 클래스 값을 여러개 넣어도 덮어씌워져 버리는 문제 발생.

    // 그래서 나온게 SuperTypeToken

    public static void main(String[] args) {

        // type safe 하게 put 할 수 있음.
        TypeSafeMapClass m = new TypeSafeMapClass();
        m.put(Integer.class, 1);
        m.put(String.class, "String");
        m.put(List.class, Arrays.asList(1,2,3)); // List<Integer>
        m.put(List.class, Arrays.asList("a", "b", "c")); // List<String>

        // 이렇게는 사용 불가하다.
        //  m.put(List<Integer>.class, Arrays.asList(1,2,3)); // List<Integer>
        //  m.put(List<String>.class, Arrays.asList("a", "b", "c")); // List<String>

        System.out.println(m.get(Integer.class));
        System.out.println(m.get(String.class));
        System.out.println(m.get(List.class));

    }
}
