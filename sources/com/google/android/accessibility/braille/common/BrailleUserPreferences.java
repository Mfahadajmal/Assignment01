package com.google.android.accessibility.braille.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.AppCompatDelegateImpl;
import com.google.android.accessibility.braille.brailledisplay.controller.utils.BrailleKeyBindingUtils$$ExternalSyntheticLambda2;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.bt.BtConnectManager$$ExternalSyntheticLambda1;
import com.google.android.accessibility.braille.common.translate.BrailleLanguages;
import com.google.android.accessibility.braille.translate.GoogleBrailleTranslatorFactory;
import com.google.android.accessibility.braille.translate.TranslatorFactory;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.flogger.context.ContextDataProvider;
import j$.util.Collection;
import j$.util.DesugarArrays;
import j$.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleUserPreferences {
    private static int currentVersion = -1;

    public static void decreaseAutoScrollDuration(Context context) {
        writeAutoScrollDuration(context, readAutoScrollDuration(context) - 500);
    }

    public static String[] getAvailableBlinkingIntervalsMs(Context context) {
        return context.getResources().getStringArray(R.array.blinking_interval_entries_values);
    }

    public static int getCurrentTypingLanguageType$ar$edu(Context context) {
        if (isCurrentActiveInputCodeEightDot(context)) {
            return 8;
        }
        return 6;
    }

    public static BrailleLanguages.Code getNextInputCode(Context context) {
        List readAvailablePreferredCodes = readAvailablePreferredCodes(context);
        int indexOf = readAvailablePreferredCodes.indexOf(readCurrentActiveInputCodeAndCorrect(context)) + 1;
        if (indexOf >= readAvailablePreferredCodes.size()) {
            indexOf = 0;
        }
        return (BrailleLanguages.Code) readAvailablePreferredCodes.get(indexOf);
    }

    private static String getOldKey(String str) {
        return str.replace("_brailleime", "");
    }

    public static SharedPreferences getSharedPreferences$ar$ds(Context context) {
        migrateIfNecessary(context);
        return SpannableUtils$IdentifierSpan.getSharedPreferences(context, "braille_keyboard");
    }

    public static int getTimedMessageDurationInMillisecond(Context context, int i) {
        return Math.max(Math.round((i / 5.0f) * Float.parseFloat(readTimedMessageDurationFraction(context)) * 1000.0f), 3000);
    }

    public static void increaseAutoScrollDuration(Context context) {
        writeAutoScrollDuration(context, readAutoScrollDuration(context) + 500);
    }

    public static boolean isCurrentActiveInputCodeEightDot(Context context) {
        return readCurrentActiveInputCodeAndCorrect(context).eightDot;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x01ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void migrateIfNecessary(android.content.Context r14) {
        /*
            Method dump skipped, instructions count: 591
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.braille.common.BrailleUserPreferences.migrateIfNecessary(android.content.Context):void");
    }

    public static boolean readAccumulateMode(Context context) {
        return getSharedPreferences$ar$ds(context).getBoolean(context.getString(R.string.pref_brailleime_accumulate_mode), true);
    }

    public static boolean readAutoAdjustDurationEnable(Context context) {
        return getSharedPreferences$ar$ds(context).getBoolean(context.getString(R.string.pref_bd_auto_adjust_duration_enable_key), true);
    }

    public static int readAutoScrollDuration(Context context) {
        return getSharedPreferences$ar$ds(context).getInt(context.getString(R.string.pref_bd_auto_scroll_duration_key), 3000);
    }

    public static List readAvailablePreferredCodes(Context context) {
        List readPreferredCodes = readPreferredCodes(context);
        List availableCodes = BrailleLanguages.getAvailableCodes(context);
        int size = availableCodes.size();
        while (true) {
            size--;
            if (size >= 0) {
                if (!readPreferredCodes.contains(availableCodes.get(size))) {
                    availableCodes.remove(size);
                }
            } else {
                return availableCodes;
            }
        }
    }

    public static int readBlinkingIntervalMs(Context context) {
        String string = getSharedPreferences$ar$ds(context).getString(context.getString(R.string.pref_bd_blinking_interval_key), "750");
        if (DesugarArrays.stream(getAvailableBlinkingIntervalsMs(context)).anyMatch(new BrailleKeyBindingUtils$$ExternalSyntheticLambda2(string, 16))) {
            return Integer.parseInt(string);
        }
        return Integer.parseInt("750");
    }

    public static boolean readContractedMode(Context context) {
        return getSharedPreferences$ar$ds(context).getBoolean(context.getString(R.string.pref_braille_contracted_mode), false);
    }

    public static BrailleLanguages.Code readCurrentActiveInputCodeAndCorrect(Context context) {
        BrailleLanguages.Code code = (BrailleLanguages.Code) AppCompatDelegate.Api33Impl.valueOfSafe(getSharedPreferences$ar$ds(context).getString(context.getString(R.string.pref_brailleime_translator_code), BrailleLanguages.getDefaultCode(context).name()), BrailleLanguages.getDefaultCode(context));
        List readAvailablePreferredCodes = readAvailablePreferredCodes(context);
        if (readAvailablePreferredCodes.contains(code)) {
            return code;
        }
        BrailleLanguages.Code code2 = (BrailleLanguages.Code) readAvailablePreferredCodes.get(0);
        writeCurrentActiveInputCode(context, code2);
        return code2;
    }

    public static BrailleLanguages.Code readCurrentActiveOutputCodeAndCorrect(Context context) {
        BrailleLanguages.Code code = (BrailleLanguages.Code) AppCompatDelegate.Api33Impl.valueOfSafe(getSharedPreferences$ar$ds(context).getString(context.getString(R.string.pref_bd_output_code), BrailleLanguages.getDefaultCode(context).name()), BrailleLanguages.getDefaultCode(context));
        List readAvailablePreferredCodes = readAvailablePreferredCodes(context);
        if (!readAvailablePreferredCodes.contains(code)) {
            code = (BrailleLanguages.Code) readAvailablePreferredCodes.get(0);
        }
        writeCurrentActiveOutputCode(context, code);
        return code;
    }

    public static TouchDots readLayoutMode(Context context) {
        String name;
        SharedPreferences sharedPreferences$ar$ds = getSharedPreferences$ar$ds(context);
        String string = context.getString(R.string.pref_brailleime_layout_mode);
        if (AppCompatDelegateImpl.Api21Impl.isPhoneSizedDevice(context.getResources())) {
            name = TouchDots.AUTO_DETECT.name();
        } else {
            name = TouchDots.TABLETOP.name();
        }
        return (TouchDots) AppCompatDelegate.Api33Impl.valueOfSafe(sharedPreferences$ar$ds.getString(string, name), TouchDots.AUTO_DETECT);
    }

    private static List readPreferredCodes(Context context) {
        Set<String> stringSet = getSharedPreferences$ar$ds(context).getStringSet(context.getString(R.string.pref_brailleime_translator_codes_preferred), RegularImmutableSet.EMPTY);
        if (!stringSet.isEmpty()) {
            List list = (List) Collection.EL.stream(stringSet).map(new BtConnectManager$$ExternalSyntheticLambda1(9)).filter(new BrailleUserPreferences$$ExternalSyntheticLambda2(0)).collect(Collectors.toList());
            if (list.isEmpty()) {
                return ImmutableList.of((Object) BrailleLanguages.getDefaultCode(context));
            }
            return list;
        }
        return ImmutableList.of((Object) BrailleLanguages.getDefaultCode(context));
    }

    public static boolean readReverseDotsMode(Context context) {
        return getSharedPreferences$ar$ds(context).getBoolean(context.getString(R.string.pref_brailleime_reverse_dots_mode), false);
    }

    public static boolean readReversePanningButtons(Context context) {
        return getSharedPreferences$ar$ds(context).getBoolean(context.getString(R.string.pref_bd_reverse_panning_buttons), false);
    }

    public static int readSwitchContractedCount(Context context) {
        return getSharedPreferences$ar$ds(context).getInt(context.getString(R.string.pref_bd_switch_contracted_count_key), 0);
    }

    public static String readTimedMessageDurationFraction(Context context) {
        return getSharedPreferences$ar$ds(context).getString(context.getString(R.string.pref_bd_timed_message_duration_fraction_key), "1");
    }

    public static TranslatorFactory readTranslatorFactory(Context context) {
        try {
            return new GoogleBrailleTranslatorFactory((TranslatorFactory) Class.forName(TranslatorFactory.class.getPackage().getName() + "." + ContextDataProvider.toLowerCase("LibLouis") + ".LibLouis").getDeclaredConstructor(null).newInstance(null), new ApplicationModule(context));
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not find TranslatorFactory for name ".concat("LibLouis"), e);
        }
    }

    public static void removePreferredCode(Context context, BrailleLanguages.Code code) {
        ArrayList arrayList = new ArrayList(readPreferredCodes(context));
        if (arrayList.remove(code)) {
            writePreferredCodes(context, arrayList);
        }
    }

    public static boolean shouldLaunchTutorial(Context context) {
        return getSharedPreferences$ar$ds(context).getBoolean(context.getString(R.string.pref_brailleime_auto_launch_tutorial), true);
    }

    public static void writeAutoScrollDuration(Context context, int i) {
        getSharedPreferences$ar$ds(context).edit().putInt(context.getString(R.string.pref_bd_auto_scroll_duration_key), ContextDataProvider.constrainToRange(i, 500, 20000)).apply();
    }

    public static void writeContractedMode(Context context, boolean z) {
        getSharedPreferences$ar$ds(context).edit().putBoolean(context.getString(R.string.pref_braille_contracted_mode), z).apply();
    }

    public static void writeCurrentActiveInputCode(Context context, BrailleLanguages.Code code) {
        getSharedPreferences$ar$ds(context).edit().putString(context.getString(R.string.pref_brailleime_translator_code), code.name()).apply();
    }

    public static void writeCurrentActiveOutputCode(Context context, BrailleLanguages.Code code) {
        getSharedPreferences$ar$ds(context).edit().putString(context.getString(R.string.pref_bd_output_code), code.name()).apply();
    }

    public static void writePreferredCodes(Context context, List list) {
        HashSet hashSet = new HashSet();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            hashSet.add(((BrailleLanguages.Code) it.next()).name());
        }
        getSharedPreferences$ar$ds(context).edit().putStringSet(context.getString(R.string.pref_brailleime_translator_codes_preferred), hashSet).apply();
    }

    public static void writeSwitchContactedCount(Context context) {
        getSharedPreferences$ar$ds(context).edit().putInt(context.getString(R.string.pref_bd_switch_contracted_count_key), Math.min(6, readSwitchContractedCount(context) + 1)).apply();
    }
}
