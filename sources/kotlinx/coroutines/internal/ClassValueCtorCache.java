package kotlinx.coroutines.internal;

import kotlin.jvm.functions.Function1;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ClassValueCtorCache extends CtorCache {
    public static final ClassValueCtorCache INSTANCE = new ClassValueCtorCache();
    private static final ClassValueCtorCache$cache$1 cache = new ClassValue() { // from class: kotlinx.coroutines.internal.ClassValueCtorCache$cache$1
        @Override // java.lang.ClassValue
        public final /* bridge */ /* synthetic */ Object computeValue(Class cls) {
            cls.getClass();
            return ExceptionsConstructorKt.createConstructor(cls);
        }
    };

    private ClassValueCtorCache() {
    }

    @Override // kotlinx.coroutines.internal.CtorCache
    public final Function1 get(Class cls) {
        Object obj;
        obj = cache.get(cls);
        return (Function1) obj;
    }
}
