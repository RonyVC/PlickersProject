package karimabdelmalek.plickersproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;


import karimabdelmalek.plickersproject.Adapters.ChoicesListAdapter;
import karimabdelmalek.plickersproject.DataClasses.Choice;
import karimabdelmalek.plickersproject.DataClasses.PollResponse;
import karimabdelmalek.plickersproject.Helpers.Utilities;

/**
 * Created by karimtalaat on 9/14/15.
 */
public class ActivityQuestionDetails extends ActionBarActivity
{
    TextView questionBody;
    String selectedQuestion;
    ArrayList <PollResponse> response;
    ListView choicesList;
    Button studentsChoices;
    ImageView questionLogo;
    List<Choice> selectedQuestionChoices;
    ChoicesListAdapter choicesAdapter;
    ImageLoader imageLoader;
    View footerView;
    public static Context currentContext;
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_details);
        currentContext = this;
        questionBody = (TextView)findViewById(R.id.questionTextBody);
        choicesList = (ListView)findViewById(R.id.listOfChoices);
        questionLogo = (ImageView) findViewById(R.id.questionLogo);
        toolBar = (Toolbar) findViewById(R.id.toolbar);
        toolBar.setTitle(R.string.questionsDetailsTitle);
        toolBar.setTitleTextColor(getResources().getColor(R.color.whiteColor));
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        footerView = LayoutInflater.from(this).inflate(R.layout.students_button_layout, null);
        studentsChoices = (Button) (footerView).findViewById(R.id.studentsChoicesButton);
        studentsChoices.setOnClickListener(studentsChoicesClickListener);



        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            selectedQuestion = extras.getString("selectedQuestion");
            response = (ArrayList<PollResponse>)extras.getSerializable("Response");
        }

        questionBody.setText(selectedQuestion);
        Utilities.ApplyFontBold(questionBody, this);
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(currentContext));
        String uri = Utilities.getImageUri(response, selectedQuestion);
        imageLoader.displayImage(uri,questionLogo);


        selectedQuestionChoices = Utilities.getChoicesList(response,selectedQuestion);
        choicesAdapter = new ChoicesListAdapter(this,selectedQuestionChoices,R.layout.choices_item);
        choicesList.addFooterView(footerView);
        choicesList.setAdapter(choicesAdapter);

    }


    View.OnClickListener studentsChoicesClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent();
            i.putExtra("selectedQuestion", selectedQuestion);
            i.putParcelableArrayListExtra("Response",response);
            i.setClass(ActivityQuestionDetails.this,ActivityStudentChoices.class);
            ActivityQuestionDetails.this.startActivity(i);
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                // app icon in action bar clicked; goto parent activity.
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
