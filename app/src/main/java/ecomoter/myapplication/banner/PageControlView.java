package ecomoter.myapplication.banner;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import ecomoter.myapplication.R;

/**
 * 导航
 * @author ld
 * 20130711
 *
 */
public class PageControlView extends LinearLayout {
	private Context context;
	public int count;
	public int image[] = { R.mipmap.index_page_control_u,
			R.mipmap.index_page_control_s };

	public PageControlView(Context context) {
		super(context);
		this.context = context;
	}

	public PageControlView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public void generatePageControl(int currentPage) {
		this.removeAllViews();
		for (int i = 0; i < count; i++) {
			ImageView imageView = new ImageView(context);

			if (i == currentPage-1) {
				imageView.setImageResource(image[1]);
			}else {
				imageView.setImageResource(image[0]);
			}
			LayoutParams lp = new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.setMargins(0, 0, 10, 0);
			imageView.setLayoutParams(lp);
			this.addView(imageView);
		}
	}
}