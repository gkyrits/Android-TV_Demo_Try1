package gkyrits.test.tv_demo_try1;

import android.view.ContextThemeWrapper;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.leanback.widget.Presenter;

public class ImagePresender extends Presenter {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        ImageView image = new ImageView(new ContextThemeWrapper(parent.getContext(),R.style.ImageRowCard));
        image.setImageResource(R.drawable.ic_emoy_24);
        image.setBackgroundResource(R.drawable.row_card_background);
        image.setFocusable(true);
        image.setClickable(true);
        return new ViewHolder(image);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        //...
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }
}
