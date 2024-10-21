package com.google.android.libraries.surveys.internal.datastore;

import com.google.android.libraries.surveys.PresentSurveyRequest;
import com.google.android.libraries.surveys.internal.model.SurveyDataImpl;
import com.google.android.libraries.surveys.internal.model.SurveyStyle;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SurveyDataStore {
    public static final SurveyDataStore instance = new SurveyDataStore();
    public final Map sessionIdToPsdMap = new HashMap();
    public final Map triggerIdToLastTriggerRequestTimeMsMap = new HashMap();
    public final Map sessionIdToSurveyDataMap = new HashMap();
    public final Map sessionIdToSurveyStyleMap = new HashMap();
    public final Map sessionIdToEventListenerMap = new HashMap();

    public final SurveyDataImpl getSurveyData(String str) {
        return (SurveyDataImpl) this.sessionIdToSurveyDataMap.get(str);
    }

    public final PresentSurveyRequest.SurveyEventListener getSurveyEventListener(String str) {
        return (PresentSurveyRequest.SurveyEventListener) this.sessionIdToEventListenerMap.get(str);
    }

    public final SurveyStyle getSurveyStyle(String str) {
        return (SurveyStyle) this.sessionIdToSurveyStyleMap.get(str);
    }
}
