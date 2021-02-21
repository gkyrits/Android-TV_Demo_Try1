package gkyrits.test.tv_demo_try1;

import android.content.Context;
import android.view.ViewGroup;

import androidx.leanback.widget.ImageCardView;
import androidx.leanback.widget.Presenter;

public class CardPresender extends Presenter {

    Context mContext;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        mContext = parent.getContext();
        return new ViewHolder(new ImageCardView(mContext));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        ImageCardView card = (ImageCardView)viewHolder.view;
        Model.Element elem = (Model.Element)item;
        card.setTitleText(elem.getTitle());
        card.setContentText(elem.getDescription());
        card.setMainImage(mContext.getDrawable(R.drawable.ic_banner_foreground));
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
    }
}
