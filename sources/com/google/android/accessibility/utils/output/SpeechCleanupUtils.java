package com.google.android.accessibility.utils.output;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import androidx.core.util.Pair;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SpeechCleanupUtils {
    private static final Pattern CONSECUTIVE_CHARACTER_PATTERN = Pattern.compile("([\\-\\\\/|!@#$%^&*\\(\\)=_+\\[\\]\\{\\}.?;'\":<>\\u2022])\\1+");
    private static final ImmutableMap TALKBACK_PUNCTUATION_AND_SYMBOL;

    static {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        builder.put$ar$ds$de9b9d28_0('&', new Pair(Integer.valueOf(R.string.symbol_ampersand), 1));
        builder.put$ar$ds$de9b9d28_0('<', new Pair(Integer.valueOf(R.string.symbol_angle_bracket_left), 1));
        builder.put$ar$ds$de9b9d28_0('>', new Pair(Integer.valueOf(R.string.symbol_angle_bracket_right), 1));
        builder.put$ar$ds$de9b9d28_0('\'', new Pair(Integer.valueOf(R.string.symbol_apostrophe), 0));
        builder.put$ar$ds$de9b9d28_0('*', new Pair(Integer.valueOf(R.string.symbol_asterisk), 1));
        builder.put$ar$ds$de9b9d28_0('@', new Pair(Integer.valueOf(R.string.symbol_at_sign), 1));
        builder.put$ar$ds$de9b9d28_0('\\', new Pair(Integer.valueOf(R.string.symbol_backslash), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8226, new Pair(Integer.valueOf(R.string.symbol_bullet), 1));
        builder.put$ar$ds$de9b9d28_0('^', new Pair(Integer.valueOf(R.string.symbol_caret), 1));
        builder.put$ar$ds$de9b9d28_0((char) 162, new Pair(Integer.valueOf(R.string.symbol_cent), 0));
        builder.put$ar$ds$de9b9d28_0(':', new Pair(Integer.valueOf(R.string.symbol_colon), 1));
        builder.put$ar$ds$de9b9d28_0(',', new Pair(Integer.valueOf(R.string.symbol_comma), 0));
        builder.put$ar$ds$de9b9d28_0((char) 169, new Pair(Integer.valueOf(R.string.symbol_copyright), 1));
        builder.put$ar$ds$de9b9d28_0('{', new Pair(Integer.valueOf(R.string.symbol_curly_bracket_left), 1));
        builder.put$ar$ds$de9b9d28_0('}', new Pair(Integer.valueOf(R.string.symbol_curly_bracket_right), 1));
        builder.put$ar$ds$de9b9d28_0((char) 176, new Pair(Integer.valueOf(R.string.symbol_degree), 1));
        builder.put$ar$ds$de9b9d28_0((char) 247, new Pair(Integer.valueOf(R.string.symbol_division), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8230, new Pair(Integer.valueOf(R.string.symbol_ellipsis), 0));
        builder.put$ar$ds$de9b9d28_0((char) 8212, new Pair(Integer.valueOf(R.string.symbol_em_dash), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8211, new Pair(Integer.valueOf(R.string.symbol_en_dash), 1));
        builder.put$ar$ds$de9b9d28_0('!', new Pair(Integer.valueOf(R.string.symbol_exclamation_mark), 0));
        builder.put$ar$ds$de9b9d28_0('`', new Pair(Integer.valueOf(R.string.symbol_grave_accent), 1));
        builder.put$ar$ds$de9b9d28_0('-', new Pair(Integer.valueOf(R.string.symbol_hyphen_minus), 0));
        builder.put$ar$ds$de9b9d28_0((char) 8222, new Pair(Integer.valueOf(R.string.symbol_low_double_quote), 1));
        builder.put$ar$ds$de9b9d28_0((char) 215, new Pair(Integer.valueOf(R.string.symbol_multiplication), 1));
        builder.put$ar$ds$de9b9d28_0('\n', new Pair(Integer.valueOf(R.string.symbol_new_line), 0));
        builder.put$ar$ds$de9b9d28_0((char) 182, new Pair(Integer.valueOf(R.string.symbol_paragraph_mark), 1));
        builder.put$ar$ds$de9b9d28_0('(', new Pair(Integer.valueOf(R.string.symbol_parenthesis_left), 1));
        builder.put$ar$ds$de9b9d28_0(')', new Pair(Integer.valueOf(R.string.symbol_parenthesis_right), 1));
        builder.put$ar$ds$de9b9d28_0('%', new Pair(Integer.valueOf(R.string.symbol_percent), 1));
        builder.put$ar$ds$de9b9d28_0('.', new Pair(Integer.valueOf(R.string.symbol_period), 0));
        builder.put$ar$ds$de9b9d28_0((char) 960, new Pair(Integer.valueOf(R.string.symbol_pi), 1));
        builder.put$ar$ds$de9b9d28_0('#', new Pair(Integer.valueOf(R.string.symbol_pound), 0));
        builder.put$ar$ds$de9b9d28_0('$', new Pair(Integer.valueOf(R.string.symbol_dollar_sign), 0));
        builder.put$ar$ds$de9b9d28_0((char) 8364, new Pair(Integer.valueOf(R.string.symbol_euro), 0));
        builder.put$ar$ds$de9b9d28_0((char) 163, new Pair(Integer.valueOf(R.string.symbol_pound_sterling), 0));
        builder.put$ar$ds$de9b9d28_0((char) 165, new Pair(Integer.valueOf(R.string.symbol_yen), 0));
        builder.put$ar$ds$de9b9d28_0((char) 8369, new Pair(Integer.valueOf(R.string.symbol_currency_peso), 0));
        builder.put$ar$ds$de9b9d28_0((char) 8377, new Pair(Integer.valueOf(R.string.symbol_rupee), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8363, new Pair(Integer.valueOf(R.string.symbol_currency_dong), 0));
        builder.put$ar$ds$de9b9d28_0((char) 164, new Pair(Integer.valueOf(R.string.symbol_currency_sign), 0));
        builder.put$ar$ds$de9b9d28_0('?', new Pair(Integer.valueOf(R.string.symbol_question_mark), 0));
        builder.put$ar$ds$de9b9d28_0('\"', new Pair(Integer.valueOf(R.string.symbol_quotation_mark), 1));
        builder.put$ar$ds$de9b9d28_0((char) 174, new Pair(Integer.valueOf(R.string.symbol_registered_trademark), 1));
        builder.put$ar$ds$de9b9d28_0(';', new Pair(Integer.valueOf(R.string.symbol_semicolon), 1));
        builder.put$ar$ds$de9b9d28_0('/', new Pair(Integer.valueOf(R.string.symbol_slash), 1));
        Integer valueOf = Integer.valueOf(R.string.symbol_space);
        builder.put$ar$ds$de9b9d28_0(' ', new Pair(valueOf, 0));
        builder.put$ar$ds$de9b9d28_0((char) 160, new Pair(valueOf, 0));
        builder.put$ar$ds$de9b9d28_0('[', new Pair(Integer.valueOf(R.string.symbol_square_bracket_left), 1));
        builder.put$ar$ds$de9b9d28_0(']', new Pair(Integer.valueOf(R.string.symbol_square_bracket_right), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8730, new Pair(Integer.valueOf(R.string.symbol_square_root), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8482, new Pair(Integer.valueOf(R.string.symbol_trademark), 1));
        builder.put$ar$ds$de9b9d28_0('_', new Pair(Integer.valueOf(R.string.symbol_underscore), 1));
        builder.put$ar$ds$de9b9d28_0('|', new Pair(Integer.valueOf(R.string.symbol_vertical_bar), 1));
        builder.put$ar$ds$de9b9d28_0((char) 172, new Pair(Integer.valueOf(R.string.symbol_not_sign), 1));
        builder.put$ar$ds$de9b9d28_0((char) 166, new Pair(Integer.valueOf(R.string.symbol_broken_bar), 1));
        builder.put$ar$ds$de9b9d28_0((char) 181, new Pair(Integer.valueOf(R.string.symbol_micro_sign), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8776, new Pair(Integer.valueOf(R.string.symbol_almost_equals), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8800, new Pair(Integer.valueOf(R.string.symbol_not_equals), 1));
        builder.put$ar$ds$de9b9d28_0((char) 167, new Pair(Integer.valueOf(R.string.symbol_section_sign), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8593, new Pair(Integer.valueOf(R.string.symbol_upwards_arrow), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8592, new Pair(Integer.valueOf(R.string.symbol_leftwards_arrow), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8594, new Pair(Integer.valueOf(R.string.symbol_rightwards_arrow), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8595, new Pair(Integer.valueOf(R.string.symbol_downwards_arrow), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9829, new Pair(Integer.valueOf(R.string.symbol_black_heart), 1));
        builder.put$ar$ds$de9b9d28_0('~', new Pair(Integer.valueOf(R.string.symbol_tilde), 1));
        builder.put$ar$ds$de9b9d28_0('=', new Pair(Integer.valueOf(R.string.symbol_equal), 1));
        builder.put$ar$ds$de9b9d28_0((char) 65510, new Pair(Integer.valueOf(R.string.symbol_won), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8361, new Pair(Integer.valueOf(R.string.symbol_won_sign), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8251, new Pair(Integer.valueOf(R.string.symbol_reference), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9734, new Pair(Integer.valueOf(R.string.symbol_white_star), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9733, new Pair(Integer.valueOf(R.string.symbol_black_star), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9825, new Pair(Integer.valueOf(R.string.symbol_white_heart), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9675, new Pair(Integer.valueOf(R.string.symbol_white_circle), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9679, new Pair(Integer.valueOf(R.string.symbol_black_circle), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8857, new Pair(Integer.valueOf(R.string.symbol_solar), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9678, new Pair(Integer.valueOf(R.string.symbol_bullseye), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9831, new Pair(Integer.valueOf(R.string.symbol_white_club_suit), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9828, new Pair(Integer.valueOf(R.string.symbol_white_spade_suit), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9756, new Pair(Integer.valueOf(R.string.symbol_white_left_pointing_index), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9758, new Pair(Integer.valueOf(R.string.symbol_white_right_pointing_index), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9680, new Pair(Integer.valueOf(R.string.symbol_circle_left_half_black), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9681, new Pair(Integer.valueOf(R.string.symbol_circle_right_half_black), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9633, new Pair(Integer.valueOf(R.string.symbol_white_square), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9632, new Pair(Integer.valueOf(R.string.symbol_black_square), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9651, new Pair(Integer.valueOf(R.string.symbol_white_up_pointing_triangle), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9661, new Pair(Integer.valueOf(R.string.symbol_white_down_pointing_triangle), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9665, new Pair(Integer.valueOf(R.string.symbol_white_left_pointing_triangle), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9655, new Pair(Integer.valueOf(R.string.symbol_white_right_pointing_triangle), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9671, new Pair(Integer.valueOf(R.string.symbol_white_diamond), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9833, new Pair(Integer.valueOf(R.string.symbol_quarter_note), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9834, new Pair(Integer.valueOf(R.string.symbol_eighth_note), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9836, new Pair(Integer.valueOf(R.string.symbol_beamed_sixteenth_note), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9792, new Pair(Integer.valueOf(R.string.symbol_female), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9794, new Pair(Integer.valueOf(R.string.symbol_male), 1));
        builder.put$ar$ds$de9b9d28_0((char) 12304, new Pair(Integer.valueOf(R.string.symbol_left_black_lenticular_bracket), 1));
        builder.put$ar$ds$de9b9d28_0((char) 12305, new Pair(Integer.valueOf(R.string.symbol_right_black_lenticular_bracket), 1));
        builder.put$ar$ds$de9b9d28_0((char) 12300, new Pair(Integer.valueOf(R.string.symbol_left_corner_bracket), 1));
        builder.put$ar$ds$de9b9d28_0((char) 12301, new Pair(Integer.valueOf(R.string.symbol_right_corner_bracket), 1));
        builder.put$ar$ds$de9b9d28_0((char) 177, new Pair(Integer.valueOf(R.string.symbol_plus_minus_sign), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8467, new Pair(Integer.valueOf(R.string.symbol_liter), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8451, new Pair(Integer.valueOf(R.string.symbol_celsius_degree), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8457, new Pair(Integer.valueOf(R.string.symbol_fahrenheit_degree), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8786, new Pair(Integer.valueOf(R.string.symbol_approximately_equals), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8747, new Pair(Integer.valueOf(R.string.symbol_integral), 1));
        builder.put$ar$ds$de9b9d28_0((char) 10216, new Pair(Integer.valueOf(R.string.symbol_mathematical_left_angle_bracket), 1));
        builder.put$ar$ds$de9b9d28_0((char) 10217, new Pair(Integer.valueOf(R.string.symbol_mathematical_right_angle_bracket), 1));
        builder.put$ar$ds$de9b9d28_0((char) 12306, new Pair(Integer.valueOf(R.string.symbol_postal_mark), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9650, new Pair(Integer.valueOf(R.string.symbol_black_triangle_pointing_up), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9660, new Pair(Integer.valueOf(R.string.symbol_black_triangle_pointing_down), 1));
        Integer valueOf2 = Integer.valueOf(R.string.symbol_black_suit_of_diamonds);
        builder.put$ar$ds$de9b9d28_0((char) 9830, new Pair(valueOf2, 1));
        builder.put$ar$ds$de9b9d28_0((char) 9670, new Pair(valueOf2, 1));
        builder.put$ar$ds$de9b9d28_0((char) 65381, new Pair(Integer.valueOf(R.string.symbol_halfwidth_katakana_middle_dot), 1));
        builder.put$ar$ds$de9b9d28_0((char) 9642, new Pair(Integer.valueOf(R.string.symbol_black_smallsquare), 1));
        builder.put$ar$ds$de9b9d28_0((char) 12298, new Pair(Integer.valueOf(R.string.symbol_left_angle_bracket), 1));
        builder.put$ar$ds$de9b9d28_0((char) 12299, new Pair(Integer.valueOf(R.string.symbol_right_angle_bracket), 1));
        builder.put$ar$ds$de9b9d28_0((char) 161, new Pair(Integer.valueOf(R.string.symbol_inverted_exclamation_mark), 1));
        builder.put$ar$ds$de9b9d28_0((char) 191, new Pair(Integer.valueOf(R.string.symbol_inverted_question_mark), 0));
        builder.put$ar$ds$de9b9d28_0((char) 65292, new Pair(Integer.valueOf(R.string.symbol_full_width_comma), 0));
        builder.put$ar$ds$de9b9d28_0((char) 65281, new Pair(Integer.valueOf(R.string.symbol_full_width_exclamation_mark), 0));
        builder.put$ar$ds$de9b9d28_0((char) 12290, new Pair(Integer.valueOf(R.string.symbol_full_width_ideographic_full_stop), 1));
        builder.put$ar$ds$de9b9d28_0((char) 65311, new Pair(Integer.valueOf(R.string.symbol_full_width_question_mark), 0));
        builder.put$ar$ds$de9b9d28_0((char) 183, new Pair(Integer.valueOf(R.string.symbol_middle_dot), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8221, new Pair(Integer.valueOf(R.string.symbol_right_double_quotation_mark), 1));
        builder.put$ar$ds$de9b9d28_0((char) 12289, new Pair(Integer.valueOf(R.string.symbol_ideographic_comma), 0));
        builder.put$ar$ds$de9b9d28_0((char) 65306, new Pair(Integer.valueOf(R.string.symbol_full_width_colon), 1));
        builder.put$ar$ds$de9b9d28_0((char) 65307, new Pair(Integer.valueOf(R.string.symbol_full_width_semicolon), 1));
        builder.put$ar$ds$de9b9d28_0((char) 65286, new Pair(Integer.valueOf(R.string.symbol_full_width_ampersand), 1));
        builder.put$ar$ds$de9b9d28_0((char) 65342, new Pair(Integer.valueOf(R.string.symbol_full_width_circumflex), 1));
        builder.put$ar$ds$de9b9d28_0((char) 65374, new Pair(Integer.valueOf(R.string.symbol_full_width_tilde), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8220, new Pair(Integer.valueOf(R.string.symbol_left_double_quotation_mark), 1));
        builder.put$ar$ds$de9b9d28_0((char) 65288, new Pair(Integer.valueOf(R.string.symbol_full_width_left_parenthesis), 1));
        builder.put$ar$ds$de9b9d28_0((char) 65289, new Pair(Integer.valueOf(R.string.symbol_full_width_right_parenthesis), 1));
        builder.put$ar$ds$de9b9d28_0((char) 65290, new Pair(Integer.valueOf(R.string.symbol_full_width_asterisk), 1));
        builder.put$ar$ds$de9b9d28_0((char) 65343, new Pair(Integer.valueOf(R.string.symbol_full_width_underscore), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8217, new Pair(Integer.valueOf(R.string.symbol_right_single_quotation_mark), 1));
        builder.put$ar$ds$de9b9d28_0((char) 65371, new Pair(Integer.valueOf(R.string.symbol_full_width_left_curly_bracket), 1));
        builder.put$ar$ds$de9b9d28_0((char) 65373, new Pair(Integer.valueOf(R.string.symbol_full_width_right_curly_bracket), 1));
        builder.put$ar$ds$de9b9d28_0((char) 65308, new Pair(Integer.valueOf(R.string.symbol_full_width_less_than_sign), 1));
        builder.put$ar$ds$de9b9d28_0((char) 65310, new Pair(Integer.valueOf(R.string.symbol_full_width_greater_than_sign), 1));
        builder.put$ar$ds$de9b9d28_0((char) 8216, new Pair(Integer.valueOf(R.string.symbol_left_single_quotation_mark), 1));
        builder.put$ar$ds$de9b9d28_0((char) 1614, new Pair(Integer.valueOf(R.string.symbol_fatha), 1));
        builder.put$ar$ds$de9b9d28_0((char) 1616, new Pair(Integer.valueOf(R.string.symbol_kasra), 1));
        builder.put$ar$ds$de9b9d28_0((char) 1615, new Pair(Integer.valueOf(R.string.symbol_damma), 1));
        builder.put$ar$ds$de9b9d28_0((char) 1611, new Pair(Integer.valueOf(R.string.symbol_fathatan), 1));
        builder.put$ar$ds$de9b9d28_0((char) 1613, new Pair(Integer.valueOf(R.string.symbol_kasratan), 1));
        builder.put$ar$ds$de9b9d28_0((char) 1612, new Pair(Integer.valueOf(R.string.symbol_dammatan), 1));
        builder.put$ar$ds$de9b9d28_0((char) 1617, new Pair(Integer.valueOf(R.string.symbol_shadda), 1));
        builder.put$ar$ds$de9b9d28_0((char) 1618, new Pair(Integer.valueOf(R.string.symbol_sukun), 1));
        TALKBACK_PUNCTUATION_AND_SYMBOL = builder.buildOrThrow();
    }

    public static String characterToName(Context context, char c) {
        return characterToName(context, c, 0);
    }

    public static CharSequence cleanUp(Context context, CharSequence charSequence) {
        Object[] spans;
        int length;
        if (charSequence != null) {
            CharSequence trimText = SpannableUtils$IdentifierSpan.trimText(charSequence);
            int length2 = trimText.length();
            if (length2 == 1) {
                String cleanValueFor = getCleanValueFor(context, trimText.charAt(0));
                if (TextUtils.equals(cleanValueFor, trimText)) {
                    return trimText;
                }
                if ((charSequence instanceof Spannable) && (length = (spans = ((Spannable) charSequence).getSpans(0, charSequence.length(), Object.class)).length) != 0) {
                    SpannableString spannableString = new SpannableString(cleanValueFor);
                    for (int i = 0; i < length; i++) {
                        spannableString.setSpan(spans[i], 0, spannableString.length(), 0);
                    }
                    return spannableString;
                }
                return cleanValueFor;
            }
            if (length2 == 0 && charSequence.length() > 0) {
                return getCleanValueFor(context, charSequence.toString().charAt(0));
            }
            return charSequence;
        }
        return charSequence;
    }

    public static CharSequence collapseRepeatedCharactersAndCleanUp(Context context, CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            charSequence = null;
        } else {
            Matcher matcher = CONSECUTIVE_CHARACTER_PATTERN.matcher(charSequence);
            while (matcher.find()) {
                String string = context.getString(R.string.character_collapse_template, Integer.valueOf(matcher.group().length()), getCleanValueFor(context, matcher.group().charAt(0)));
                int end = (matcher.end() - matcher.group().length()) + string.length();
                charSequence = matcher.replaceFirst(string);
                matcher = CONSECUTIVE_CHARACTER_PATTERN.matcher(charSequence);
                matcher.region(end, charSequence.length());
            }
        }
        return cleanUp(context, charSequence);
    }

    public static String getCleanValueFor(Context context, char c) {
        int intValue;
        Pair pair = (Pair) TALKBACK_PUNCTUATION_AND_SYMBOL.get(Character.valueOf(c));
        if (pair != null && (intValue = ((Integer) pair.first).intValue()) != 0) {
            return context.getString(intValue);
        }
        return Character.toString(c);
    }

    public static String characterToName(Context context, char c, int i) {
        int intValue;
        Pair pair = (Pair) TALKBACK_PUNCTUATION_AND_SYMBOL.get(Character.valueOf(c));
        if (pair == null) {
            return null;
        }
        if ((c != ' ' && ((Integer) pair.second).intValue() < i) || (intValue = ((Integer) pair.first).intValue()) == 0 || c == ' ') {
            return null;
        }
        return context.getString(intValue);
    }
}
