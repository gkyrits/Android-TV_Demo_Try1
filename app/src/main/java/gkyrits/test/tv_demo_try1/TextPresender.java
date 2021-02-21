package gkyrits.test.tv_demo_try1;

import android.view.ContextThemeWrapper;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.leanback.widget.Presenter;

public class TextPresender extends Presenter {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        TextView text = new TextView(new ContextThemeWrapper(parent.getContext(),R.style.TextRowCard));
        text.setFocusable(true);
        text.setClickable(true);
        return new ViewHolder(text);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        Model.Element elem = (Model.Element)item;
        TextView text = (TextView) viewHolder.view;
        text.setText(elem.getTitle());
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        //...
    }
}
