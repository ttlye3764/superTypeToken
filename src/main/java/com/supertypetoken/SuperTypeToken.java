package com.supertypetoken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuperTypeToken {

    static class Sup<T> {
        T value;
    }

    // Nested Static Class = Inner Class ? 같은 말일까? 확인해보자
    static class Sub<T> extends Sup<List<String>> {

    }


    public static void main(String[] args) throws NoSuchFieldException {


        Sup<String> s = new Sup<>();
        // -------
        // reflect 를 통한 조회
        s.getClass().getDeclaredField("value").getType(); // 이렇게 해도 Object 타입으로 조회됨.
                                                                 // 왜? String으로 안나오냐? java는 erasure 를 사용하기 때문에.
        // 하지만, Object가 아닌 String으로 조회할 수 있음.
        Sub b = new Sub();
        Type t = b.getClass().getGenericSuperclass();
        ParameterizedType ptype = (ParameterizedType) t;
        System.out.println(ptype.getActualTypeArguments()[0]);


        // 익명 클래스로 사용할 수도 있음
        Sup ss  = new Sup<List<Integer>>() {};
        Type tt = b.getClass().getGenericSuperclass();
        ParameterizedType pttype = (ParameterizedType) tt;
        System.out.println(pttype.getActualTypeArguments()[0]);

        // TypeToken의 한계를 넘은 SuerTypeToken
        TypeSafeMapClass m = new TypeSafeMapClass();
        m.put(new TypeReference<Integer>(){} ,1);
        m.put(new TypeReference<String>(){} ,"String");
        m.put(new TypeReference<List<String>>(){} ,Arrays.asList("a","b","c"));
        m.put(new TypeReference<List<Integer>>(){} ,Arrays.asList(1,2,3));

        // 익명클래스 인스턴스를 만들어서 익명클래스가 사용하고 있는 슈퍼클래스의 제네릭 타입 파라미터 정보를 전달하기 위한 용도 {}
        System.out.println(m.get(new TypeReference<Integer>(){}));
        System.out.println(m.get(new TypeReference<String>(){}));
        System.out.println(m.get(new TypeReference<List<String>>(){}));
        System.out.println(m.get(new TypeReference<List<Integer>>(){}));
     }


    static class TypeSafeMapClass {

        Map<Type, Object> map = new HashMap<>();

        // 와일드카드 문법
        Map<TypeReference<?>, Object> typeSafeMap = new HashMap<>();

        <T> void put(TypeReference<T> tr, T value) {
            map.put(tr.type, value);
        }

        <T> T get(TypeReference<T> tr) {

            if (tr.type instanceof Class<?>) {
                return ((Class<T>) tr.type).cast(map.get(tr.type));
            } else {
                return ((Class<T>) ((ParameterizedType) tr.type).getRawType()).cast(map.get(tr.type));
            }

        }
    }
}
