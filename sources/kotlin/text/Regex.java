package kotlin.text;

import java.io.Serializable;
import java.util.regex.Pattern;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Regex implements Serializable {
    public final Pattern nativePattern;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Serialized implements Serializable {
        private static final long serialVersionUID = 0;
        private final int flags;
        private final String pattern;

        public Serialized(String str, int i) {
            this.pattern = str;
            this.flags = i;
        }

        private final Object readResolve() {
            Pattern compile = Pattern.compile(this.pattern, this.flags);
            compile.getClass();
            return new Regex(compile);
        }
    }

    public Regex(Pattern pattern) {
        this.nativePattern = pattern;
    }

    private final Object writeReplace() {
        String pattern = this.nativePattern.pattern();
        pattern.getClass();
        return new Serialized(pattern, this.nativePattern.flags());
    }

    public final String toString() {
        String pattern = this.nativePattern.toString();
        pattern.getClass();
        return pattern;
    }
}
