package androidx.activity.result.contract;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextHelper;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.EmptyMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ActivityResultContracts$RequestMultiplePermissions extends ActivityResultContract {
    public static final AppCompatTextHelper.Api28Impl Companion$ar$class_merging$110e097a_0 = new AppCompatTextHelper.Api28Impl();

    @Override // androidx.activity.result.contract.ActivityResultContract
    public final /* bridge */ /* synthetic */ Intent createIntent$ar$ds(Object obj) {
        String[] strArr = (String[]) obj;
        strArr.getClass();
        Intent putExtra = new Intent("androidx.activity.result.contract.action.REQUEST_PERMISSIONS").putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr);
        putExtra.getClass();
        return putExtra;
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public final /* bridge */ /* synthetic */ AccessibilityNodeInfoCompat.CollectionItemInfoCompat getSynchronousResult$ar$class_merging$ar$class_merging(Context context, Object obj) {
        String[] strArr = (String[]) obj;
        strArr.getClass();
        if (strArr.length == 0) {
            return new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(EmptyMap.INSTANCE);
        }
        for (String str : strArr) {
            if (EditorInfoCompat.checkSelfPermission(context, str) != 0) {
                return null;
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(OnDeviceSmartReplyLogEvent.SmartReply.coerceAtLeast(OnDeviceLanguageIdentificationLogEvent.mapCapacity(strArr.length), 16));
        for (String str2 : strArr) {
            Pair pair = new Pair(str2, true);
            linkedHashMap.put(pair.first, pair.second);
        }
        return new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(linkedHashMap);
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public final /* bridge */ /* synthetic */ Object parseResult(int i, Intent intent) {
        boolean z;
        if (i != -1 || intent == null) {
            return EmptyMap.INSTANCE;
        }
        String[] stringArrayExtra = intent.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
        int[] intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
        if (intArrayExtra != null && stringArrayExtra != null) {
            ArrayList arrayList = new ArrayList(intArrayExtra.length);
            for (int i2 : intArrayExtra) {
                if (i2 == 0) {
                    z = true;
                } else {
                    z = false;
                }
                arrayList.add(Boolean.valueOf(z));
            }
            List filterNotNull = OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.filterNotNull(stringArrayExtra);
            Iterator it = filterNotNull.iterator();
            Iterator it2 = arrayList.iterator();
            ArrayList arrayList2 = new ArrayList(Math.min(OnDeviceLanguageIdentificationLogEvent.collectionSizeOrDefault$ar$ds(filterNotNull), OnDeviceLanguageIdentificationLogEvent.collectionSizeOrDefault$ar$ds(arrayList)));
            while (it.hasNext() && it2.hasNext()) {
                arrayList2.add(new Pair(it.next(), it2.next()));
            }
            return OnDeviceLanguageIdentificationLogEvent.toMap(arrayList2);
        }
        return EmptyMap.INSTANCE;
    }
}
