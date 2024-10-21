package com.google.gson.internal;

import _COROUTINE._BOUNDARY;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonIOException;
import com.google.gson.internal.reflect.ReflectionHelper;
import com.google.gson.reflect.TypeToken;
import com.google.mlkit.common.internal.model.CustomRemoteModelManager;
import j$.util.concurrent.ConcurrentHashMap;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConstructorConstructor {
    private final Map instanceCreators;
    private final List reflectionFilters;

    public ConstructorConstructor(Map map, List list) {
        this.instanceCreators = map;
        this.reflectionFilters = list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String checkInstantiable(Class cls) {
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            return "Interfaces can't be instantiated! Register an InstanceCreator or a TypeAdapter for this type. Interface name: ".concat(String.valueOf(cls.getName()));
        }
        if (Modifier.isAbstract(modifiers)) {
            return "Abstract classes can't be instantiated! Adjust the R8 configuration or register an InstanceCreator or a TypeAdapter for this type. Class name: " + cls.getName() + "\nSee " + CustomRemoteModelManager.createUrl("r8-abstract-class");
        }
        return null;
    }

    public final ObjectConstructor get(TypeToken typeToken) {
        ObjectConstructor objectConstructor;
        ObjectConstructor objectConstructor2;
        final String str;
        ObjectConstructor objectConstructor3;
        Map map = this.instanceCreators;
        final Type type = typeToken.type;
        final InstanceCreator instanceCreator = (InstanceCreator) map.get(type);
        final int i = 1;
        if (instanceCreator != null) {
            return new ObjectConstructor() { // from class: com.google.gson.internal.ConstructorConstructor.2
                @Override // com.google.gson.internal.ObjectConstructor
                public final Object construct() {
                    if (i != 0) {
                        return instanceCreator.createInstance$ar$ds();
                    }
                    return instanceCreator.createInstance$ar$ds();
                }
            };
        }
        final Class cls = typeToken.rawType;
        final InstanceCreator instanceCreator2 = (InstanceCreator) this.instanceCreators.get(cls);
        final int i2 = 0;
        if (instanceCreator2 == null) {
            final int i3 = 3;
            final int i4 = 4;
            if (EnumSet.class.isAssignableFrom(cls)) {
                objectConstructor = new ObjectConstructor() { // from class: com.google.gson.internal.ConstructorConstructor.3
                    @Override // com.google.gson.internal.ObjectConstructor
                    public final Object construct() {
                        switch (i3) {
                            case 0:
                                throw new JsonIOException((String) type);
                            case 1:
                                try {
                                    return UnsafeAllocator.INSTANCE.newInstance((Class) type);
                                } catch (Exception e) {
                                    throw new RuntimeException("Unable to create instance of " + String.valueOf(type) + ". Registering an InstanceCreator or a TypeAdapter for this type, or adding a no-args constructor may fix this problem.", e);
                                }
                            case 2:
                                throw new JsonIOException((String) type);
                            case 3:
                                Object obj = type;
                                if (obj instanceof ParameterizedType) {
                                    Type type2 = ((ParameterizedType) obj).getActualTypeArguments()[0];
                                    if (type2 instanceof Class) {
                                        return EnumSet.noneOf((Class) type2);
                                    }
                                    throw new JsonIOException("Invalid EnumSet type: ".concat(String.valueOf(type.toString())));
                                }
                                throw new JsonIOException("Invalid EnumSet type: ".concat(String.valueOf(obj.toString())));
                            case 4:
                                Object obj2 = type;
                                if (obj2 instanceof ParameterizedType) {
                                    Type type3 = ((ParameterizedType) obj2).getActualTypeArguments()[0];
                                    if (type3 instanceof Class) {
                                        return new EnumMap((Class) type3);
                                    }
                                    throw new JsonIOException("Invalid EnumMap type: ".concat(String.valueOf(type.toString())));
                                }
                                throw new JsonIOException("Invalid EnumMap type: ".concat(String.valueOf(obj2.toString())));
                            case 5:
                                throw new JsonIOException((String) type);
                            case 6:
                                throw new JsonIOException((String) type);
                            default:
                                try {
                                    return ((Constructor) type).newInstance(null);
                                } catch (IllegalAccessException e2) {
                                    throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e2);
                                } catch (InstantiationException e3) {
                                    throw new RuntimeException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(ReflectionHelper.constructorToString((Constructor) type), "Failed to invoke constructor '", "' with no args"), e3);
                                } catch (InvocationTargetException e4) {
                                    throw new RuntimeException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(ReflectionHelper.constructorToString((Constructor) type), "Failed to invoke constructor '", "' with no args"), e4.getCause());
                                }
                        }
                    }
                };
            } else if (cls == EnumMap.class) {
                objectConstructor = new ObjectConstructor() { // from class: com.google.gson.internal.ConstructorConstructor.3
                    @Override // com.google.gson.internal.ObjectConstructor
                    public final Object construct() {
                        switch (i4) {
                            case 0:
                                throw new JsonIOException((String) type);
                            case 1:
                                try {
                                    return UnsafeAllocator.INSTANCE.newInstance((Class) type);
                                } catch (Exception e) {
                                    throw new RuntimeException("Unable to create instance of " + String.valueOf(type) + ". Registering an InstanceCreator or a TypeAdapter for this type, or adding a no-args constructor may fix this problem.", e);
                                }
                            case 2:
                                throw new JsonIOException((String) type);
                            case 3:
                                Object obj = type;
                                if (obj instanceof ParameterizedType) {
                                    Type type2 = ((ParameterizedType) obj).getActualTypeArguments()[0];
                                    if (type2 instanceof Class) {
                                        return EnumSet.noneOf((Class) type2);
                                    }
                                    throw new JsonIOException("Invalid EnumSet type: ".concat(String.valueOf(type.toString())));
                                }
                                throw new JsonIOException("Invalid EnumSet type: ".concat(String.valueOf(obj.toString())));
                            case 4:
                                Object obj2 = type;
                                if (obj2 instanceof ParameterizedType) {
                                    Type type3 = ((ParameterizedType) obj2).getActualTypeArguments()[0];
                                    if (type3 instanceof Class) {
                                        return new EnumMap((Class) type3);
                                    }
                                    throw new JsonIOException("Invalid EnumMap type: ".concat(String.valueOf(type.toString())));
                                }
                                throw new JsonIOException("Invalid EnumMap type: ".concat(String.valueOf(obj2.toString())));
                            case 5:
                                throw new JsonIOException((String) type);
                            case 6:
                                throw new JsonIOException((String) type);
                            default:
                                try {
                                    return ((Constructor) type).newInstance(null);
                                } catch (IllegalAccessException e2) {
                                    throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e2);
                                } catch (InstantiationException e3) {
                                    throw new RuntimeException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(ReflectionHelper.constructorToString((Constructor) type), "Failed to invoke constructor '", "' with no args"), e3);
                                } catch (InvocationTargetException e4) {
                                    throw new RuntimeException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(ReflectionHelper.constructorToString((Constructor) type), "Failed to invoke constructor '", "' with no args"), e4.getCause());
                                }
                        }
                    }
                };
            } else {
                objectConstructor = null;
            }
            if (objectConstructor != null) {
                return objectConstructor;
            }
            int filterResult$ar$edu = CustomRemoteModelManager.getFilterResult$ar$edu(this.reflectionFilters, cls);
            final int i5 = 6;
            final int i6 = 5;
            final int i7 = 7;
            if (Modifier.isAbstract(cls.getModifiers())) {
                objectConstructor2 = null;
            } else {
                try {
                    final Constructor declaredConstructor = cls.getDeclaredConstructor(null);
                    if (filterResult$ar$edu != 1 && (!CustomRemoteModelManager.canAccess(declaredConstructor, null) || (filterResult$ar$edu == 4 && !Modifier.isPublic(declaredConstructor.getModifiers())))) {
                        final String _BOUNDARY$ar$MethodOutlining$dc56d17a_11 = _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_11(cls, "Unable to invoke no-args constructor of ", "; constructor is not accessible and ReflectionAccessFilter does not permit making it accessible. Register an InstanceCreator or a TypeAdapter for this type, change the visibility of the constructor or adjust the access filter.");
                        objectConstructor2 = new ObjectConstructor() { // from class: com.google.gson.internal.ConstructorConstructor.3
                            @Override // com.google.gson.internal.ObjectConstructor
                            public final Object construct() {
                                switch (i6) {
                                    case 0:
                                        throw new JsonIOException((String) _BOUNDARY$ar$MethodOutlining$dc56d17a_11);
                                    case 1:
                                        try {
                                            return UnsafeAllocator.INSTANCE.newInstance((Class) _BOUNDARY$ar$MethodOutlining$dc56d17a_11);
                                        } catch (Exception e) {
                                            throw new RuntimeException("Unable to create instance of " + String.valueOf(_BOUNDARY$ar$MethodOutlining$dc56d17a_11) + ". Registering an InstanceCreator or a TypeAdapter for this type, or adding a no-args constructor may fix this problem.", e);
                                        }
                                    case 2:
                                        throw new JsonIOException((String) _BOUNDARY$ar$MethodOutlining$dc56d17a_11);
                                    case 3:
                                        Object obj = _BOUNDARY$ar$MethodOutlining$dc56d17a_11;
                                        if (obj instanceof ParameterizedType) {
                                            Type type2 = ((ParameterizedType) obj).getActualTypeArguments()[0];
                                            if (type2 instanceof Class) {
                                                return EnumSet.noneOf((Class) type2);
                                            }
                                            throw new JsonIOException("Invalid EnumSet type: ".concat(String.valueOf(_BOUNDARY$ar$MethodOutlining$dc56d17a_11.toString())));
                                        }
                                        throw new JsonIOException("Invalid EnumSet type: ".concat(String.valueOf(obj.toString())));
                                    case 4:
                                        Object obj2 = _BOUNDARY$ar$MethodOutlining$dc56d17a_11;
                                        if (obj2 instanceof ParameterizedType) {
                                            Type type3 = ((ParameterizedType) obj2).getActualTypeArguments()[0];
                                            if (type3 instanceof Class) {
                                                return new EnumMap((Class) type3);
                                            }
                                            throw new JsonIOException("Invalid EnumMap type: ".concat(String.valueOf(_BOUNDARY$ar$MethodOutlining$dc56d17a_11.toString())));
                                        }
                                        throw new JsonIOException("Invalid EnumMap type: ".concat(String.valueOf(obj2.toString())));
                                    case 5:
                                        throw new JsonIOException((String) _BOUNDARY$ar$MethodOutlining$dc56d17a_11);
                                    case 6:
                                        throw new JsonIOException((String) _BOUNDARY$ar$MethodOutlining$dc56d17a_11);
                                    default:
                                        try {
                                            return ((Constructor) _BOUNDARY$ar$MethodOutlining$dc56d17a_11).newInstance(null);
                                        } catch (IllegalAccessException e2) {
                                            throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e2);
                                        } catch (InstantiationException e3) {
                                            throw new RuntimeException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(ReflectionHelper.constructorToString((Constructor) _BOUNDARY$ar$MethodOutlining$dc56d17a_11), "Failed to invoke constructor '", "' with no args"), e3);
                                        } catch (InvocationTargetException e4) {
                                            throw new RuntimeException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(ReflectionHelper.constructorToString((Constructor) _BOUNDARY$ar$MethodOutlining$dc56d17a_11), "Failed to invoke constructor '", "' with no args"), e4.getCause());
                                        }
                                }
                            }
                        };
                    } else {
                        if (filterResult$ar$edu == 1) {
                            ReflectionHelper.RecordHelper recordHelper = ReflectionHelper.RECORD_HELPER;
                            try {
                                declaredConstructor.setAccessible(true);
                                str = null;
                            } catch (Exception e) {
                                str = "Failed making constructor '" + ReflectionHelper.constructorToString(declaredConstructor) + "' accessible; either increase its visibility or write a custom InstanceCreator or TypeAdapter for its declaring type: " + e.getMessage() + ReflectionHelper.getInaccessibleTroubleshootingSuffix(e);
                            }
                            if (str != null) {
                                objectConstructor2 = new ObjectConstructor() { // from class: com.google.gson.internal.ConstructorConstructor.3
                                    @Override // com.google.gson.internal.ObjectConstructor
                                    public final Object construct() {
                                        switch (i5) {
                                            case 0:
                                                throw new JsonIOException((String) str);
                                            case 1:
                                                try {
                                                    return UnsafeAllocator.INSTANCE.newInstance((Class) str);
                                                } catch (Exception e2) {
                                                    throw new RuntimeException("Unable to create instance of " + String.valueOf(str) + ". Registering an InstanceCreator or a TypeAdapter for this type, or adding a no-args constructor may fix this problem.", e2);
                                                }
                                            case 2:
                                                throw new JsonIOException((String) str);
                                            case 3:
                                                Object obj = str;
                                                if (obj instanceof ParameterizedType) {
                                                    Type type2 = ((ParameterizedType) obj).getActualTypeArguments()[0];
                                                    if (type2 instanceof Class) {
                                                        return EnumSet.noneOf((Class) type2);
                                                    }
                                                    throw new JsonIOException("Invalid EnumSet type: ".concat(String.valueOf(str.toString())));
                                                }
                                                throw new JsonIOException("Invalid EnumSet type: ".concat(String.valueOf(obj.toString())));
                                            case 4:
                                                Object obj2 = str;
                                                if (obj2 instanceof ParameterizedType) {
                                                    Type type3 = ((ParameterizedType) obj2).getActualTypeArguments()[0];
                                                    if (type3 instanceof Class) {
                                                        return new EnumMap((Class) type3);
                                                    }
                                                    throw new JsonIOException("Invalid EnumMap type: ".concat(String.valueOf(str.toString())));
                                                }
                                                throw new JsonIOException("Invalid EnumMap type: ".concat(String.valueOf(obj2.toString())));
                                            case 5:
                                                throw new JsonIOException((String) str);
                                            case 6:
                                                throw new JsonIOException((String) str);
                                            default:
                                                try {
                                                    return ((Constructor) str).newInstance(null);
                                                } catch (IllegalAccessException e22) {
                                                    throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e22);
                                                } catch (InstantiationException e3) {
                                                    throw new RuntimeException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(ReflectionHelper.constructorToString((Constructor) str), "Failed to invoke constructor '", "' with no args"), e3);
                                                } catch (InvocationTargetException e4) {
                                                    throw new RuntimeException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(ReflectionHelper.constructorToString((Constructor) str), "Failed to invoke constructor '", "' with no args"), e4.getCause());
                                                }
                                        }
                                    }
                                };
                            }
                        }
                        objectConstructor2 = new ObjectConstructor() { // from class: com.google.gson.internal.ConstructorConstructor.3
                            @Override // com.google.gson.internal.ObjectConstructor
                            public final Object construct() {
                                switch (i7) {
                                    case 0:
                                        throw new JsonIOException((String) declaredConstructor);
                                    case 1:
                                        try {
                                            return UnsafeAllocator.INSTANCE.newInstance((Class) declaredConstructor);
                                        } catch (Exception e2) {
                                            throw new RuntimeException("Unable to create instance of " + String.valueOf(declaredConstructor) + ". Registering an InstanceCreator or a TypeAdapter for this type, or adding a no-args constructor may fix this problem.", e2);
                                        }
                                    case 2:
                                        throw new JsonIOException((String) declaredConstructor);
                                    case 3:
                                        Object obj = declaredConstructor;
                                        if (obj instanceof ParameterizedType) {
                                            Type type2 = ((ParameterizedType) obj).getActualTypeArguments()[0];
                                            if (type2 instanceof Class) {
                                                return EnumSet.noneOf((Class) type2);
                                            }
                                            throw new JsonIOException("Invalid EnumSet type: ".concat(String.valueOf(declaredConstructor.toString())));
                                        }
                                        throw new JsonIOException("Invalid EnumSet type: ".concat(String.valueOf(obj.toString())));
                                    case 4:
                                        Object obj2 = declaredConstructor;
                                        if (obj2 instanceof ParameterizedType) {
                                            Type type3 = ((ParameterizedType) obj2).getActualTypeArguments()[0];
                                            if (type3 instanceof Class) {
                                                return new EnumMap((Class) type3);
                                            }
                                            throw new JsonIOException("Invalid EnumMap type: ".concat(String.valueOf(declaredConstructor.toString())));
                                        }
                                        throw new JsonIOException("Invalid EnumMap type: ".concat(String.valueOf(obj2.toString())));
                                    case 5:
                                        throw new JsonIOException((String) declaredConstructor);
                                    case 6:
                                        throw new JsonIOException((String) declaredConstructor);
                                    default:
                                        try {
                                            return ((Constructor) declaredConstructor).newInstance(null);
                                        } catch (IllegalAccessException e22) {
                                            throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e22);
                                        } catch (InstantiationException e3) {
                                            throw new RuntimeException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(ReflectionHelper.constructorToString((Constructor) declaredConstructor), "Failed to invoke constructor '", "' with no args"), e3);
                                        } catch (InvocationTargetException e4) {
                                            throw new RuntimeException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(ReflectionHelper.constructorToString((Constructor) declaredConstructor), "Failed to invoke constructor '", "' with no args"), e4.getCause());
                                        }
                                }
                            }
                        };
                    }
                } catch (NoSuchMethodException unused) {
                    objectConstructor2 = null;
                }
            }
            if (objectConstructor2 == null) {
                final int i8 = 2;
                if (Collection.class.isAssignableFrom(cls)) {
                    if (SortedSet.class.isAssignableFrom(cls)) {
                        objectConstructor3 = new ObjectConstructor() { // from class: com.google.gson.internal.ConstructorConstructor.11
                            @Override // com.google.gson.internal.ObjectConstructor
                            public final Object construct() {
                                switch (i) {
                                    case 0:
                                        return new LinkedHashSet();
                                    case 1:
                                        return new TreeSet();
                                    case 2:
                                        return new ArrayDeque();
                                    case 3:
                                        return new ArrayList();
                                    case 4:
                                        return new ConcurrentSkipListMap();
                                    case 5:
                                        return new ConcurrentHashMap();
                                    case 6:
                                        return new TreeMap();
                                    case 7:
                                        return new LinkedHashMap();
                                    default:
                                        return new LinkedTreeMap(LinkedTreeMap.NATURAL_ORDER, true);
                                }
                            }
                        };
                    } else if (Set.class.isAssignableFrom(cls)) {
                        objectConstructor3 = new ObjectConstructor() { // from class: com.google.gson.internal.ConstructorConstructor.11
                            @Override // com.google.gson.internal.ObjectConstructor
                            public final Object construct() {
                                switch (i2) {
                                    case 0:
                                        return new LinkedHashSet();
                                    case 1:
                                        return new TreeSet();
                                    case 2:
                                        return new ArrayDeque();
                                    case 3:
                                        return new ArrayList();
                                    case 4:
                                        return new ConcurrentSkipListMap();
                                    case 5:
                                        return new ConcurrentHashMap();
                                    case 6:
                                        return new TreeMap();
                                    case 7:
                                        return new LinkedHashMap();
                                    default:
                                        return new LinkedTreeMap(LinkedTreeMap.NATURAL_ORDER, true);
                                }
                            }
                        };
                    } else if (Queue.class.isAssignableFrom(cls)) {
                        objectConstructor3 = new ObjectConstructor() { // from class: com.google.gson.internal.ConstructorConstructor.11
                            @Override // com.google.gson.internal.ObjectConstructor
                            public final Object construct() {
                                switch (i8) {
                                    case 0:
                                        return new LinkedHashSet();
                                    case 1:
                                        return new TreeSet();
                                    case 2:
                                        return new ArrayDeque();
                                    case 3:
                                        return new ArrayList();
                                    case 4:
                                        return new ConcurrentSkipListMap();
                                    case 5:
                                        return new ConcurrentHashMap();
                                    case 6:
                                        return new TreeMap();
                                    case 7:
                                        return new LinkedHashMap();
                                    default:
                                        return new LinkedTreeMap(LinkedTreeMap.NATURAL_ORDER, true);
                                }
                            }
                        };
                    } else {
                        objectConstructor3 = new ObjectConstructor() { // from class: com.google.gson.internal.ConstructorConstructor.11
                            @Override // com.google.gson.internal.ObjectConstructor
                            public final Object construct() {
                                switch (i3) {
                                    case 0:
                                        return new LinkedHashSet();
                                    case 1:
                                        return new TreeSet();
                                    case 2:
                                        return new ArrayDeque();
                                    case 3:
                                        return new ArrayList();
                                    case 4:
                                        return new ConcurrentSkipListMap();
                                    case 5:
                                        return new ConcurrentHashMap();
                                    case 6:
                                        return new TreeMap();
                                    case 7:
                                        return new LinkedHashMap();
                                    default:
                                        return new LinkedTreeMap(LinkedTreeMap.NATURAL_ORDER, true);
                                }
                            }
                        };
                    }
                } else if (Map.class.isAssignableFrom(cls)) {
                    if (ConcurrentNavigableMap.class.isAssignableFrom(cls)) {
                        objectConstructor3 = new ObjectConstructor() { // from class: com.google.gson.internal.ConstructorConstructor.11
                            @Override // com.google.gson.internal.ObjectConstructor
                            public final Object construct() {
                                switch (i4) {
                                    case 0:
                                        return new LinkedHashSet();
                                    case 1:
                                        return new TreeSet();
                                    case 2:
                                        return new ArrayDeque();
                                    case 3:
                                        return new ArrayList();
                                    case 4:
                                        return new ConcurrentSkipListMap();
                                    case 5:
                                        return new ConcurrentHashMap();
                                    case 6:
                                        return new TreeMap();
                                    case 7:
                                        return new LinkedHashMap();
                                    default:
                                        return new LinkedTreeMap(LinkedTreeMap.NATURAL_ORDER, true);
                                }
                            }
                        };
                    } else if (ConcurrentMap.class.isAssignableFrom(cls)) {
                        final int i9 = 5;
                        objectConstructor3 = new ObjectConstructor() { // from class: com.google.gson.internal.ConstructorConstructor.11
                            @Override // com.google.gson.internal.ObjectConstructor
                            public final Object construct() {
                                switch (i9) {
                                    case 0:
                                        return new LinkedHashSet();
                                    case 1:
                                        return new TreeSet();
                                    case 2:
                                        return new ArrayDeque();
                                    case 3:
                                        return new ArrayList();
                                    case 4:
                                        return new ConcurrentSkipListMap();
                                    case 5:
                                        return new ConcurrentHashMap();
                                    case 6:
                                        return new TreeMap();
                                    case 7:
                                        return new LinkedHashMap();
                                    default:
                                        return new LinkedTreeMap(LinkedTreeMap.NATURAL_ORDER, true);
                                }
                            }
                        };
                    } else if (SortedMap.class.isAssignableFrom(cls)) {
                        objectConstructor3 = new ObjectConstructor() { // from class: com.google.gson.internal.ConstructorConstructor.11
                            @Override // com.google.gson.internal.ObjectConstructor
                            public final Object construct() {
                                switch (i5) {
                                    case 0:
                                        return new LinkedHashSet();
                                    case 1:
                                        return new TreeSet();
                                    case 2:
                                        return new ArrayDeque();
                                    case 3:
                                        return new ArrayList();
                                    case 4:
                                        return new ConcurrentSkipListMap();
                                    case 5:
                                        return new ConcurrentHashMap();
                                    case 6:
                                        return new TreeMap();
                                    case 7:
                                        return new LinkedHashMap();
                                    default:
                                        return new LinkedTreeMap(LinkedTreeMap.NATURAL_ORDER, true);
                                }
                            }
                        };
                    } else if ((type instanceof ParameterizedType) && !String.class.isAssignableFrom(new TypeToken(((ParameterizedType) type).getActualTypeArguments()[0]).rawType)) {
                        objectConstructor3 = new ObjectConstructor() { // from class: com.google.gson.internal.ConstructorConstructor.11
                            @Override // com.google.gson.internal.ObjectConstructor
                            public final Object construct() {
                                switch (i7) {
                                    case 0:
                                        return new LinkedHashSet();
                                    case 1:
                                        return new TreeSet();
                                    case 2:
                                        return new ArrayDeque();
                                    case 3:
                                        return new ArrayList();
                                    case 4:
                                        return new ConcurrentSkipListMap();
                                    case 5:
                                        return new ConcurrentHashMap();
                                    case 6:
                                        return new TreeMap();
                                    case 7:
                                        return new LinkedHashMap();
                                    default:
                                        return new LinkedTreeMap(LinkedTreeMap.NATURAL_ORDER, true);
                                }
                            }
                        };
                    } else {
                        final int i10 = 8;
                        objectConstructor3 = new ObjectConstructor() { // from class: com.google.gson.internal.ConstructorConstructor.11
                            @Override // com.google.gson.internal.ObjectConstructor
                            public final Object construct() {
                                switch (i10) {
                                    case 0:
                                        return new LinkedHashSet();
                                    case 1:
                                        return new TreeSet();
                                    case 2:
                                        return new ArrayDeque();
                                    case 3:
                                        return new ArrayList();
                                    case 4:
                                        return new ConcurrentSkipListMap();
                                    case 5:
                                        return new ConcurrentHashMap();
                                    case 6:
                                        return new TreeMap();
                                    case 7:
                                        return new LinkedHashMap();
                                    default:
                                        return new LinkedTreeMap(LinkedTreeMap.NATURAL_ORDER, true);
                                }
                            }
                        };
                    }
                } else {
                    objectConstructor3 = null;
                }
                if (objectConstructor3 != null) {
                    return objectConstructor3;
                }
                final String checkInstantiable = checkInstantiable(cls);
                if (checkInstantiable != null) {
                    return new ObjectConstructor() { // from class: com.google.gson.internal.ConstructorConstructor.3
                        @Override // com.google.gson.internal.ObjectConstructor
                        public final Object construct() {
                            switch (i2) {
                                case 0:
                                    throw new JsonIOException((String) checkInstantiable);
                                case 1:
                                    try {
                                        return UnsafeAllocator.INSTANCE.newInstance((Class) checkInstantiable);
                                    } catch (Exception e2) {
                                        throw new RuntimeException("Unable to create instance of " + String.valueOf(checkInstantiable) + ". Registering an InstanceCreator or a TypeAdapter for this type, or adding a no-args constructor may fix this problem.", e2);
                                    }
                                case 2:
                                    throw new JsonIOException((String) checkInstantiable);
                                case 3:
                                    Object obj = checkInstantiable;
                                    if (obj instanceof ParameterizedType) {
                                        Type type2 = ((ParameterizedType) obj).getActualTypeArguments()[0];
                                        if (type2 instanceof Class) {
                                            return EnumSet.noneOf((Class) type2);
                                        }
                                        throw new JsonIOException("Invalid EnumSet type: ".concat(String.valueOf(checkInstantiable.toString())));
                                    }
                                    throw new JsonIOException("Invalid EnumSet type: ".concat(String.valueOf(obj.toString())));
                                case 4:
                                    Object obj2 = checkInstantiable;
                                    if (obj2 instanceof ParameterizedType) {
                                        Type type3 = ((ParameterizedType) obj2).getActualTypeArguments()[0];
                                        if (type3 instanceof Class) {
                                            return new EnumMap((Class) type3);
                                        }
                                        throw new JsonIOException("Invalid EnumMap type: ".concat(String.valueOf(checkInstantiable.toString())));
                                    }
                                    throw new JsonIOException("Invalid EnumMap type: ".concat(String.valueOf(obj2.toString())));
                                case 5:
                                    throw new JsonIOException((String) checkInstantiable);
                                case 6:
                                    throw new JsonIOException((String) checkInstantiable);
                                default:
                                    try {
                                        return ((Constructor) checkInstantiable).newInstance(null);
                                    } catch (IllegalAccessException e22) {
                                        throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e22);
                                    } catch (InstantiationException e3) {
                                        throw new RuntimeException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(ReflectionHelper.constructorToString((Constructor) checkInstantiable), "Failed to invoke constructor '", "' with no args"), e3);
                                    } catch (InvocationTargetException e4) {
                                        throw new RuntimeException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(ReflectionHelper.constructorToString((Constructor) checkInstantiable), "Failed to invoke constructor '", "' with no args"), e4.getCause());
                                    }
                            }
                        }
                    };
                }
                if (filterResult$ar$edu == 1) {
                    return new ObjectConstructor() { // from class: com.google.gson.internal.ConstructorConstructor.3
                        @Override // com.google.gson.internal.ObjectConstructor
                        public final Object construct() {
                            switch (i) {
                                case 0:
                                    throw new JsonIOException((String) cls);
                                case 1:
                                    try {
                                        return UnsafeAllocator.INSTANCE.newInstance((Class) cls);
                                    } catch (Exception e2) {
                                        throw new RuntimeException("Unable to create instance of " + String.valueOf(cls) + ". Registering an InstanceCreator or a TypeAdapter for this type, or adding a no-args constructor may fix this problem.", e2);
                                    }
                                case 2:
                                    throw new JsonIOException((String) cls);
                                case 3:
                                    Object obj = cls;
                                    if (obj instanceof ParameterizedType) {
                                        Type type2 = ((ParameterizedType) obj).getActualTypeArguments()[0];
                                        if (type2 instanceof Class) {
                                            return EnumSet.noneOf((Class) type2);
                                        }
                                        throw new JsonIOException("Invalid EnumSet type: ".concat(String.valueOf(cls.toString())));
                                    }
                                    throw new JsonIOException("Invalid EnumSet type: ".concat(String.valueOf(obj.toString())));
                                case 4:
                                    Object obj2 = cls;
                                    if (obj2 instanceof ParameterizedType) {
                                        Type type3 = ((ParameterizedType) obj2).getActualTypeArguments()[0];
                                        if (type3 instanceof Class) {
                                            return new EnumMap((Class) type3);
                                        }
                                        throw new JsonIOException("Invalid EnumMap type: ".concat(String.valueOf(cls.toString())));
                                    }
                                    throw new JsonIOException("Invalid EnumMap type: ".concat(String.valueOf(obj2.toString())));
                                case 5:
                                    throw new JsonIOException((String) cls);
                                case 6:
                                    throw new JsonIOException((String) cls);
                                default:
                                    try {
                                        return ((Constructor) cls).newInstance(null);
                                    } catch (IllegalAccessException e22) {
                                        throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e22);
                                    } catch (InstantiationException e3) {
                                        throw new RuntimeException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(ReflectionHelper.constructorToString((Constructor) cls), "Failed to invoke constructor '", "' with no args"), e3);
                                    } catch (InvocationTargetException e4) {
                                        throw new RuntimeException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(ReflectionHelper.constructorToString((Constructor) cls), "Failed to invoke constructor '", "' with no args"), e4.getCause());
                                    }
                            }
                        }
                    };
                }
                final String _BOUNDARY$ar$MethodOutlining$dc56d17a_112 = _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_11(cls, "Unable to create instance of ", "; ReflectionAccessFilter does not permit using reflection or Unsafe. Register an InstanceCreator or a TypeAdapter for this type or adjust the access filter to allow using reflection.");
                return new ObjectConstructor() { // from class: com.google.gson.internal.ConstructorConstructor.3
                    @Override // com.google.gson.internal.ObjectConstructor
                    public final Object construct() {
                        switch (i8) {
                            case 0:
                                throw new JsonIOException((String) _BOUNDARY$ar$MethodOutlining$dc56d17a_112);
                            case 1:
                                try {
                                    return UnsafeAllocator.INSTANCE.newInstance((Class) _BOUNDARY$ar$MethodOutlining$dc56d17a_112);
                                } catch (Exception e2) {
                                    throw new RuntimeException("Unable to create instance of " + String.valueOf(_BOUNDARY$ar$MethodOutlining$dc56d17a_112) + ". Registering an InstanceCreator or a TypeAdapter for this type, or adding a no-args constructor may fix this problem.", e2);
                                }
                            case 2:
                                throw new JsonIOException((String) _BOUNDARY$ar$MethodOutlining$dc56d17a_112);
                            case 3:
                                Object obj = _BOUNDARY$ar$MethodOutlining$dc56d17a_112;
                                if (obj instanceof ParameterizedType) {
                                    Type type2 = ((ParameterizedType) obj).getActualTypeArguments()[0];
                                    if (type2 instanceof Class) {
                                        return EnumSet.noneOf((Class) type2);
                                    }
                                    throw new JsonIOException("Invalid EnumSet type: ".concat(String.valueOf(_BOUNDARY$ar$MethodOutlining$dc56d17a_112.toString())));
                                }
                                throw new JsonIOException("Invalid EnumSet type: ".concat(String.valueOf(obj.toString())));
                            case 4:
                                Object obj2 = _BOUNDARY$ar$MethodOutlining$dc56d17a_112;
                                if (obj2 instanceof ParameterizedType) {
                                    Type type3 = ((ParameterizedType) obj2).getActualTypeArguments()[0];
                                    if (type3 instanceof Class) {
                                        return new EnumMap((Class) type3);
                                    }
                                    throw new JsonIOException("Invalid EnumMap type: ".concat(String.valueOf(_BOUNDARY$ar$MethodOutlining$dc56d17a_112.toString())));
                                }
                                throw new JsonIOException("Invalid EnumMap type: ".concat(String.valueOf(obj2.toString())));
                            case 5:
                                throw new JsonIOException((String) _BOUNDARY$ar$MethodOutlining$dc56d17a_112);
                            case 6:
                                throw new JsonIOException((String) _BOUNDARY$ar$MethodOutlining$dc56d17a_112);
                            default:
                                try {
                                    return ((Constructor) _BOUNDARY$ar$MethodOutlining$dc56d17a_112).newInstance(null);
                                } catch (IllegalAccessException e22) {
                                    throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e22);
                                } catch (InstantiationException e3) {
                                    throw new RuntimeException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(ReflectionHelper.constructorToString((Constructor) _BOUNDARY$ar$MethodOutlining$dc56d17a_112), "Failed to invoke constructor '", "' with no args"), e3);
                                } catch (InvocationTargetException e4) {
                                    throw new RuntimeException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(ReflectionHelper.constructorToString((Constructor) _BOUNDARY$ar$MethodOutlining$dc56d17a_112), "Failed to invoke constructor '", "' with no args"), e4.getCause());
                                }
                        }
                    }
                };
            }
            return objectConstructor2;
        }
        return new ObjectConstructor() { // from class: com.google.gson.internal.ConstructorConstructor.2
            @Override // com.google.gson.internal.ObjectConstructor
            public final Object construct() {
                if (i2 != 0) {
                    return instanceCreator2.createInstance$ar$ds();
                }
                return instanceCreator2.createInstance$ar$ds();
            }
        };
    }

    public final String toString() {
        return this.instanceCreators.toString();
    }
}
