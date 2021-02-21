package gkyrits.test.tv_demo_try1;



import android.os.Bundle;
import android.widget.Toast;

import androidx.leanback.app.PlaybackFragment;
import androidx.leanback.widget.AbstractDetailsDescriptionPresenter;
import androidx.leanback.widget.Action;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ClassPresenterSelector;
import androidx.leanback.widget.ControlButtonPresenterSelector;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.PlaybackControlsRow;
import androidx.leanback.widget.PlaybackControlsRowPresenter;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;

public class PlayerControlFragment extends PlaybackFragment {

    private ArrayObjectAdapter mPrimaryActionsAdapter;
    private ArrayObjectAdapter mSecondaryActionsAdapter;

    private PlaybackControlsRow.PlayPauseAction mPlayPauseAction;
    private PlaybackControlsRow.RepeatAction mRepeatAction;
    private PlaybackControlsRow.ShuffleAction mShuffleAction;
    private PlaybackControlsRow.FastForwardAction mFastForwardAction;
    private PlaybackControlsRow.RewindAction mRewindAction;
    private PlaybackControlsRow.SkipNextAction mSkipNextAction;
    private PlaybackControlsRow.SkipPreviousAction mSkipPreviousAction;
    private PlaybackControlsRow.HighQualityAction mHighQualityAction;
    private PlaybackControlsRow.ClosedCaptioningAction mClosedCaptionAction;

    //-------------------------------
    static class DescriptionPresenter extends AbstractDetailsDescriptionPresenter {

        @Override
        protected void onBindDescription(ViewHolder vh, Object item) {
            Model.Element elem = (Model.Element)item;
            vh.getTitle().setText(elem.getTitle());
            vh.getTitle().setFocusable(true);
            vh.getTitle().requestFocus();
            vh.getTitle().setFocusable(false);
        }
    }
    //---------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Model.Element mElem = (Model.Element) getActivity().getIntent().getSerializableExtra(PlayerActivity.ELEM_KEY);
        setFadingEnabled(false);
        setBackgroundType(PlaybackFragment.BG_LIGHT);

        setupPlayAdapter(mElem);
        setupListener();
    }


    private void setupPlayAdapter(Model.Element elem) {
        ClassPresenterSelector playpres = createPlayPresenter();
        ArrayObjectAdapter mRowsAdapter = new ArrayObjectAdapter(playpres);
        PlaybackControlsRow mPlaybackControlsRow = createControlsRow(elem);
        mRowsAdapter.add(mPlaybackControlsRow);
        setAdapter(mRowsAdapter);
    }

    private ClassPresenterSelector createPlayPresenter(){
        ClassPresenterSelector prSelector = new ClassPresenterSelector();
        PlaybackControlsRowPresenter presenter = new PlaybackControlsRowPresenter(new DescriptionPresenter());
        presenter.setSecondaryActionsHidden(true);
        prSelector.addClassPresenter(PlaybackControlsRow.class, presenter);
        prSelector.addClassPresenter(ListRow.class, new ListRowPresenter());
        return prSelector;
    }

    private PlaybackControlsRow createControlsRow(Model.Element elem) {
        PlaybackControlsRow mPlaybackControlsRow = new PlaybackControlsRow(elem);
        ControlButtonPresenterSelector presenterSelector = new ControlButtonPresenterSelector();
        mPrimaryActionsAdapter = new ArrayObjectAdapter(presenterSelector);
        mSecondaryActionsAdapter = new ArrayObjectAdapter(presenterSelector);
        mPlaybackControlsRow.setPrimaryActionsAdapter(mPrimaryActionsAdapter);
        mPlaybackControlsRow.setSecondaryActionsAdapter(mSecondaryActionsAdapter);
        initActions();
        return mPlaybackControlsRow;
    }

    private void initActions() {
        mPlayPauseAction     = new PlaybackControlsRow.PlayPauseAction(getActivity());
        mRepeatAction        = new PlaybackControlsRow.RepeatAction(getActivity());
        mShuffleAction       = new PlaybackControlsRow.ShuffleAction(getActivity());
        mSkipNextAction      = new PlaybackControlsRow.SkipNextAction(getActivity());
        mSkipPreviousAction  = new PlaybackControlsRow.SkipPreviousAction(getActivity());
        mFastForwardAction   = new PlaybackControlsRow.FastForwardAction(getActivity());
        mRewindAction        = new PlaybackControlsRow.RewindAction(getActivity());
        mHighQualityAction   = new PlaybackControlsRow.HighQualityAction(getActivity());
        mClosedCaptionAction = new PlaybackControlsRow.ClosedCaptioningAction(getActivity());

        setupPrimaryActionsRow();
        setupSecondaryActionsRow();
    }

    private void setupPrimaryActionsRow() {
        mPrimaryActionsAdapter.add(mSkipPreviousAction);
        mPrimaryActionsAdapter.add(mRewindAction);
        mPrimaryActionsAdapter.add(mPlayPauseAction);
        mPrimaryActionsAdapter.add(mFastForwardAction);
        mPrimaryActionsAdapter.add(mSkipNextAction);
    }

    private void setupSecondaryActionsRow() {
        mSecondaryActionsAdapter.add(mRepeatAction);
        mSecondaryActionsAdapter.add(mShuffleAction);
        mSecondaryActionsAdapter.add(mHighQualityAction);
        mSecondaryActionsAdapter.add(mClosedCaptionAction);
    }

    private void setupListener(){
        setOnItemViewClickedListener(new OnItemViewClickedListener() {
            @Override
            public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
                Action action = (Action)item;
                if(action.getId() == mPlayPauseAction.getId()) {
                    if(mPlayPauseAction.getIndex() == PlaybackControlsRow.PlayPauseAction.PLAY) {
                        setFadingEnabled(true);
                        //mControlsCallback.play();
                        ((PlayerActivity)getActivity()).video.start();
                        //mRowsAdapter.notifyArrayItemRangeChanged(0, 1);
                    } else {
                        setFadingEnabled( false );
                        //mControlsCallback.pause();
                        ((PlayerActivity)getActivity()).video.pause();
                    }
                    ((PlaybackControlsRow.MultiAction) action).nextIndex();
                    mPrimaryActionsAdapter.notifyArrayItemRangeChanged(mPrimaryActionsAdapter.indexOf(action), 1);
                } else {
                    Toast.makeText( getActivity(), "Other action", Toast.LENGTH_SHORT ).show();
                }

            }
        });
    }



}
