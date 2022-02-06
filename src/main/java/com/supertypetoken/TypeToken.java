package com.supertypetoken;

public class TypeToken {

    static Object create () {
        return new Object();
    }

    static <T>T createT(Class<T> clazz) throws Exception {
        return clazz.getDeclaredConstructor(clazz).newInstance();
    }

    public static void main(String[] args) throws Exception {
//        Integer o = createT(Integer.class);             Integer타입은 이렇게 생성할 수 없는데 이유는,
//                                                        Integer 클래스는 new Integer()가 불가능한 default 생성자가 존재하지 않기 때문에

        String s = createT(String.class);
        System.out.println(s.getClass());
    }
}
