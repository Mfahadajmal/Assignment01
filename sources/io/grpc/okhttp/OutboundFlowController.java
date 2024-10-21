package io.grpc.okhttp;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.Preference;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.collect.ImmutableMap;
import com.google.scone.proto.Survey$BranchingDestination;
import com.google.scone.proto.Survey$Question;
import com.google.scone.proto.Survey$Rating;
import io.grpc.okhttp.OkHttpClientStream;
import io.grpc.okhttp.internal.framed.FrameWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import okio.Buffer;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OutboundFlowController {
    public final Object OutboundFlowController$ar$connectionState;
    public final Object OutboundFlowController$ar$frameWriter;
    public final Object OutboundFlowController$ar$transport;
    public int initialWindowSize;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class StreamState {
        public int allocatedBytes;
        private final OkHttpClientStream.TransportState stream$ar$class_merging;
        private final int streamId;
        public int window;
        public final Buffer pendingWriteBuffer = new Buffer();
        public boolean pendingBufferHasEndOfStream = false;

        public StreamState(int i, int i2, OkHttpClientStream.TransportState transportState) {
            this.streamId = i;
            this.window = i2;
            this.stream$ar$class_merging = transportState;
        }

        final boolean hasPendingData() {
            if (this.pendingWriteBuffer.size > 0) {
                return true;
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final int incrementStreamWindow(int i) {
            if (i > 0 && Preference.DEFAULT_ORDER - i < this.window) {
                throw new IllegalArgumentException("Window size overflow for stream: " + this.streamId);
            }
            int i2 = this.window + i;
            this.window = i2;
            return i2;
        }

        final int unallocatedBytes() {
            return Math.max(0, Math.min(this.window, (int) this.pendingWriteBuffer.size)) - this.allocatedBytes;
        }

        final int writableWindow() {
            return Math.min(this.window, ((StreamState) OutboundFlowController.this.OutboundFlowController$ar$connectionState).window);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, io.grpc.okhttp.internal.framed.FrameWriter] */
        /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.Object, io.grpc.okhttp.internal.framed.FrameWriter] */
        final void write(Buffer buffer, int i, boolean z) {
            do {
                int min = Math.min(i, OutboundFlowController.this.OutboundFlowController$ar$frameWriter.maxDataLength());
                int i2 = -min;
                ((StreamState) OutboundFlowController.this.OutboundFlowController$ar$connectionState).incrementStreamWindow(i2);
                incrementStreamWindow(i2);
                try {
                    boolean z2 = false;
                    if (buffer.size == min && z) {
                        z2 = true;
                    }
                    OutboundFlowController.this.OutboundFlowController$ar$frameWriter.data(z2, this.streamId, buffer, min);
                    this.stream$ar$class_merging.onSentBytes(min);
                    i -= min;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } while (i > 0);
        }

        final void writeBytes$ar$ds(int i, WriteStatus writeStatus) {
            int min = Math.min(i, writableWindow());
            int i2 = 0;
            while (hasPendingData() && min > 0) {
                Buffer buffer = this.pendingWriteBuffer;
                long j = min;
                long j2 = buffer.size;
                if (j >= j2) {
                    int i3 = (int) j2;
                    i2 += i3;
                    write(buffer, i3, this.pendingBufferHasEndOfStream);
                } else {
                    i2 += min;
                    write(buffer, min, false);
                }
                writeStatus.numWrites++;
                min = Math.min(i - i2, writableWindow());
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Transport {
        StreamState[] getActiveStreams();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class WriteStatus {
        int numWrites;

        public WriteStatus() {
        }

        public final int getNextQuestionOrdinal(ImmutableMap immutableMap, int i, int i2, Survey$Question survey$Question) {
            Survey$Rating survey$Rating;
            Survey$Rating survey$Rating2;
            if (survey$Question.questionCase_ == 6) {
                survey$Rating = (Survey$Rating) survey$Question.question_;
            } else {
                survey$Rating = Survey$Rating.DEFAULT_INSTANCE;
            }
            if (survey$Rating.branchingConfig_.size() != 0) {
                if (survey$Question.questionCase_ == 6) {
                    survey$Rating2 = (Survey$Rating) survey$Question.question_;
                } else {
                    survey$Rating2 = Survey$Rating.DEFAULT_INSTANCE;
                }
                Iterator<E> it = survey$Rating2.branchingConfig_.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Survey$Rating.BranchingConfig branchingConfig = (Survey$Rating.BranchingConfig) it.next();
                    Iterator<E> it2 = branchingConfig.ratingOrdinal_.iterator();
                    while (it2.hasNext()) {
                        if (((Integer) it2.next()).intValue() == i2) {
                            Survey$BranchingDestination survey$BranchingDestination = branchingConfig.branchingDestination_;
                            if (survey$BranchingDestination == null) {
                                survey$BranchingDestination = Survey$BranchingDestination.DEFAULT_INSTANCE;
                            }
                            int forNumber$ar$edu$36e8454a_0 = Survey$BranchingDestination.DestinationType.forNumber$ar$edu$36e8454a_0(survey$BranchingDestination.destinationType_);
                            if (forNumber$ar$edu$36e8454a_0 == 0) {
                                forNumber$ar$edu$36e8454a_0 = Survey$BranchingDestination.DestinationType.UNRECOGNIZED$ar$edu$b8e6fbeb_0;
                            }
                            if (forNumber$ar$edu$36e8454a_0 != 0) {
                                int i3 = forNumber$ar$edu$36e8454a_0 - 2;
                                if (i3 != 2) {
                                    if (i3 == 3) {
                                        this.numWrites = i + 1;
                                    }
                                } else {
                                    Survey$BranchingDestination survey$BranchingDestination2 = branchingConfig.branchingDestination_;
                                    if (survey$BranchingDestination2 == null) {
                                        survey$BranchingDestination2 = Survey$BranchingDestination.DEFAULT_INSTANCE;
                                    }
                                    if (immutableMap.containsKey(survey$BranchingDestination2.toBranchingGroup_)) {
                                        Survey$BranchingDestination survey$BranchingDestination3 = branchingConfig.branchingDestination_;
                                        if (survey$BranchingDestination3 == null) {
                                            survey$BranchingDestination3 = Survey$BranchingDestination.DEFAULT_INSTANCE;
                                        }
                                        this.numWrites = ((Integer) immutableMap.get(survey$BranchingDestination3.toBranchingGroup_)).intValue();
                                    }
                                }
                            } else {
                                throw null;
                            }
                        }
                    }
                }
            }
            return this.numWrites;
        }

        final boolean hasWritten() {
            if (this.numWrites > 0) {
                return true;
            }
            return false;
        }

        public WriteStatus(byte[] bArr) {
            this.numWrites = -1;
        }
    }

    public OutboundFlowController(Context context) {
        this.OutboundFlowController$ar$frameWriter = new FloatingActionButton.ShadowDelegateImpl(this);
        this.OutboundFlowController$ar$connectionState = context;
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
        this.OutboundFlowController$ar$transport = sharedPreferences;
        this.initialWindowSize = (int) (SpannableUtils$IdentifierSpan.getFloatFromStringPref(sharedPreferences, context.getResources(), R.string.pref_speech_rate_key, R.string.pref_speech_rate_default) * 100.0f);
    }

    public static float forceRateToDefaultWhenClose(float f) {
        if (f > 0.95f && f < 1.05f) {
            return 1.0f;
        }
        return f;
    }

    /* JADX WARN: Type inference failed for: r0v8, types: [android.content.SharedPreferences, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v0, types: [android.content.SharedPreferences, java.lang.Object] */
    public final void changeSpeechRate$ar$ds(boolean z) {
        float max;
        float floatFromStringPref = SpannableUtils$IdentifierSpan.getFloatFromStringPref(this.OutboundFlowController$ar$transport, ((Context) this.OutboundFlowController$ar$connectionState).getResources(), R.string.pref_speech_rate_key, R.string.pref_speech_rate_default);
        if (z) {
            max = Math.min(floatFromStringPref * 1.1f, 6.0f);
        } else {
            max = Math.max(floatFromStringPref / 1.1f, 0.1f);
        }
        float forceRateToDefaultWhenClose = forceRateToDefaultWhenClose(max);
        this.initialWindowSize = (int) (100.0f * forceRateToDefaultWhenClose);
        this.OutboundFlowController$ar$transport.edit().putString(((Context) this.OutboundFlowController$ar$connectionState).getString(R.string.pref_speech_rate_key), Float.toString(forceRateToDefaultWhenClose)).putInt(((Context) this.OutboundFlowController$ar$connectionState).getString(R.string.pref_speech_rate_seekbar_key_int), this.initialWindowSize).apply();
    }

    public final void data(boolean z, StreamState streamState, Buffer buffer, boolean z2) {
        buffer.getClass();
        int writableWindow = streamState.writableWindow();
        boolean hasPendingData = streamState.hasPendingData();
        int i = (int) buffer.size;
        if (!hasPendingData && writableWindow >= i) {
            streamState.write(buffer, i, z);
        } else {
            if (!hasPendingData && writableWindow > 0) {
                streamState.write(buffer, writableWindow, false);
            }
            streamState.pendingWriteBuffer.write(buffer, (int) buffer.size);
            streamState.pendingBufferHasEndOfStream = z | streamState.pendingBufferHasEndOfStream;
        }
        if (z2) {
            flush();
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, io.grpc.okhttp.internal.framed.FrameWriter] */
    public final void flush() {
        try {
            this.OutboundFlowController$ar$frameWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final void windowUpdate$ar$ds(StreamState streamState, int i) {
        if (streamState == null) {
            ((StreamState) this.OutboundFlowController$ar$connectionState).incrementStreamWindow(i);
            writeStreams();
            return;
        }
        streamState.incrementStreamWindow(i);
        WriteStatus writeStatus = new WriteStatus();
        streamState.writeBytes$ar$ds(streamState.writableWindow(), writeStatus);
        if (writeStatus.hasWritten()) {
            flush();
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [io.grpc.okhttp.OutboundFlowController$Transport, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v3, types: [io.grpc.okhttp.OutboundFlowController$Transport, java.lang.Object] */
    public final void writeStreams() {
        StreamState[] activeStreams = this.OutboundFlowController$ar$transport.getActiveStreams();
        Collections.shuffle(Arrays.asList(activeStreams));
        int length = activeStreams.length;
        int i = ((StreamState) this.OutboundFlowController$ar$connectionState).window;
        while (true) {
            int i2 = 0;
            if (length <= 0 || i <= 0) {
                break;
            }
            int ceil = (int) Math.ceil(i / length);
            for (int i3 = 0; i3 < length && i > 0; i3++) {
                StreamState streamState = activeStreams[i3];
                int min = Math.min(i, Math.min(streamState.unallocatedBytes(), ceil));
                if (min > 0) {
                    streamState.allocatedBytes += min;
                    i -= min;
                }
                if (streamState.unallocatedBytes() > 0) {
                    activeStreams[i2] = streamState;
                    i2++;
                }
            }
            length = i2;
        }
        WriteStatus writeStatus = new WriteStatus();
        for (StreamState streamState2 : this.OutboundFlowController$ar$transport.getActiveStreams()) {
            streamState2.writeBytes$ar$ds(streamState2.allocatedBytes, writeStatus);
            streamState2.allocatedBytes = 0;
        }
        if (writeStatus.hasWritten()) {
            flush();
        }
    }

    public OutboundFlowController(Transport transport, FrameWriter frameWriter) {
        this.OutboundFlowController$ar$transport = transport;
        frameWriter.getClass();
        this.OutboundFlowController$ar$frameWriter = frameWriter;
        this.initialWindowSize = 65535;
        this.OutboundFlowController$ar$connectionState = new StreamState(0, 65535, null);
    }
}
