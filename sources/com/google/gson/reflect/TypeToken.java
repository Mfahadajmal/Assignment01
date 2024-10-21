package com.google.gson.reflect;

import com.google.gson.internal.C$Gson$Types;
import com.google.mlkit.common.internal.model.CustomRemoteModelManager;
import j$.util.Objects;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TypeToken {
    private final int hashCode;
    public final Class rawType;
    public final Type type;

    protected TypeToken() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            if (parameterizedType.getRawType() == TypeToken.class) {
                Type canonicalize = C$Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
                if (!Objects.equals(System.getProperty("gson.allowCapturingTypeVariables"), "true")) {
                    verifyNoTypeVariable(canonicalize);
                }
                this.type = canonicalize;
                this.rawType = C$Gson$Types.getRawType(canonicalize);
                this.hashCode = canonicalize.hashCode();
                return;
            }
        } else if (genericSuperclass == TypeToken.class) {
            throw new IllegalStateException("TypeToken must be created with a type argument: new TypeToken<...>() {}; When using code shrinkers (ProGuard, R8, ...) make sure that generic signatures are preserved.\nSee ".concat(String.valueOf(CustomRemoteModelManager.createUrl("type-token-raw"))));
        }
        throw new IllegalStateException("Must only create direct subclasses of TypeToken");
    }

    private static void verifyNoTypeVariable(Type type) {
        if (!(type instanceof TypeVariable)) {
            if (type instanceof GenericArrayType) {
                verifyNoTypeVariable(((GenericArrayType) type).getGenericComponentType());
                return;
            }
            int i = 0;
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type ownerType = parameterizedType.getOwnerType();
                if (ownerType != null) {
                    verifyNoTypeVariable(ownerType);
                }
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                int length = actualTypeArguments.length;
                while (i < length) {
                    verifyNoTypeVariable(actualTypeArguments[i]);
                    i++;
                }
                return;
            }
            if (type instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type;
                for (Type type2 : wildcardType.getLowerBounds()) {
                    verifyNoTypeVariable(type2);
                }
                Type[] upperBounds = wildcardType.getUpperBounds();
                int length2 = upperBounds.length;
                while (i < length2) {
                    verifyNoTypeVariable(upperBounds[i]);
                    i++;
                }
                return;
            }
            if (type != null) {
                return;
            } else {
                throw new IllegalArgumentException("TypeToken captured `null` as type argument; probably a compiler / runtime bug");
            }
        }
        TypeVariable typeVariable = (TypeVariable) type;
        throw new IllegalArgumentException("TypeToken type argument must not contain a type variable; captured type variable " + typeVariable.getName() + " declared by " + String.valueOf(typeVariable.getGenericDeclaration()) + "\nSee " + CustomRemoteModelManager.createUrl("typetoken-type-variable"));
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof TypeToken) && C$Gson$Types.equals(this.type, ((TypeToken) obj).type)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.hashCode;
    }

    public final String toString() {
        return C$Gson$Types.typeToString(this.type);
    }

    public TypeToken(Type type) {
        type.getClass();
        Type canonicalize = C$Gson$Types.canonicalize(type);
        this.type = canonicalize;
        this.rawType = C$Gson$Types.getRawType(canonicalize);
        this.hashCode = canonicalize.hashCode();
    }
}
