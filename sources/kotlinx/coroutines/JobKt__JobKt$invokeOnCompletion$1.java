package kotlinx.coroutines;

import com.google.common.labs.kotlin.base.VoidAsUnit$VoidFunction1;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class JobKt__JobKt$invokeOnCompletion$1 extends FunctionReference implements Function1 {
    private final /* synthetic */ int switching_field;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JobKt__JobKt$invokeOnCompletion$1(Object obj, int i) {
        super(1, obj, InternalCompletionHandler.class, "invoke", "invoke(Ljava/lang/Throwable;)V");
        this.switching_field = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* synthetic */ Object invoke(Object obj) {
        if (this.switching_field != 0) {
            ((VoidAsUnit$VoidFunction1) this.receiver).invoke(obj);
            return Unit.INSTANCE;
        }
        ((InternalCompletionHandler) this.receiver).invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JobKt__JobKt$invokeOnCompletion$1(Object obj, int i, byte[] bArr) {
        super(1, obj, VoidAsUnit$VoidFunction1.class, "invoke", "invoke(Ljava/lang/Object;)V");
        this.switching_field = i;
    }
}
