package com.google.android.accessibility.braille.translate.liblouis;

import android.content.Context;
import com.google.android.accessibility.braille.translate.BrailleTranslator;
import com.google.android.accessibility.braille.translate.TranslatorFactory;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LibLouis implements TranslatorFactory {
    private final BrailleTranslator getBrailleTranslator(Context context, boolean z, String str, String str2) {
        return getBrailleTranslator$ar$ds(z, new LibLouisTranslator(context, str), new LibLouisTranslator(context, str2));
    }

    private static final BrailleTranslator getBrailleTranslator$ar$ds(boolean z, LibLouisTranslator libLouisTranslator, LibLouisTranslator libLouisTranslator2) {
        if (z) {
            return new ExpandableContractedTranslator(libLouisTranslator, libLouisTranslator2);
        }
        return libLouisTranslator;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.accessibility.braille.translate.TranslatorFactory
    public final BrailleTranslator create(Context context, String str, boolean z) {
        char c;
        switch (str.hashCode()) {
            case -2072311548:
                if (str.equals("KOREAN")) {
                    c = '0';
                    break;
                }
                c = 65535;
                break;
            case -2040443908:
                if (str.equals("RUSSIAN_DETAILED")) {
                    c = 'G';
                    break;
                }
                c = 65535;
                break;
            case -2034270371:
                if (str.equals("CZECH_COMP8")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case -2028285916:
                if (str.equals("FRENCH_COMP8")) {
                    c = 30;
                    break;
                }
                c = 65535;
                break;
            case -2021434509:
                if (str.equals("RUSSIAN")) {
                    c = 'E';
                    break;
                }
                c = 65535;
                break;
            case -1995722395:
                if (str.equals("NEPALI")) {
                    c = '8';
                    break;
                }
                c = 65535;
                break;
            case -1947597975:
                if (str.equals("HUNGARIAN_COMP8")) {
                    c = '(';
                    break;
                }
                c = 65535;
                break;
            case -1929340143:
                if (str.equals("POLISH")) {
                    c = '?';
                    break;
                }
                c = 65535;
                break;
            case -1886748730:
                if (str.equals("DANISH_8")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case -1879642962:
                if (str.equals("TURKISH_COMP8")) {
                    c = 'T';
                    break;
                }
                c = 65535;
                break;
            case -1846121942:
                if (str.equals("SLOVAK")) {
                    c = 'K';
                    break;
                }
                c = 65535;
                break;
            case -1836278292:
                if (str.equals("SWEDEN")) {
                    c = 'N';
                    break;
                }
                c = 65535;
                break;
            case -1816168223:
                if (str.equals("BULGARIAN_COMP8")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1668883419:
                if (str.equals("CHINESE_CHINA_COMMON")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -1593427212:
                if (str.equals("SERBIAN")) {
                    c = 'H';
                    break;
                }
                c = 65535;
                break;
            case -1482433306:
                if (str.equals("SINHALA")) {
                    c = 'J';
                    break;
                }
                c = 65535;
                break;
            case -1469617824:
                if (str.equals("NORWEGIAN_8_6FALLBACK")) {
                    c = '<';
                    break;
                }
                c = 65535;
                break;
            case -1421437994:
                if (str.equals("UKRAINIAN_COMP8")) {
                    c = 'W';
                    break;
                }
                c = 65535;
                break;
            case -1405627549:
                if (str.equals("CROATIAN")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -1312663163:
                if (str.equals("FINNISH_COMP8")) {
                    c = 28;
                    break;
                }
                c = 65535;
                break;
            case -1293848364:
                if (str.equals("SPANISH")) {
                    c = 'L';
                    break;
                }
                c = 65535;
                break;
            case -1264323348:
                if (str.equals("EN_US_EBAE")) {
                    c = 25;
                    break;
                }
                c = 65535;
                break;
            case -1223480823:
                if (str.equals("VIETNAMESE_COMP8")) {
                    c = 'Z';
                    break;
                }
                c = 65535;
                break;
            case -1171574191:
                if (str.equals("ESTONIAN")) {
                    c = 26;
                    break;
                }
                c = 65535;
                break;
            case -1150616619:
                if (str.equals("DUTCH_NL")) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case -1010252815:
                if (str.equals("EN_COMP6")) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case -1009171561:
                if (str.equals("DANISH_COMP8")) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case -1000522119:
                if (str.equals("EN_NABCC")) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case -981927346:
                if (str.equals("NORWEGIAN")) {
                    c = ':';
                    break;
                }
                c = 65535;
                break;
            case -871655265:
                if (str.equals("HUNGARIAN")) {
                    c = '\'';
                    break;
                }
                c = 65535;
                break;
            case -784801052:
                if (str.equals("CHINESE_CHINA_TWO_CELLS")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -753706525:
                if (str.equals("SWEDISH_COMP8_1989")) {
                    c = 'P';
                    break;
                }
                c = 65535;
                break;
            case -753706497:
                if (str.equals("SWEDISH_COMP8_1996")) {
                    c = 'Q';
                    break;
                }
                c = 65535;
                break;
            case -642689219:
                if (str.equals("RUSSIAN_COMP8")) {
                    c = 'F';
                    break;
                }
                c = 65535;
                break;
            case -546942598:
                if (str.equals("CHINESE_TAIWAN")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -535800076:
                if (str.equals("CANTONESE")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -505022199:
                if (str.equals("GUJARATI")) {
                    c = '#';
                    break;
                }
                c = 65535;
                break;
            case -461658289:
                if (str.equals("GREEK_INTERNATIONAL")) {
                    c = '\"';
                    break;
                }
                c = 65535;
                break;
            case -391870441:
                if (str.equals("BULGARIAN")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -333322621:
                if (str.equals("ROMANIAN_COMP8")) {
                    c = 'D';
                    break;
                }
                c = 65535;
                break;
            case -275013988:
                if (str.equals("TELUGU_IN")) {
                    c = 'S';
                    break;
                }
                c = 65535;
                break;
            case -247588444:
                if (str.equals("TURKISH")) {
                    c = 'U';
                    break;
                }
                c = 65535;
                break;
            case -221382872:
                if (str.equals("KANNADA")) {
                    c = '.';
                    break;
                }
                c = 65535;
                break;
            case -134892613:
                if (str.equals("FINNISH")) {
                    c = 27;
                    break;
                }
                c = 65535;
                break;
            case 83890:
                if (str.equals("UEB")) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case 2613230:
                if (str.equals("URDU")) {
                    c = 'X';
                    break;
                }
                c = 65535;
                break;
            case 27709000:
                if (str.equals("CHINESE_CHINA_CURRENT_WITH_TONES")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 29896625:
                if (str.equals("JAPANESE")) {
                    c = '-';
                    break;
                }
                c = 65535;
                break;
            case 51347894:
                if (str.equals("ARABIC_COMP8")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 64625555:
                if (str.equals("CZECH")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 66140283:
                if (str.equals("EN_IN")) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case 66140652:
                if (str.equals("EN_UK")) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case 68081376:
                if (str.equals("GREEK")) {
                    c = '!';
                    break;
                }
                c = 65535;
                break;
            case 68745394:
                if (str.equals("HINDI")) {
                    c = '&';
                    break;
                }
                c = 65535;
                break;
            case 71485245:
                if (str.equals("KHMER")) {
                    c = '/';
                    break;
                }
                c = 65535;
                break;
            case 79588515:
                if (str.equals("TAMIL")) {
                    c = 'R';
                    break;
                }
                c = 65535;
                break;
            case 82477587:
                if (str.equals("WELSH")) {
                    c = '[';
                    break;
                }
                c = 65535;
                break;
            case 104299922:
                if (str.equals("DUTCH_COMP8")) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case 179514490:
                if (str.equals("ICELANDIC_COMP8")) {
                    c = '*';
                    break;
                }
                c = 65535;
                break;
            case 193127033:
                if (str.equals("NORWEGIAN_8_NO")) {
                    c = '=';
                    break;
                }
                c = 65535;
                break;
            case 243547852:
                if (str.equals("UKRAINIAN")) {
                    c = 'V';
                    break;
                }
                c = 65535;
                break;
            case 247646750:
                if (str.equals("LITHUANIAN_8")) {
                    c = '4';
                    break;
                }
                c = 65535;
                break;
            case 354604482:
                if (str.equals("KURDISH")) {
                    c = '2';
                    break;
                }
                c = 65535;
                break;
            case 493632039:
                if (str.equals("PUNJABI")) {
                    c = 'C';
                    break;
                }
                c = 65535;
                break;
            case 568123045:
                if (str.equals("SWEDEN_8")) {
                    c = 'O';
                    break;
                }
                c = 65535;
                break;
            case 666502719:
                if (str.equals("NEPALI_IN")) {
                    c = '9';
                    break;
                }
                c = 65535;
                break;
            case 671907871:
                if (str.equals("LATVIAN")) {
                    c = '5';
                    break;
                }
                c = 65535;
                break;
            case 1010710335:
                if (str.equals("VIETNAMESE")) {
                    c = 'Y';
                    break;
                }
                c = 65535;
                break;
            case 1055466096:
                if (str.equals("ICELANDIC")) {
                    c = ')';
                    break;
                }
                c = 65535;
                break;
            case 1078508304:
                if (str.equals("GERMAN_COMP8")) {
                    c = ' ';
                    break;
                }
                c = 65535;
                break;
            case 1153964845:
                if (str.equals("CROATIAN_COMP8")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 1202625047:
                if (str.equals("SINDHI_IN")) {
                    c = 'I';
                    break;
                }
                c = 65535;
                break;
            case 1229835752:
                if (str.equals("ITALIAN_COMP6")) {
                    c = '+';
                    break;
                }
                c = 65535;
                break;
            case 1229835754:
                if (str.equals("ITALIAN_COMP8")) {
                    c = ',';
                    break;
                }
                c = 65535;
                break;
            case 1260628615:
                if (str.equals("NORWEGIAN_8")) {
                    c = ';';
                    break;
                }
                c = 65535;
                break;
            case 1273686606:
                if (str.equals("CATALAN")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 1322880565:
                if (str.equals("PORTUGUESE")) {
                    c = 'A';
                    break;
                }
                c = 65535;
                break;
            case 1452470399:
                if (str.equals("PORTUGUESE_COMP8")) {
                    c = 'B';
                    break;
                }
                c = 65535;
                break;
            case 1488524197:
                if (str.equals("LITHUANIAN")) {
                    c = '3';
                    break;
                }
                c = 65535;
                break;
            case 1523859293:
                if (str.equals("MALAYALAM_IN")) {
                    c = '6';
                    break;
                }
                c = 65535;
                break;
            case 1545332827:
                if (str.equals("POLISH_COMP8")) {
                    c = '@';
                    break;
                }
                c = 65535;
                break;
            case 1683374665:
                if (str.equals("HEBREW_COMP8")) {
                    c = '%';
                    break;
                }
                c = 65535;
                break;
            case 1701651928:
                if (str.equals("NORWEGIAN_COMP8")) {
                    c = '>';
                    break;
                }
                c = 65535;
                break;
            case 1736240594:
                if (str.equals("MARATHI_IN")) {
                    c = '7';
                    break;
                }
                c = 65535;
                break;
            case 1938625708:
                if (str.equals("ARABIC")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1957175262:
                if (str.equals("SPANISH_COMP8")) {
                    c = 'M';
                    break;
                }
                c = 65535;
                break;
            case 2009207629:
                if (str.equals("DANISH")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 2015897375:
                if (str.equals("KOREAN_2006")) {
                    c = '1';
                    break;
                }
                c = 65535;
                break;
            case 2081901978:
                if (str.equals("FRENCH")) {
                    c = 29;
                    break;
                }
                c = 65535;
                break;
            case 2085466346:
                if (str.equals("CHINESE_CHINA_CURRENT_WITHOUT_TONES")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 2098911622:
                if (str.equals("GERMAN")) {
                    c = 31;
                    break;
                }
                c = 65535;
                break;
            case 2127069055:
                if (str.equals("HEBREW")) {
                    c = '$';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                LibLouisTranslator libLouisTranslator = new LibLouisTranslator(context);
                return z ? new ExpandableContractedTranslator(libLouisTranslator, new LibLouisTranslator(context, "ar-ar-g2.ctb")) : libLouisTranslator;
            case 1:
                return new LibLouisTranslator(context, "ar-ar-comp8.utb");
            case 2:
                return new LibLouisTranslator(context, "bg.utb");
            case 3:
                return new LibLouisTranslator(context, "bg.tbl");
            case 4:
                return new LibLouisTranslator(context, "zh_HK.tbl");
            case 5:
                return new LibLouisTranslator(context, "zh-tw.ctb");
            case 6:
                return new LibLouisTranslator(context, "zhcn-g1.ctb");
            case 7:
                return new LibLouisTranslator(context, "zh_CHN.tbl");
            case '\b':
                return new LibLouisTranslator(context, "zhcn-g2.ctb");
            case '\t':
                return new LibLouisTranslator(context, "zhcn-cbs.ctb");
            case '\n':
                return new LibLouisTranslator(context, "ca.tbl");
            case 11:
                return new LibLouisTranslator(context, "hr-g1.tbl");
            case '\f':
                return new LibLouisTranslator(context, "hr-comp8.tbl");
            case '\r':
                return new LibLouisTranslator(context, "cs.tbl");
            case 14:
                return new LibLouisTranslator(context, "cs-comp8.utb");
            case 15:
                return getBrailleTranslator(context, z, "da-dk-g16.ctb", "da-dk-g26.ctb");
            case 16:
                return new LibLouisTranslator(context, "da-dk-g18.ctb");
            case 17:
                return new LibLouisTranslator(context, "da-dk-g08.ctb");
            case 18:
                return new LibLouisTranslator(context, "nl-comp8.utb");
            case 19:
                return new LibLouisTranslator(context, "nl.tbl");
            case 20:
                return getBrailleTranslator(context, z, "en-gb-g1.utb", "en_GB.tbl");
            case 21:
                return new LibLouisTranslator(context, "en-in-g1.ctb");
            case 22:
                return new LibLouisTranslator(context, "en-nabcc.utb");
            case 23:
                return new LibLouisTranslator(context, "en-us-comp6.ctb");
            case 24:
                return getBrailleTranslator$ar$ds(z, new LibLouisTranslatorUeb1(context), new LibLouisTranslatorUeb2(context));
            case 25:
                return getBrailleTranslator(context, z, "en-us-g1.ctb", "en_US.tbl");
            case 26:
                return new LibLouisTranslator(context, "et-g0.utb");
            case 27:
                return new LibLouisTranslator(context, "fi.utb");
            case 28:
                return new LibLouisTranslator(context, "fi-fi-8dot.ctb");
            case 29:
                return getBrailleTranslator$ar$ds(z, new LibLouisTranslator(context, (byte[]) null), new LibLouisTranslator(context, "fr-bfu-g2.ctb"));
            case 30:
                return new LibLouisTranslator(context, "fr-bfu-comp8.utb");
            case 31:
                return getBrailleTranslator(context, z, "de-g0.utb", "de-g2.ctb");
            case ' ':
                return new LibLouisTranslator(context, "de-de-comp8.ctb");
            case '!':
                return new LibLouisTranslator(context, "el.ctb");
            case '\"':
                return new LibLouisTranslator(context, "grc-international-en.utb");
            case '#':
                return new LibLouisTranslator(context, "gu.tbl");
            case '$':
                return new LibLouisTranslator(context, "he-IL.utb");
            case '%':
                return new LibLouisTranslator(context, "he-IL-comp8.utb");
            case '&':
                return new LibLouisTranslator(context, "hi.tbl");
            case '\'':
                return getBrailleTranslator(context, z, "hu.tbl", "hu-hu-g2.ctb");
            case '(':
                return new LibLouisTranslator(context, "hu-hu-comp8.ctb");
            case ')':
                return new LibLouisTranslator(context, "is.tbl");
            case '*':
                return new LibLouisTranslator(context, "is.ctb");
            case '+':
                return new LibLouisTranslator(context, "it.tbl");
            case ',':
                return new LibLouisTranslator(context, "it-it-comp8.utb");
            case '-':
                return new LibLouisTranslator(context, "ja-kantenji.utb");
            case '.':
                return new LibLouisTranslator(context, "kn.tbl");
            case '/':
                return new LibLouisTranslator(context, "km-g1.utb");
            case '0':
                return getBrailleTranslator(context, z, "ko-g1.ctb", "ko-g2.ctb");
            case '1':
                return getBrailleTranslator(context, z, "ko-2006-g1.ctb", "ko-2006-g2.ctb");
            case '2':
                return new LibLouisTranslator(context, "ckb.tbl");
            case '3':
                return new LibLouisTranslator(context, "lt-6dot.tbl");
            case '4':
                return new LibLouisTranslator(context, "lt.tbl");
            case '5':
                return new LibLouisTranslator(context, "lv.tbl");
            case '6':
                return new LibLouisTranslator(context, "ml.tbl");
            case '7':
                return new LibLouisTranslator(context, "mr.tbl");
            case '8':
                return new LibLouisTranslator(context, "ne.ctb");
            case '9':
                return new LibLouisTranslator(context, "ne.tbl");
            case ':':
                return getBrailleTranslator(context, z, "no-no-g0.utb", "no-no-g3.ctb");
            case ';':
                return new LibLouisTranslator(context, "no-no-8dot.utb");
            case '<':
                return new LibLouisTranslator(context, "no-no-8dot-fallback-6dot-g0.utb");
            case '=':
                return new LibLouisTranslator(context, "no-no-generic.ctb");
            case '>':
                return new LibLouisTranslator(context, "no-no-comp8.ctb");
            case '?':
                return new LibLouisTranslator(context, (char[]) null);
            case '@':
                return new LibLouisTranslator(context, "pl-pl-comp8.ctb");
            case 'A':
                return getBrailleTranslator(context, z, "pt-pt-g1.utb", "pt.tbl");
            case 'B':
                return new LibLouisTranslator(context, "pt-pt-comp8.ctb");
            case 'C':
                return new LibLouisTranslator(context, "pa.tbl");
            case 'D':
                return new LibLouisTranslator(context, "ro.tbl");
            case 'E':
                return new LibLouisTranslator(context, "ru-ru-g1.ctb");
            case 'F':
                return new LibLouisTranslator(context, "ru.ctb");
            case 'G':
                return new LibLouisTranslator(context, "ru-litbrl-detailed.utb");
            case 'H':
                return new LibLouisTranslator(context, "sr.tbl");
            case 'I':
                return new LibLouisTranslator(context, "sd.tbl");
            case 'J':
                return new LibLouisTranslator(context, "sin.utb");
            case 'K':
                return new LibLouisTranslator(context, "sk-g1.ctb");
            case 'L':
                return getBrailleTranslator$ar$ds(z, new LibLouisTranslatorSpanish(context), new LibLouisTranslator(context, "es-g2.ctb"));
            case 'M':
                return new LibLouisTranslator(context, "Es-Es-G0.utb");
            case 'N':
                return getBrailleTranslator(context, z, "sv-g0.utb", "sv-g2.ctb");
            case 'O':
                return new LibLouisTranslator(context, "se-se.ctb");
            case 'P':
                return new LibLouisTranslator(context, "sv-1989.ctb");
            case 'Q':
                return new LibLouisTranslator(context, "sv-1996.ctb");
            case 'R':
                return new LibLouisTranslator(context, "ta-ta-g1.ctb");
            case 'S':
                return new LibLouisTranslator(context, "te.tbl");
            case 'T':
                return new LibLouisTranslator(context, "tr.tbl");
            case 'U':
                return getBrailleTranslator(context, z, "tr-g1.ctb", "tr-g2.tbl");
            case 'V':
                return new LibLouisTranslator(context, "uk.utb");
            case 'W':
                return new LibLouisTranslator(context, "uk-comp.utb");
            case 'X':
                return getBrailleTranslator(context, z, "ur-pk-g1.utb", "ur-pk-g2.ctb");
            case 'Y':
                return getBrailleTranslator(context, z, "vi-vn-g0.utb", "vi-vn-g2.ctb");
            case 'Z':
                return new LibLouisTranslator(context, "vi-cb8.utb");
            case '[':
                return getBrailleTranslator(context, z, "cy-cy-g1.utb", "cy.tbl");
            default:
                throw new IllegalArgumentException("unrecognized code ".concat(String.valueOf(str)));
        }
    }

    @Override // com.google.android.accessibility.braille.translate.TranslatorFactory
    public final String getLibraryName() {
        return getClass().getSimpleName();
    }
}
