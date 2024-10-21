package androidx.core.view;

import android.view.ViewConfiguration;
import androidx.navigation.NavArgument;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.functions.Function1;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ViewConfigurationCompat$Api26Impl {
    public static float getScaledHorizontalScrollFactor(ViewConfiguration viewConfiguration) {
        return viewConfiguration.getScaledHorizontalScrollFactor();
    }

    public static float getScaledVerticalScrollFactor(ViewConfiguration viewConfiguration) {
        return viewConfiguration.getScaledVerticalScrollFactor();
    }

    public static final List missingRequiredArguments(Map map, Function1 function1) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : map.entrySet()) {
            NavArgument navArgument = (NavArgument) entry.getValue();
            if (navArgument != null && !navArgument.isNullable && !navArgument.isDefaultValuePresent) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        Set keySet = linkedHashMap.keySet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : keySet) {
            if (((Boolean) function1.invoke((String) obj)).booleanValue()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
