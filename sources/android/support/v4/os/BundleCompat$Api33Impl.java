package android.support.v4.os;

import _COROUTINE._BOUNDARY;
import android.os.Bundle;
import android.support.v4.app.SpecialEffectsController;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.sqlite.db.SupportSQLiteProgram;
import com.google.android.marvin.talkback.R;
import java.io.Serializable;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BundleCompat$Api33Impl {
    public static final void bind$ar$ds(SupportSQLiteProgram supportSQLiteProgram, Object[] objArr) {
        long j;
        if (objArr != null) {
            int i = 0;
            while (i < objArr.length) {
                Object obj = objArr[i];
                i++;
                if (obj == null) {
                    supportSQLiteProgram.bindNull(i);
                } else if (obj instanceof byte[]) {
                    supportSQLiteProgram.bindBlob(i, (byte[]) obj);
                } else if (obj instanceof Float) {
                    supportSQLiteProgram.bindDouble(i, ((Number) obj).floatValue());
                } else if (obj instanceof Double) {
                    supportSQLiteProgram.bindDouble(i, ((Number) obj).doubleValue());
                } else if (obj instanceof Long) {
                    supportSQLiteProgram.bindLong(i, ((Number) obj).longValue());
                } else if (obj instanceof Integer) {
                    supportSQLiteProgram.bindLong(i, ((Number) obj).intValue());
                } else if (obj instanceof Short) {
                    supportSQLiteProgram.bindLong(i, ((Number) obj).shortValue());
                } else if (obj instanceof Byte) {
                    supportSQLiteProgram.bindLong(i, ((Number) obj).byteValue());
                } else if (obj instanceof String) {
                    supportSQLiteProgram.bindString(i, (String) obj);
                } else if (obj instanceof Boolean) {
                    if (true != ((Boolean) obj).booleanValue()) {
                        j = 0;
                    } else {
                        j = 1;
                    }
                    supportSQLiteProgram.bindLong(i, j);
                } else {
                    throw new IllegalArgumentException("Cannot bind " + obj + " at index " + i + " Supported types: Null, ByteArray, Float, Double, Long, Int, Short, Byte, String");
                }
            }
        }
    }

    public static final SpecialEffectsController createController$ar$ds(ViewGroup viewGroup) {
        return new SpecialEffectsController(viewGroup, null);
    }

    public static final int from$ar$edu$ar$ds(int i) {
        if (i != 0) {
            if (i == 4) {
                return 4;
            }
            if (i == 8) {
                return 3;
            }
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Unknown visibility "));
        }
        return 2;
    }

    public static final SpecialEffectsController getOrCreateController$ar$class_merging$ar$ds$ar$class_merging$ar$class_merging(ViewGroup viewGroup, BundleCompat$Api33Impl bundleCompat$Api33Impl) {
        bundleCompat$Api33Impl.getClass();
        Object tag = viewGroup.getTag(R.id.special_effects_controller_view_tag);
        if (tag instanceof SpecialEffectsController) {
            return (SpecialEffectsController) tag;
        }
        SpecialEffectsController createController$ar$ds = createController$ar$ds(viewGroup);
        viewGroup.setTag(R.id.special_effects_controller_view_tag, createController$ar$ds);
        return createController$ar$ds;
    }

    public static <T> T getParcelable(Bundle bundle, String str, Class<T> cls) {
        Object parcelable;
        parcelable = bundle.getParcelable(str, cls);
        return (T) parcelable;
    }

    static <T> T[] getParcelableArray(Bundle bundle, String str, Class<T> cls) {
        Object[] parcelableArray;
        parcelableArray = bundle.getParcelableArray(str, cls);
        return (T[]) parcelableArray;
    }

    static <T> ArrayList<T> getParcelableArrayList(Bundle bundle, String str, Class<? extends T> cls) {
        ArrayList<T> parcelableArrayList;
        parcelableArrayList = bundle.getParcelableArrayList(str, cls);
        return parcelableArrayList;
    }

    static <T extends Serializable> T getSerializable(Bundle bundle, String str, Class<T> cls) {
        Serializable serializable;
        serializable = bundle.getSerializable(str, cls);
        return (T) serializable;
    }

    static <T> SparseArray<T> getSparseParcelableArray(Bundle bundle, String str, Class<? extends T> cls) {
        SparseArray<T> sparseParcelableArray;
        sparseParcelableArray = bundle.getSparseParcelableArray(str, cls);
        return sparseParcelableArray;
    }

    public static /* synthetic */ String toStringGeneratedc4bc3d545aab1bc8(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return "null";
                }
                return "REMOVING";
            }
            return "ADDING";
        }
        return "NONE";
    }

    public final int asOperationState$ar$edu(View view) {
        if (view.getAlpha() == 0.0f && view.getVisibility() == 0) {
            return 4;
        }
        return from$ar$edu$ar$ds(view.getVisibility());
    }
}
