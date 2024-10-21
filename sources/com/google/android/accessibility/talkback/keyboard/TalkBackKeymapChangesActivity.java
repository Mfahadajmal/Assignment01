package com.google.android.accessibility.talkback.keyboard;

import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.text.HtmlCompat;
import com.google.android.accessibility.selecttospeak.UIManager$$ExternalSyntheticLambda2;
import com.google.android.accessibility.utils.preference.BasePreferencesActivity;
import com.google.android.marvin.talkback.R;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TalkBackKeymapChangesActivity extends BasePreferencesActivity {
    static final Uri HYPERLINK_DEFAULT_KEYMAP_HELP_PAGE = Uri.parse("https://support.google.com/accessibility/android/answer/6110948");

    @Override // com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity, android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        Spanned fromHtml;
        super.onCreate(bundle);
        setContentView(R.layout.activity_talkback_keymap_changes);
        fromHtml = HtmlCompat.Api24Impl.fromHtml(getString(R.string.keycombo_keymap_changes_activity_content), 0);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(fromHtml);
        spannableStringBuilder.append((CharSequence) "\n");
        SpannableString valueOf = SpannableString.valueOf(getString(R.string.keycombo_keymap_changes_activity_content_learn_more));
        valueOf.setSpan(new URLSpan(String.valueOf(HYPERLINK_DEFAULT_KEYMAP_HELP_PAGE) + "?hl=" + Locale.getDefault().toLanguageTag()), 0, valueOf.length(), 33);
        spannableStringBuilder.append((CharSequence) valueOf);
        TextView textView = (TextView) findViewById(R.id.content);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(spannableStringBuilder);
        ((Button) findViewById(R.id.open_settings)).setOnClickListener(new UIManager$$ExternalSyntheticLambda2(this, 17));
    }
}
