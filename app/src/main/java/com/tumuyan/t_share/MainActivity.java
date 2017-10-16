package com.tumuyan.t_share;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Math.abs;


public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private EditText mTextMessage0;


     int i =1;
    public String text0 = "";
    public String text9 = "";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // get share 2 text3
            Intent intentg = getIntent();
            String action = intentg.getAction();  //分享的action都是Intent.ACTION_SEND
            String type = intentg.getType();//获取分享来的数据类型，和上面<data android:mimeType="text/plain" />中的一致
            //具体还有其他的类型，请上网参考
            // if (Intent.ACTION_SEND.equals(action) && type != null) {    if ("text/plain".equals(type)) {       }    }
            String text3 = intentg.getStringExtra(Intent.EXTRA_TEXT);  //text3即分享信息



            // get clipboard 2 text
            ClipboardManager mClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData abc = mClipboard.getPrimaryClip();
            ClipData.Item clipitem = abc.getItemAt(0);
            String text = clipitem.getText().toString();

   /*         if(i==1)
            {
                text0 =getString(R.string.content_Ctype) +  text;
            }  else {
                text0 = getString(R.string.content_Atype) +  "";
            }

*/
            if(i==1)
            {
                text0 = getString(R.string.content_Ctype) +  text;
                text9 = text3+"";
            }  else {
                text0 = getString(R.string.content_Atype) +  text3;
                text9 = text+"";
            }

            String text2="";
            if (text9.indexOf("http")>-1) {
                text2=text9.substring(text9.indexOf("http"));
                 text2 = text2.replaceAll("\\s(.)*","");
            }else {
                text2=getString(R.string.content_Empty);
            }


            //  set clipboard
            //   ClipData mClipData=ClipData.newPlainText(null,text);
            //  myClipboard.setPrimaryClip(mClipData);



            switch (item.getItemId()) {
                case R.id.navigation_home:
                    i=abs(i-1);
                    mTextMessage.setText(text0);

                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(text2);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(text2);
 //                   mTextMessage.setText(R.string.title_notifications);
                    Uri uri = Uri.parse(text2.toString());
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ClipboardManager mClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData abc = mClipboard.getPrimaryClip();
        ClipData.Item clipitem = abc.getItemAt(0);
        String text = clipitem.getText().toString();
  //      mTextMessage = (EditText)findViewById(R.id.editText);
        mTextMessage = (TextView) findViewById(R.id.message);
        mTextMessage.setText(getString(R.string.content_Ctype)+ text);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
