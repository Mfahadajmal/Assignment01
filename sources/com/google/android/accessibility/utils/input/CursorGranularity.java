package com.google.android.accessibility.utils.input;

import com.google.android.marvin.talkback.R;
import java.util.Arrays;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum CursorGranularity {
    DEFAULT(R.string.granularity_default, 1, 0),
    CHARACTER(R.string.granularity_character, 2, 1),
    WORD(R.string.granularity_word, 3, 2),
    LINE(R.string.granularity_line, 4, 4),
    PARAGRAPH(R.string.granularity_paragraph, 5, 8),
    WEB_HEADING(R.string.granularity_web_heading, 6, 0),
    WEB_LINK(R.string.granularity_web_link, 7, 0),
    WEB_LIST(R.string.granularity_web_list, 8, 0),
    WEB_CONTROL(R.string.granularity_web_control, 9, 0),
    HEADING(R.string.granularity_native_heading, 10, 0),
    CONTROL(R.string.granularity_native_control, 11, 0),
    LINK(R.string.granularity_native_link, 12, 0),
    WEB_LANDMARK(R.string.granularity_web_landmark, 13, 0),
    WINDOWS(R.string.granularity_window, 14, 0),
    CONTAINER(R.string.granularity_container, 15, 0);

    public final int id;
    public final int resourceId;
    public final int value;

    CursorGranularity(int i, int i2, int i3) {
        this.resourceId = i;
        this.id = i2;
        this.value = i3;
    }

    public static void extractFromMask(int i, boolean z, String[] strArr, List list) {
        list.clear();
        for (CursorGranularity cursorGranularity : values()) {
            int i2 = cursorGranularity.value;
            if (i2 != 0 && (i & i2) == i2) {
                list.add(cursorGranularity);
            }
        }
        if (z) {
            if (strArr == null) {
                list.add(WEB_HEADING);
                list.add(WEB_CONTROL);
                list.add(WEB_LIST);
            } else {
                List asList = Arrays.asList(strArr);
                if (asList.contains("HEADING")) {
                    list.add(WEB_HEADING);
                }
                if (asList.contains("CONTROL")) {
                    list.add(WEB_CONTROL);
                }
                if (asList.contains("LINK")) {
                    list.add(WEB_LINK);
                }
            }
        } else {
            list.add(HEADING);
            list.add(CONTROL);
            list.add(LINK);
        }
        list.add(WINDOWS);
        list.add(CONTAINER);
        list.add(DEFAULT);
    }

    public final boolean isMicroGranularity() {
        int i = this.resourceId;
        if (i != R.string.granularity_character && i != R.string.granularity_word && i != R.string.granularity_line && i != R.string.granularity_paragraph) {
            return false;
        }
        return true;
    }

    public final boolean isNativeMacroGranularity() {
        int i = this.resourceId;
        if (i != R.string.granularity_native_heading && i != R.string.granularity_native_control && i != R.string.granularity_native_link) {
            return false;
        }
        return true;
    }

    public final boolean isWebGranularity() {
        int i = this.resourceId;
        if (i != R.string.granularity_web_heading && i != R.string.granularity_web_link && i != R.string.granularity_web_list && i != R.string.granularity_web_control && i != R.string.granularity_web_landmark) {
            return false;
        }
        return true;
    }
}
