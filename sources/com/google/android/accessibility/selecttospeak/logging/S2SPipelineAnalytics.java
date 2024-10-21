package com.google.android.accessibility.selecttospeak.logging;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.common.base.Stopwatch;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class S2SPipelineAnalytics {
    public static final SpannableUtils$NonCopyableTextSpan Companion$ar$class_merging$83731acb_0$ar$class_merging$ar$class_merging$ar$class_merging = new SpannableUtils$NonCopyableTextSpan();
    private final SharedPreferences prefs;
    public final Map elapsedMetrics = new LinkedHashMap();
    public final Stopwatch stopWatch = new Stopwatch();

    public S2SPipelineAnalytics(Context context) {
        this.prefs = SpannableUtils$NonCopyableTextSpan.getPipelineSharedPreference$ar$ds(context);
    }

    public final void amend() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : this.elapsedMetrics.entrySet()) {
            String str = "";
            String string = this.prefs.getString((String) entry.getKey(), "");
            if (string != null) {
                str = string;
            }
            StringBuilder sb = new StringBuilder(str);
            Iterator it = ((List) entry.getValue()).iterator();
            while (it.hasNext()) {
                sb.append(((Number) it.next()).intValue() + ",");
            }
            linkedHashMap.put(entry.getKey(), sb.toString());
        }
        SharedPreferences.Editor edit = this.prefs.edit();
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            edit.putString((String) entry2.getKey(), (String) entry2.getValue());
        }
        edit.apply();
        cleanupInMemoryMetrics();
    }

    public final void cleanupInMemoryMetrics() {
        this.stopWatch.reset$ar$ds();
        this.elapsedMetrics.clear();
    }

    public final void onUiReady() {
        cleanupInMemoryMetrics();
        Stopwatch stopwatch = this.stopWatch;
        stopwatch.reset$ar$ds();
        stopwatch.start$ar$ds$db96ddcc_0();
    }

    public final void recordElapsed(String str) {
        int elapsed = (int) this.stopWatch.elapsed(TimeUnit.MILLISECONDS);
        Stopwatch stopwatch = this.stopWatch;
        stopwatch.reset$ar$ds();
        stopwatch.start$ar$ds$db96ddcc_0();
        Map map = this.elapsedMetrics;
        Object obj = map.get(str);
        if (obj == null) {
            obj = new ArrayList();
            map.put(str, obj);
        }
        ((List) obj).add(Integer.valueOf(elapsed));
    }
}
