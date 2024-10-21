package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.widget.CompoundButton;
import androidx.navigation.ActivityNavigator$hostActivity$1;
import androidx.room.PooledConnection;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CompoundButtonCompat$Api21Impl {
    public static final Object execSQL(PooledConnection pooledConnection, String str, Continuation continuation) {
        Object usePrepared = pooledConnection.usePrepared(str, ActivityNavigator$hostActivity$1.INSTANCE$ar$class_merging$561cc4db_0, continuation);
        if (usePrepared == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return usePrepared;
        }
        return Unit.INSTANCE;
    }

    public static ColorStateList getButtonTintList(CompoundButton compoundButton) {
        return compoundButton.getButtonTintList();
    }

    public static PorterDuff.Mode getButtonTintMode(CompoundButton compoundButton) {
        return compoundButton.getButtonTintMode();
    }

    public static void setButtonTintList(CompoundButton compoundButton, ColorStateList colorStateList) {
        compoundButton.setButtonTintList(colorStateList);
    }

    public static void setButtonTintMode(CompoundButton compoundButton, PorterDuff.Mode mode) {
        compoundButton.setButtonTintMode(mode);
    }
}
