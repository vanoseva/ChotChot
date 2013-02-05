package adlm.t3k48.ChotChot;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.content.Context;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: vanoseva
 * Date: 12/29/12
 * Time: 11:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class AdlmSquare extends RelativeLayout implements View.OnTouchListener {

    public static int INVALID_NUMBER = -1;

    // Hien thi so cua o.
    // TODO: Sau nay chung ta co the mo rong khong chi la so,
    // ma co the la hinh anh bat ky, vi vay chung ta nen the
    // hien day la ImageView hon la TextView.
    private TextView tvNumber;

    // Hinh nen cho square.
    private ImageView ivBackground;

    private Context context;

    private AdlmSquareListener listener;

//    // Gia tri hien thi tren tvNumber.
//    // TODO: Sau nay chung ta nen luu id cua image hon la chi
//    // don thuan so.
    private int number;

    public AdlmSquare(Context context) {
        super(context);

        this.init(context);
    }

    /**
     * Khoi tao cac gia tri ban dau.
     */
    private void init(Context context) {

        this.context = context;

        this.listener = null;

        this.number = AdlmSquare.INVALID_NUMBER;

        RelativeLayout.LayoutParams lpBackground = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpBackground.addRule(RelativeLayout.CENTER_VERTICAL);
        lpBackground.addRule(RelativeLayout.CENTER_HORIZONTAL);

        this.ivBackground = new ImageView(this.context);
        this.ivBackground.setLayoutParams(lpBackground);
        this.addView(this.ivBackground);

        RelativeLayout.LayoutParams lpNumber = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lpNumber.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lpNumber.addRule(RelativeLayout.CENTER_VERTICAL);

        this.tvNumber = new TextView(this.context);
        this.tvNumber.setLayoutParams(lpNumber);
        this.tvNumber.setText(Integer.toString(0));     // Mac dinh de gia tri 0.

        this.addView(this.tvNumber);

        this.setOnTouchListener(this);

        this.setBackgroundColor(Color.RED);
    }

    /**
     * Thiet lap anh nen cho square.
     * NOTE: Can phai goi ham nay ngay sau khi khoi tao.
     * @param backgroundResourceID ID resource (drawable).
     */
    public void setBackground(int backgroundResourceID) {
        this.ivBackground.setImageResource(backgroundResourceID);
    }

    /**
     * Hien thi noi dung cua square.
     * TODO: Ham nay se sua lai de tham so truyen vao
     * la ID cua resource (image).
     * @param number
     */
    public void setContent(int number) {
        this.number = number;

        // Hien thi number ra man hinh.
        this.tvNumber.setText(Integer.toString(number));
    }

    public int getContent() {
        return this.number;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int pointerCount = event.getPointerCount();

        for (int i = 0; i < pointerCount; i++) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    Log.v("novastar", "onTouchUp: " + this.tvNumber.getText());

                    if (this.listener != null) {
                        //Log.v("novastar", "getContent: " + this.getContent());
                        this.listener.onSquareClick(this, new AdlmSquareArguments(this.number));
                    }

                    break;
            }
        }


        return true;
    }

    /**
     * Khi extend ViewGroup, can phai override ham nay, neu khong cac child view se khong hien
     * thi.
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    public void setAdlmSquareListener(AdlmSquareListener listener) {
        this.listener = listener;
    }
}
