package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.widget.TextView;
import androidx.lifecycle.ViewModelStore;
import androidx.room.DatabaseConfiguration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import kotlin.Pair;
import kotlin.collections.EmptyList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextViewCompat$Api23Impl {
    /* JADX WARN: Type inference failed for: r5v0, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v8, types: [java.util.Map, java.lang.Object] */
    public static final List findMigrationPath$ar$class_merging$ar$class_merging(ViewModelStore viewModelStore, int i, int i2) {
        boolean z;
        Pair pair;
        boolean z2;
        if (i == i2) {
            return EmptyList.INSTANCE;
        }
        if (i2 > i) {
            z = true;
        } else {
            z = false;
        }
        ArrayList arrayList = new ArrayList();
        do {
            if (z) {
                if (i >= i2) {
                    return arrayList;
                }
            } else if (i <= i2) {
                return arrayList;
            }
            if (z) {
                TreeMap treeMap = (TreeMap) viewModelStore.ViewModelStore$ar$map.get(Integer.valueOf(i));
                if (treeMap != null) {
                    pair = new Pair(treeMap, treeMap.descendingKeySet());
                }
                pair = null;
            } else {
                TreeMap treeMap2 = (TreeMap) viewModelStore.ViewModelStore$ar$map.get(Integer.valueOf(i));
                if (treeMap2 != null) {
                    pair = new Pair(treeMap2, treeMap2.keySet());
                }
                pair = null;
            }
            if (pair == null) {
                break;
            }
            Map map = (Map) pair.first;
            Iterator it = ((Iterable) pair.second).iterator();
            while (it.hasNext()) {
                int intValue = ((Number) it.next()).intValue();
                if (z) {
                    if (i + 1 <= intValue && intValue <= i2) {
                        Object obj = map.get(Integer.valueOf(intValue));
                        obj.getClass();
                        arrayList.add(obj);
                        z2 = true;
                        i = intValue;
                        break;
                    }
                } else if (i2 <= intValue && intValue < i) {
                    Object obj2 = map.get(Integer.valueOf(intValue));
                    obj2.getClass();
                    arrayList.add(obj2);
                    z2 = true;
                    i = intValue;
                    break;
                    break;
                }
            }
            z2 = false;
        } while (z2);
        return null;
    }

    static int getBreakStrategy(TextView textView) {
        return textView.getBreakStrategy();
    }

    static ColorStateList getCompoundDrawableTintList(TextView textView) {
        return textView.getCompoundDrawableTintList();
    }

    static PorterDuff.Mode getCompoundDrawableTintMode(TextView textView) {
        return textView.getCompoundDrawableTintMode();
    }

    static int getHyphenationFrequency(TextView textView) {
        return textView.getHyphenationFrequency();
    }

    public static final boolean isMigrationRequired(DatabaseConfiguration databaseConfiguration, int i, int i2) {
        if ((i <= i2 || !databaseConfiguration.allowDestructiveMigrationOnDowngrade) && databaseConfiguration.requireMigration && !databaseConfiguration.migrationNotRequiredFrom.contains(Integer.valueOf(i))) {
            return true;
        }
        return false;
    }

    static void setBreakStrategy(TextView textView, int i) {
        textView.setBreakStrategy(i);
    }

    public static void setCompoundDrawableTintList(TextView textView, ColorStateList colorStateList) {
        textView.setCompoundDrawableTintList(colorStateList);
    }

    public static void setCompoundDrawableTintMode(TextView textView, PorterDuff.Mode mode) {
        textView.setCompoundDrawableTintMode(mode);
    }

    static void setHyphenationFrequency(TextView textView, int i) {
        textView.setHyphenationFrequency(i);
    }
}
