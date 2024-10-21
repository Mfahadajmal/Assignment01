package com.google.android.accessibility.braille.brailledisplay.platform.lib;

import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SharedPreferencesStringList {
    private static final String DELIMITER = "$";

    public static TreeMap readTreeMap$ar$ds(SharedPreferences sharedPreferences) {
        TreeMap treeMap = new TreeMap();
        Set<String> stringSet = sharedPreferences.getStringSet("remembered_devices_with_addresses", new HashSet());
        if (stringSet.isEmpty()) {
            return new TreeMap();
        }
        for (String str : stringSet) {
            int indexOf = str.indexOf(DELIMITER);
            if (indexOf != -1) {
                String substring = str.substring(indexOf + 1);
                if (!substring.isEmpty()) {
                    try {
                        treeMap.put(Integer.valueOf(Integer.parseInt(str.substring(0, indexOf))), substring);
                    } catch (NumberFormatException unused) {
                    }
                }
            }
        }
        return treeMap;
    }

    public static void writeTreeMap$ar$ds(SharedPreferences sharedPreferences, TreeMap treeMap) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int i = 0;
        for (String str : treeMap.values()) {
            linkedHashSet.add(i + String.valueOf(DELIMITER) + str);
            i++;
        }
        sharedPreferences.edit().putStringSet("remembered_devices_with_addresses", linkedHashSet).apply();
    }
}
