package androidx.room;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.CancellationSignal;
import android.support.v7.app.AppCompatDelegate;
import androidx.lifecycle.MutableLiveData;
import androidx.sqlite.db.framework.FrameworkSQLiteDatabase;
import java.lang.reflect.Method;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.random.Random;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TriggerBasedInvalidationTracker$onAllowRefresh$1 extends Lambda implements Function0 {
    private final /* synthetic */ int switching_field;
    public static final TriggerBasedInvalidationTracker$onAllowRefresh$1 INSTANCE$ar$class_merging$23822fe0_0 = new TriggerBasedInvalidationTracker$onAllowRefresh$1(10);
    public static final TriggerBasedInvalidationTracker$onAllowRefresh$1 INSTANCE$ar$class_merging$c1c37390_0 = new TriggerBasedInvalidationTracker$onAllowRefresh$1(9);
    public static final TriggerBasedInvalidationTracker$onAllowRefresh$1 INSTANCE$ar$class_merging$66e53fc8_0 = new TriggerBasedInvalidationTracker$onAllowRefresh$1(8);
    public static final TriggerBasedInvalidationTracker$onAllowRefresh$1 INSTANCE$ar$class_merging$b028af8d_0 = new TriggerBasedInvalidationTracker$onAllowRefresh$1(7);
    public static final TriggerBasedInvalidationTracker$onAllowRefresh$1 INSTANCE$ar$class_merging$369147ba_0 = new TriggerBasedInvalidationTracker$onAllowRefresh$1(6);
    public static final TriggerBasedInvalidationTracker$onAllowRefresh$1 INSTANCE$ar$class_merging$c6fcde7f_0 = new TriggerBasedInvalidationTracker$onAllowRefresh$1(5);
    public static final TriggerBasedInvalidationTracker$onAllowRefresh$1 INSTANCE$ar$class_merging$a9b4be51_0 = new TriggerBasedInvalidationTracker$onAllowRefresh$1(4);
    public static final TriggerBasedInvalidationTracker$onAllowRefresh$1 INSTANCE$ar$class_merging$a11f4c67_0 = new TriggerBasedInvalidationTracker$onAllowRefresh$1(3);
    public static final TriggerBasedInvalidationTracker$onAllowRefresh$1 INSTANCE$ar$class_merging$723ce0fa_0 = new TriggerBasedInvalidationTracker$onAllowRefresh$1(2);
    public static final TriggerBasedInvalidationTracker$onAllowRefresh$1 INSTANCE$ar$class_merging = new TriggerBasedInvalidationTracker$onAllowRefresh$1(1);
    public static final TriggerBasedInvalidationTracker$onAllowRefresh$1 INSTANCE = new TriggerBasedInvalidationTracker$onAllowRefresh$1(0);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TriggerBasedInvalidationTracker$onAllowRefresh$1(int i) {
        super(0);
        this.switching_field = i;
    }

    @Override // kotlin.jvm.functions.Function0
    public final /* synthetic */ Object invoke() {
        Class<?> returnType;
        switch (this.switching_field) {
            case 0:
                return true;
            case 1:
                return Integer.valueOf(Random.Default.nextInt$ar$ds() + 65536);
            case 2:
                try {
                    Lazy lazy = FrameworkSQLiteDatabase.getThreadSessionMethod$delegate;
                    Method getThreadSessionMethod$ar$ds = AppCompatDelegate.Api24Impl.getGetThreadSessionMethod$ar$ds();
                    if (getThreadSessionMethod$ar$ds == null || (returnType = getThreadSessionMethod$ar$ds.getReturnType()) == null) {
                        return null;
                    }
                    Class<?> cls = Integer.TYPE;
                    return returnType.getDeclaredMethod("beginTransaction", cls, SQLiteTransactionListener.class, cls, CancellationSignal.class);
                } catch (Throwable unused) {
                    return null;
                }
            case 3:
                try {
                    Method declaredMethod = SQLiteDatabase.class.getDeclaredMethod("getThreadSession", null);
                    declaredMethod.setAccessible(true);
                    return declaredMethod;
                } catch (Throwable unused2) {
                    return null;
                }
            case 4:
                return false;
            case 5:
                return new MutableLiveData();
            case 6:
                return new MutableLiveData();
            case 7:
                return new MutableLiveData();
            case 8:
                return new MutableLiveData();
            case 9:
                return new MutableLiveData();
            default:
                return new MutableLiveData();
        }
    }
}
