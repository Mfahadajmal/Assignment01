package com.google.android.libraries.performance.primes.metrics.crash.applicationexit;

import _COROUTINE._BOUNDARY;
import android.content.res.Configuration;
import androidx.preference.Preference;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ApplicationExitConfigurations implements MetricConfigurations {
    private final int enablement$ar$edu;
    private final String reportingProcessShortName;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public Object ApplicationExitConfigurations$Builder$ar$reportingProcessShortName;
        public int enablement$ar$edu;

        public Builder() {
        }

        public final Feedback.UniversalSearch build() {
            int i = this.enablement$ar$edu;
            if (i != 0) {
                return new Feedback.UniversalSearch(i, (Configuration) this.ApplicationExitConfigurations$Builder$ar$reportingProcessShortName);
            }
            throw new IllegalStateException("Missing required properties: action");
        }

        public final void reset() {
            this.ApplicationExitConfigurations$Builder$ar$reportingProcessShortName = null;
            this.enablement$ar$edu = 0;
        }

        public final void setAction$ar$edu$aa732c31_0$ar$ds(int i) {
            this.enablement$ar$edu = i;
        }

        public Builder(byte[] bArr) {
            this();
        }

        public Builder(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            this();
        }
    }

    public ApplicationExitConfigurations() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApplicationExitConfigurations)) {
            return false;
        }
        ApplicationExitConfigurations applicationExitConfigurations = (ApplicationExitConfigurations) obj;
        int i = this.enablement$ar$edu;
        int enablement$ar$edu = applicationExitConfigurations.getEnablement$ar$edu();
        if (i != 0) {
            if (enablement$ar$edu == 1 && this.reportingProcessShortName.equals(applicationExitConfigurations.getReportingProcessShortName())) {
                return true;
            }
            return false;
        }
        throw null;
    }

    public final int getEnablement$ar$edu() {
        return this.enablement$ar$edu;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final /* synthetic */ int getRateLimitPerSecond() {
        return Preference.DEFAULT_ORDER;
    }

    public final String getReportingProcessShortName() {
        return this.reportingProcessShortName;
    }

    public final int hashCode() {
        return ((_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.enablement$ar$edu) ^ 1000003) * 1000003) ^ this.reportingProcessShortName.hashCode();
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        if (getEnablement$ar$edu() == 1) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return "ApplicationExitConfigurations{enablement=" + MetricEnablement.toStringGenerated282cd02a285bcce0(this.enablement$ar$edu) + ", reportingProcessShortName=" + this.reportingProcessShortName + "}";
    }

    public ApplicationExitConfigurations(int i, String str) {
        this();
        this.enablement$ar$edu = 1;
        this.reportingProcessShortName = str;
    }
}
