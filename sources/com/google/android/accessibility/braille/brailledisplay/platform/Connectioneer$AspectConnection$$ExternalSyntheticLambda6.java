package com.google.android.accessibility.braille.brailledisplay.platform;

import android.graphics.Bitmap;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer;
import com.google.android.accessibility.talkback.preference.base.TalkBackPreferenceFragment$$ExternalSyntheticLambda6;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import j$.util.function.Consumer$CC;
import java.util.function.Consumer;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class Connectioneer$AspectConnection$$ExternalSyntheticLambda6 implements Consumer {
    private final /* synthetic */ int switching_field;

    public /* synthetic */ Connectioneer$AspectConnection$$ExternalSyntheticLambda6(int i) {
        this.switching_field = i;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.switching_field) {
            case 0:
                ((Connectioneer.AspectConnection.Callback) obj).onScanningChanged();
                return;
            case 1:
                ((Connectioneer.AspectConnection.Callback) obj).onConnectRfcommStarted();
                return;
            case 2:
                ((Connectioneer.AspectConnection.Callback) obj).onConnectHidStarted();
                return;
            case 3:
                ((Connectioneer.AspectConnection.Callback) obj).onDeviceListCleared();
                return;
            case 4:
                ((Connectioneer.AspectEnablement.Callback) obj).onEnablementChanged();
                return;
            case 5:
                ((FloatingActionButton.ShadowDelegateImpl) obj).onRead();
                return;
            case 6:
                ((TalkBackPreferenceFragment$$ExternalSyntheticLambda6) obj).onSurveyAvailable();
                return;
            case 7:
                ((TalkBackPreferenceFragment$$ExternalSyntheticLambda6) obj).onSurveyAvailable();
                return;
            default:
                Bitmap bitmap = (Bitmap) obj;
                if (!bitmap.isRecycled()) {
                    bitmap.recycle();
                    return;
                }
                return;
        }
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.switching_field) {
            case 0:
                return Consumer$CC.$default$andThen(this, consumer);
            case 1:
                return Consumer$CC.$default$andThen(this, consumer);
            case 2:
                return Consumer$CC.$default$andThen(this, consumer);
            case 3:
                return Consumer$CC.$default$andThen(this, consumer);
            case 4:
                return Consumer$CC.$default$andThen(this, consumer);
            case 5:
                return Consumer$CC.$default$andThen(this, consumer);
            case 6:
                return Consumer$CC.$default$andThen(this, consumer);
            case 7:
                return Consumer$CC.$default$andThen(this, consumer);
            default:
                return Consumer$CC.$default$andThen(this, consumer);
        }
    }
}
