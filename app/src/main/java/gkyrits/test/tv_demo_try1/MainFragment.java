package gkyrits.test.tv_demo_try1;

import android.content.Intent;
import android.os.Bundle;

import androidx.leanback.app.BrowseFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;

public class MainFragment extends BrowseFragment {

    private static final String ROWS_TITLE = "rows";
    private static final String PAGES_TITLE = "pages";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupUI();
        setupRowAdapter();
        setupListeners();
    }

    private void setupRowAdapter(){
        ArrayObjectAdapter mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        mRowsAdapter.add(createRow());
        setAdapter(mRowsAdapter);
    }

    private ListRow createRow(){
        ArrayObjectAdapter rowAdapter = new ArrayObjectAdapter(new ElemPresender(getContext()));
        Model.Element elem1 = new Model.Element(Model.TYPE_CARD,ROWS_TITLE,"Rows Browser Demo","");
        rowAdapter.add(elem1);
        Model.Element elem2 = new Model.Element(Model.TYPE_CARD,PAGES_TITLE,"Rages Browser Demo","");
        rowAdapter.add(elem2);
        return new ListRow(rowAdapter);
    }

    private void setupUI(){
        setTitle("TV Test2");
        setHeadersState(HEADERS_DISABLED);
        //setHeadersTransitionOnBackEnabled(false);
    }

    private void setupListeners(){
        setOnItemViewClickedListener(new OnItemViewClickedListener() {
            @Override
            public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
                Model.Element elem = (Model.Element)item;
                if(elem.getTitle().equals(ROWS_TITLE)) {
                    Intent i = new Intent(getContext(),RowActivity.class);
                    startActivity(i);
                }
                else if(elem.getTitle().equals(PAGES_TITLE)) {
                    Intent i = new Intent(getContext(),PageActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}
