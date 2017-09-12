package ecomoter.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class JumpToQQActivity extends AppCompatActivity {
    private final String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=491902166&version=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump_to_qq);
        findViewById(R.id.btn_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
            }
        });
    }
}
