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

public class PageCFragment extends RowsFragment implements KeyHandler {

    private ArrayObjectAdapter mRowsAdapter;
    private Model model;


    private void setupRowAdapter(){
        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        createRows();
        setAdapter(mRowsAdapter);
    }

    private void createRows(){
        model = new Model();
        model.initialize();
        for(Model.Row row : model.rows){
            ListRow listrow = crateElemRow(row);
            mRowsAdapter.add(listrow);
        }
    }

    private ListRow crateElemRow(Model.Row row){
        ElemPresender elemItem = new ElemPresender(getContext());
        ArrayObjectAdapter elemRowAdapt = new ArrayObjectAdapter(elemItem);
        for(Model.Element elem:row.elements)
            elemRowAdapt.add(elem);
        HeaderItem hitem = new HeaderItem(mRowsAdapter.size()-1,row.getTitle());
        hitem.setDescription(row.getDescription());
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
