package androidx.room.util;

import android.speech.tts.Voice;
import android.util.Pair;
import androidx.room.util.TableInfo;
import com.google.android.accessibility.talkback.contextmenu.ContextMenuItem;
import com.google.android.gms.common.Feature;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.trace.SpanEvent;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import com.google.common.flogger.context.Tags;
import com.google.mlkit.logging.schema.OnDeviceObjectInferenceLogEvent;
import com.google.mlkit.vision.text.internal.LegacyTextRecognitionDelegateUtils;
import java.io.File;
import java.util.Comparator;
import java.util.Map;
import org.chromium.net.impl.CronetLibraryLoader;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1 implements Comparator {
    private final /* synthetic */ int switching_field;

    public SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1(int i) {
        this.switching_field = i;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.switching_field) {
            case 0:
                return OnDeviceObjectInferenceLogEvent.compareValues((Integer) ((Map.Entry) obj).getKey(), (Integer) ((Map.Entry) obj2).getKey());
            case 1:
                byte[] bArr = (byte[]) obj;
                byte[] bArr2 = (byte[]) obj2;
                int length = bArr.length;
                int length2 = bArr2.length;
                if (length != length2) {
                    return length - length2;
                }
                for (int i = 0; i < bArr.length; i++) {
                    byte b = bArr[i];
                    byte b2 = bArr2[i];
                    if (b != b2) {
                        return b - b2;
                    }
                }
                return 0;
            case 2:
                return OnDeviceObjectInferenceLogEvent.compareValues((Integer) ((Map.Entry) obj).getKey(), (Integer) ((Map.Entry) obj2).getKey());
            case 3:
                return OnDeviceObjectInferenceLogEvent.compareValues(((TableInfo.Column) obj).name, ((TableInfo.Column) obj2).name);
            case 4:
                return OnDeviceObjectInferenceLogEvent.compareValues(((TableInfo.Index) obj).name, ((TableInfo.Index) obj2).name);
            case 5:
                return OnDeviceObjectInferenceLogEvent.compareValues(((File) obj2).getName(), ((File) obj).getName());
            case 6:
                return OnDeviceObjectInferenceLogEvent.compareValues(((Voice) obj).getLocale().getCountry(), ((Voice) obj2).getLocale().getCountry());
            case 7:
                return OnDeviceObjectInferenceLogEvent.compareValues(((Voice) obj).getLocale().getLanguage(), ((Voice) obj2).getLocale().getLanguage());
            case 8:
                return Integer.valueOf(((ContextMenuItem) obj).order).compareTo(Integer.valueOf(((ContextMenuItem) obj2).order));
            case 9:
                Feature feature = (Feature) obj;
                Feature feature2 = (Feature) obj2;
                if (!feature.name.equals(feature2.name)) {
                    return feature.name.compareTo(feature2.name);
                }
                return Long.compare(feature.getVersion(), feature2.getVersion());
            case 10:
                Object obj3 = ((AppLifecycleMonitor) obj).AppLifecycleMonitor$ar$tracker;
                throw null;
            case 11:
                MetricTransmitter metricTransmitter = (MetricTransmitter) obj;
                MetricTransmitter metricTransmitter2 = (MetricTransmitter) obj2;
                metricTransmitter.getTransmitterPriority();
                int i2 = metricTransmitter.getTransmitterPriority().priority;
                metricTransmitter2.getTransmitterPriority();
                int i3 = metricTransmitter2.getTransmitterPriority().priority;
                if (i2 == i3) {
                    return 0;
                }
                if (i2 <= i3) {
                    return 1;
                }
                return -1;
            case 12:
                return (int) (((SpanEvent) obj).startMs - ((SpanEvent) obj2).startMs);
            case 13:
                Tags.Type of = Tags.Type.of(obj);
                Tags.Type of2 = Tags.Type.of(obj2);
                if (of == of2) {
                    int ordinal = of.ordinal();
                    if (ordinal != 0) {
                        if (ordinal != 1) {
                            if (ordinal != 2) {
                                if (ordinal == 3) {
                                    return ((Double) obj).compareTo((Double) obj2);
                                }
                                throw null;
                            }
                            return ((Long) obj).compareTo((Long) obj2);
                        }
                        return ((String) obj).compareTo((String) obj2);
                    }
                    return ((Boolean) obj).compareTo((Boolean) obj2);
                }
                return of.compareTo(of2);
            case 14:
                return ((String) ((Map.Entry) obj).getKey()).compareTo((String) ((Map.Entry) obj2).getKey());
            case 15:
                Comparator comparator = LegacyTextRecognitionDelegateUtils.languageComparator;
                return ((Integer) ((Map.Entry) obj).getValue()).compareTo((Integer) ((Map.Entry) obj2).getValue());
            default:
                String str = CronetLibraryLoader.TAG;
                return ((Long) ((Pair) obj).first).compareTo((Long) ((Pair) obj2).first);
        }
    }
}
