package gkyrits.test.tv_demo_try1;

import android.os.Bundle;
import android.widget.Toast;

import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.FocusHighlight;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.VerticalGridPresenter;

public class PageEFragment extends GridFragment implements KeyHandler{

    private static final int COLUMNS = 6;
    private static final int ITEMS = 20;

    private ArrayObjectAdapter mElemAdapter;

    private void setupGridAdapter(){
        VerticalGridPresenter gridPresdr = new VerticalGridPresenter(FocusHighlight.ZOOM_FACTOR_SMALL);
        gridPresdr.setNumberOfColumns(COLUMNS);
        setGridPresenter(gridPresdr);

        mElemAdapter = crateElems();
        setAdapter(mElemAdapter);
    }


    private ArrayObjectAdapter crateElems(){
        ElemPresender elemItem = new ElemPresender(getContext());
        ArrayObjectAdapter elemAdapt = new ArrayObjectAdapter(elemItem);
        for(int ii=0; ii<ITEMS; ii++) {
            Model.Element elem = new Model.Element(Model.TYPE_IMAGE,"Icon_"+ii,"...","");
            elemAdapt.add(elem);
        }
        return elemAdapt;
    }

    private void setupListeners(){
        setOnItemViewClickedListener(new OnItemViewClickedListener() {
            @Override
            public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
                Model.Element elem = (Model.Element)item;
                Toast.makeText(getContext(),"Click:"+elem.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupGridAdapter();
        setupListeners();
    }

    @Override
    public boolean handleKeyDown(int keyCode) {
        return false;
    }
}
