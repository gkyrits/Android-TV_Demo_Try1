package gkyrits.test.tv_demo_try1;

import android.content.Intent;
import android.os.Bundle;

import androidx.leanback.app.SearchFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.SpeechRecognitionCallback;

import static android.app.Activity.RESULT_OK;

public class ElemsSearchFragment extends SearchFragment implements SearchFragment.SearchResultProvider, SpeechRecognitionCallback {

    public static final int SPEECH_REQUEST_CODE = 42;
    private ArrayObjectAdapter mRowsAdapter;


    private void loadQuery(String query) {
        if(mRowsAdapter != null)
            mRowsAdapter.clear();
        if(query == null || query.length() == 0)
            return;

        ElemPresender elemItem = new ElemPresender(getContext());
        ArrayObjectAdapter listRowAdapt = new ArrayObjectAdapter(elemItem);
        //add results...
        Model.Element elem1 = new Model.Element(Model.TYPE_TEXT,query,"query text","");
        listRowAdapt.add(elem1);
        Model.Element elem2 = new Model.Element(Model.TYPE_IMAGE,"Elem2","Related elem2","");
        listRowAdapt.add(elem2);
        Model.Element elem3 = new Model.Element(Model.TYPE_CARD,"Elem3","Related elem3","");
        listRowAdapt.add(elem3);

        if(listRowAdapt.size() == 0)
            return;
        HeaderItem header = new HeaderItem("Search Results");
        mRowsAdapter.add(new ListRow(header, listRowAdapt));
    }

    private void setupListener(){
        setOnItemViewClickedListener(new OnItemViewClickedListener() {
            @Override
            public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
                Model.Element elem = (Model.Element)item;
                Intent i = new Intent(getContext(),DetailsActivity.class);
                i.putExtra(ElemDetailsFragment.ELEM_KEY,elem);
                startActivity(i);
            }
        });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSearchResultProvider(this);
        setSpeechRecognitionCallback(this);

        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        setupListener();
    }

    @Override
    public ObjectAdapter getResultsAdapter() {
        return mRowsAdapter;
    }

    @Override
    public boolean onQueryTextChange(String newQuery) {
        loadQuery(newQuery);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        loadQuery(query);
        return true;
    }

    @Override
    public void recognizeSpeech() {
        startActivityForResult(getRecognizerIntent(),SPEECH_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            setSearchQuery(data, true);
        }
    }




}
