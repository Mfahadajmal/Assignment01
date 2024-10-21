package com.google.common.collect;

import com.google.android.play.core.splitinstall.SplitInstallSharedPreferences;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import j$.util.function.BiFunction$CC;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class CollectCollectors$$ExternalSyntheticLambda21 implements BinaryOperator {
    private final /* synthetic */ int switching_field;

    public /* synthetic */ CollectCollectors$$ExternalSyntheticLambda21(int i) {
        this.switching_field = i;
    }

    public final /* synthetic */ BiFunction andThen(Function function) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return BiFunction$CC.$default$andThen(this, function);
                }
                return BiFunction$CC.$default$andThen(this, function);
            }
            return BiFunction$CC.$default$andThen(this, function);
        }
        return BiFunction$CC.$default$andThen(this, function);
    }

    /* JADX WARN: Type inference failed for: r7v4, types: [java.lang.Object, java.lang.Iterable] */
    @Override // java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    ImmutableMap.Builder builder = (ImmutableMap.Builder) obj;
                    ImmutableMap.Builder builder2 = (ImmutableMap.Builder) obj2;
                    builder2.getClass();
                    builder.ensureCapacity(builder.size + builder2.size);
                    Object obj3 = builder2.ImmutableMap$Builder$ar$alternatingKeysAndValues;
                    Object obj4 = builder.ImmutableMap$Builder$ar$alternatingKeysAndValues;
                    int i2 = builder.size;
                    int i3 = builder2.size;
                    System.arraycopy(obj3, 0, obj4, i2 + i2, i3 + i3);
                    builder.size += builder2.size;
                    return builder;
                }
                return ((ImmutableSet.Builder) obj).combine((ImmutableSet.Builder) obj2);
            }
            SplitInstallSharedPreferences splitInstallSharedPreferences = (SplitInstallSharedPreferences) obj;
            Iterator it = ((SplitInstallSharedPreferences) obj2).SplitInstallSharedPreferences$ar$context.iterator();
            while (it.hasNext()) {
                splitInstallSharedPreferences.add$ar$ds$52de16dd_0((Range) it.next());
            }
            return splitInstallSharedPreferences;
        }
        ImmutableList.Builder builder3 = (ImmutableList.Builder) obj;
        ImmutableList.Builder builder4 = (ImmutableList.Builder) obj2;
        builder3.addAll(builder4.contents, builder4.size);
        return builder3;
    }
}
