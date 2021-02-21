package gkyrits.test.tv_demo_try1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.leanback.app.BackgroundManager;
import androidx.leanback.app.BrowseFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.DividerRow;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.PageRow;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.SectionRow;

public class PageFragment extends BrowseFragment  implements KeyHandler{

    private static final int PAGE1_ID =1;
    private static final int PAGE2_ID =2;
    private static final int PAGE3_ID =3;
    private static final int PAGE4_ID =4;
    private static final int PAGE5_ID =5;

    private ArrayObjectAdapter mRowsAdapter;
    private BackgroundManager mBackgroundManager;
    private Fragment fragm;

    private boolean header_open=true;

    //-----------------------
    private class PageRowFragmentFactory extends BrowseFragment.FragmentFactory{

        @Override
        public Fragment createFragment(Object rowObj) {
            Row row = (Row) rowObj;
            //mBackgroundManager.setDrawable(null);
            switch((int) row.getHeaderItem().getId()){
                case PAGE1_ID:
                    fragm = new PageAFragment();
                    return fragm;
                case PAGE2_ID:
                    fragm = new PageBFragment();
                    return fragm;
                case PAGE3_ID:
                    fragm = new PageCFragment();
                    return fragm;
                case PAGE4_ID:
                    fragm = new PageDFragment();
                    return fragm;
                case PAGE5_ID:
                    fragm = new PageEFragment();
                    return fragm;
            }
            return null;
        }
    }
    //-----------------------



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //...
        setupUI();
        setupRowAdapter();
        setupPageManager();
        setupListeners();
    }

    private void setupPageManager(){
        mBackgroundManager = BackgroundManager.getInstance(getActivity());
        mBackgroundManager.attach(getActivity().getWindow());
        getMainFragmentRegistry().registerFragment(PageRow.class, new PageRowFragmentFactory());
    }

    private void setupRowAdapter(){
        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        mRowsAdapter.add(new SectionRow(new HeaderItem("Main Pages!")));
        mRowsAdapter.add(new DividerRow());
        createPages();
        setAdapter(mRowsAdapter);

        /*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                createPages();
                startEntranceTransition();
            }
        }, 2000);
         */
    }

    private void createPages(){
        HeaderItem headerItem1 = new HeaderItem(PAGE1_ID, "Page 1");
        PageRow pageRow1 = new PageRow(headerItem1);
        mRowsAdapter.add(pageRow1);

        HeaderItem headerItem2 = new HeaderItem(PAGE2_ID, "Page 2");
        PageRow pageRow2 = new PageRow(headerItem2);
        mRowsAdapter.add(pageRow2);

        HeaderItem headerItem3 = new HeaderItem(PAGE3_ID, "Page 3");
        PageRow pageRow3 = new PageRow(headerItem3);
        mRowsAdapter.add(pageRow3);

        HeaderItem headerItem4 = new HeaderItem(PAGE4_ID, "Page 4");
        PageRow pageRow4 = new PageRow(headerItem4);
        mRowsAdapter.add(pageRow4);

        HeaderItem headerItem5 = new HeaderItem(PAGE5_ID, "Page 5");
        PageRow pageRow5 = new PageRow(headerItem5);
        mRowsAdapter.add(pageRow5);
    }

    private void setupUI(){
        setTitle("Page Demo");
        setHeadersState(HEADERS_ENABLED);
        //setHeadersTransitionOnBackEnabled(false);
        //prepareEntranceTransition();
    }

    private void setupListeners(){
        /*setOnSearchClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Search...",Toast.LENGTH_SHORT).show();
            }
        });*/

        setBrowseTransitionListener(new BrowseTransitionListener(){
            @Override
            public void onHeadersTransitionStop(boolean withHeaders) {
                super.onHeadersTransitionStop(withHeaders);
                //Toast.makeText(getContext(),"Headers "+withHeaders,Toast.LENGTH_SHORT).show();
                header_open=withHeaders;
            }
        });
    }

    @Override
    public boolean handleKeyDown(int keyCode){
        if(header_open)
            return false;
            switch(keyCode){
                case KeyEvent.KEYCODE_DPAD_UP:
                case KeyEvent.KEYCODE_DPAD_DOWN:
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    KeyHandler keyhdl = (KeyHandler)fragm;
                    return keyhdl.handleKeyDown(keyCode);
            }
        return false;
    }

}
