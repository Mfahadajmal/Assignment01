package com.google.android.accessibility.talkback.trainingcommon.content;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.android.accessibility.talkback.labeling.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.trainingcommon.TrainingIpcClient;
import com.google.android.marvin.talkback.R;
import j$.util.function.Consumer$CC;
import java.util.function.Consumer;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PageButton extends PageContentConfig {
    public Button button;
    public final ButtonOnClickListener buttonOnClickListener;
    public final int textResId;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ButtonOnClickListener extends Consumer {
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'OPEN_READING_MODE_PAGE' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PageButtonAction implements ButtonOnClickListener {
        private static final /* synthetic */ PageButtonAction[] $VALUES;
        public static final PageButtonAction OPEN_LOOKOUT_PAGE;
        public static final PageButtonAction OPEN_READING_MODE_PAGE;
        private final ButtonOnClickListener onClickListener;

        static {
            final int i = 1;
            final int i2 = 0;
            PageButtonAction pageButtonAction = new PageButtonAction("OPEN_READING_MODE_PAGE", 0, new ButtonOnClickListener() { // from class: com.google.android.accessibility.talkback.trainingcommon.content.PageButton$PageButtonAction$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    if (i != 0) {
                        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.accessibility.reader&hl=en_US&gl=US"));
                        intent.setFlags(268435456);
                        ((Context) obj).startActivity(intent);
                    } else {
                        Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.accessibility.reveal&referrer=utm_source%3Dgoogle%26utm_campaign%3Dtbtutorial%26anid%3Dadmob"));
                        intent2.setFlags(268435456);
                        ((Context) obj).startActivity(intent2);
                    }
                }

                public final /* synthetic */ Consumer andThen(Consumer consumer) {
                    if (i != 0) {
                        return Consumer$CC.$default$andThen(this, consumer);
                    }
                    return Consumer$CC.$default$andThen(this, consumer);
                }
            });
            OPEN_READING_MODE_PAGE = pageButtonAction;
            PageButtonAction pageButtonAction2 = new PageButtonAction("OPEN_LOOKOUT_PAGE", 1, new ButtonOnClickListener() { // from class: com.google.android.accessibility.talkback.trainingcommon.content.PageButton$PageButtonAction$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    if (i2 != 0) {
                        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.accessibility.reader&hl=en_US&gl=US"));
                        intent.setFlags(268435456);
                        ((Context) obj).startActivity(intent);
                    } else {
                        Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.accessibility.reveal&referrer=utm_source%3Dgoogle%26utm_campaign%3Dtbtutorial%26anid%3Dadmob"));
                        intent2.setFlags(268435456);
                        ((Context) obj).startActivity(intent2);
                    }
                }

                public final /* synthetic */ Consumer andThen(Consumer consumer) {
                    if (i2 != 0) {
                        return Consumer$CC.$default$andThen(this, consumer);
                    }
                    return Consumer$CC.$default$andThen(this, consumer);
                }
            });
            OPEN_LOOKOUT_PAGE = pageButtonAction2;
            $VALUES = new PageButtonAction[]{pageButtonAction, pageButtonAction2};
        }

        private PageButtonAction(String str, int i, ButtonOnClickListener buttonOnClickListener) {
            this.onClickListener = buttonOnClickListener;
        }

        public static PageButtonAction[] values() {
            return (PageButtonAction[]) $VALUES.clone();
        }

        @Override // java.util.function.Consumer
        public final /* bridge */ /* synthetic */ void accept(Object obj) {
            this.onClickListener.accept((Context) obj);
        }

        public final /* synthetic */ Consumer andThen(Consumer consumer) {
            return Consumer$CC.$default$andThen(this, consumer);
        }
    }

    public PageButton(int i, ButtonOnClickListener buttonOnClickListener) {
        this.textResId = i;
        this.buttonOnClickListener = buttonOnClickListener;
    }

    @Override // com.google.android.accessibility.talkback.trainingcommon.content.PageContentConfig
    public final View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, TrainingIpcClient.ServiceData serviceData) {
        View inflate = layoutInflater.inflate(R.layout.training_button, viewGroup, false);
        Button button = (Button) inflate.findViewById(R.id.training_button);
        button.setText(this.textResId);
        if (this.buttonOnClickListener == null) {
            button.setOnClickListener(new LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0(this, context, 3));
        } else {
            button.setOnClickListener(new LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0(this, context, 4));
        }
        return inflate;
    }
}
