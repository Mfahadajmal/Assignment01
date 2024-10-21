package androidx.core.view;

/* compiled from: PG */
/* loaded from: classes.dex */
final class VelocityTrackerFallback {
    public final float[] mMovements = new float[20];
    public final long[] mEventTimes = new long[20];
    public float mLastComputedVelocity = 0.0f;
    public int mDataPointsBufferSize = 0;
    public int mDataPointsBufferLastUsedIndex = 0;

    public static float kineticEnergyToVelocity(float f) {
        float f2;
        float abs = Math.abs(f);
        float sqrt = (float) Math.sqrt(abs + abs);
        if (f < 0.0f) {
            f2 = -1.0f;
        } else {
            f2 = 1.0f;
        }
        return f2 * sqrt;
    }
}
