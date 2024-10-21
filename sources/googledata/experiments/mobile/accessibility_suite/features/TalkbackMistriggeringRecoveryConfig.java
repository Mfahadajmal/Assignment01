package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkbackMistriggeringRecoveryConfig implements Supplier {
    public static final TalkbackMistriggeringRecoveryConfig INSTANCE = new TalkbackMistriggeringRecoveryConfig();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new TalkbackMistriggeringRecoveryConfigFlagsImpl());

    public static boolean automaticTurnOff(Context context) {
        return INSTANCE.get().automaticTurnOff(context);
    }

    @Override // com.google.common.base.Supplier
    public final TalkbackMistriggeringRecoveryConfigFlags get() {
        return (TalkbackMistriggeringRecoveryConfigFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
