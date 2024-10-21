package com.google.android.accessibility.talkback.logging;

import android.os.SystemClock;
import com.google.android.accessibility.talkback.trainingcommon.PageConfig;
import com.google.android.accessibility.talkback.trainingcommon.TrainingMetricStore$Type;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.flogger.context.ContextDataProvider;
import j$.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TrainingMetricHolder {
    public boolean countingCompleteDuration;
    public boolean hasCompletedTraining;
    public boolean hasStartedTraining;
    public long startTrainingTime;
    public final TrainingMetricStore$Type trainingType;
    public final Map pageEnterTimeMap = new HashMap();
    public final Map pageStayingDurationMap = new HashMap();
    public final Set completedPages = new HashSet();
    public Duration completedTrainingTime = Duration.ZERO;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TrainingMetric {
        public final ImmutableSet completedPages;
        public final ImmutableMap stayingPageDuration;
        public final Duration trainingCompletedDuration;
        public final boolean trainingStarted;
        public final TrainingMetricStore$Type type;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder {
            public ImmutableSet completedPages;
            public byte set$0;
            public ImmutableMap stayingPageDuration;
            public Duration trainingCompletedDuration;
            public boolean trainingStarted;
            public TrainingMetricStore$Type type;

            public Builder() {
            }

            public final void setCompletedPages$ar$ds(ImmutableSet immutableSet) {
                if (immutableSet != null) {
                    this.completedPages = immutableSet;
                    return;
                }
                throw new NullPointerException("Null completedPages");
            }

            public final void setStayingPageDuration$ar$ds(ImmutableMap immutableMap) {
                if (immutableMap != null) {
                    this.stayingPageDuration = immutableMap;
                    return;
                }
                throw new NullPointerException("Null stayingPageDuration");
            }

            public final void setTrainingCompletedDuration$ar$ds(Duration duration) {
                if (duration != null) {
                    this.trainingCompletedDuration = duration;
                    return;
                }
                throw new NullPointerException("Null trainingCompletedDuration");
            }

            public Builder(byte[] bArr) {
                this();
            }
        }

        public TrainingMetric() {
        }

        public final ImmutableSet completedPages() {
            return this.completedPages;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof TrainingMetric) {
                TrainingMetric trainingMetric = (TrainingMetric) obj;
                if (this.type.equals(trainingMetric.type()) && this.trainingStarted == trainingMetric.trainingStarted() && this.trainingCompletedDuration.equals(trainingMetric.trainingCompletedDuration()) && ContextDataProvider.equalsImpl(this.stayingPageDuration, trainingMetric.stayingPageDuration()) && this.completedPages.equals(trainingMetric.completedPages())) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            int i;
            int hashCode = this.type.hashCode() ^ 1000003;
            if (true != this.trainingStarted) {
                i = 1237;
            } else {
                i = 1231;
            }
            return (((((((hashCode * 1000003) ^ i) * 1000003) ^ this.trainingCompletedDuration.hashCode()) * 1000003) ^ this.stayingPageDuration.hashCode()) * 1000003) ^ this.completedPages.hashCode();
        }

        public final ImmutableMap stayingPageDuration() {
            return this.stayingPageDuration;
        }

        public final String toString() {
            return "TrainingMetric: ".concat(String.valueOf(StringBuilderUtils.joinFields(StringBuilderUtils.optionalField("type", type()), StringBuilderUtils.optionalTag("trainingStarted", trainingStarted()), StringBuilderUtils.optionalField("trainingCompletedDuration", trainingCompletedDuration()), StringBuilderUtils.optionalField("stayingPageDuration", stayingPageDuration()), StringBuilderUtils.optionalField("completedPages", completedPages()))));
        }

        public final Duration trainingCompletedDuration() {
            return this.trainingCompletedDuration;
        }

        public final boolean trainingStarted() {
            return this.trainingStarted;
        }

        public final TrainingMetricStore$Type type() {
            return this.type;
        }

        public TrainingMetric(TrainingMetricStore$Type trainingMetricStore$Type, boolean z, Duration duration, ImmutableMap immutableMap, ImmutableSet immutableSet) {
            this();
            this.type = trainingMetricStore$Type;
            this.trainingStarted = z;
            this.trainingCompletedDuration = duration;
            this.stayingPageDuration = immutableMap;
            this.completedPages = immutableSet;
        }
    }

    public TrainingMetricHolder(TrainingMetricStore$Type trainingMetricStore$Type) {
        this.trainingType = trainingMetricStore$Type;
    }

    private final boolean isOnboarding() {
        if (this.trainingType == TrainingMetricStore$Type.ONBOARDING) {
            return true;
        }
        return false;
    }

    public final void onTrainingPageEntered(PageConfig.PageId pageId) {
        if (pageId != null) {
            this.pageEnterTimeMap.put(pageId, Long.valueOf(SystemClock.uptimeMillis()));
            if (isOnboarding()) {
                if (pageId != PageConfig.PageId.PAGE_ID_UPDATE_WELCOME) {
                    return;
                }
            } else if (pageId != PageConfig.PageId.PAGE_ID_WELCOME_TO_TALKBACK) {
                return;
            }
            if (!this.countingCompleteDuration) {
                this.countingCompleteDuration = true;
                this.hasStartedTraining = true;
                this.startTrainingTime = SystemClock.uptimeMillis();
            }
        }
    }

    public final void onTrainingPageLeft(PageConfig.PageId pageId) {
        Long l;
        if (pageId != null && (l = (Long) this.pageEnterTimeMap.get(pageId)) != null) {
            Duration duration = (Duration) this.pageStayingDurationMap.get(pageId);
            Duration ofMillis = Duration.ofMillis(SystemClock.uptimeMillis() - l.longValue());
            if (duration != null) {
                ofMillis = ofMillis.plus(duration);
            }
            this.pageStayingDurationMap.put(pageId, ofMillis);
            if (isOnboarding()) {
                if (pageId != PageConfig.PageId.PAGE_ID_NEW_BRAILLE_SHORTCUTS) {
                    return;
                }
            } else if (pageId != PageConfig.PageId.PAGE_ID_MENUS && pageId != PageConfig.PageId.PAGE_ID_MENUS_PRE_R) {
                return;
            }
            if (this.countingCompleteDuration) {
                this.hasCompletedTraining = true;
                this.completedTrainingTime = this.completedTrainingTime.plus(Duration.ofMillis(SystemClock.uptimeMillis() - this.startTrainingTime));
                this.countingCompleteDuration = false;
            }
        }
    }
}
