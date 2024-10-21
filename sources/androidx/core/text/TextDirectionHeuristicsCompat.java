package androidx.core.text;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextDirectionHeuristicsCompat {
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_LTR;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_RTL;
    public static final TextDirectionHeuristicCompat LTR = new TextDirectionHeuristicInternal(null, false);
    public static final TextDirectionHeuristicCompat RTL = new TextDirectionHeuristicInternal(null, true);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class FirstStrong implements TextDirectionAlgorithm {
        static final FirstStrong INSTANCE = new FirstStrong();

        private FirstStrong() {
        }

        @Override // androidx.core.text.TextDirectionHeuristicsCompat.TextDirectionAlgorithm
        public final int checkRtl$ar$ds(CharSequence charSequence, int i) {
            int i2 = 2;
            for (int i3 = 0; i3 < i && i2 == 2; i3++) {
                byte directionality = Character.getDirectionality(charSequence.charAt(i3));
                if (directionality != 0) {
                    if (directionality != 1 && directionality != 2) {
                        switch (directionality) {
                            case 14:
                            case 15:
                                break;
                            case 16:
                            case 17:
                                break;
                            default:
                                i2 = 2;
                                break;
                        }
                    }
                    i2 = 0;
                }
                i2 = 1;
            }
            return i2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    interface TextDirectionAlgorithm {
        int checkRtl$ar$ds(CharSequence charSequence, int i);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    abstract class TextDirectionHeuristicImpl implements TextDirectionHeuristicCompat {
        private final TextDirectionAlgorithm mAlgorithm;

        public TextDirectionHeuristicImpl(TextDirectionAlgorithm textDirectionAlgorithm) {
            this.mAlgorithm = textDirectionAlgorithm;
        }

        protected abstract boolean defaultIsRtl();

        @Override // androidx.core.text.TextDirectionHeuristicCompat
        public final boolean isRtl$ar$ds(CharSequence charSequence, int i) {
            if (charSequence != null && i >= 0 && charSequence.length() - i >= 0) {
                TextDirectionAlgorithm textDirectionAlgorithm = this.mAlgorithm;
                if (textDirectionAlgorithm != null) {
                    int checkRtl$ar$ds = textDirectionAlgorithm.checkRtl$ar$ds(charSequence, i);
                    if (checkRtl$ar$ds == 0) {
                        return true;
                    }
                    if (checkRtl$ar$ds != 1) {
                        return defaultIsRtl();
                    }
                    return false;
                }
                return defaultIsRtl();
            }
            throw new IllegalArgumentException();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class TextDirectionHeuristicInternal extends TextDirectionHeuristicImpl {
        private final boolean mDefaultIsRtl;

        public TextDirectionHeuristicInternal(TextDirectionAlgorithm textDirectionAlgorithm, boolean z) {
            super(textDirectionAlgorithm);
            this.mDefaultIsRtl = z;
        }

        @Override // androidx.core.text.TextDirectionHeuristicsCompat.TextDirectionHeuristicImpl
        protected final boolean defaultIsRtl() {
            return this.mDefaultIsRtl;
        }
    }

    static {
        FirstStrong firstStrong = FirstStrong.INSTANCE;
        FIRSTSTRONG_LTR = new TextDirectionHeuristicInternal(firstStrong, false);
        FIRSTSTRONG_RTL = new TextDirectionHeuristicInternal(firstStrong, true);
    }
}
