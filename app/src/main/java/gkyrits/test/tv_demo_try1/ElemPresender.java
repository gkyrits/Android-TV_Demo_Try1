package gkyrits.test.tv_demo_try1;

import android.content.Context;

import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;

public class ElemPresender extends PresenterSelector {

    private final Context mContext;

    public ElemPresender(Context context) {
        mContext=context;
    }

    @Override
    public Presenter getPresenter(Object item) {
        Model.Element elem = (Model.Element)item;
        switch(elem.getType()){
            case Model.TYPE_TEXT:
                return new TextPresender();
            case Model.TYPE_IMAGE:
                return new ImagePresender();
            case Model.TYPE_CARD:
                return new CardPresender();
        }
        return null;
    }
}
