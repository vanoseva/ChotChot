package adlm.t3k48.ChotChot;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.widget.RelativeLayout;
import android.view.View.OnLayoutChangeListener;
import android.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: vanoseva
 * Date: 12/29/12
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class IngameActivity extends Activity implements OnLayoutChangeListener, AdlmGroundListener {
    // Khoang cach giua ground voi cac thanh phan xung quan.
    public static int SPACE_GROUND = 5;
    public static int GROUND_ROW_NUMBER = 2;
    public static int GROUND_COL_NUMBER = 2;
    public static int SQUARE_ROW_NUMBER = 10;
    public static int SQUARE_COL_NUMBER = 10;

    // Xac dinh trang thai khoi tao cac ground.
    private boolean initialize = false;

    private RelativeLayout rlIngame;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ingame);

        Intent intent = this.getIntent();
        //Log.v("novastar", "My text pass from MyActivity: " + intent.getStringExtra("mymsg"));



        this.rlIngame = (RelativeLayout)this.findViewById(R.id.rlIngame);
        if (this.rlIngame == null) {
            // Thong bao loi tai day.
            return;
        }
        this.rlIngame.addOnLayoutChangeListener(this);

        //Log.v("novastar", "Ingame: " + rlIngame.getWidth() + " - " + rlIngame.getHeight());
    }

    @Override
    public void onStart() {
        super.onStart();
        //Log.v("novastar", "onStart: Ground Width = " + this.adlmGround.getWidth() + " - Ground Height = " + this.adlmGround.getHeight());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        //Log.v("novastar", "onWindowFocusChanged: Ground Width = " + this.adlmGround.getWidth() + " - Ground Height = " + this.adlmGround.getHeight());
    }

    /**
     * Multiplay se co 02 ground.
     */
    private void drawGround(int width, int height) {
        if (this.initialize) {
            return;
        }

        int groundWidth = (width - (IngameActivity.GROUND_COL_NUMBER + 1) * IngameActivity.SPACE_GROUND) / IngameActivity.GROUND_COL_NUMBER;
        int groundHeight = (height - (IngameActivity.GROUND_ROW_NUMBER + 1) * IngameActivity.GROUND_ROW_NUMBER) / IngameActivity.GROUND_ROW_NUMBER;

        for (int i = 0; i < IngameActivity.GROUND_COL_NUMBER; i++) {
            for (int j = 0; j < IngameActivity.GROUND_ROW_NUMBER; j++) {

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(groundWidth, groundHeight);
                lp.leftMargin = groundWidth * i + (i + 1) * AdlmGround.SPACE_WIDTH;
                lp.topMargin = groundHeight * j + (j + 1) * AdlmGround.SPACE_HEIGHT;

                AdlmGround adlmGround = new AdlmGround(this);
                adlmGround.setLayoutParams(lp);
                adlmGround.setSquare(IngameActivity.SQUARE_COL_NUMBER, IngameActivity.SQUARE_ROW_NUMBER);
                adlmGround.setAdlmGroundListener(this);
                this.rlIngame.addView(adlmGround);
            }
        }

        this.initialize = true;
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

        Log.v("novastar", "Ingame: " + v.getWidth() + " - " + v.getHeight());

        this.drawGround(v.getWidth(), v.getHeight());
    }


    public void onGroundFinish(Object sender) {
        // Hien thi thong bao nguoi choi da hoan thanh.
        Log.v("novastar", "Finish");

        setContentView(R.layout.result);

    }
}
