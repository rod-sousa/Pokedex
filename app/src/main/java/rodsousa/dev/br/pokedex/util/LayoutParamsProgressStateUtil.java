package rodsousa.dev.br.pokedex.util;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

public class LayoutParamsProgressStateUtil {

    private static final int MAXIMUM_WIDTH_IN_XML = 220;
    private static final int POKEMON_MAXIMUN_STRENGTH = 130;

    public static ViewGroup.LayoutParams percentSizeStats(int value, ImageView imageView, Context context){
        int valueFormatedDp = (MAXIMUM_WIDTH_IN_XML*value)/POKEMON_MAXIMUN_STRENGTH;

        if (valueFormatedDp > MAXIMUM_WIDTH_IN_XML){
            valueFormatedDp = MAXIMUM_WIDTH_IN_XML;
        }

        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.width = (int) (valueFormatedDp * context.getResources().getDisplayMetrics().density);
        return params;
    }
}
