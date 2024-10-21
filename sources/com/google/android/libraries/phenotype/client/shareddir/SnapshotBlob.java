package com.google.android.libraries.phenotype.client.shareddir;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SnapshotBlob {
    public static final SnapshotBlob EMPTY = new SnapshotBlob(FlagsBlob.EMPTY, SnapshotTokens.DEFAULT_INSTANCE);
    public final FlagsBlob flagsBlob;
    public final SnapshotTokens tokens;

    public SnapshotBlob(FlagsBlob flagsBlob, SnapshotTokens snapshotTokens) {
        flagsBlob.getClass();
        this.flagsBlob = flagsBlob;
        this.tokens = snapshotTokens;
    }

    public final String getSnapshotToken() {
        return this.tokens.snapshotToken_;
    }
}
