package com.example.loginform2;


import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.InputFilter;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.RelativeLayout.ALIGN_PARENT_RIGHT;
import static java.lang.System.loadLibrary;

public class MainActivity extends Activity {
    public String sGameActivity = "com.unity3d.player.UnityPlayerActivity";
    //url para seu canal no youtube - URL YOUTUBE CHANEL
    public String sUrl = "https://www.youtube.com/c";
    public String sUser = "";

// nao modifique os nomes dos edtext e buttoon
    EditText mail, pass;
    Button init;
    ProgressBar progressBar;




    private void SetupForm() {

        //Add relative layout

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setBackgroundColor(Color.parseColor("#ff1f2b3f"));
        linearLayout.setPadding(convertDipToPixels(15.0f), convertDipToPixels(15.0f), convertDipToPixels(15.0f), convertDipToPixels(15.0f));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        //Big text - BASE 64
        TextView teamName = new TextView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(convertDipToPixels(10.0f), convertDipToPixels(10.0f), convertDipToPixels(10.0f), convertDipToPixels(10.0f));
        teamName.setTextColor(Color.RED);
        teamName.setLayoutParams(layoutParams);
        teamName.setGravity(1);
        teamName.setText(new String(Base64.decode("SFlVUEFJIE1PRFM=", 0)));
        teamName.setTextSize(45.0f);


        //Login to play note text
        TextView note = new TextView(this);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.setMargins(0, 0, 0, 150);
        layoutParams.setMargins(convertDipToPixels(10.0f), convertDipToPixels(10.0f), convertDipToPixels(10.0f), convertDipToPixels(10.0f));
        note.setLayoutParams(layoutParams2);
        note.setGravity(1);
        note.setText(Html.fromHtml("<font face='fantasy'><b><font color='#57c4aa'>LOGIN PARA JOGAR</b></font>"));
        note.setTextSize(20.0f);

        //Username text
        TextView userTextView = new TextView(this);
        userTextView.setText("Usuario:");
        userTextView.setTextColor(Color.parseColor("#ffff00"));
        userTextView.setTextSize(20.0f);

        TextView test = new TextView(this);
        test.setText("");
        test.setTextColor(Color.parseColor("#ff0000"));
        test.setTextSize(20.0f);



        //Username form
        mail = new EditText(this);
        mail.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        mail.setHint("Digite o Usuário...");
        mail.setHintTextColor(Color.parseColor("#fffafa"));
        mail.setTextColor(Color.parseColor("#ffffff"));
        mail.setSingleLine(true);
        mail.setFilters(new InputFilter[]{new InputFilter.LengthFilter(32)});

        //Password text
        TextView passTextView = new TextView(this);
        passTextView.setText("Senha:");
        passTextView.setTextColor(Color.parseColor("#ffff00"));
        passTextView.setTextSize(20.0f);

        //Password form
        pass = new EditText(this);
        pass.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        pass.setHint("Digite a senha...");
        pass.setHintTextColor(Color.parseColor("#fffafa"));
        pass.setTextColor(Color.parseColor("#ffffff"));
        pass.setSingleLine(true);
        pass.setInputType(129);

        //Framelayout
        FrameLayout frameLayout = new FrameLayout(this);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -2);
        layoutParams3.topMargin = convertDipToPixels(15.0f);
        layoutParams3.bottomMargin = convertDipToPixels(15.0f);
        layoutParams3.gravity = 17;

        frameLayout.setLayoutParams(layoutParams3);
        init = new Button(this);
        init.setBackgroundColor(Color.parseColor("#FF0000"));
        init.setText("Logar");
        init.setTextColor(Color.parseColor("#e8f8f4"));


        frameLayout.addView(init);

        RelativeLayout relativelayout = new RelativeLayout(this);
        relativelayout.setLayoutParams(new RelativeLayout.LayoutParams(-2, -1));
        relativelayout.setPadding(convertDipToPixels(5.0f), convertDipToPixels(5.0f), convertDipToPixels(5.0f), convertDipToPixels(5.0f));
        relativelayout.setBackgroundColor(Color.parseColor("#ff1f2b3f"));
        relativeLayout.setVerticalGravity(16);



        //Footer text
        LinearLayout linearLayout3 = new LinearLayout(this);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams5.addRule(12);
        linearLayout3.setLayoutParams(layoutParams5);
        linearLayout3.setOrientation(LinearLayout.VERTICAL);

        TextView footerText = new TextView(this);
        footerText.setText(Html.fromHtml("<font face='monospace'>Tela de login by <font color='#ffff00'>Hyupai Mods</font></font>"));
        footerText.setTextColor(Color.parseColor("#00ff00"));
        footerText.setGravity(17);
        footerText.setTextSize(17.0f);
        linearLayout3.addView(footerText);

        footerText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(sUrl + "/S4muu")));
            }
        });

        //Description with scroll view
        ScrollView scrollView = new ScrollView(this);
        RelativeLayout.LayoutParams rlsv = new RelativeLayout.LayoutParams(-1, -1);
        rlsv.topMargin = 100;
        rlsv.bottomMargin = 50;
        scrollView.setLayoutParams(rlsv);
        scrollView.setScrollBarSize(convertDipToPixels(5.0f));

        TextView desc = new TextView(this);
        desc.setText(Html.fromHtml("Olá Amigo!<br/>Essa tela de login foi feita pelo <b><font color='red'>Hyupai Mods</b><br/><br/>Para usar o Mod você deve ter um <b><font color='red'>login!</font></b><br/>Se você não tem um login clique no meu nome na parte debaixo..."));
        desc.setTextColor(Color.parseColor("#ffffff"));
        desc.setTextSize(20.0f);

        //Add views
        scrollView.addView(desc);
        relativelayout.addView(scrollView);
        linearLayout.addView(teamName);
        linearLayout.addView(note);
        linearLayout.addView(userTextView);
        linearLayout.addView(mail);
        linearLayout.addView(passTextView);
        linearLayout.addView(pass);
        linearLayout.addView(test);
        linearLayout.addView(frameLayout);
        linearLayout.addView(relativelayout);
        relativeLayout.addView(linearLayout);
        relativeLayout.addView(linearLayout3);

        //OnClick listeners
        footerText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(sUrl + "/S4muu")));
            }
        });


        setContentView(relativeLayout);
        TryLoginPHP();
    }

    private final String USER = "USER";
    private final String PASS = "PASS";
    private Prefs prefs;
    private void TryLoginPHP() {
        prefs = Prefs.with(this);

        mail.setText(prefs.read(USER, ""));
        pass.setText(prefs.read(PASS, ""));


        init.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (!mail.getText().toString().isEmpty() && !pass.getText().toString().isEmpty()) {
                    prefs.write(USER, mail.getText().toString());
                    prefs.write(PASS, pass.getText().toString());
                    new Auth(MainActivity.this).execute(mail.getText().toString(), pass.getText().toString());
                }
                if (mail.getText().toString().isEmpty() && pass.getText().toString().isEmpty()) {
                    mail.setError("Please enter username");
                    pass.setError("Please enter password");
                }
                if (mail.getText().toString().isEmpty()) {
                    mail.setError("Please enter username");
                }
                if (pass.getText().toString().isEmpty()) {
                    pass.setError("Please enter password");
                }
            }
        });
    }
    private int convertDipToPixels(float f) {
        return (int) ((f * getResources().getDisplayMetrics().density) + 0.5f);
    }



    public String urlRequest(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(str).openConnection().getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                sb.append("\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //comente ou descomente essa parte
        loadLibrary("MyLib");
        SetupForm();
    }
}