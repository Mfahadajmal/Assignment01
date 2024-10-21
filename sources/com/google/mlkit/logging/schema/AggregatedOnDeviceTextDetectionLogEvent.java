package com.google.mlkit.logging.schema;

import _COROUTINE._BOUNDARY;
import android.text.TextUtils;
import androidx.navigation.NavDeepLink;
import com.google.android.accessibility.utils.labeling.Label;
import com.google.android.libraries.surveys.internal.event.SurveyInternalEvent;
import com.google.android.libraries.surveys.internal.model.SurveyStyle;
import io.grpc.Attributes;
import io.grpc.NameResolver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AggregatedOnDeviceTextDetectionLogEvent {
    public Object AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount;
    public Object AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats;
    public Object AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LogEventKey {
        public MLKitEnum$ErrorCode errorCode;
        private Boolean hasResult = null;
        private ImageInfo imageInfo = null;
        public Boolean isColdCall;
        public OnDeviceTextRecognizerOptions recognizerOptions;

        public LogEventKey(AggregatedOnDeviceTextDetectionLogEvent aggregatedOnDeviceTextDetectionLogEvent) {
            this.errorCode = (MLKitEnum$ErrorCode) aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount;
            this.isColdCall = (Boolean) aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats;
            this.recognizerOptions = (OnDeviceTextRecognizerOptions) aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof LogEventKey)) {
                return false;
            }
            LogEventKey logEventKey = (LogEventKey) obj;
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.errorCode, logEventKey.errorCode)) {
                Boolean bool = logEventKey.hasResult;
                if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(null, null) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.isColdCall, logEventKey.isColdCall)) {
                    ImageInfo imageInfo = logEventKey.imageInfo;
                    if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(null, null) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.recognizerOptions, logEventKey.recognizerOptions)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.errorCode, null, this.isColdCall, null, this.recognizerOptions});
        }
    }

    public AggregatedOnDeviceTextDetectionLogEvent() {
    }

    public final NavDeepLink build() {
        Object obj = this.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey;
        return new NavDeepLink((String) obj, (String) this.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount, (String) this.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats);
    }

    public final void setSessionId$ar$ds(String str) {
        if (str != null) {
            this.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount = str;
            return;
        }
        throw new NullPointerException("Null sessionId");
    }

    public final void setSurveyStyle$ar$ds(SurveyStyle surveyStyle) {
        if (surveyStyle != null) {
            this.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats = surveyStyle;
            return;
        }
        throw new NullPointerException("Null surveyStyle");
    }

    public final void setTriggerId$ar$ds(String str) {
        if (str != null) {
            this.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey = str;
            return;
        }
        throw new NullPointerException("Null triggerId");
    }

    public AggregatedOnDeviceTextDetectionLogEvent(AggregatedOnDeviceTextDetectionLogEvent aggregatedOnDeviceTextDetectionLogEvent) {
        this.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey = aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats;
        this.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount = aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount;
        this.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats = aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey;
    }

    /* renamed from: build, reason: collision with other method in class */
    public final SurveyInternalEvent m220build() {
        Object obj;
        Object obj2;
        Object obj3 = this.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount;
        if (obj3 != null && (obj = this.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey) != null && (obj2 = this.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats) != null) {
            return new SurveyInternalEvent((String) obj3, (String) obj, (SurveyStyle) obj2);
        }
        StringBuilder sb = new StringBuilder();
        if (this.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount == null) {
            sb.append(" sessionId");
        }
        if (this.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey == null) {
            sb.append(" triggerId");
        }
        if (this.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats == null) {
            sb.append(" surveyStyle");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }

    public AggregatedOnDeviceTextDetectionLogEvent(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        this();
    }

    public AggregatedOnDeviceTextDetectionLogEvent(byte[] bArr, char[] cArr, byte[] bArr2) {
        this();
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.util.List, java.lang.Object] */
    public AggregatedOnDeviceTextDetectionLogEvent(List list, List list2) {
        this.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey = new ArrayList();
        this.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats = new ArrayList();
        this.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount = new ArrayList();
        if (list.isEmpty()) {
            if (list2 != null) {
                this.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey.addAll(list2);
                return;
            }
            return;
        }
        if (list2 == null || list2.isEmpty()) {
            return;
        }
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            final Label label = (Label) it.next();
            if (label != null) {
                hashMap.put(new Object(label) { // from class: com.google.android.accessibility.talkback.labeling.LabelSeparator$LabelWrapper
                    private final Label label;

                    {
                        this.label = label;
                    }

                    public final boolean equals(Object obj) {
                        if (obj != null && (obj instanceof LabelSeparator$LabelWrapper)) {
                            LabelSeparator$LabelWrapper labelSeparator$LabelWrapper = (LabelSeparator$LabelWrapper) obj;
                            if (TextUtils.equals(this.label.mPackageName, labelSeparator$LabelWrapper.label.mPackageName)) {
                                if (TextUtils.equals(this.label.mPackageSignature, labelSeparator$LabelWrapper.label.mPackageSignature)) {
                                    if (TextUtils.equals(this.label.mViewName, labelSeparator$LabelWrapper.label.mViewName)) {
                                        if (TextUtils.equals(this.label.mLocale, labelSeparator$LabelWrapper.label.mLocale)) {
                                            if (this.label.mPackageVersion == labelSeparator$LabelWrapper.label.mPackageVersion) {
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        return false;
                    }

                    public final int hashCode() {
                        int i;
                        String str = this.label.mPackageName;
                        if (str != null) {
                            i = str.hashCode();
                        } else {
                            i = 0;
                        }
                        String str2 = this.label.mPackageSignature;
                        int hashCode = i + 17 + this.label.mPackageSignature.hashCode();
                        String str3 = this.label.mViewName;
                        int hashCode2 = hashCode + this.label.mViewName.hashCode();
                        String str4 = this.label.mLocale;
                        int hashCode3 = hashCode2 + this.label.mLocale.hashCode();
                        return hashCode3 + (hashCode3 * 31) + this.label.mPackageVersion;
                    }
                }, label);
            }
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            final Label label2 = (Label) it2.next();
            if (label2 != null) {
                Label label3 = (Label) hashMap.get(new Object(label2) { // from class: com.google.android.accessibility.talkback.labeling.LabelSeparator$LabelWrapper
                    private final Label label;

                    {
                        this.label = label2;
                    }

                    public final boolean equals(Object obj) {
                        if (obj != null && (obj instanceof LabelSeparator$LabelWrapper)) {
                            LabelSeparator$LabelWrapper labelSeparator$LabelWrapper = (LabelSeparator$LabelWrapper) obj;
                            if (TextUtils.equals(this.label.mPackageName, labelSeparator$LabelWrapper.label.mPackageName)) {
                                if (TextUtils.equals(this.label.mPackageSignature, labelSeparator$LabelWrapper.label.mPackageSignature)) {
                                    if (TextUtils.equals(this.label.mViewName, labelSeparator$LabelWrapper.label.mViewName)) {
                                        if (TextUtils.equals(this.label.mLocale, labelSeparator$LabelWrapper.label.mLocale)) {
                                            if (this.label.mPackageVersion == labelSeparator$LabelWrapper.label.mPackageVersion) {
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        return false;
                    }

                    public final int hashCode() {
                        int i;
                        String str = this.label.mPackageName;
                        if (str != null) {
                            i = str.hashCode();
                        } else {
                            i = 0;
                        }
                        String str2 = this.label.mPackageSignature;
                        int hashCode = i + 17 + this.label.mPackageSignature.hashCode();
                        String str3 = this.label.mViewName;
                        int hashCode2 = hashCode + this.label.mViewName.hashCode();
                        String str4 = this.label.mLocale;
                        int hashCode3 = hashCode2 + this.label.mLocale.hashCode();
                        return hashCode3 + (hashCode3 * 31) + this.label.mPackageVersion;
                    }
                });
                if (label3 != null) {
                    this.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount.add(label3);
                    this.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats.add(label2);
                } else {
                    this.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey.add(label2);
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.List, java.lang.Object] */
    /* renamed from: build, reason: collision with other method in class */
    public final NameResolver.ResolutionResult m221build() {
        return new NameResolver.ResolutionResult(this.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey, (Attributes) this.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount, (NameResolver.ConfigOrError) this.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats);
    }

    public AggregatedOnDeviceTextDetectionLogEvent(byte[] bArr, byte[] bArr2) {
        this.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey = Collections.emptyList();
        this.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount = Attributes.EMPTY;
    }
}
