package com.google.android.accessibility.braille.common.translate;

import android.content.res.Resources;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleTranslateUtils {
    public static final BrailleCharacter DOTS3456 = new BrailleCharacter(3, 4, 5, 6);
    public static final BrailleCharacter DOTS45 = new BrailleCharacter(4, 5);
    public static final BrailleCharacter DOTS46 = new BrailleCharacter(4, 6);
    public static final BrailleCharacter DOTS456 = new BrailleCharacter(4, 5, 6);
    public static final BrailleCharacter DOT6 = new BrailleCharacter(6);

    public static String getDotsText(Resources resources, BrailleCharacter brailleCharacter) {
        return resources.getQuantityString(R.plurals.braille_dots, brailleCharacter.getOnCount(), brailleCharacter.toString().replace("", " ").trim());
    }

    public static boolean isPronounceable(String str) {
        if (str.length() != 1 || Character.getType(str.charAt(0)) != 18) {
            return true;
        }
        return false;
    }
}
