package gkyrits.test.tv_demo_try1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.leanback.app.BrowseFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.DividerRow;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.SectionRow;

public class RowFragment extends BrowseFragment {

    private ArrayObjectAdapter mRowsAdapter;
    private Model model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupUI();
        setupRowAdapter();
        setupListeners();
    }

    private void setupRowAdapter(){
        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        createRows();
        setAdapter(mRowsAdapter);
    }

    private void createRows(){
        mRowsAdapter.add(new SectionRow(new HeaderItem("Main Title!")));
        mRowsAdapter.add(new DividerRow());
        model = new Model();
        model.initialize();
        for(Model.Row row : model.rows){
            ListRow listrow = crateElemRow(row);
            mRowsAdapter.add(listrow);
        }
        mRowsAdapter.add(new DividerRow());
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

    private void setupUI(){
        setTitle("Rows Demo");
        setHeadersState(HEADERS_ENABLED);
        //setHeadersTransitionOnBackEnabled(false);
    }


    private void setupListeners(){
        setOnItemViewClickedListener(new OnItemViewClickedListener() {
            @Override
            public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
                Model.Element elem = (Model.Element)item;
                Intent i = new Intent(getContext(),DetailsActivity.class);
                i.putExtra(ElemDetailsFragment.ELEM_KEY,elem);
                startActivity(i);
            }
        });

        setOnSearchClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Search...:",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
