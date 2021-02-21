package gkyrits.test.tv_demo_try1;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class PageActivity extends Activity {

    private PageFragment page_frmg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        page_frmg = (PageFragment)getFragmentManager().findFragmentById(R.id.page_fragment);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(page_frmg.handleKeyDown(keyCode))
            return true;
        return super.onKeyDown(keyCode, event);
    }
}