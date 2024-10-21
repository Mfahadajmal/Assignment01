package com.google.android.accessibility.talkback.preference.search;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import com.android.talkback.TalkBackPreferencesActivity;
import com.google.android.accessibility.talkback.preference.base.SelectorMenuFragment;
import com.google.android.accessibility.talkback.preference.base.VerbosityPrefFragment;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.marvin.talkback.R;
import com.google.android.settings.search.SearchIndexablesContract;
import com.google.android.settings.search.SearchIndexablesProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TalkBackSearchIndexablesProvider extends SearchIndexablesProvider {
    public static final String SETTING_SEARCH_READING_CONTROL_PUNCTUATION = "com.google.android.accessibility.talkback.preference.search.customize.reading.control.punctuation.ACTION";
    public static final String SETTING_SEARCH_VERBOSITY_PUNCTUATION = "com.google.android.accessibility.talkback.preference.search.verbosity.punctuation.ACTION";

    public static void assignToFragmentFromSearch(Intent intent) {
        char c;
        if (!FormFactorUtils.getInstance().isAndroidWear && !FormFactorUtils.getInstance().isAndroidAuto && !FormFactorUtils.getInstance().isAndroidTv) {
            String action = intent.getAction();
            int hashCode = action.hashCode();
            if (hashCode != -1144506673) {
                if (hashCode == 1726007988 && action.equals(SETTING_SEARCH_VERBOSITY_PUNCTUATION)) {
                    c = 0;
                }
                c = 65535;
            } else {
                if (action.equals(SETTING_SEARCH_READING_CONTROL_PUNCTUATION)) {
                    c = 1;
                }
                c = 65535;
            }
            if (c != 0) {
                if (c == 1) {
                    intent.putExtra("FragmentName", SelectorMenuFragment.class.getName());
                    return;
                }
                return;
            }
            intent.putExtra("FragmentName", VerbosityPrefFragment.class.getName());
        }
    }

    public static boolean isFromSearchIndexablesContract(Intent intent) {
        char c;
        if (intent == null || intent.getAction() == null) {
            return false;
        }
        String action = intent.getAction();
        int hashCode = action.hashCode();
        if (hashCode != -1144506673) {
            if (hashCode == 1726007988 && action.equals(SETTING_SEARCH_VERBOSITY_PUNCTUATION)) {
                c = 0;
            }
            c = 65535;
        } else {
            if (action.equals(SETTING_SEARCH_READING_CONTROL_PUNCTUATION)) {
                c = 1;
            }
            c = 65535;
        }
        if (c != 0 && c != 1) {
            return false;
        }
        return true;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // com.google.android.settings.search.SearchIndexablesProvider
    public Cursor queryNonIndexableKeys(String[] strArr) {
        return null;
    }

    @Override // com.google.android.settings.search.SearchIndexablesProvider
    public Cursor queryRawData(String[] strArr) {
        MatrixCursor matrixCursor = new MatrixCursor(SearchIndexablesContract.INDEXABLES_RAW_COLUMNS);
        Context context = getContext();
        if (context != null && !FormFactorUtils.getInstance().isAndroidWear && !FormFactorUtils.getInstance().isAndroidAuto && !FormFactorUtils.getInstance().isAndroidTv) {
            Object[] objArr = new Object[16];
            objArr[12] = context.getString(R.string.pref_punctuation_key);
            objArr[1] = context.getString(R.string.title_pref_punctuation);
            objArr[9] = SETTING_SEARCH_VERBOSITY_PUNCTUATION;
            objArr[10] = context.getPackageName();
            objArr[11] = TalkBackPreferencesActivity.class.getName();
            objArr[6] = context.getString(R.string.search_summary_2_levels, context.getString(R.string.pref_verbosity_title));
            matrixCursor.addRow(objArr);
            Object[] objArr2 = new Object[16];
            objArr2[12] = context.getString(R.string.pref_selector_punctuation_key);
            objArr2[1] = context.getString(R.string.pref_punctuation_title);
            objArr2[9] = SETTING_SEARCH_READING_CONTROL_PUNCTUATION;
            objArr2[10] = context.getPackageName();
            objArr2[11] = TalkBackPreferencesActivity.class.getName();
            objArr2[6] = context.getString(R.string.search_summary_3_levels, context.getString(R.string.title_pref_manage_customize_menus), context.getString(R.string.title_pref_category_selector_menu));
            matrixCursor.addRow(objArr2);
        }
        return matrixCursor;
    }

    @Override // com.google.android.settings.search.SearchIndexablesProvider
    public Cursor queryXmlResources(String[] strArr) {
        return null;
    }
}
