package com.google.android.accessibility.talkback.training;

import com.google.android.accessibility.talkback.trainingcommon.NavigationButtonBar;
import com.google.android.accessibility.talkback.trainingcommon.PageConfig;
import com.google.android.accessibility.talkback.trainingcommon.TrainingConfig;
import com.google.android.accessibility.talkback.trainingcommon.content.LinkCondition;
import com.google.android.accessibility.talkback.trainingcommon.content.PageButton;
import com.google.android.accessibility.talkback.trainingcommon.content.Text;
import com.google.android.accessibility.talkback.trainingcommon.content.TextList;
import com.google.android.accessibility.talkback.trainingcommon.content.TextWithIcon;
import com.google.android.accessibility.talkback.trainingcommon.content.Tip;
import com.google.android.accessibility.talkback.trainingcommon.content.WholeScreenText;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TutorialConfigUtils {
    public static final TrainingConfig TUTORIAL_PRACTICE_GESTURE;
    public static final TrainingConfig TUTORIAL_PRACTICE_GESTURE_PRE_R;

    static {
        TrainingConfig.Builder builder = new TrainingConfig.Builder(R.string.practice_gestures_title);
        builder.addPages$ar$ds(getInitialPageBuilder(PageConfig.PageId.PAGE_ID_PRACTICE_GESTURES));
        builder.buttons = NavigationButtonBar.DEFAULT_BUTTONS;
        TUTORIAL_PRACTICE_GESTURE = builder.build();
        TrainingConfig.Builder builder2 = new TrainingConfig.Builder(R.string.practice_gestures_title);
        builder2.addPages$ar$ds(getInitialPageBuilder(PageConfig.PageId.PAGE_ID_PRACTICE_GESTURES_PRE_R));
        builder2.buttons = NavigationButtonBar.DEFAULT_BUTTONS;
        TUTORIAL_PRACTICE_GESTURE_PRE_R = builder2.build();
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0036, code lost:
    
        r7 = r7.getCurrentSpellCheckerInfo();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static j$.util.Optional getCorrectSpellCheckTutorialPageId(android.content.Context r7) {
        /*
            boolean r0 = com.google.android.accessibility.utils.FeatureSupport.isMultiFingerGestureSupported()
            java.util.Locale r1 = java.util.Locale.getDefault()
            java.lang.String r2 = r1.getLanguage()
            java.util.Locale r3 = java.util.Locale.ENGLISH
            java.lang.String r3 = r3.getLanguage()
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L24
            if (r0 == 0) goto L1d
            com.google.android.accessibility.talkback.trainingcommon.PageConfig$PageId r7 = com.google.android.accessibility.talkback.trainingcommon.PageConfig.PageId.PAGE_ID_TYPO_CORRECTION
            goto L1f
        L1d:
            com.google.android.accessibility.talkback.trainingcommon.PageConfig$PageId r7 = com.google.android.accessibility.talkback.trainingcommon.PageConfig.PageId.PAGE_ID_TYPO_CORRECTION_PRE_R
        L1f:
            j$.util.Optional r7 = j$.util.Optional.of(r7)
            return r7
        L24:
            boolean r2 = _COROUTINE._BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21()
            if (r2 != 0) goto L2c
            goto Laa
        L2c:
            java.lang.String r2 = "textservices"
            java.lang.Object r7 = r7.getSystemService(r2)
            android.view.textservice.TextServicesManager r7 = (android.view.textservice.TextServicesManager) r7
            if (r7 == 0) goto Laa
            android.view.textservice.SpellCheckerInfo r7 = org.chromium.base.BuildInfo$$ExternalSyntheticApiModelOutline0.m(r7)
            if (r7 == 0) goto Laa
            java.lang.String r2 = r1.getLanguage()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 == 0) goto L49
            java.lang.String r2 = ""
            goto L74
        L49:
            java.lang.String r2 = r1.getCountry()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 == 0) goto L58
            java.lang.String r2 = r1.getLanguage()
            goto L74
        L58:
            java.lang.String r2 = r1.getLanguage()
            java.lang.String r3 = r1.getCountry()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r2)
            r2 = 95
            r4.append(r2)
            r4.append(r3)
            java.lang.String r2 = r4.toString()
        L74:
            r3 = 0
        L75:
            int r4 = r7.getSubtypeCount()
            if (r3 >= r4) goto Laa
            android.view.textservice.SpellCheckerSubtype r4 = r7.getSubtypeAt(r3)
            java.lang.String r4 = r4.getLocale()
            boolean r5 = r2.equals(r4)
            if (r5 == 0) goto L8a
            goto L9b
        L8a:
            java.lang.String r5 = r1.getLanguage()
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto La7
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L9b
            goto La7
        L9b:
            if (r0 == 0) goto La0
            com.google.android.accessibility.talkback.trainingcommon.PageConfig$PageId r7 = com.google.android.accessibility.talkback.trainingcommon.PageConfig.PageId.PAGE_ID_TYPO_CORRECTION_NOT_ENGLISH
            goto La2
        La0:
            com.google.android.accessibility.talkback.trainingcommon.PageConfig$PageId r7 = com.google.android.accessibility.talkback.trainingcommon.PageConfig.PageId.PAGE_ID_TYPO_CORRECTION_NOT_ENGLISH_PRE_R
        La2:
            j$.util.Optional r7 = j$.util.Optional.of(r7)
            return r7
        La7:
            int r3 = r3 + 1
            goto L75
        Laa:
            j$.util.Optional r7 = j$.util.Optional.empty()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.training.TutorialConfigUtils.getCorrectSpellCheckTutorialPageId(android.content.Context):j$.util.Optional");
    }

    public static PageConfig.Builder getInitialPageBuilder(PageConfig.PageId pageId) {
        int ordinal = pageId.ordinal();
        int i = 0;
        switch (ordinal) {
            case 3:
                PageConfig.Builder builder = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_WELCOME_TO_TALKBACK, R.string.welcome_to_talkback_title);
                builder.setOnlyOneFocus$ar$ds();
                builder.idleAnnouncementConfig = new PageConfig.IdleAnnouncementConfig(null);
                builder.addExitBanner$ar$ds(PageConfig.PageAndContentPredicate.SUPPORT_EXIT_BANNER);
                builder.addTextWithTtsSpan$ar$ds(R.string.welcome_to_talkback_text, R.string.welcome_to_talkback_text_tts);
                return builder;
            case 4:
                PageConfig.Builder builder2 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_EXPLORE_BY_TOUCH, R.string.explore_by_touch_title);
                builder2.addText$ar$ds(R.string.explore_by_touch_text);
                return builder2;
            case 5:
                PageConfig.Builder builder3 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_SCROLLING, R.string.scrolling_title);
                builder3.addText$ar$ds(R.string.scrolling_text);
                builder3.contents.add(new TextList());
                builder3.contents.add(new WholeScreenText(Text.Paragraph.builder(R.string.scrolling_text_completed).autoBuild()));
                return builder3;
            case 6:
                PageConfig.Builder builder4 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_GESTURES_PAGE_FOR_GESTURE_NAVIGATION_USER, R.string.system_gestures_title);
                builder4.addText$ar$ds(R.string.system_gestures_text);
                builder4.addTextWithIcon$ar$ds(R.string.system_gestures_home_text, R.drawable.ic_gesture_2fingeredgeup);
                builder4.addTextWithIcon$ar$ds(R.string.system_gestures_overview_screen_text, R.drawable.ic_gesture_2fingeredgeuphold);
                builder4.addTextWithIcon$ar$ds(R.string.system_gestures_back_text, R.drawable.ic_gesture_2fingerinward);
                builder4.addTextWithIcon$ar$ds(R.string.system_gestures_open_notifications_text_for_gesture_navigation, R.drawable.ic_gesture_2fingeredgedown);
                PageConfig.PageAndContentPredicate pageAndContentPredicate = PageConfig.PageAndContentPredicate.ACCESSIBILITY_SERVICE_TOGGLE_VIA_SHORTCUT;
                TextWithIcon textWithIcon = new TextWithIcon(R.string.system_gestures_accessibility_shortcut_text, R.drawable.ic_gesture_3fingeredgeup);
                textWithIcon.setShowingPredicate(pageAndContentPredicate);
                builder4.contents.add(textWithIcon);
                return builder4;
            case 7:
                PageConfig.Builder builder5 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_GESTURES_PAGE_FOR_3_BUTTON_NAVIGATION_USER, R.string.system_gestures_title);
                builder5.addTextWithIcon$ar$ds(R.string.system_gestures_open_notifications_text_for_3_button_navigation, R.drawable.ic_gesture_2fingeredgedown);
                return builder5;
            case 8:
                PageConfig.Builder builder6 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_ADJUSTING_VOLUME, R.string.adjusting_volume_title);
                builder6.addText$ar$ds(R.string.adjusting_volume_head);
                builder6.addTextWithBullet$ar$ds(R.string.adjusting_volume_first_instruction);
                builder6.addTextWithBullet$ar$ds$8797f945_0(R.string.adjusting_volume_second_instruction);
                builder6.addText$ar$ds(R.string.adjusting_volume_tail);
                return builder6;
            case 9:
                PageConfig.Builder builder7 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_MENUS, R.string.menus_title);
                builder7.addHeading$ar$ds(R.string.talkback_menu_title);
                builder7.addText$ar$ds(R.string.menus_talkback_menu_text);
                builder7.addHeading$ar$ds(R.string.setting_selector_heading);
                builder7.addText$ar$ds(R.string.menus_selector_text);
                return builder7;
            case 10:
                PageConfig.Builder builder8 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_MENUS_PRE_R, R.string.menus_title);
                builder8.addHeading$ar$ds(R.string.talkback_menu_title);
                builder8.addText$ar$ds(R.string.menus_talkback_menu_text_pre_r);
                builder8.addHeading$ar$ds(R.string.setting_selector_heading);
                builder8.addText$ar$ds(R.string.menus_selector_text_pre_r);
                return builder8;
            case 11:
                PageConfig.Builder builder9 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_TUTORIAL_FINISHED, R.string.all_set_title);
                builder9.addText$ar$ds(R.string.all_set_text);
                builder9.addLink$ar$ds(R.string.text_editing_link_text, R.string.text_editing_link_subtext, R.drawable.ic_text_fields_alt_googblue_24dp, R.string.using_text_boxes_title);
                builder9.addLink$ar$ds(R.string.reading_navigation_link_text, R.string.reading_navigation_link_subtext, R.drawable.ic_chrome_reader_mode_googblue_24dp, R.string.read_by_character_title);
                builder9.addLink$ar$ds(R.string.voice_commands_link_text, R.string.voice_commands_link_subtext, R.drawable.ic_keyboard_voice_googblue_24dp, R.string.voice_commands_title);
                builder9.addLink$ar$ds(R.string.additional_tips_link_text, R.string.additional_tips_link_subtext, R.drawable.ic_tips_and_updates_24dp, R.string.making_calls_title);
                builder9.addLink$ar$ds(R.string.practice_gestures_link_text, R.string.practice_gestures_link_subtext, R.drawable.ic_gesture_googblue_24dp, R.string.practice_gestures_title);
                return builder9;
            case 12:
                PageConfig.Builder builder10 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_TUTORIAL_INDEX, R.string.talkback_tutorial_title);
                builder10.addText$ar$ds(R.string.talkback_tutorial_text);
                builder10.addLink$ar$ds(R.string.basic_navigation_link_text, R.string.basic_navigation_link_subtext, R.drawable.ic_navigation_googblue_24dp, R.string.welcome_to_talkback_title);
                builder10.addLink$ar$ds(R.string.text_editing_link_text, R.string.text_editing_link_subtext, R.drawable.ic_text_fields_alt_googblue_24dp, R.string.using_text_boxes_title);
                builder10.addLink$ar$ds(R.string.reading_navigation_link_text, R.string.reading_navigation_link_subtext, R.drawable.ic_chrome_reader_mode_googblue_24dp, R.string.read_by_character_title);
                builder10.addLink$ar$ds(R.string.voice_commands_link_text, R.string.voice_commands_link_subtext, R.drawable.ic_keyboard_voice_googblue_24dp, R.string.voice_commands_title);
                builder10.addLink$ar$ds(R.string.additional_tips_link_text, R.string.additional_tips_link_subtext, R.drawable.ic_tips_and_updates_24dp, R.string.making_calls_title);
                builder10.contents.add(new LinkCondition(PageConfig.PageAndContentPredicate.TALKBACK_ON, new TutorialConfigUtils$$ExternalSyntheticLambda1(i), new int[]{R.string.practice_gestures_title}));
                return builder10;
            case 13:
                PageConfig.Builder builder11 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_USING_TEXT_BOXES, R.string.using_text_boxes_title);
                builder11.addText$ar$ds(R.string.using_text_boxes_text);
                builder11.addEditTextWithHint$ar$ds(R.string.edit_box_hint);
                return builder11;
            case 14:
                PageConfig.Builder builder12 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_TYPING_TEXT, R.string.typing_text_title);
                builder12.addText$ar$ds(R.string.typing_text_text);
                builder12.addEditTextWithHint$ar$ds(R.string.enter_text_here);
                builder12.addText$ar$ds(R.string.typing_text_with_braille_keyboard_text);
                return builder12;
            case 15:
                PageConfig.Builder builder13 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_MOVING_CURSOR, R.string.moving_cursor_title);
                builder13.addText$ar$ds(R.string.moving_cursor_text);
                builder13.addEditTextWithContent$ar$ds(R.string.edit_box_text);
                return builder13;
            case 16:
                PageConfig.Builder builder14 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_SELECTING_TEXT, R.string.selecting_text_title);
                builder14.addText$ar$ds(R.string.selecting_text_text);
                builder14.addEditTextWithContent$ar$ds(R.string.edit_box_text);
                return builder14;
            case 17:
                PageConfig.Builder builder15 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_SELECTING_TEXT_PRE_R, R.string.selecting_text_title);
                builder15.addText$ar$ds(R.string.selecting_text_text_pre_r);
                builder15.addEditTextWithContent$ar$ds(R.string.edit_box_text);
                return builder15;
            case 18:
                PageConfig.Builder builder16 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_COPY_CUT_PASTE, R.string.copy_cut_paste_title);
                builder16.addText$ar$ds(R.string.copy_text);
                builder16.addEditTextWithContent$ar$ds(R.string.edit_box_text);
                builder16.addText$ar$ds(R.string.cut_paste_text);
                builder16.addEditTextWithHint$ar$ds(R.string.edit_box_hint_paste_text);
                return builder16;
            case 19:
                PageConfig.Builder builder17 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_COPY_CUT_PASTE_PRE_R, R.string.copy_cut_paste_title);
                builder17.addText$ar$ds(R.string.copy_text_pre_r);
                builder17.addEditTextWithContent$ar$ds(R.string.edit_box_text);
                builder17.addText$ar$ds(R.string.cut_paste_text_pre_r);
                builder17.addEditTextWithHint$ar$ds(R.string.edit_box_hint_paste_text);
                return builder17;
            case 20:
                PageConfig.Builder builder18 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_TYPO_CORRECTION, R.string.typo_correction_title);
                builder18.addText$ar$ds(R.string.typo_correction_text);
                builder18.addEditTextWithContent$ar$ds(R.string.typo_correction_typo_example);
                return builder18;
            case 21:
                PageConfig.Builder builder19 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_TYPO_CORRECTION_PRE_R, R.string.typo_correction_title);
                builder19.addText$ar$ds(R.string.typo_correction_text_pre_r);
                builder19.addEditTextWithContent$ar$ds(R.string.typo_correction_typo_example);
                return builder19;
            case 22:
                PageConfig.Builder builder20 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_TYPO_CORRECTION_NOT_ENGLISH, R.string.typo_correction_title);
                builder20.addText$ar$ds(R.string.typo_correction_text_supported_but_not_english);
                builder20.addEditTextWithHint$ar$ds(R.string.typo_correction_editbox_hint);
                return builder20;
            case 23:
                PageConfig.Builder builder21 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_TYPO_CORRECTION_NOT_ENGLISH_PRE_R, R.string.typo_correction_title);
                builder21.addText$ar$ds(R.string.typo_correction_text_supported_but_not_english_pre_r);
                builder21.addEditTextWithHint$ar$ds(R.string.typo_correction_editbox_hint);
                return builder21;
            case 24:
                PageConfig.Builder builder22 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_READ_BY_CHARACTER, R.string.read_by_character_title);
                builder22.addText$ar$ds$ac980ab3_0(R.string.read_by_character_text, ImmutableList.of((Object) Integer.valueOf(R.string.granularity_character)));
                return builder22;
            case 25:
                PageConfig.Builder builder23 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_READ_BY_CHARACTER_PRE_R, R.string.read_by_character_title);
                builder23.addText$ar$ds$ac980ab3_0(R.string.read_by_character_text_pre_r, ImmutableList.of((Object) Integer.valueOf(R.string.granularity_character)));
                return builder23;
            case 26:
                PageConfig.Builder builder24 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_JUMP_BETWEEN_CONTROLS, R.string.jump_between_controls_title);
                builder24.addText$ar$ds$ac980ab3_0(R.string.jump_between_controls_text, ImmutableList.of((Object) Integer.valueOf(R.string.granularity_native_control)));
                builder24.addButton$ar$ds(R.string.button_1);
                builder24.addButton$ar$ds(R.string.button_2);
                builder24.addButton$ar$ds(R.string.button_3);
                builder24.addButton$ar$ds(R.string.button_4);
                return builder24;
            case 27:
                PageConfig.Builder builder25 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_JUMP_BETWEEN_CONTROLS_PRE_R, R.string.jump_between_controls_title);
                builder25.addText$ar$ds$ac980ab3_0(R.string.jump_between_controls_text_pre_r, ImmutableList.of((Object) Integer.valueOf(R.string.granularity_native_control)));
                builder25.addButton$ar$ds(R.string.button_1);
                builder25.addButton$ar$ds(R.string.button_2);
                builder25.addButton$ar$ds(R.string.button_3);
                builder25.addButton$ar$ds(R.string.button_4);
                return builder25;
            case 28:
                PageConfig.Builder builder26 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_JUMP_BETWEEN_LINKS, R.string.jump_between_links_title);
                builder26.addText$ar$ds$ac980ab3_0(R.string.jump_between_links_text, ImmutableList.of((Object) Integer.valueOf(R.string.granularity_native_link)));
                builder26.addText$ar$ds(R.string.paragraph1_text);
                builder26.addTextWithLink$ar$ds(R.string.link1_text);
                builder26.addText$ar$ds(R.string.paragraph2_text);
                builder26.addTextWithLink$ar$ds(R.string.link2_text);
                builder26.addText$ar$ds(R.string.paragraph3_text);
                builder26.addTextWithLink$ar$ds(R.string.link3_text);
                builder26.addText$ar$ds(R.string.paragraph4_text);
                builder26.addTextWithLink$ar$ds(R.string.link4_text);
                builder26.addTextWithLink$ar$ds(R.string.target_link_text);
                return builder26;
            case 29:
                PageConfig.Builder builder27 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_JUMP_BETWEEN_LINKS_PRE_R, R.string.jump_between_links_title);
                builder27.addText$ar$ds$ac980ab3_0(R.string.jump_between_links_text_pre_r, ImmutableList.of((Object) Integer.valueOf(R.string.granularity_native_link)));
                builder27.addText$ar$ds(R.string.paragraph1_text);
                builder27.addTextWithLink$ar$ds(R.string.link1_text);
                builder27.addText$ar$ds(R.string.paragraph2_text);
                builder27.addTextWithLink$ar$ds(R.string.link2_text);
                builder27.addText$ar$ds(R.string.paragraph3_text);
                builder27.addTextWithLink$ar$ds(R.string.link3_text);
                builder27.addText$ar$ds(R.string.paragraph4_text);
                builder27.addTextWithLink$ar$ds(R.string.link4_text);
                builder27.addTextWithLink$ar$ds(R.string.target_link_text);
                return builder27;
            case 30:
                PageConfig.Builder builder28 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_JUMP_BETWEEN_HEADINGS, R.string.jump_between_headings_title);
                builder28.addText$ar$ds$ac980ab3_0(R.string.jump_between_headings_text, ImmutableList.of((Object) Integer.valueOf(R.string.granularity_native_heading)));
                builder28.addDivider$ar$ds();
                builder28.addHeading$ar$ds(R.string.content_heading);
                builder28.addText$ar$ds(R.string.content_text);
                builder28.addDivider$ar$ds();
                builder28.addHeading$ar$ds(R.string.navigation_heading);
                builder28.addText$ar$ds(R.string.find_finish_button_text);
                return builder28;
            case 31:
                PageConfig.Builder builder29 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_JUMP_BETWEEN_HEADINGS_PRE_R, R.string.jump_between_headings_title);
                builder29.addText$ar$ds$ac980ab3_0(R.string.jump_between_headings_text_pre_r, ImmutableList.of((Object) Integer.valueOf(R.string.granularity_native_heading)));
                builder29.addDivider$ar$ds();
                builder29.addHeading$ar$ds(R.string.content_heading);
                builder29.addText$ar$ds(R.string.content_text);
                builder29.addDivider$ar$ds();
                builder29.addHeading$ar$ds(R.string.navigation_heading);
                builder29.addText$ar$ds(R.string.find_finish_button_text);
                return builder29;
            case 32:
                PageConfig.Builder builder30 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_VOICE_COMMANDS, R.string.voice_commands_title);
                builder30.addText$ar$ds(R.string.voice_commands_text);
                return builder30;
            case 33:
                PageConfig.Builder builder31 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_PRACTICE_GESTURES, R.string.practice_gestures_title);
                builder31.addText$ar$ds(R.string.practice_gestures_text);
                builder31.captureAllGestures$ar$ds();
                return builder31;
            case 34:
                PageConfig.Builder builder32 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_PRACTICE_GESTURES_PRE_R, R.string.practice_gestures_title);
                builder32.addText$ar$ds(R.string.practice_gestures_text_pre_r);
                builder32.captureAllGestures$ar$ds();
                return builder32;
            default:
                switch (ordinal) {
                    case 42:
                        PageConfig.Builder builder33 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_ADDITIONAL_TIPS_MAKING_CALLS, R.string.making_calls_title);
                        builder33.addText$ar$ds(R.string.making_calls_text);
                        builder33.addTip$ar$ds(R.string.making_calls_tips);
                        return builder33;
                    case 43:
                        PageConfig.Builder builder34 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_ADDITIONAL_TIPS_SENDING_MESSAGES, R.string.sending_messages_title);
                        builder34.addText$ar$ds(R.string.sending_messages_text);
                        builder34.addTip$ar$ds(R.string.sending_message_tips);
                        return builder34;
                    case 44:
                        PageConfig.Builder builder35 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_ADDITIONAL_TIPS_READING_WEB_EMAILS, R.string.reading_web_emails_title);
                        builder35.addText$ar$ds(R.string.reading_web_emails_text);
                        builder35.addButton$ar$ds$de42c80f_0(PageButton.PageButtonAction.OPEN_READING_MODE_PAGE);
                        builder35.contents.add(new Tip(R.string.reading_web_emails_tips, R.string.reading_web_emails_tips_tts));
                        return builder35;
                    case 45:
                        PageConfig.Builder builder36 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_ADDITIONAL_TIPS_LOOKOUT, R.string.lookout_title);
                        builder36.addText$ar$ds(R.string.lookout_text);
                        builder36.addButton$ar$ds$de42c80f_0(PageButton.PageButtonAction.OPEN_LOOKOUT_PAGE);
                        builder36.addTip$ar$ds(R.string.lookout_tips);
                        return builder36;
                    case 46:
                        PageConfig.Builder builder37 = new PageConfig.Builder(PageConfig.PageId.PAGE_ID_ADDITIONAL_TIPS_CHECKING_NOTIFICATIONS, R.string.checking_notifications_title);
                        builder37.addText$ar$ds(R.string.checking_notifications_text_head);
                        builder37.addTextWithBullet$ar$ds(R.string.checking_notifications_step_one);
                        builder37.addTextWithBullet$ar$ds$8797f945_0(R.string.checking_notifications_step_two);
                        builder37.addTextWithTtsSpan$ar$ds(R.string.checking_notifications_text_tail, R.string.checking_notifications_text_tail_tts);
                        builder37.addTip$ar$ds(R.string.checking_notifications_tips);
                        return builder37;
                    default:
                        LogUtils.w("TutorialConfigUtils", "No matched: ".concat(String.valueOf(String.valueOf(pageId))), new Object[0]);
                        return null;
                }
        }
    }
}
