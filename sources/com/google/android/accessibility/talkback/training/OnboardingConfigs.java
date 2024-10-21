package com.google.android.accessibility.talkback.training;

import com.google.android.accessibility.talkback.trainingcommon.NavigationButtonBar;
import com.google.android.accessibility.talkback.trainingcommon.PageConfig;
import com.google.android.accessibility.talkback.trainingcommon.TrainingConfig;
import com.google.android.accessibility.talkback.trainingcommon.content.Image;
import com.google.android.accessibility.talkback.trainingcommon.content.Note;
import com.google.android.accessibility.talkback.trainingcommon.content.Text;
import com.google.android.accessibility.talkback.trainingcommon.content.TextWithIcon;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnboardingConfigs {
    public static final TrainingConfig ON_BOARDING_FOR_MULTIFINGER_GESTURES;
    public static final PageConfig.Builder googleDisabilitySupport;
    public static final PageConfig.Builder imageDescription;
    public static final PageConfig.Builder newBrailleShortcuts;
    public static final PageConfig.Builder punctuationAndSymbols;
    public static final PageConfig.Builder updateWelcome;
    public static final PageConfig.Builder welcomeToUpdatedTalkBackForMultiFingerGestures;

    static {
        PageConfig.Builder builder = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_WELCOME_TO_UPDATED_TALKBACK_FOR_MULTIFINGER_GESTURES, R.string.welcome_to_updated_talkback_title);
        builder.addText$ar$ds(R.string.welcome_to_android11_text);
        PageConfig.PageAndContentPredicate pageAndContentPredicate = PageConfig.PageAndContentPredicate.GESTURE_CHANGED;
        Note note = new Note();
        note.setShowingPredicate(pageAndContentPredicate);
        builder.contents.add(note);
        builder.contents.add(new TextWithIcon());
        builder.captureGesture$ar$ds(20, R.string.new_shortcut_gesture_pause_media_announcement);
        builder.addTextWithIcon$ar$ds(R.string.new_shortcut_gesture_stop_speech_text, R.drawable.ic_gesture_2fingertap);
        builder.captureGesture$ar$ds(19, R.string.new_shortcut_gesture_stop_speech_announcement);
        builder.addTextWithIcon$ar$ds(R.string.new_shortcut_gesture_reading_menu_text, R.drawable.ic_gesture_3fingerright);
        builder.addTextWithIcon$ar$ds(R.string.new_shortcut_gesture_copy_text_text, R.drawable.ic_gesture_3fingerdoubletap);
        builder.captureGesture$ar$ds(23, R.string.new_shortcut_gesture_copy_text_announcement);
        builder.addTextWithIcon$ar$ds(R.string.new_shortcut_gesture_paste_text_text, R.drawable.ic_gesture_3fingertripletap);
        builder.captureGesture$ar$ds(24, R.string.new_shortcut_gesture_paste_text_announcement);
        builder.addTextWithIcon$ar$ds(R.string.new_shortcut_gesture_cut_text_text, R.drawable.ic_gesture_3fingerdoubletaphold);
        builder.captureGesture$ar$ds(41, R.string.new_shortcut_gesture_cut_text_announcement);
        builder.addTextWithIcon$ar$ds(R.string.new_shortcut_gesture_selection_mode_text, R.drawable.ic_gesture_2fingerdoubletaphold);
        builder.captureGesture$ar$ds(40, R.string.new_shortcut_gesture_selection_mode_on_announcement);
        welcomeToUpdatedTalkBackForMultiFingerGestures = builder;
        PageConfig.Builder builder2 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_UPDATE_WELCOME, R.string.welcome_to_updated_talkback_title);
        builder2.addExitBanner$ar$ds(PageConfig.PageAndContentPredicate.SUPPORT_EXIT_BANNER);
        builder2.addText$ar$ds(R.string.update_welcome_text_head);
        builder2.addTextWithBullet$ar$ds(R.string.update_welcome_text_item_one);
        builder2.addTextWithBullet$ar$ds$8797f945_0(R.string.update_welcome_text_item_two);
        builder2.addTextWithBullet$ar$ds$8797f945_0(R.string.update_welcome_text_item_three);
        builder2.addTextWithBullet$ar$ds$8797f945_0(R.string.update_welcome_text_item_four);
        builder2.addTextWithBullet$ar$ds$8797f945_0(R.string.update_welcome_text_item_five);
        builder2.addTextWithTtsSpan$ar$ds(R.string.update_welcome_text_tail, R.string.update_welcome_text_tail_tts);
        updateWelcome = builder2;
        PageConfig.Builder builder3 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_DETAILED_IMAGE_DESCRIPTIONS, R.string.onboarding_detailed_image_descriptions_title);
        builder3.addText$ar$ds(R.string.onboarding_detailed_image_descriptions_content_1);
        builder3.contents.add(new Image());
        builder3.addTextWithTtsSpan$ar$ds(R.string.onboarding_detailed_image_descriptions_content_2, R.string.onboarding_detailed_image_descriptions_content_2_tts);
        builder3.addText$ar$ds(R.string.onboarding_detailed_image_descriptions_content_3);
        List list = builder3.contents;
        Text.Paragraph.Builder builder4 = Text.Paragraph.builder(R.string.onboarding_detailed_image_descriptions_content_4);
        builder4.setLink$ar$ds(true);
        builder4.urlLink = "https://support.google.com/accessibility/android/answer/11101402";
        builder4.setSubText$ar$ds(true);
        list.add(new Text(builder4.autoBuild()));
        imageDescription = builder3;
        PageConfig.Builder builder5 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_GOOGLE_DISABILITY_SUPPORT, R.string.onboarding_google_disability_support_title);
        builder5.addTextWithTtsSpan$ar$ds(R.string.onboarding_google_disability_support_content, R.string.onboarding_google_disability_support_content_tts);
        builder5.addTextWithLink$ar$ds$31d16ee8_0(R.string.onboarding_google_disability_support_content_with_link, "https://support.google.com/accessibility/answer/7641084");
        googleDisabilitySupport = builder5;
        PageConfig.Builder builder6 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_PUNCTUATION_AND_SYMBOLS, R.string.onboarding_punctuation_and_symbols_title);
        builder6.addTextWithTtsSpan$ar$ds(R.string.onboarding_punctuation_and_symbols_content, R.string.onboarding_punctuation_and_symbols_content_tts);
        builder6.addTextWithLink$ar$ds$31d16ee8_0(R.string.onboarding_punctuation_and_symbols_content_with_link, "https://support.google.com/accessibility/android/answer/6283655?ref_topic=10601571&sjid=10534216736552497237-NA#zippy=%2Cchange-verbosity");
        punctuationAndSymbols = builder6;
        PageConfig.Builder builder7 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_NEW_BRAILLE_SHORTCUTS, R.string.new_braille_shortcuts_and_languages_title);
        builder7.addText$ar$ds(R.string.new_braille_shortcuts_introduction);
        builder7.addHeading$ar$ds(R.string.new_braille_shortcuts_heading_one);
        builder7.addTextWithBullet$ar$ds(R.string.new_braille_display_shortcuts_bullet_point_one);
        builder7.addTextWithBullet$ar$ds$8797f945_0(R.string.new_braille_display_shortcuts_bullet_point_two);
        builder7.addHeading$ar$ds(R.string.new_braille_shortcuts_heading_two);
        builder7.addTextWithBullet$ar$ds(R.string.new_braille_keyboard_shortcuts_bullet_point_one);
        builder7.addTextWithBullet$ar$ds$8797f945_0(R.string.new_braille_keyboard_shortcuts_bullet_point_two);
        builder7.addText$ar$ds(R.string.new_braille_shortcuts_end);
        newBrailleShortcuts = builder7;
        TrainingConfig.Builder builder8 = new TrainingConfig.Builder(R.string.welcome_to_updated_talkback_title);
        builder8.setPages$ar$ds(ImmutableList.of((Object) builder));
        builder8.buttons = ImmutableList.of((Object) 2);
        ON_BOARDING_FOR_MULTIFINGER_GESTURES = builder8.build();
    }

    public static TrainingConfig.Builder constructOnBoardingConfigBuilder() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(updateWelcome);
        arrayList.add(imageDescription);
        arrayList.add(googleDisabilitySupport);
        arrayList.add(punctuationAndSymbols);
        TrainingConfig.Builder builder = new TrainingConfig.Builder(R.string.new_feature_in_talkback_title);
        builder.setPages$ar$ds(arrayList);
        TrainingConfig.Builder addPageEndOfSection = builder.addPageEndOfSection(newBrailleShortcuts);
        addPageEndOfSection.buttons = NavigationButtonBar.DEFAULT_BUTTONS;
        return addPageEndOfSection;
    }
}
