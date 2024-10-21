package com.google.android.accessibility.selecttospeak.services;

import android.content.Context;
import android.media.AudioManager;
import android.os.Message;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.WeakReferenceHandler;
import com.google.android.accessibility.utils.volumebutton.VolumeButtonPatternDetector;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StreamVolumeController implements VolumeButtonPatternDetector.OnPatternMatchListener {
    public static final int DEFAULT_STREAM = 10;
    public final AudioManager audioManager;
    private final VolumeStreamHandler handler = new VolumeStreamHandler(this);
    public final VolumeButtonPatternDetector patternDetector;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class VolumeStreamHandler extends WeakReferenceHandler {
        public VolumeStreamHandler(StreamVolumeController streamVolumeController) {
            super(streamVolumeController);
        }

        @Override // com.google.android.accessibility.utils.WeakReferenceHandler
        protected final /* bridge */ /* synthetic */ void handleMessage(Message message, Object obj) {
            StreamVolumeController streamVolumeController = (StreamVolumeController) obj;
            int i = message.arg1;
            int i2 = message.arg2;
            int i3 = 1;
            if (i != 1) {
                return;
            }
            AudioManager audioManager = streamVolumeController.audioManager;
            if (i2 != 24) {
                i3 = -1;
            }
            audioManager.adjustSuggestedStreamVolume(i3, 10, 21);
        }
    }

    public StreamVolumeController(Context context) {
        VolumeButtonPatternDetector volumeButtonPatternDetector = new VolumeButtonPatternDetector();
        this.patternDetector = volumeButtonPatternDetector;
        volumeButtonPatternDetector.mListener = this;
        this.audioManager = (AudioManager) context.getSystemService("audio");
    }

    @Override // com.google.android.accessibility.utils.volumebutton.VolumeButtonPatternDetector.OnPatternMatchListener
    public final void onPatternMatched(int i, int i2, Performance.EventId eventId) {
        VolumeStreamHandler volumeStreamHandler = this.handler;
        volumeStreamHandler.sendMessage(volumeStreamHandler.obtainMessage(0, i, i2));
    }
}
