package com.google.android.libraries.storage.file.openers;

import android.net.Uri;
import com.google.android.libraries.phenotype.client.shareddir.ByteArrayInflater$1;
import com.google.android.libraries.phenotype.client.shareddir.FlagsBlob;
import com.google.android.libraries.phenotype.client.shareddir.SnapshotBlob;
import com.google.android.libraries.phenotype.client.shareddir.SnapshotTokens;
import com.google.android.libraries.storage.file.MonitorInputStream;
import com.google.android.libraries.storage.file.Opener;
import com.google.android.libraries.storage.file.common.ReleasableResource;
import com.google.android.libraries.storage.file.spi.Monitor;
import com.google.android.libraries.storage.file.spi.Transform;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.NaturalOrdering;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.zip.Inflater;
import kotlinx.coroutines.scheduling.WorkQueue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ReadStreamOpener implements Opener {
    private final /* synthetic */ int switching_field;

    public ReadStreamOpener(int i) {
        this.switching_field = i;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.google.android.libraries.storage.file.spi.Backend, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.util.List, java.lang.Object] */
    public static final InputStream open$ar$ds$ar$class_merging$ar$class_merging(WorkQueue workQueue) {
        MonitorInputStream monitorInputStream;
        InputStream openForRead = workQueue.WorkQueue$ar$buffer.openForRead((Uri) workQueue.WorkQueue$ar$consumerIndex);
        ArrayList arrayList = new ArrayList();
        arrayList.add(openForRead);
        if (!workQueue.WorkQueue$ar$lastScheduledTask.isEmpty()) {
            ?? r2 = workQueue.WorkQueue$ar$lastScheduledTask;
            ArrayList arrayList2 = new ArrayList();
            Iterator it = r2.iterator();
            while (it.hasNext()) {
                Monitor.InputMonitor monitorRead$ar$ds = ((Monitor) it.next()).monitorRead$ar$ds();
                if (monitorRead$ar$ds != null) {
                    arrayList2.add(monitorRead$ar$ds);
                }
            }
            if (!arrayList2.isEmpty()) {
                monitorInputStream = new MonitorInputStream(openForRead, arrayList2);
            } else {
                monitorInputStream = null;
            }
            if (monitorInputStream != null) {
                arrayList.add(monitorInputStream);
            }
        }
        for (Transform transform : workQueue.WorkQueue$ar$blockingTasksInBuffer) {
            arrayList.add(transform.wrapForRead$ar$ds());
        }
        Collections.reverse(arrayList);
        return (InputStream) arrayList.get(0);
    }

    @Override // com.google.android.libraries.storage.file.Opener
    public final /* synthetic */ Object open$ar$class_merging$a9b907d0_0$ar$class_merging(WorkQueue workQueue) {
        long j;
        String str;
        FlagsBlob.ParsedParam parsedParam;
        if (this.switching_field != 0) {
            InputStream open$ar$ds$ar$class_merging$ar$class_merging = open$ar$ds$ar$class_merging$ar$class_merging(workQueue);
            try {
                CodedInputStream newInstance = CodedInputStream.newInstance(open$ar$ds$ar$class_merging$ar$class_merging);
                SnapshotBlob snapshotBlob = SnapshotBlob.EMPTY;
                int readSFixed32 = newInstance.readSFixed32();
                if (readSFixed32 <= 1) {
                    newInstance.readSFixed32();
                    int pushLimit = newInstance.pushLimit(newInstance.readUInt32());
                    SnapshotTokens snapshotTokens = (SnapshotTokens) GeneratedMessageLite.parseFrom(SnapshotTokens.DEFAULT_INSTANCE, newInstance, ExtensionRegistryLite.getGeneratedRegistry());
                    newInstance.popLimit(pushLimit);
                    byte[] readByteArray = newInstance.readByteArray();
                    ReleasableResource releasableResource = new ReleasableResource(1);
                    try {
                        ((Inflater) releasableResource.ReleasableResource$ar$resource).setInput(readByteArray);
                        try {
                            CodedInputStream newInstance2 = CodedInputStream.newInstance(new ByteArrayInflater$1(releasableResource));
                            FlagsBlob flagsBlob = FlagsBlob.EMPTY;
                            int readRawVarint32 = newInstance2.readRawVarint32();
                            if (readRawVarint32 >= 0) {
                                ImmutableSortedSet.Builder builder = new ImmutableSortedSet.Builder(NaturalOrdering.INSTANCE);
                                long j2 = 0;
                                for (int i = 0; i < readRawVarint32; i++) {
                                    long readRawVarint64 = newInstance2.readRawVarint64();
                                    int i2 = (int) readRawVarint64;
                                    long j3 = readRawVarint64 >>> 3;
                                    if (j3 == 0) {
                                        j = 0;
                                        str = newInstance2.readString();
                                    } else {
                                        long j4 = j3 + j2;
                                        if (j4 <= 2305843009213693951L) {
                                            j = j4;
                                            str = null;
                                        } else {
                                            throw new InvalidProtocolBufferException("Flag name larger than max size");
                                        }
                                    }
                                    int i3 = i2 & 7;
                                    if (i3 != 0 && i3 != 1) {
                                        if (i3 != 2) {
                                            if (i3 != 3) {
                                                if (i3 != 4) {
                                                    if (i3 == 5) {
                                                        parsedParam = new FlagsBlob.ParsedParam(j, str, i3, 0L, newInstance2.readByteArray());
                                                    } else {
                                                        throw new InvalidProtocolBufferException("Unrecognized flag type " + i3);
                                                    }
                                                } else {
                                                    parsedParam = new FlagsBlob.ParsedParam(j, str, i3, 0L, newInstance2.readString());
                                                }
                                            } else {
                                                parsedParam = new FlagsBlob.ParsedParam(j, str, i3, Double.doubleToRawLongBits(newInstance2.readDouble()), null);
                                            }
                                        } else {
                                            parsedParam = new FlagsBlob.ParsedParam(j, str, i3, newInstance2.readRawVarint64(), null);
                                        }
                                    } else {
                                        parsedParam = new FlagsBlob.ParsedParam(j, str, i3, 0L, null);
                                    }
                                    long j5 = parsedParam.intName;
                                    if (j5 != 0) {
                                        j2 = j5;
                                    }
                                    builder.add$ar$ds$7e8aa2c7_0(parsedParam);
                                }
                                FlagsBlob flagsBlob2 = new FlagsBlob(builder.build());
                                releasableResource.close();
                                SnapshotBlob snapshotBlob2 = new SnapshotBlob(flagsBlob2, snapshotTokens);
                                if (open$ar$ds$ar$class_merging$ar$class_merging != null) {
                                    open$ar$ds$ar$class_merging$ar$class_merging.close();
                                }
                                return snapshotBlob2;
                            }
                            throw new InvalidProtocolBufferException("Negative number of flags");
                        } finally {
                            ((Inflater) releasableResource.ReleasableResource$ar$resource).reset();
                        }
                    } finally {
                    }
                } else {
                    throw new InvalidProtocolBufferException("Unsupported version: " + readSFixed32 + ". Current version is: 1");
                }
            } finally {
            }
        } else {
            return open$ar$ds$ar$class_merging$ar$class_merging(workQueue);
        }
    }
}
