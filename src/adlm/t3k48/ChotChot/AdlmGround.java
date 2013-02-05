package adlm.t3k48.ChotChot;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.content.Context;
import android.view.ViewTreeObserver;
import android.util.Log;
import java.util.Vector;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: vanoseva
 * Date: 12/29/12
 * Time: 2:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdlmGround extends RelativeLayout implements  ViewTreeObserver.OnGlobalLayoutListener, AdlmSquareListener {

    public static int BEGIN_NUMBER = 1;

    private Context context;

    // So luong square trong ground.
    private int rowNumber;
    private int colNumber;

    private boolean  intialize;

    // Khoang cach giua cac square.
    public static int SPACE_WIDTH = 5;
    public static int SPACE_HEIGHT = 5;

    // So tiep theo can phai tim.
    private int nextNumber;

    // So cuoi cung can phai tim.
    // Khi tim duoc so nay thi van choi
    // se ket thuc.
    private int lastNumber;

    private AdlmGroundListener listener;

    public AdlmGround(Context context) {
        super(context);

        this.init(context);
    }

    private void init(Context context) {
        this.context = context;

        this.intialize = false;

        this.nextNumber = AdlmGround.BEGIN_NUMBER;
        this.lastNumber = AdlmSquare.INVALID_NUMBER;

        this.setBackgroundColor(Color.BLUE);
    }

    public void setSquare(int rowNumber, int colNumber) {
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
    }

    /**
     * Khi extend ViewGroup, can phai override ham nay, neu khong cac child view se khong hien
     * thi.
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected  void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Log.v("novastar", "onDraw");

        //Log.v("novastar", this + ": " + canvas.getWidth() + " - " + canvas.getHeight());
        //Log.v("novastar", this + ": " + this.getWidth() + " - " + this.getHeight());

        this.drawSquare();
    }

    @Override
    public void onGlobalLayout() {
        //Log.v("novastar", "onGlobalLayout " + this);
    }

    private void drawSquare() {

        if (this.intialize) {
            return;
        }

        int squareWidth = (this.getWidth() - (this.colNumber + 1) * AdlmGround.SPACE_WIDTH) / this.colNumber;
        int squareHeight = (this.getHeight() - (this.rowNumber + 1) * AdlmGround.SPACE_HEIGHT) / this.rowNumber;

        Vector numbers = new Vector();
        for (int i = 0; i < this.rowNumber * this.colNumber; i++) {
            numbers.addElement(AdlmGround.BEGIN_NUMBER + i);
        }

        this.lastNumber = this.rowNumber * this.colNumber;

        Random random = new Random();

        for (int i = 0; i < this.rowNumber; i++) {
            for (int j = 0; j < this.colNumber; j++) {

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(squareWidth, squareHeight);
                lp.leftMargin = squareWidth * j + (j + 1) * AdlmGround.SPACE_WIDTH;
                lp.topMargin = squareHeight * i + (i + 1) * AdlmGround.SPACE_HEIGHT;

                AdlmSquare adlmSquare = new AdlmSquare(this.context);
                adlmSquare.setLayoutParams(lp);
                adlmSquare.setLayoutParams(lp);

                int index = random.nextInt(numbers.size());
                int number = (Integer)numbers.elementAt(index);
                numbers.removeElementAt(index);

                adlmSquare.setContent(number);

                adlmSquare.setAdlmSquareListener(this);

                this.addView(adlmSquare);
            }
        }

        this.intialize = true;
    }


    public void onSquareClick(Object sender, AdlmSquareArguments args) {

        Log.v("novastar", "Check: " + args.Number + " - " + this.nextNumber);
        if (args.Number == this.nextNumber) {

            // Chuyen sang so tiep theo.
            this.nextNumber++;

            // Dua square nay ve trang thai da duoc chon dung.
            AdlmSquare square = (AdlmSquare)sender;
            square.setVisibility(RelativeLayout.INVISIBLE);

            //Log.v("novastar", "Square click: " + args.Number);

            if (args.Number == this.lastNumber) {
                // Raise su kien bao da Ground nay da hoan thanh.
                if (this.listener != null) {
                    this.listener.onGroundFinish(this);
                }
            }
        }
    }

    public void setAdlmGroundListener(AdlmGroundListener listener) {
        this.listener = listener;
    }
}
