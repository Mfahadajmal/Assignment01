package androidx.room.coroutines;

import androidx.room.RoomConnectionManager;
import androidx.room.RoomDatabase;
import com.google.android.accessibility.selecttospeak.ui.PlusMinusButtons;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FlowUtil$createFlow$1$1$1$invokeSuspend$$inlined$internalPerform$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function1 $block$inlined;
    final /* synthetic */ boolean $inTransaction = false;
    final /* synthetic */ RoomDatabase $this_internalPerform;
    /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* compiled from: PG */
    /* renamed from: androidx.room.coroutines.FlowUtil$createFlow$1$1$1$invokeSuspend$$inlined$internalPerform$1$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Function1 $block$inlined;
        private /* synthetic */ Object L$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Continuation continuation, Function1 function1) {
            super(2, continuation);
            this.$block$inlined = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation, this.$block$inlined);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((RoomConnectionManager.SupportPooledConnection.SupportTransactor) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
            RoomConnectionManager.SupportPooledConnection.SupportTransactor supportTransactor = (RoomConnectionManager.SupportPooledConnection.SupportTransactor) this.L$0;
            supportTransactor.getClass();
            return this.$block$inlined.invoke(supportTransactor.getRawConnection$ar$class_merging$ar$class_merging());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowUtil$createFlow$1$1$1$invokeSuspend$$inlined$internalPerform$1(boolean z, RoomDatabase roomDatabase, Continuation continuation, Function1 function1) {
        super(2, continuation);
        this.$this_internalPerform = roomDatabase;
        this.$block$inlined = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        FlowUtil$createFlow$1$1$1$invokeSuspend$$inlined$internalPerform$1 flowUtil$createFlow$1$1$1$invokeSuspend$$inlined$internalPerform$1 = new FlowUtil$createFlow$1$1$1$invokeSuspend$$inlined$internalPerform$1(false, this.$this_internalPerform, continuation, this.$block$inlined);
        flowUtil$createFlow$1$1$1$invokeSuspend$$inlined$internalPerform$1.L$0 = obj;
        return flowUtil$createFlow$1$1$1$invokeSuspend$$inlined$internalPerform$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((FlowUtil$createFlow$1$1$1$invokeSuspend$$inlined$internalPerform$1) create((RoomConnectionManager.SupportPooledConnection) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        RoomConnectionManager.SupportPooledConnection supportPooledConnection;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                    return obj;
                }
                supportPooledConnection = (RoomConnectionManager.SupportPooledConnection) this.L$0;
                OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
            } else {
                supportPooledConnection = (RoomConnectionManager.SupportPooledConnection) this.L$0;
                OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                if (!((Boolean) obj).booleanValue()) {
                    PlusMinusButtons invalidationTracker$ar$class_merging = this.$this_internalPerform.getInvalidationTracker$ar$class_merging();
                    this.L$0 = supportPooledConnection;
                    this.L$1 = null;
                    this.label = 2;
                    if (invalidationTracker$ar$class_merging.sync$room_runtime_release(this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                }
            }
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(null, this.$block$inlined);
            this.L$0 = supportPooledConnection;
            this.L$1 = null;
            this.label = 3;
            Object withTransaction$ar$edu = supportPooledConnection.withTransaction$ar$edu(0, anonymousClass1, this);
            if (withTransaction$ar$edu == coroutineSingletons) {
                return coroutineSingletons;
            }
            return withTransaction$ar$edu;
        }
        OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
        RoomConnectionManager.SupportPooledConnection supportPooledConnection2 = (RoomConnectionManager.SupportPooledConnection) this.L$0;
        supportPooledConnection2.getClass();
        return this.$block$inlined.invoke(supportPooledConnection2.getRawConnection$ar$class_merging$ar$class_merging());
    }
}
