package com.google.android.accessibility.selecttospeak.iterator;

import androidx.room.SharedSQLiteStatement$stmt$2;
import com.google.android.accessibility.selecttospeak.AccessibilityNodeInfoCompatWithVisibility;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SentenceIteratorFactory {
    public static final SentenceIteratorFactory INSTANCE = new SentenceIteratorFactory();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CharacterCounter {
        public int characterCount;

        public CharacterCounter() {
            this((char[]) null);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if ((obj instanceof CharacterCounter) && this.characterCount == ((CharacterCounter) obj).characterCount) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return this.characterCount;
        }

        public final String toString() {
            return "CharacterCounter(characterCount=" + this.characterCount + ")";
        }

        public CharacterCounter(byte[] bArr) {
            this.characterCount = 0;
        }

        public /* synthetic */ CharacterCounter(char[] cArr) {
            this((byte[]) null);
        }
    }

    private SentenceIteratorFactory() {
    }

    public static final SentenceIterator generateIterator(List list) {
        Paragraph paragraph;
        CharacterCounter characterCounter = new CharacterCounter((char[]) null);
        if (list.isEmpty()) {
            return new SentenceIterator(EmptyList.INSTANCE, characterCounter.characterCount);
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        Paragraph paragraph2 = null;
        while (it.hasNext()) {
            AccessibilityNodeInfoCompatWithVisibility accessibilityNodeInfoCompatWithVisibility = (AccessibilityNodeInfoCompatWithVisibility) it.next();
            List generateSentences$ar$ds = generateSentences$ar$ds(accessibilityNodeInfoCompatWithVisibility.getNodeDescription(), characterCounter, new SharedSQLiteStatement$stmt$2(accessibilityNodeInfoCompatWithVisibility, 11));
            Sentence sentence = (Sentence) OnDeviceLanguageIdentificationLogEvent.firstOrNull(generateSentences$ar$ds);
            if (sentence != null) {
                paragraph = sentence.paragraph;
            } else {
                paragraph = null;
            }
            arrayList.addAll(generateSentences$ar$ds);
            if (paragraph2 != null) {
                paragraph2.next = paragraph;
            }
            if (paragraph != null) {
                paragraph.prev = paragraph2;
            }
            paragraph2 = paragraph;
        }
        return new SentenceIterator(arrayList, characterCounter.characterCount);
    }

    public static final List generateSentences$ar$ds(CharSequence charSequence, CharacterCounter characterCounter, Function0 function0) {
        ArrayList arrayList = new ArrayList();
        if (charSequence == null) {
            charSequence = "";
        }
        Paragraph paragraph = new Paragraph(null);
        if (charSequence.length() != 0) {
            characterCounter.characterCount += charSequence.length();
            String obj = charSequence.toString();
            BreakIterator sentenceInstance = BreakIterator.getSentenceInstance();
            sentenceInstance.setText(obj);
            int first = sentenceInstance.first();
            Sentence sentence = null;
            for (int next = sentenceInstance.next(); next != -1; next = sentenceInstance.next()) {
                Sentence sentence2 = new Sentence((TextLocationFinder) function0.invoke(), first, charSequence.subSequence(first, next), paragraph);
                arrayList.add(sentence2);
                sentence = sentence2;
                first = next;
            }
            if (!arrayList.isEmpty()) {
                paragraph.head = (Sentence) OnDeviceLanguageIdentificationLogEvent.first(arrayList);
                paragraph.tail = sentence;
                return arrayList;
            }
        }
        return EmptyList.INSTANCE;
    }
}
