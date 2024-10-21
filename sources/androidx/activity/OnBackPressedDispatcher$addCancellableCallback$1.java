package androidx.activity;

import androidx.room.RoomDatabase;
import com.google.common.labs.kotlin.base.VoidAsUnit$VoidFunction0;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class OnBackPressedDispatcher$addCancellableCallback$1 extends FunctionReference implements Function0 {
    private final /* synthetic */ int switching_field;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OnBackPressedDispatcher$addCancellableCallback$1(Object obj, int i) {
        super(0, obj, OnBackPressedDispatcher.class, "updateEnabledCallbacks", "updateEnabledCallbacks()V");
        this.switching_field = i;
    }

    @Override // kotlin.jvm.functions.Function0
    public final /* synthetic */ Object invoke() {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    ((VoidAsUnit$VoidFunction0) this.receiver).invoke();
                    return Unit.INSTANCE;
                }
                ((RoomDatabase) this.receiver).onClosed();
                return Unit.INSTANCE;
            }
            ((OnBackPressedDispatcher) this.receiver).updateEnabledCallbacks();
            return Unit.INSTANCE;
        }
        ((OnBackPressedDispatcher) this.receiver).updateEnabledCallbacks();
        return Unit.INSTANCE;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OnBackPressedDispatcher$addCancellableCallback$1(Object obj, int i, short[] sArr) {
        super(0, obj, VoidAsUnit$VoidFunction0.class, "invoke", "invoke()V");
        this.switching_field = i;
    }
}
