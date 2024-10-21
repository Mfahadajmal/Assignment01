package androidx.dynamicanimation.animation;

import androidx.dynamicanimation.animation.DynamicAnimation;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SpringForce {
    private double mDampedFreq;
    public double mDampingRatio;
    private double mFinalPosition;
    private double mGammaMinus;
    private double mGammaPlus;
    public boolean mInitialized;
    private final DynamicAnimation.MassState mMassState;
    public double mNaturalFreq;
    public double mValueThreshold;
    public double mVelocityThreshold;

    public SpringForce() {
        this.mNaturalFreq = Math.sqrt(1500.0d);
        this.mDampingRatio = 0.5d;
        this.mInitialized = false;
        this.mFinalPosition = Double.MAX_VALUE;
        this.mMassState = new DynamicAnimation.MassState();
    }

    public final float getFinalPosition() {
        return (float) this.mFinalPosition;
    }

    public final void setFinalPosition$ar$ds(float f) {
        this.mFinalPosition = f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final DynamicAnimation.MassState updateValues(double d, double d2, long j) {
        double cos;
        double d3;
        if (!this.mInitialized) {
            if (this.mFinalPosition != Double.MAX_VALUE) {
                double d4 = this.mDampingRatio;
                if (d4 > 1.0d) {
                    double d5 = this.mNaturalFreq;
                    this.mGammaPlus = ((-d4) * d5) + (d5 * Math.sqrt((d4 * d4) - 1.0d));
                    double d6 = this.mDampingRatio;
                    double d7 = this.mNaturalFreq;
                    this.mGammaMinus = ((-d6) * d7) - (d7 * Math.sqrt((d6 * d6) - 1.0d));
                } else if (d4 >= 0.0d && d4 < 1.0d) {
                    this.mDampedFreq = this.mNaturalFreq * Math.sqrt(1.0d - (d4 * d4));
                }
                this.mInitialized = true;
            } else {
                throw new IllegalStateException("Error: Final position of the spring must be set before the animation starts");
            }
        }
        double d8 = d - this.mFinalPosition;
        double d9 = this.mDampingRatio;
        double d10 = j / 1000.0d;
        if (d9 > 1.0d) {
            double d11 = this.mGammaMinus;
            double d12 = ((d11 * d8) - d2) / (d11 - this.mGammaPlus);
            double d13 = d8 - d12;
            double pow = Math.pow(2.718281828459045d, d11 * d10) * d13;
            double pow2 = Math.pow(2.718281828459045d, this.mGammaPlus * d10) * d12;
            double d14 = this.mGammaMinus;
            double pow3 = d13 * d14 * Math.pow(2.718281828459045d, d14 * d10);
            double d15 = this.mGammaPlus;
            cos = pow3 + (d12 * d15 * Math.pow(2.718281828459045d, d15 * d10));
            d3 = pow + pow2;
        } else if (d9 == 1.0d) {
            double d16 = this.mNaturalFreq;
            double d17 = d2 + (d16 * d8);
            double d18 = d8 + (d17 * d10);
            d3 = Math.pow(2.718281828459045d, (-d16) * d10) * d18;
            double pow4 = d18 * Math.pow(2.718281828459045d, (-this.mNaturalFreq) * d10);
            double d19 = -this.mNaturalFreq;
            cos = (pow4 * d19) + (d17 * Math.pow(2.718281828459045d, d10 * d19));
        } else {
            double d20 = 1.0d / this.mDampedFreq;
            double d21 = this.mNaturalFreq;
            double d22 = d20 * ((d9 * d21 * d8) + d2);
            double pow5 = Math.pow(2.718281828459045d, (-d9) * d21 * d10) * ((Math.cos(this.mDampedFreq * d10) * d8) + (Math.sin(this.mDampedFreq * d10) * d22));
            double d23 = this.mNaturalFreq;
            double d24 = this.mDampingRatio;
            double d25 = (-d23) * pow5 * d24;
            double pow6 = Math.pow(2.718281828459045d, (-d24) * d23 * d10);
            double d26 = this.mDampedFreq;
            double sin = (-d26) * d8 * Math.sin(d26 * d10);
            double d27 = this.mDampedFreq;
            cos = d25 + (pow6 * (sin + (d22 * d27 * Math.cos(d27 * d10))));
            d3 = pow5;
        }
        DynamicAnimation.MassState massState = this.mMassState;
        massState.mValue = (float) (d3 + this.mFinalPosition);
        massState.mVelocity = (float) cos;
        return massState;
    }

    public SpringForce(float f) {
        this.mNaturalFreq = Math.sqrt(1500.0d);
        this.mDampingRatio = 0.5d;
        this.mInitialized = false;
        this.mFinalPosition = Double.MAX_VALUE;
        this.mMassState = new DynamicAnimation.MassState();
        this.mFinalPosition = f;
    }
}
