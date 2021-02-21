package gkyrits.test.tv_demo_try1;

import android.os.Bundle;
import android.widget.Toast;

import androidx.leanback.app.RowsFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;

public class PageDFragment extends RowsFragment implements KeyHandler{

    private static final int ROWS = 3;
    private static final int ITEMS = 18;

    private ArrayObjectAdapter mRowsAdapter;

    private void setupRowAdapter(){
        ListRowPresenter listRowPresdr = new ListRowPresenter();
        listRowPresdr.setNumRows(ROWS);
        mRowsAdapter = new ArrayObjectAdapter(listRowPresdr);
        createRows();
        setAdapter(mRowsAdapter);
    }

    private void createRows(){
        ListRow listrow = crateElemRow();
        mRowsAdapter.add(listrow);
    }

    private ListRow crateElemRow(){
        ElemPresender elemItem = new ElemPresender(getContext());
        ArrayObjectAdapter elemRowAdapt = new ArrayObjectAdapter(elemItem);
        for(int ii=0; ii<ITEMS; ii++) {
            Model.Element elem = new Model.Element(Model.TYPE_IMAGE,"Icon_"+ii,"...","");
            elemRowAdapt.add(elem);
        }
        HeaderItem hitem = new HeaderItem("Icons");
        hitem.setDescription("Test Icons");
        return new ListRow(hitem,elemRowAdapt);
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

        setupRowAdapter();
        setupListeners();
    }

    @Override
    public boolean handleKeyDown(int keyCode) {
        return false;
    }
}
