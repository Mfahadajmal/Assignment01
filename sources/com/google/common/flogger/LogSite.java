package com.google.common.flogger;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class LogSite implements LogSiteKey {
    public static final LogSite INVALID = new LogSite() { // from class: com.google.common.flogger.LogSite.1
        @Override // com.google.common.flogger.LogSite
        public final String getClassName() {
            return "<unknown class>";
        }

        @Override // com.google.common.flogger.LogSite
        public final String getFileName() {
            return null;
        }

        @Override // com.google.common.flogger.LogSite
        public final int getLineNumber() {
            return 0;
        }

        @Override // com.google.common.flogger.LogSite
        public final String getMethodName() {
            return "<unknown method>";
        }
    };

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class InjectedLogSite extends LogSite {
        private final int encodedLineNumber;
        private int hashcode = 0;
        private final String internalClassName;
        private final String methodName;
        private final String sourceFileName;

        public InjectedLogSite(String str, String str2, int i, String str3) {
            this.internalClassName = str;
            this.methodName = str2;
            this.encodedLineNumber = i;
            this.sourceFileName = str3;
        }

        public final boolean equals(Object obj) {
            if (obj instanceof InjectedLogSite) {
                InjectedLogSite injectedLogSite = (InjectedLogSite) obj;
                if (this.methodName.equals(injectedLogSite.methodName) && this.encodedLineNumber == injectedLogSite.encodedLineNumber && getClassName().equals(injectedLogSite.getClassName())) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.common.flogger.LogSite
        public final String getClassName() {
            return this.internalClassName.replace('/', '.');
        }

        @Override // com.google.common.flogger.LogSite
        public final String getFileName() {
            return this.sourceFileName;
        }

        @Override // com.google.common.flogger.LogSite
        public final int getLineNumber() {
            return (char) this.encodedLineNumber;
        }

        @Override // com.google.common.flogger.LogSite
        public final String getMethodName() {
            return this.methodName;
        }

        public final int hashCode() {
            int i = this.hashcode;
            if (i == 0) {
                int hashCode = ((this.methodName.hashCode() + 4867) * 31) + this.encodedLineNumber;
                this.hashcode = hashCode;
                return hashCode;
            }
            return i;
        }
    }

    public abstract String getClassName();

    public abstract String getFileName();

    public abstract int getLineNumber();

    public abstract String getMethodName();

    public final String toString() {
        StringBuilder sb = new StringBuilder("LogSite{ class=");
        sb.append(getClassName());
        sb.append(", method=");
        sb.append(getMethodName());
        sb.append(", line=");
        sb.append(getLineNumber());
        if (getFileName() != null) {
            sb.append(", file=");
            sb.append(getFileName());
        }
        sb.append(" }");
        return sb.toString();
    }
}
