package com.example.thesaurus;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final int STARTING_CHILDREN = 2;

    Button btnSubmit;
    EditText wordInput;
    LinearLayout layout;


    private Context context;


    String[][] synonyms = new String[][] { {"swift",  "abrupt", "expeditious", "hasty", "nimble", "quick", "rapid", "speedy", "sudden", "unexpected"},
            {"objective", "detached", "disinterested", "dispassionate", "equitable", "evenhanded", "nonpartisan", "open-minded", "unbiased"},
            {"calculate", "adjust", "appraise", "consider", "count", "determine", "forecast", "gauge", "guess", "measure", "multiply", "reckon", "subtract", "tally", "weigh", "work out"},
            {"create", "build", "conceive", "constitute", "construct", "design", "devise", "discover", "establish", "forge", "form"} };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        btnSubmit = findViewById(R.id.btn_submit);
        wordInput = findViewById(R.id.word_input);
        layout = findViewById(R.id.layout);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSynonyms();

                String input = wordInput.getText().toString();

                String[] inputSynonyms = getSynonyms(input);

                for(String synonym: inputSynonyms){
                    layout.addView(makeText(synonym));
                }
            }
        });

    }

    private String[] getSynonyms(String s) {
        for(String[] group: synonyms){
            for(String word: group){
                if(s.equals(word)){
                    return group;
                }
            }
        }
        String[] noneFound = new String[1];
        noneFound[0] = "No known synonyms";

        return noneFound;
    }

    private TextView makeText(String synonym){

        TextView view = new TextView(context);
        view.setText(synonym);
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32);
        view.setPadding(15,15,15,15);
        view.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        view.setWidth(2000);

        return view;
    }

    private void clearSynonyms() {
        int numChildren = layout.getChildCount();
        for(int i = numChildren-1; i >= STARTING_CHILDREN; i-- ){
            View v = layout.getChildAt(i);
            layout.removeView(v);
        }
    }
}
