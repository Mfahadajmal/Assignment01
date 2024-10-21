package com.google.android.accessibility.talkback.trainingcommon.content;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.BulletSpan;
import android.text.style.TtsSpan;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.accessibility.talkback.trainingcommon.TrainingIpcClient;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Text extends PageContentConfig {
    private final Paragraph[] paragraphs;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Paragraph {
        private final int actionKey;
        private final boolean bulletPoint;
        private final int defaultGestureResId;
        private final boolean link;
        private final boolean subText;
        private final ImmutableList textArgResIds;
        private final int textResId;
        private final String textString;
        private final int textTtsSpanResId;
        private final int textWithActualGestureResId;
        private final String urlLink;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder {
            public int actionKey;
            private boolean bulletPoint;
            public int defaultGestureResId;
            private boolean link;
            public byte set$0;
            private boolean subText;
            private ImmutableList textArgResIds;
            private int textResId;
            public String textString;
            private int textTtsSpanResId;
            public int textWithActualGestureResId;
            public String urlLink;

            public Builder() {
            }

            public final Paragraph autoBuild() {
                ImmutableList immutableList;
                String str;
                if (this.set$0 == -1 && (immutableList = this.textArgResIds) != null && (str = this.urlLink) != null) {
                    return new Paragraph(this.textResId, this.textString, immutableList, this.textWithActualGestureResId, this.textTtsSpanResId, this.actionKey, this.defaultGestureResId, this.bulletPoint, this.subText, this.link, str);
                }
                StringBuilder sb = new StringBuilder();
                if ((this.set$0 & 1) == 0) {
                    sb.append(" textResId");
                }
                if (this.textArgResIds == null) {
                    sb.append(" textArgResIds");
                }
                if ((this.set$0 & 2) == 0) {
                    sb.append(" textWithActualGestureResId");
                }
                if ((this.set$0 & 4) == 0) {
                    sb.append(" textTtsSpanResId");
                }
                if ((this.set$0 & 8) == 0) {
                    sb.append(" actionKey");
                }
                if ((this.set$0 & 16) == 0) {
                    sb.append(" defaultGestureResId");
                }
                if ((this.set$0 & 32) == 0) {
                    sb.append(" bulletPoint");
                }
                if ((this.set$0 & 64) == 0) {
                    sb.append(" subText");
                }
                if ((this.set$0 & 128) == 0) {
                    sb.append(" link");
                }
                if (this.urlLink == null) {
                    sb.append(" urlLink");
                }
                throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
            }

            public final void setBulletPoint$ar$ds(boolean z) {
                this.bulletPoint = z;
                this.set$0 = (byte) (this.set$0 | 32);
            }

            public final void setLink$ar$ds(boolean z) {
                this.link = z;
                this.set$0 = (byte) (this.set$0 | Byte.MIN_VALUE);
            }

            public final void setSubText$ar$ds(boolean z) {
                this.subText = z;
                this.set$0 = (byte) (this.set$0 | 64);
            }

            public final void setTextArgResIds$ar$ds(ImmutableList immutableList) {
                if (immutableList != null) {
                    this.textArgResIds = immutableList;
                    return;
                }
                throw new NullPointerException("Null textArgResIds");
            }

            public final void setTextResId$ar$ds(int i) {
                this.textResId = i;
                this.set$0 = (byte) (this.set$0 | 1);
            }

            public final void setTextTtsSpanResId$ar$ds(int i) {
                this.textTtsSpanResId = i;
                this.set$0 = (byte) (this.set$0 | 4);
            }

            public Builder(byte[] bArr) {
                this();
            }
        }

        public Paragraph() {
        }

        public static Builder builder() {
            Builder builder = new Builder(null);
            builder.setTextResId$ar$ds(-1);
            builder.textString = null;
            int i = ImmutableList.ImmutableList$ar$NoOp;
            builder.setTextArgResIds$ar$ds(RegularImmutableList.EMPTY);
            builder.textWithActualGestureResId = -1;
            builder.set$0 = (byte) (builder.set$0 | 2);
            builder.setTextTtsSpanResId$ar$ds(-1);
            builder.actionKey = -1;
            int i2 = builder.set$0 | 8;
            builder.defaultGestureResId = -1;
            builder.set$0 = (byte) (((byte) i2) | 16);
            builder.setBulletPoint$ar$ds(false);
            builder.setSubText$ar$ds(false);
            builder.setLink$ar$ds(false);
            builder.urlLink = "";
            return builder;
        }

        public final int actionKey() {
            return this.actionKey;
        }

        public final boolean bulletPoint() {
            return this.bulletPoint;
        }

        public final int defaultGestureResId() {
            return this.defaultGestureResId;
        }

        public final boolean equals(Object obj) {
            String str;
            if (obj == this) {
                return true;
            }
            if (obj instanceof Paragraph) {
                Paragraph paragraph = (Paragraph) obj;
                if (this.textResId == paragraph.textResId() && ((str = this.textString) != null ? str.equals(paragraph.textString()) : paragraph.textString() == null) && ContextDataProvider.equalsImpl(this.textArgResIds, paragraph.textArgResIds()) && this.textWithActualGestureResId == paragraph.textWithActualGestureResId() && this.textTtsSpanResId == paragraph.textTtsSpanResId() && this.actionKey == paragraph.actionKey() && this.defaultGestureResId == paragraph.defaultGestureResId() && this.bulletPoint == paragraph.bulletPoint() && this.subText == paragraph.subText() && this.link == paragraph.link() && this.urlLink.equals(paragraph.urlLink())) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            int hashCode;
            int i;
            int i2;
            String str = this.textString;
            if (str == null) {
                hashCode = 0;
            } else {
                hashCode = str.hashCode();
            }
            int hashCode2 = ((hashCode ^ ((this.textResId ^ 1000003) * 1000003)) * 1000003) ^ this.textArgResIds.hashCode();
            int i3 = this.textWithActualGestureResId;
            int i4 = this.textTtsSpanResId;
            int i5 = this.actionKey;
            int i6 = this.defaultGestureResId;
            int i7 = 1237;
            if (true != this.bulletPoint) {
                i = 1237;
            } else {
                i = 1231;
            }
            int i8 = ((((((((((hashCode2 * 1000003) ^ i3) * 1000003) ^ i4) * 1000003) ^ i5) * 1000003) ^ i6) * 1000003) ^ i) * 1000003;
            if (true != this.subText) {
                i2 = 1237;
            } else {
                i2 = 1231;
            }
            int i9 = (i8 ^ i2) * 1000003;
            if (true == this.link) {
                i7 = 1231;
            }
            return ((i9 ^ i7) * 1000003) ^ this.urlLink.hashCode();
        }

        public final boolean link() {
            return this.link;
        }

        public final boolean subText() {
            return this.subText;
        }

        public final ImmutableList textArgResIds() {
            return this.textArgResIds;
        }

        public final int textResId() {
            return this.textResId;
        }

        public final String textString() {
            return this.textString;
        }

        public final int textTtsSpanResId() {
            return this.textTtsSpanResId;
        }

        public final int textWithActualGestureResId() {
            return this.textWithActualGestureResId;
        }

        public final String toString() {
            return "Paragraph{textResId=" + this.textResId + ", textString=" + this.textString + ", textArgResIds=" + String.valueOf(this.textArgResIds) + ", textWithActualGestureResId=" + this.textWithActualGestureResId + ", textTtsSpanResId=" + this.textTtsSpanResId + ", actionKey=" + this.actionKey + ", defaultGestureResId=" + this.defaultGestureResId + ", bulletPoint=" + this.bulletPoint + ", subText=" + this.subText + ", link=" + this.link + ", urlLink=" + this.urlLink + "}";
        }

        public final String urlLink() {
            return this.urlLink;
        }

        public Paragraph(int i, String str, ImmutableList immutableList, int i2, int i3, int i4, int i5, boolean z, boolean z2, boolean z3, String str2) {
            this();
            this.textResId = i;
            this.textString = str;
            this.textArgResIds = immutableList;
            this.textWithActualGestureResId = i2;
            this.textTtsSpanResId = i3;
            this.actionKey = i4;
            this.defaultGestureResId = i5;
            this.bulletPoint = z;
            this.subText = z2;
            this.link = z3;
            this.urlLink = str2;
        }

        public static Builder builder(int i) {
            Builder builder = builder();
            builder.setTextResId$ar$ds(i);
            return builder;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class TextURLSpan extends URLSpan {
        private final Context context;
        private final String urlText;

        public TextURLSpan(Context context, String str, String str2) {
            super(str2);
            this.context = context;
            this.urlText = str;
        }

        @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
        public final void onClick(View view) {
            String url = super.getURL();
            if (TextUtils.isEmpty(url)) {
                super.onClick(view);
                Context context = this.context;
                Toast.makeText(context, context.getString(R.string.activated_view, this.urlText), 1).show();
                return;
            }
            this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
        }
    }

    public Text(Paragraph... paragraphArr) {
        this.paragraphs = paragraphArr;
    }

    private static String getParagraphString(Paragraph paragraph, Context context) {
        String textString = paragraph.textString();
        if (textString != null) {
            return textString;
        }
        return context.getString(paragraph.textResId());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.accessibility.talkback.trainingcommon.content.PageContentConfig
    public View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, TrainingIpcClient.ServiceData serviceData) {
        String paragraphString;
        int i;
        TrainingIpcClient.ServiceData serviceData2 = serviceData;
        int i2 = 0;
        View inflate = layoutInflater.inflate(R.layout.training_text, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.training_text);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        char c = 0;
        boolean z = false;
        boolean z2 = false;
        while (c <= 0) {
            Paragraph paragraph = this.paragraphs[i2];
            z |= paragraph.subText();
            if (!TextUtils.isEmpty(spannableStringBuilder)) {
                spannableStringBuilder.append((CharSequence) "\n\n");
            }
            if (paragraph.textArgResIds() != null && ((RegularImmutableList) paragraph.textArgResIds()).size > 0) {
                Object[] objArr = new Object[((RegularImmutableList) paragraph.textArgResIds()).size];
                for (int i3 = i2; i3 < ((RegularImmutableList) paragraph.textArgResIds()).size; i3++) {
                    objArr[i3] = ContextDataProvider.toLowerCase(context.getString(((Integer) paragraph.textArgResIds().get(i3)).intValue()));
                }
                paragraphString = context.getString(paragraph.textResId(), objArr);
            } else if (paragraph.actionKey() != -1 && paragraph.textWithActualGestureResId() != -1) {
                String str = (String) serviceData2.actionKeyToGestureText.get(serviceData2.context.getString(paragraph.actionKey()));
                if (str == null) {
                    if (paragraph.defaultGestureResId() == -1) {
                        paragraphString = getParagraphString(paragraph, context);
                    } else {
                        int textWithActualGestureResId = paragraph.textWithActualGestureResId();
                        Object[] objArr2 = new Object[1];
                        objArr2[i2] = ContextDataProvider.toLowerCase(context.getString(paragraph.defaultGestureResId()));
                        paragraphString = context.getString(textWithActualGestureResId, objArr2);
                    }
                } else {
                    int textWithActualGestureResId2 = paragraph.textWithActualGestureResId();
                    Object[] objArr3 = new Object[1];
                    objArr3[i2] = ContextDataProvider.toLowerCase(str);
                    paragraphString = context.getString(textWithActualGestureResId2, objArr3);
                }
            } else {
                paragraphString = getParagraphString(paragraph, context);
            }
            SpannableString spannableString = new SpannableString(paragraphString);
            if (paragraph.link()) {
                spannableString.setSpan(new TextURLSpan(context, paragraphString, paragraph.urlLink()), i2, paragraphString.length(), 18);
            }
            if (paragraph.bulletPoint()) {
                if (SpannableUtils$IdentifierSpan.isAtLeastP()) {
                    i = 0;
                    spannableString.setSpan(new BulletSpan(50, context.getColor(R.color.training_text_color), 8), 0, 1, 33);
                } else {
                    i = 0;
                    spannableString.setSpan(new BulletSpan(50, context.getColor(R.color.training_text_color)), 0, 1, 33);
                }
            } else {
                i = i2;
            }
            if (paragraph.textTtsSpanResId() != -1) {
                spannableString.setSpan(new TtsSpan.TextBuilder(context.getString(paragraph.textTtsSpanResId())).build(), i, paragraphString.length(), i);
            }
            spannableStringBuilder.append((CharSequence) spannableString);
            if (!z2 && paragraph.link()) {
                z2 = true;
            }
            serviceData2 = serviceData;
            c = 1;
            i2 = 0;
        }
        if (z) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(0, 0, 0, 0);
            textView.setLayoutParams(layoutParams);
        }
        textView.setText(spannableStringBuilder);
        textView.setElegantTextHeight(true);
        if (z2) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        return inflate;
    }
}
