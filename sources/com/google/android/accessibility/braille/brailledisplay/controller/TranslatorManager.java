package com.google.android.accessibility.braille.brailledisplay.controller;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.common.translate.BrailleLanguages;
import com.google.android.accessibility.braille.translate.BrailleTranslator;
import com.google.android.accessibility.braille.translate.TranslatorFactory;
import com.google.android.marvin.talkback.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TranslatorManager implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final Context context;
    public volatile BrailleTranslator inputTranslator;
    public volatile BrailleTranslator outputTranslator;
    public final SharedPreferences sharedPreferences;
    private final List outputCodeChangedListeners = new ArrayList();
    public final List inputCodeChangedListeners = new ArrayList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface InputCodeChangedListener {
        void onInputCodeChanged();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OutputCodeChangedListener {
        void onOutputCodeChanged();
    }

    public TranslatorManager(Context context) {
        this.context = context;
        SharedPreferences sharedPreferences$ar$ds = BrailleUserPreferences.getSharedPreferences$ar$ds(context);
        this.sharedPreferences = sharedPreferences$ar$ds;
        sharedPreferences$ar$ds.registerOnSharedPreferenceChangeListener(this);
        updateInputTranslator();
        updateOutputTranslators();
    }

    private final void updateInputTranslator() {
        Context context = this.context;
        TranslatorFactory readTranslatorFactory = BrailleUserPreferences.readTranslatorFactory(context);
        BrailleLanguages.Code readCurrentActiveInputCodeAndCorrect = BrailleUserPreferences.readCurrentActiveInputCodeAndCorrect(context);
        BrailleTranslator create = readTranslatorFactory.create(this.context, readCurrentActiveInputCodeAndCorrect.name(), BrailleUserPreferences.readContractedMode(context));
        boolean z = !create.equals(this.inputTranslator);
        this.inputTranslator = create;
        if (z) {
            Iterator it = this.inputCodeChangedListeners.iterator();
            while (it.hasNext()) {
                ((InputCodeChangedListener) it.next()).onInputCodeChanged();
            }
        }
    }

    private final void updateOutputTranslators() {
        Context context = this.context;
        TranslatorFactory readTranslatorFactory = BrailleUserPreferences.readTranslatorFactory(context);
        BrailleLanguages.Code readCurrentActiveOutputCodeAndCorrect = BrailleUserPreferences.readCurrentActiveOutputCodeAndCorrect(context);
        BrailleTranslator create = readTranslatorFactory.create(this.context, readCurrentActiveOutputCodeAndCorrect.name(), BrailleUserPreferences.readContractedMode(context));
        boolean z = !create.equals(this.outputTranslator);
        this.outputTranslator = create;
        if (z) {
            Iterator it = this.outputCodeChangedListeners.iterator();
            while (it.hasNext()) {
                ((OutputCodeChangedListener) it.next()).onOutputCodeChanged();
            }
        }
    }

    public final void addOnOutputTablesChangedListener(OutputCodeChangedListener outputCodeChangedListener) {
        this.outputCodeChangedListeners.add(outputCodeChangedListener);
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (this.context.getString(R.string.pref_bd_output_code).equals(str)) {
            updateOutputTranslators();
            return;
        }
        if (this.context.getString(R.string.pref_brailleime_translator_code).equals(str)) {
            updateInputTranslator();
        } else if (this.context.getString(R.string.pref_braille_contracted_mode).equals(str)) {
            updateOutputTranslators();
            updateInputTranslator();
        }
    }

    public final void removeOnOutputTablesChangedListener(OutputCodeChangedListener outputCodeChangedListener) {
        this.outputCodeChangedListeners.remove(outputCodeChangedListener);
    }
}
