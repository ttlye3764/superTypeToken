package com.supertypetoken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

public class TypeReference<T, R> {
    Type type;
    T val1; // 타입변수(type variable) 이라고 부름
    R val2;

    public TypeReference() {
        Type stype = getClass().getGenericSuperclass();
        if (stype instanceof ParameterizedType) {
            this.type = ((ParameterizedType) stype).getActualTypeArguments()[0];
        } else throw new RuntimeException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass().getSuperclass() != o.getClass().getSuperclass()) return false;
        TypeReference<?, ?> that = (TypeReference<?, ?>) o;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
