package com.google.common.base;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.flogger.context.ContextDataProvider;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Splitter {
    public final boolean omitEmptyStrings;
    private final Strategy strategy;
    public final CharMatcher trimmer;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.common.base.Splitter$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass2 implements Strategy {
        final /* synthetic */ Object Splitter$2$ar$val$separator;
        private final /* synthetic */ int switching_field;

        public AnonymousClass2(Object obj, int i) {
            this.switching_field = i;
            this.Splitter$2$ar$val$separator = obj;
        }

        @Override // com.google.common.base.Splitter.Strategy
        public final /* synthetic */ Iterator iterator(final Splitter splitter, final CharSequence charSequence) {
            if (this.switching_field != 0) {
                return new AbstractIterator(splitter, charSequence) { // from class: com.google.common.base.Splitter$1$1
                    @Override // com.google.common.base.AbstractIterator
                    public final int separatorEnd(int i) {
                        return i + 1;
                    }

                    @Override // com.google.common.base.AbstractIterator
                    public final int separatorStart(int i) {
                        CharSequence charSequence2 = this.toSplit;
                        int length = charSequence2.length();
                        ContextDataProvider.checkPositionIndex$ar$ds(i, length);
                        while (i < length) {
                            Splitter.AnonymousClass2 anonymousClass2 = Splitter.AnonymousClass2.this;
                            if (!((CharMatcher) anonymousClass2.Splitter$2$ar$val$separator).matches(charSequence2.charAt(i))) {
                                i++;
                            } else {
                                return i;
                            }
                        }
                        return -1;
                    }
                };
            }
            return new AbstractIterator(splitter, charSequence) { // from class: com.google.common.base.Splitter.2.1
                @Override // com.google.common.base.AbstractIterator
                public final int separatorEnd(int i) {
                    return i + ((String) AnonymousClass2.this.Splitter$2$ar$val$separator).length();
                }

                /* JADX WARN: Code restructure failed: missing block: B:9:0x002a, code lost:
                
                    r7 = r7 + 1;
                 */
                @Override // com.google.common.base.AbstractIterator
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final int separatorStart(int r7) {
                    /*
                        r6 = this;
                        com.google.common.base.Splitter$2 r0 = com.google.common.base.Splitter.AnonymousClass2.this
                        java.lang.Object r0 = r0.Splitter$2$ar$val$separator
                        java.lang.String r0 = (java.lang.String) r0
                        int r0 = r0.length()
                        java.lang.CharSequence r1 = r6.toSplit
                        int r1 = r1.length()
                        int r1 = r1 - r0
                    L11:
                        if (r7 > r1) goto L31
                        r2 = 0
                    L14:
                        if (r2 >= r0) goto L30
                        java.lang.CharSequence r3 = r6.toSplit
                        int r4 = r2 + r7
                        com.google.common.base.Splitter$2 r5 = com.google.common.base.Splitter.AnonymousClass2.this
                        java.lang.Object r5 = r5.Splitter$2$ar$val$separator
                        char r3 = r3.charAt(r4)
                        java.lang.String r5 = (java.lang.String) r5
                        char r4 = r5.charAt(r2)
                        if (r3 == r4) goto L2d
                        int r7 = r7 + 1
                        goto L11
                    L2d:
                        int r2 = r2 + 1
                        goto L14
                    L30:
                        return r7
                    L31:
                        r7 = -1
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.Splitter.AnonymousClass2.AnonymousClass1.separatorStart(int):int");
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Strategy {
        Iterator iterator(Splitter splitter, CharSequence charSequence);
    }

    private Splitter(Strategy strategy, boolean z, CharMatcher charMatcher) {
        this.strategy = strategy;
        this.omitEmptyStrings = z;
        this.trimmer = charMatcher;
    }

    public static Splitter on(char c) {
        return new Splitter(new AnonymousClass2(new CharMatcher.Is(c), 1));
    }

    public final Splitter omitEmptyStrings() {
        return new Splitter(this.strategy, true, this.trimmer);
    }

    public final Iterable split(final CharSequence charSequence) {
        charSequence.getClass();
        return new Iterable(this) { // from class: com.google.common.base.Splitter.5
            final /* synthetic */ Splitter this$0;

            {
                this.this$0 = this;
            }

            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return this.this$0.splittingIterator(charSequence);
            }

            public final String toString() {
                Joiner joiner = new Joiner(", ");
                StringBuilder sb = new StringBuilder();
                sb.append('[');
                joiner.appendTo$ar$ds(sb, iterator());
                sb.append(']');
                return sb.toString();
            }
        };
    }

    public final List splitToList(CharSequence charSequence) {
        charSequence.getClass();
        Iterator splittingIterator = splittingIterator(charSequence);
        ArrayList arrayList = new ArrayList();
        while (splittingIterator.hasNext()) {
            arrayList.add((String) splittingIterator.next());
        }
        return DesugarCollections.unmodifiableList(arrayList);
    }

    public final Iterator splittingIterator(CharSequence charSequence) {
        return this.strategy.iterator(this, charSequence);
    }

    private Splitter(Strategy strategy) {
        this(strategy, false, CharMatcher.None.INSTANCE);
    }

    public static Splitter on(String str) {
        ContextDataProvider.checkArgument(str.length() != 0, (Object) "The separator may not be the empty string.");
        return str.length() == 1 ? on(str.charAt(0)) : new Splitter(new AnonymousClass2(str, 0));
    }
}
