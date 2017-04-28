package android.base.util;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ScaleXSpan;


/**
 * The type Letter spacing utils.
 */
public class LetterSpacingUtils {

    /**
     * The constant NORMAL.
     */
    public  static final float NORMAL = 0f;
    /**
     * The constant NORMALBIG.
     */
    public  static final float NORMALBIG = 0.025f;
    /**
     * The constant BIG.
     */
    public  static final float BIG = 0.05f;
    /**
     * The constant BIGGEST.
     */
    public  static final float BIGGEST = 0.2f;

    private LetterSpacingUtils() {
    }
    /**
     * Apply letter spacing spannable string.
     *
     * @param originalText  the original text
     * @param letterSpacing the letter spacing
     * @return the spannable string
     */
    public static SpannableString applyLetterSpacing(String originalText, float letterSpacing) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < originalText.length(); i++) {
            String c = String.format("%s", originalText.charAt(i));
            builder.append(c);
            if (i + 1 < originalText.length()) {
                builder.append("\u00A0");
            }
        }
        SpannableString finalText = new SpannableString(builder.toString());
        if (builder.toString().length() > 1) {
            for (int i = 1; i < builder.toString().length(); i += 2) {
                finalText.setSpan(new ScaleXSpan((letterSpacing + 1) / 10), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return finalText;
    }
}
