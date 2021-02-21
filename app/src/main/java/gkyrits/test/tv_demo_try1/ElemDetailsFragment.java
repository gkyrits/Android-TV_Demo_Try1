package gkyrits.test.tv_demo_try1;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.leanback.app.DetailsFragment;
import androidx.leanback.widget.AbstractDetailsDescriptionPresenter;
import androidx.leanback.widget.Action;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ClassPresenterSelector;
import androidx.leanback.widget.DetailsOverviewRow;
import androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.SparseArrayObjectAdapter;

public class ElemDetailsFragment extends DetailsFragment {

    public static final String ELEM_KEY = "elem_key";
    public static final int ACTION_WATCH = 1;

    private ArrayObjectAdapter mRowsAdapter;
    private Model.Element detailElem;

    //---------------------------------------------------------
    private class DetailsDescriptionPresenter extends AbstractDetailsDescriptionPresenter{

        @Override
        protected void onBindDescription(ViewHolder vh, Object item) {
            Model.Element elem = (Model.Element)item;
            vh.getTitle().setText(elem.getTitle());
            vh.getSubtitle().setText("Element");
            vh.getBody().setText(elem.getDescription());
        }
    }
    //---------------------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getActivity().getIntent().getExtras();
        detailElem = ( Model.Element)extras.getSerializable(ELEM_KEY);
        //Toast.makeText(getContext(),"Click:"+elem.getTitle(),Toast.LENGTH_SHORT).show();

        setTitle(detailElem.getTitle());
        setupDetailAdapter(detailElem);
        setUpListeners();
    }

    private void setupDetailAdapter(Model.Element elem){
        ClassPresenterSelector detailPresenter = createDetailsPresenter();
        mRowsAdapter = new ArrayObjectAdapter(detailPresenter);
        DetailsOverviewRow mDetailRow = new DetailsOverviewRow(elem);
        Drawable image = getContext().getDrawable(R.drawable.ic_emoy_24);
        mDetailRow.setImageDrawable(image);
        setActions(mDetailRow);
        mRowsAdapter.add(mDetailRow);
        createRelatedRow();
        setAdapter(mRowsAdapter);
    }


    private ClassPresenterSelector createDetailsPresenter(){
        ClassPresenterSelector prSelector = new ClassPresenterSelector();
        FullWidthDetailsOverviewRowPresenter presenter = new FullWidthDetailsOverviewRowPresenter(new DetailsDescriptionPresenter());
        //presenter.setOnActionClickedListener()
        prSelector.addClassPresenter(DetailsOverviewRow.class, presenter);
        prSelector.addClassPresenter(ListRow.class, new ListRowPresenter());
        return prSelector;
    }

    private void setActions(DetailsOverviewRow mDetailRow){
        mDetailRow.setActionsAdapter(new SparseArrayObjectAdapter(){
            @Override
            public int size() {
                return 3;
            }

            @Override
            public Object get(int position) {
                switch(position){
                    case 0:
                        return new Action(ACTION_WATCH,"Watch");
                    case 1:
                        return new Action(42,"Rent");
                    case 2:
                        return new Action(42,"Preview");
                }
                return null;
            }
        });
    }

    private void createRelatedRow(){
        ElemPresender elemPresdr = new ElemPresender(getContext());
        ArrayObjectAdapter listRowAdapt = new ArrayObjectAdapter(elemPresdr);
        Model.Element elem1 = new Model.Element(Model.TYPE_TEXT,"Elem1","Related elem1","");
        listRowAdapt.add(elem1);
        Model.Element elem2 = new Model.Element(Model.TYPE_IMAGE,"Elem2","Related elem2","");
        listRowAdapt.add(elem2);
        Model.Element elem3 = new Model.Element(Model.TYPE_CARD,"Elem3","Related elem3","");
        listRowAdapt.add(elem3);
        HeaderItem hitem = new HeaderItem("Related");
        mRowsAdapter.add(new ListRow(hitem,listRowAdapt));
    }

    private String getURLForResource (int res_id) {
        return Uri.parse("android.resource://"+getContext().getPackageName()+"/" +res_id).toString();
    }

    private void setUpListeners(){
        setOnItemViewClickedListener(new OnItemViewClickedListener() {
            @Override
            public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
                if(item instanceof Model.Element) {
                    Model.Element elem = (Model.Element) item;
                    Toast.makeText(getContext(), "Click:" + elem.getTitle(), Toast.LENGTH_SHORT).show();
                }
                else if(item instanceof Action) {
                    Action act = (Action)item;
                    int act_id = (int) act.getId();
                    Toast.makeText(getContext(), "Action:"+act_id, Toast.LENGTH_SHORT).show();
                    if(act_id==ACTION_WATCH){
                        Intent i = new Intent(getContext(),PlayerActivity.class);
                        //detailElem.videoPath = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
                        //detailElem.videoPath = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4";
                        if(detailElem.getType()==Model.TYPE_TEXT)
                            detailElem.videoPath = getURLForResource(R.raw.big_bunny);
                        else
                            detailElem.videoPath = getURLForResource(R.raw.rodos);
                        i.putExtra(PlayerActivity.ELEM_KEY,detailElem);
                        startActivity(i);
                        }
                    }
                }
        });

        setOnSearchClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //...
            }
        });
    }


}
