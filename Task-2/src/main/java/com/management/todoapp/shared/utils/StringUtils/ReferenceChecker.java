package com.management.todoapp.shared.utils.StringUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ReferenceChecker {
    private static final Set<Class<?>> SIMPLE_TYPES = new HashSet<>() {{
        add(Boolean.class);
        add(Byte.class);
        add(Character.class);
        add(Double.class);
        add(Float.class);
        add(Integer.class);
        add(Long.class);
        add(Short.class);
        add(String.class);
        add(LocalDateTime.class);
    }};

    public static boolean isReferenceObject(Class<?> clazz) {
        if (clazz.isPrimitive() || SIMPLE_TYPES.contains(clazz)) {
            return false;
        }
        return true;
    }
}
