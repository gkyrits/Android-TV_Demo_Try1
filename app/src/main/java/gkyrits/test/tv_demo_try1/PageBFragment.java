package gkyrits.test.tv_demo_try1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.leanback.app.BrowseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PageBFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PageBFragment extends Fragment implements BrowseFragment.MainFragmentAdapterProvider, KeyHandler {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PageBFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PageBFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PageBFragment newInstance(String param1, String param2) {
        PageBFragment fragment = new PageBFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void next_focus(){
        getView().requestFocus();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page_b, container, false);
    }

    @Override
    public BrowseFragment.MainFragmentAdapter getMainFragmentAdapter() {
        return new BrowseFragment.MainFragmentAdapter(this);
    }

    @Override
    public boolean handleKeyDown(int keyCode) {
        View v = getView();
        View vf = v.findFocus();
        if(vf==null)
            return false;
        View nf=null;
        switch(keyCode){
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                nf = v.findViewById(vf.getNextFocusRightId());
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                if(vf.getNextFocusLeftId()==R.id.browse_frame){
                    return false;
                }
                nf = v.findViewById(vf.getNextFocusLeftId());
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                nf = v.findViewById(vf.getNextFocusUpId());
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                nf = v.findViewById(vf.getNextFocusDownId());
                break;
        }
        if(nf!=null)
            nf.requestFocus();
        return true;
    }

}