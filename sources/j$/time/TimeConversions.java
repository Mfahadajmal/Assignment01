package j$.time;

/* loaded from: classes2.dex */
public class TimeConversions {
    public static Duration convert(java.time.Duration duration) {
        long seconds;
        int nano;
        if (duration == null) {
            return null;
        }
        seconds = duration.getSeconds();
        nano = duration.getNano();
        return Duration.r(seconds, nano);
    }

    public static java.time.Duration convert(Duration duration) {
        java.time.Duration ofSeconds;
        if (duration == null) {
            return null;
        }
        ofSeconds = java.time.Duration.ofSeconds(duration.getSeconds(), duration.n());
        return ofSeconds;
    }
}
