package kotlin.coroutines;

import io.grpc.ManagedChannelProvider;
import io.grpc.ManagedChannelRegistry;
import java.util.List;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CopyableThreadContextElement;
import kotlinx.coroutines.ThreadContextElement;
import kotlinx.coroutines.internal.ThreadState;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CombinedContext$toString$1 extends Lambda implements Function2 {
    private final /* synthetic */ int switching_field;
    public static final CombinedContext$toString$1 INSTANCE$ar$class_merging$77c6765b_0 = new CombinedContext$toString$1(9);
    public static final CombinedContext$toString$1 INSTANCE$ar$class_merging$f8339cea_0 = new CombinedContext$toString$1(8);
    public static final CombinedContext$toString$1 INSTANCE$ar$class_merging$9a0af526_0 = new CombinedContext$toString$1(7);
    public static final CombinedContext$toString$1 INSTANCE$ar$class_merging$e958891e_0 = new CombinedContext$toString$1(6);
    public static final CombinedContext$toString$1 INSTANCE$ar$class_merging$53896bf0_0 = new CombinedContext$toString$1(5);
    public static final CombinedContext$toString$1 INSTANCE$ar$class_merging$e3050a31_0 = new CombinedContext$toString$1(4);
    public static final CombinedContext$toString$1 INSTANCE$ar$class_merging$eed8d28d_0 = new CombinedContext$toString$1(3);
    public static final CombinedContext$toString$1 INSTANCE$ar$class_merging$9bb6b845_0 = new CombinedContext$toString$1(2);
    public static final CombinedContext$toString$1 INSTANCE$ar$class_merging$4b2d14aa_0 = new CombinedContext$toString$1(1);
    public static final CombinedContext$toString$1 INSTANCE = new CombinedContext$toString$1(0);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CombinedContext$toString$1(int i) {
        super(2);
        this.switching_field = i;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* synthetic */ Object invoke(Object obj, Object obj2) {
        int i;
        boolean z = false;
        ManagedChannelProvider managedChannelProvider = null;
        Integer num = null;
        switch (this.switching_field) {
            case 0:
                String str = (String) obj;
                CoroutineContext.Element element = (CoroutineContext.Element) obj2;
                str.getClass();
                element.getClass();
                if (str.length() == 0) {
                    return element.toString();
                }
                return str + ", " + element;
            case 1:
                String str2 = (String) obj;
                int intValue = ((Number) obj2).intValue();
                str2.getClass();
                List providers = ManagedChannelRegistry.getDefaultRegistry().providers();
                if (!providers.isEmpty()) {
                    managedChannelProvider = (ManagedChannelProvider) providers.get(0);
                }
                if (managedChannelProvider != null) {
                    return managedChannelProvider.builderForAddress(str2, intValue).build();
                }
                throw new ManagedChannelProvider.ProviderNotFoundException();
            case 2:
                CoroutineContext coroutineContext = (CoroutineContext) obj;
                CoroutineContext.Element element2 = (CoroutineContext.Element) obj2;
                coroutineContext.getClass();
                element2.getClass();
                CoroutineContext minusKey = coroutineContext.minusKey(element2.getKey());
                if (minusKey != EmptyCoroutineContext.INSTANCE) {
                    ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) minusKey.get(ContinuationInterceptor.Key);
                    if (continuationInterceptor == null) {
                        return new CombinedContext(minusKey, element2);
                    }
                    CoroutineContext minusKey2 = minusKey.minusKey(ContinuationInterceptor.Key);
                    if (minusKey2 == EmptyCoroutineContext.INSTANCE) {
                        return new CombinedContext(element2, continuationInterceptor);
                    }
                    return new CombinedContext(new CombinedContext(minusKey2, element2), continuationInterceptor);
                }
                return element2;
            case 3:
                CoroutineContext coroutineContext2 = (CoroutineContext) obj;
                CoroutineContext.Element element3 = (CoroutineContext.Element) obj2;
                if (element3 instanceof CopyableThreadContextElement) {
                    return coroutineContext2.plus(((CopyableThreadContextElement) element3).copyForChild());
                }
                return coroutineContext2.plus(element3);
            case 4:
                CoroutineContext.Element element4 = (CoroutineContext.Element) obj2;
                if (((Boolean) obj).booleanValue() || (element4 instanceof CopyableThreadContextElement)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            case 5:
                return Boolean.valueOf(Intrinsics.areEqual(obj, obj2));
            case 6:
                return Integer.valueOf(((Number) obj).intValue() + 1);
            case 7:
                CoroutineContext.Element element5 = (CoroutineContext.Element) obj2;
                if (element5 instanceof ThreadContextElement) {
                    if (obj instanceof Integer) {
                        num = (Integer) obj;
                    }
                    if (num != null) {
                        i = num.intValue();
                    } else {
                        i = 1;
                    }
                    if (i == 0) {
                        return element5;
                    }
                    return Integer.valueOf(i + 1);
                }
                return obj;
            case 8:
                ThreadContextElement threadContextElement = (ThreadContextElement) obj;
                CoroutineContext.Element element6 = (CoroutineContext.Element) obj2;
                if (threadContextElement == null) {
                    if (!(element6 instanceof ThreadContextElement)) {
                        return null;
                    }
                    return (ThreadContextElement) element6;
                }
                return threadContextElement;
            default:
                ThreadState threadState = (ThreadState) obj;
                CoroutineContext.Element element7 = (CoroutineContext.Element) obj2;
                if (element7 instanceof ThreadContextElement) {
                    ThreadContextElement threadContextElement2 = (ThreadContextElement) element7;
                    Object updateThreadContext = threadContextElement2.updateThreadContext(threadState.context);
                    Object[] objArr = threadState.values;
                    int i2 = threadState.i;
                    objArr[i2] = updateThreadContext;
                    ThreadContextElement[] threadContextElementArr = threadState.elements;
                    threadState.i = i2 + 1;
                    threadContextElement2.getClass();
                    threadContextElementArr[i2] = threadContextElement2;
                    return threadState;
                }
                return threadState;
        }
    }
}
