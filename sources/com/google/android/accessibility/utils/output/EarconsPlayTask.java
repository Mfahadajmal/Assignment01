package com.google.android.accessibility.utils.output;

import android.media.SoundPool;
import android.os.AsyncTask;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EarconsPlayTask extends AsyncTask {
    private final SoundPool mSoundPool;
    private final float rate;
    private final int soundId;
    private final float volume;

    public EarconsPlayTask(SoundPool soundPool, int i, float f, float f2) {
        this.mSoundPool = soundPool;
        this.soundId = i;
        this.volume = Math.min(1.0f, f);
        this.rate = f2;
    }

    @Override // android.os.AsyncTask
    protected final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
        boolean z;
        SoundPool soundPool = this.mSoundPool;
        int i = this.soundId;
        float f = this.volume;
        if (soundPool.play(i, f, f, 0, 0, this.rate) != 0) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }
}
