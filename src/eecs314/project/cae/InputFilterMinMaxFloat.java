package eecs314.project.cae;

import android.text.InputFilter;
import android.text.Spanned;

/*
 * Credit to @PratikSharma here:
 * http://stackoverflow.com/questions/14212518/is-there-any-way-to-define-a-min-and-max-value-for-edittext-in-android
 */
public class InputFilterMinMaxFloat implements InputFilter {
    private float min, max;

    public InputFilterMinMaxFloat(float min, float max) {
        this.min = min;
        this.max = max;
    }

    public InputFilterMinMaxFloat(String min, String max) {
        this.min = Float.parseFloat(min);
        this.max = Float.parseFloat(max);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {   
        try {
            float input = Float.parseFloat(dest.toString() + source.toString());
            if (isInRange(min, max, input))
                return null;
        } catch (NumberFormatException nfe) { }     
        return "";
    }

    private boolean isInRange(float a, float b, float c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}
