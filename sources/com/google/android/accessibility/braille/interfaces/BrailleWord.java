package com.google.android.accessibility.braille.interfaces;

import com.google.common.base.Splitter;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BrailleWord {
    public static final BrailleWord NEW_LINE = new BrailleWord("1246-123");
    private final List<BrailleCharacter> list;

    public BrailleWord() {
        this.list = new ArrayList();
    }

    public static BrailleWord create(String str) {
        return new BrailleWord(str);
    }

    public void append(BrailleCharacter brailleCharacter) {
        this.list.add(brailleCharacter);
    }

    public void clear() {
        this.list.clear();
    }

    public boolean contains(BrailleCharacter brailleCharacter) {
        return this.list.contains(brailleCharacter);
    }

    public boolean containsAny(Collection<BrailleCharacter> collection) {
        if (!Collections.disjoint(this.list, collection)) {
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BrailleWord)) {
            return false;
        }
        return this.list.equals(((BrailleWord) obj).list);
    }

    public BrailleCharacter get(int i) {
        return this.list.get(i);
    }

    public int hashCode() {
        return Objects.hashCode(this.list);
    }

    public int indexOf(BrailleWord brailleWord) {
        return Collections.indexOfSubList(this.list, brailleWord.list);
    }

    public void insert(int i, BrailleCharacter brailleCharacter) {
        this.list.add(i, brailleCharacter);
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public BrailleCharacter remove(int i) {
        return this.list.remove(i);
    }

    public void set(int i, BrailleCharacter brailleCharacter) {
        this.list.set(i, brailleCharacter);
    }

    public int size() {
        return this.list.size();
    }

    public BrailleWord subword(int i, int i2) {
        return new BrailleWord(this.list.subList(i, i2));
    }

    public byte[] toByteArray() {
        byte[] bArr = new byte[this.list.size()];
        for (int i = 0; i < this.list.size(); i++) {
            bArr[i] = this.list.get(i).toByte();
        }
        return bArr;
    }

    public List<BrailleCharacter> toList() {
        return new ArrayList(this.list);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            sb.append(this.list.get(i));
            if (i < this.list.size() - 1) {
                sb.append('-');
            }
        }
        return sb.toString();
    }

    public List<BrailleWord> tokenize(Collection<BrailleCharacter> collection) {
        ArrayList arrayList = new ArrayList();
        int i = -1;
        for (int i2 = 0; i2 < size(); i2++) {
            BrailleCharacter brailleCharacter = get(i2);
            if (collection.contains(brailleCharacter)) {
                if (i >= 0) {
                    arrayList.add(subword(i, i2));
                    i = -1;
                }
                arrayList.add(new BrailleWord(brailleCharacter));
            } else if (i < 0) {
                i = i2;
            }
        }
        if (i >= 0) {
            arrayList.add(subword(i, size()));
        }
        return arrayList;
    }

    public BrailleWord(BrailleWord brailleWord) {
        this();
        append(brailleWord);
    }

    public void append(BrailleWord brailleWord) {
        this.list.addAll(brailleWord.list);
    }

    public void set(int i, BrailleWord brailleWord) {
        this.list.remove(i);
        this.list.addAll(i, brailleWord.list);
    }

    public BrailleWord(BrailleWord brailleWord, int i) {
        this();
        for (int i2 = 0; i2 < i; i2++) {
            append(brailleWord);
        }
    }

    public BrailleWord(String str) {
        this();
        Iterator it = Splitter.on('-').omitEmptyStrings().split(str).iterator();
        while (it.hasNext()) {
            this.list.add(new BrailleCharacter((String) it.next()));
        }
    }

    public BrailleWord(Collection<BrailleCharacter> collection) {
        this();
        this.list.addAll(collection);
    }

    public BrailleWord(byte[] bArr) {
        this();
        for (byte b : bArr) {
            this.list.add(new BrailleCharacter(b));
        }
    }

    public BrailleWord(BrailleCharacter... brailleCharacterArr) {
        this(Arrays.asList(brailleCharacterArr));
    }
}
