package ecomoter.myapplication;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.net.URL;

/**
 * Created by lwl on 2017/4/19.
 * Describe:
 */

public class DialogFragment1 extends DialogFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window=getDialog().getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.DialogF);
//        DisplayMetrics dm = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics( dm );
//        getDialog().getWindow().setLayout( dm.widthPixels, getDialog().getWindow().getAttributes().height );
        /**
         * 设置占满全屏
         */
        window.setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT) );
        setStyle(STYLE_NO_FRAME,0);
        WindowManager.LayoutParams lp=window.getAttributes();
        lp.width=lp.MATCH_PARENT;
//        lp.alpha=0.2f;
        window.setAttributes(lp);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.dialog_view,null);
        builder.setView(view);

//        builder.setMessage("你好");
//        builder.setTitle("温馨提示");
//        builder.setPositiveButton("我再想想", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getActivity(),"我再想想",Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        builder.setNegativeButton("去意已决", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getActivity(),"去意已决",Toast.LENGTH_SHORT).show();
//            }
//        });
        AlertDialog dialog=builder.create();


        return dialog;
    }

}
