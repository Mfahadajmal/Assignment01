package kotlin.jvm.internal;

import java.io.Serializable;
import kotlin.reflect.KCallable;
import kotlin.reflect.KDeclarationContainer;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class CallableReference implements KCallable, Serializable {
    public static final Object NO_RECEIVER = NoReceiver.INSTANCE;
    private final boolean isTopLevel;
    public final String name;
    private final Class owner;
    protected final Object receiver;
    private transient KCallable reflected;
    public final String signature;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class NoReceiver implements Serializable {
        public static final NoReceiver INSTANCE = new NoReceiver();

        private NoReceiver() {
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CallableReference(Object obj, Class cls, String str, String str2, boolean z) {
        this.receiver = obj;
        this.owner = cls;
        this.name = str;
        this.signature = str2;
        this.isTopLevel = z;
    }

    @Override // kotlin.reflect.KCallable
    public final Object call$ar$ds() {
        throw null;
    }

    public final KCallable compute() {
        KCallable kCallable = this.reflected;
        if (kCallable == null) {
            KCallable computeReflected = computeReflected();
            this.reflected = computeReflected;
            return computeReflected;
        }
        return kCallable;
    }

    protected abstract KCallable computeReflected();

    public final KDeclarationContainer getOwner() {
        Class cls = this.owner;
        if (cls == null) {
            return null;
        }
        if (this.isTopLevel) {
            return new PackageReference(cls);
        }
        return Reflection.getOrCreateKotlinClass(cls);
    }
}
