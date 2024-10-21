package androidx.room;

import android.content.Context;
import android.widget.TextView;
import androidx.lifecycle.ViewModelStore;
import androidx.room.RoomConnectionManager;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper;
import androidx.work.Worker;
import androidx.work.impl.background.systemjob.SystemJobService;
import com.google.android.accessibility.selecttospeak.AccessibilityNodeInfoCompatWithVisibility;
import com.google.android.accessibility.selecttospeak.feedback.SelectToSpeakJob;
import com.google.android.accessibility.selecttospeak.iterator.Paragraph;
import com.google.android.accessibility.selecttospeak.iterator.Sentence;
import com.google.android.accessibility.selecttospeak.iterator.SentenceIterator;
import com.google.android.accessibility.selecttospeak.iterator.TextFinderFromNodeInfo;
import com.google.android.accessibility.selecttospeak.iterator.TextFinderFromTextView;
import com.google.android.accessibility.selecttospeak.overlayui.ControlOverlays;
import com.google.android.accessibility.selecttospeak.overlayui.ControlOverlaysAnimations;
import com.google.android.accessibility.selecttospeak.popup.SelectToSpeakJobModel;
import com.google.android.accessibility.selecttospeak.popup.SelectToSpeakPopupActivity;
import com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SActions;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import io.grpc.okhttp.internal.OptionalMethod;
import java.io.File;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.flow.Flow;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SharedSQLiteStatement$stmt$2 extends Lambda implements Function0 {
    final /* synthetic */ Object SharedSQLiteStatement$stmt$2$ar$this$0;
    private final /* synthetic */ int switching_field;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SharedSQLiteStatement$stmt$2(Object obj, int i) {
        super(0);
        this.switching_field = i;
        this.SharedSQLiteStatement$stmt$2$ar$this$0 = obj;
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [androidx.sqlite.db.SupportSQLiteOpenHelper, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function0
    public final /* synthetic */ Object invoke() {
        FrameworkSQLiteOpenHelper.OpenHelper openHelper;
        Paragraph paragraph;
        Paragraph paragraph2;
        SelectToSpeakJob selectToSpeakJob;
        switch (this.switching_field) {
            case 0:
                return ((SharedSQLiteStatement) this.SharedSQLiteStatement$stmt$2$ar$this$0).createNewStatement$ar$class_merging();
            case 1:
                return new RoomConnectionManager.SupportPooledConnection(new ViewModelStore(((RoomConnectionManager.SupportConnectionPool) this.SharedSQLiteStatement$stmt$2$ar$this$0).supportDriver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ViewModelStore$ar$map.getWritableDatabase(), (byte[]) null));
            case 2:
                FrameworkSQLiteOpenHelper frameworkSQLiteOpenHelper = (FrameworkSQLiteOpenHelper) this.SharedSQLiteStatement$stmt$2$ar$this$0;
                String str = frameworkSQLiteOpenHelper.name;
                if (str != null && frameworkSQLiteOpenHelper.useNoBackupDirectory) {
                    File noBackupFilesDir = frameworkSQLiteOpenHelper.context.getNoBackupFilesDir();
                    noBackupFilesDir.getClass();
                    File file = new File(noBackupFilesDir, ((FrameworkSQLiteOpenHelper) this.SharedSQLiteStatement$stmt$2$ar$this$0).name);
                    Object obj = this.SharedSQLiteStatement$stmt$2$ar$this$0;
                    String absolutePath = file.getAbsolutePath();
                    OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = new OnDeviceTextDetectionLoadLogEvent((short[]) null, (byte[]) null, (byte[]) null);
                    FrameworkSQLiteOpenHelper frameworkSQLiteOpenHelper2 = (FrameworkSQLiteOpenHelper) this.SharedSQLiteStatement$stmt$2$ar$this$0;
                    openHelper = new FrameworkSQLiteOpenHelper.OpenHelper(((FrameworkSQLiteOpenHelper) obj).context, absolutePath, onDeviceTextDetectionLoadLogEvent, frameworkSQLiteOpenHelper2.callback, frameworkSQLiteOpenHelper2.allowDataLossOnRecovery);
                } else {
                    Context context = frameworkSQLiteOpenHelper.context;
                    OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent2 = new OnDeviceTextDetectionLoadLogEvent((short[]) null, (byte[]) null, (byte[]) null);
                    FrameworkSQLiteOpenHelper frameworkSQLiteOpenHelper3 = (FrameworkSQLiteOpenHelper) this.SharedSQLiteStatement$stmt$2$ar$this$0;
                    openHelper = new FrameworkSQLiteOpenHelper.OpenHelper(context, str, onDeviceTextDetectionLoadLogEvent2, frameworkSQLiteOpenHelper3.callback, frameworkSQLiteOpenHelper3.allowDataLossOnRecovery);
                }
                openHelper.setWriteAheadLoggingEnabled(((FrameworkSQLiteOpenHelper) this.SharedSQLiteStatement$stmt$2$ar$this$0).writeAheadLoggingEnabled);
                return openHelper;
            case 3:
                throw new IllegalStateException("Expedited WorkRequests require a Worker to provide an implementation for `getForegroundInfo()`");
            case 4:
                return ((Worker) this.SharedSQLiteStatement$stmt$2$ar$this$0).doWork$ar$class_merging$ar$class_merging();
            case 5:
                return new SystemJobService.Api24Impl[((Flow[]) this.SharedSQLiteStatement$stmt$2$ar$this$0).length];
            case 6:
                return new TextFinderFromNodeInfo((AccessibilityNodeInfoCompatWithVisibility) this.SharedSQLiteStatement$stmt$2$ar$this$0);
            case 7:
                return this.SharedSQLiteStatement$stmt$2$ar$this$0;
            case 8:
                Sentence peek = ((SentenceIterator) this.SharedSQLiteStatement$stmt$2$ar$this$0).getPeek();
                if (peek == null || (paragraph = peek.paragraph.next) == null) {
                    return null;
                }
                return paragraph.head;
            case 9:
                Sentence peek2 = ((SentenceIterator) this.SharedSQLiteStatement$stmt$2$ar$this$0).getPeek();
                if (peek2 == null || (paragraph2 = peek2.paragraph.prev) == null) {
                    return null;
                }
                return paragraph2.head;
            case 10:
                return new TextFinderFromTextView((TextView) this.SharedSQLiteStatement$stmt$2$ar$this$0, 0);
            case 11:
                return new TextFinderFromNodeInfo((AccessibilityNodeInfoCompatWithVisibility) this.SharedSQLiteStatement$stmt$2$ar$this$0);
            case 12:
                ((ControlOverlaysAnimations) this.SharedSQLiteStatement$stmt$2$ar$this$0).foregroundUpdater.invoke(ControlOverlays.OverlayTypes.EXPANDABLE);
                return Unit.INSTANCE;
            case 13:
                ((ControlOverlaysAnimations) this.SharedSQLiteStatement$stmt$2$ar$this$0).foregroundUpdater.invoke(ControlOverlays.OverlayTypes.EXPANDABLE);
                return Unit.INSTANCE;
            case 14:
                ((ControlOverlaysAnimations) this.SharedSQLiteStatement$stmt$2$ar$this$0).foregroundUpdater.invoke(ControlOverlays.OverlayTypes.TRIGGER_BUTTON);
                return Unit.INSTANCE;
            case 15:
                ((ControlOverlaysAnimations) this.SharedSQLiteStatement$stmt$2$ar$this$0).triggerButtonAppearanceUpdater.invoke(true);
                ((ControlOverlaysAnimations) this.SharedSQLiteStatement$stmt$2$ar$this$0).foregroundUpdater.invoke(ControlOverlays.OverlayTypes.COLLAPSED);
                return Unit.INSTANCE;
            case 16:
                ((ControlOverlays) this.SharedSQLiteStatement$stmt$2$ar$this$0).collapse$ar$ds();
                return Unit.INSTANCE;
            case 17:
                SelectToSpeakJobModel jobModel = ((SelectToSpeakPopupActivity) this.SharedSQLiteStatement$stmt$2$ar$this$0).getJobModel();
                LogUtils.d("SelectToSpeakJobModel", "reduce speech rate. currentIndex : " + jobModel.speechIndex(), new Object[0]);
                OptionalMethod optionalMethod = jobModel.analytics$ar$class_merging$a359db55_0$ar$class_merging$ar$class_merging;
                if (optionalMethod != null) {
                    optionalMethod.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.POPUP_DECREASE_SPEED_ACTION$ar$edu);
                }
                if (jobModel.canReduceSpeechRate() && (selectToSpeakJob = jobModel.job) != null) {
                    selectToSpeakJob.reduceSpeechRate();
                }
                return Unit.INSTANCE;
            case 18:
                return Integer.valueOf(((SelectToSpeakPopupActivity) this.SharedSQLiteStatement$stmt$2$ar$this$0).getJobModel().speechIndex());
            case 19:
                return Boolean.valueOf(((SelectToSpeakPopupActivity) this.SharedSQLiteStatement$stmt$2$ar$this$0).getTextSizeModel().canIncreaseTextSize());
            default:
                return Boolean.valueOf(((SelectToSpeakPopupActivity) this.SharedSQLiteStatement$stmt$2$ar$this$0).getTextSizeModel().canReduceTextSize());
        }
    }
}
