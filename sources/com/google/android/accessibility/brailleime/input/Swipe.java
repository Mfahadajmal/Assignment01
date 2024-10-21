package com.google.android.accessibility.brailleime.input;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Swipe implements Gesture {
    public final Direction direction;
    public final int touchCount;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public Swipe(Direction direction, int i) {
        this.direction = direction;
        this.touchCount = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Swipe createFromMirror(Swipe swipe) {
        Direction direction = swipe.direction;
        if (direction == Direction.LEFT) {
            direction = Direction.RIGHT;
        } else if (direction == Direction.RIGHT) {
            direction = Direction.LEFT;
        }
        return new Swipe(direction, swipe.touchCount);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Swipe)) {
            return false;
        }
        Swipe swipe = (Swipe) obj;
        if (!this.direction.equals(swipe.direction) || this.touchCount != swipe.touchCount) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int hashCode;
        Direction direction = this.direction;
        if (direction == null) {
            hashCode = 0;
        } else {
            hashCode = direction.hashCode();
        }
        return ((hashCode + 481) * 37) + this.touchCount;
    }

    @Override // com.google.android.accessibility.brailleime.input.Gesture
    public final Gesture mirrorDots() {
        return new Swipe(this);
    }

    public final String toString() {
        return "Swipe{direction=" + String.valueOf(this.direction) + ", touchCount=" + this.touchCount + "}";
    }

    public Swipe(Swipe swipe) {
        this.direction = swipe.direction;
        this.touchCount = swipe.touchCount;
    }
}
