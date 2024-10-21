package com.google.android.accessibility.braille.translate.liblouis;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.interfaces.BrailleWord;
import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LibLouisTranslatorUeb2 extends LibLouisTranslator {
    private static final BrailleCharacter CAPITALIZE = new BrailleCharacter(6);

    public LibLouisTranslatorUeb2(Context context) {
        super(context, "en-ueb-g2.ctb");
        addToBypassMap("5-2346-3-234", "there's");
        addToBypassMap("26", "enough");
        addToBypassMap("136-256-234-256", "u.s.");
        addToBypassMap("6-136-256-6-234-256", "U.S.");
        addToBypassMap("24-256-15-256", "i.e.");
        addToBypassMap("15-256-1245-256", "e.g.");
        addToBypassMap("1234-256-134-256", "p.m.");
        addToBypassMap("1-256-134-256", "a.m.");
        addToBypassMap("5-2346-3-123-123", "there'll");
        addToBypassMap("5-2346-3-145", "there'd");
        addToBypassMap("1234-256-1234-256", "p.p.");
        addToBypassMap("136-1345-234-145", "unsaid");
        addToBypassMap("6-1234-125-256-6-145-256", "Ph.D.");
        addToBypassMap("1-256-136-256", "a.u.");
        addToBypassMap("12-1246-123456-145-146-24-1235-15", "bedfordshire");
        addToBypassMap("123-24-3-123", "li'l");
        addToBypassMap("14-1-1345-3-34", "can'st");
        addToBypassMap("2346-1235-15-5-136", "thereunder");
        addToBypassMap("145-256-134-256", "d.m.");
        addToBypassMap("124-1235-123-24-123-13456", "friendlily");
        addToBypassMap("2345-36-134-135-12-24-123-15", "t-mobile");
        addToBypassMap("2345-36-6-134-135-12-24-123-15", "t-Mobile");
        addToBypassMap("6-2345-36-6-134-135-12-24-123-15", "T-Mobile");
        addToBypassMap("1346-36-1235-1-13456", "x-ray");
        addToBypassMap("1346-36-6-1235-1-13456", "x-Ray");
        addToBypassMap("6-1346-36-6-1235-1-13456", "X-Ray");
    }

    private final void addToBypassMap(String str, String str2) {
        this.bypassMap.put(BrailleWord.create(str), str2);
        BrailleCharacter brailleCharacter = CAPITALIZE;
        this.bypassMap.put(BrailleWord.create(String.valueOf(brailleCharacter) + "-" + str), _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_22(str2));
        this.bypassMap.put(BrailleWord.create(String.valueOf(brailleCharacter) + "-" + String.valueOf(brailleCharacter) + "-" + str), ContextDataProvider.toUpperCase(str2));
    }
}
