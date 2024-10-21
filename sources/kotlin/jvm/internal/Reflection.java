package kotlin.jvm.internal;

import kotlin.reflect.KClass;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Reflection {
    public static final Intrinsics factory$ar$class_merging$693c3180_0;

    static {
        Intrinsics intrinsics = null;
        try {
            intrinsics = (Intrinsics) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (intrinsics == null) {
            intrinsics = new Intrinsics();
        }
        factory$ar$class_merging$693c3180_0 = intrinsics;
    }

    public static KClass getOrCreateKotlinClass(Class cls) {
        return new ClassReference(cls);
    }

    public static String renderLambdaToString(FunctionBase functionBase) {
        return Intrinsics.renderLambdaToString$ar$ds(functionBase);
    }
}
