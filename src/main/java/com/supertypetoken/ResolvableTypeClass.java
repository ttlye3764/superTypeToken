package com.supertypetoken;

import org.springframework.core.ResolvableType;

import java.util.List;

public class ResolvableTypeClass {
    public static void main(String[] args) {

         ResolvableType rt = ResolvableType.forClass(TypeReference.class);

        ResolvableType rti = ResolvableType.forInstance(new TypeReference<List<String>, String>(){}); // inner class가 상속을 해서 instance를 만듬
        System.out.println(rti.getSuperType().getGenerics().length);
        System.out.println(rti.getSuperType().getGeneric(0).getType());
        System.out.println(rti.getSuperType().getGeneric(0).getNested(2).getType());
    }
}
