package karimabdelmalek.plickersproject;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.adapters.SlideInRightAnimationAdapter;
import karimabdelmalek.plickersproject.Adapters.QuestionsListAdapter;
import karimabdelmalek.plickersproject.DataClasses.PollResponse;
import karimabdelmalek.plickersproject.DataClasses.Question;
import karimabdelmalek.plickersproject.Helpers.ServicesHandler;
import karimabdelmalek.plickersproject.Helpers.Utilities;
import karimabdelmalek.plickersproject.Helpers.WebDataCallBack;
import karimabdelmalek.plickersproject.ViewHolders.QuestionsListViewHolder;


public class MainActivity extends ActionBarActivity {
    ArrayList<PollResponse> response;
    ArrayList<Question> questions;
    RecyclerView questionsList;
    ProgressBar progressBar;
    QuestionsListAdapter questionsAdapter;
    Toolbar toolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        questions = new ArrayList<Question>();
        questionsList = (RecyclerView) findViewById(R.id.questions_list);
        questionsList.setLayoutManager(new LinearLayoutManager(this));
        toolBar = (Toolbar) findViewById(R.id.toolbar);
        toolBar.setTitle(R.string.questionsTitle);
        toolBar.setTitleTextColor(getResources().getColor(R.color.whiteColor));
        setSupportActionBar(toolBar);
        Utilities.LoadData(this,progressBar,dataCallBack);

    }

    WebDataCallBack dataCallBack = new WebDataCallBack() {
        @Override
        public void resumeUi(Object result) {
            if(result!=null && response.size() > 0)
            {
                progressBar.setVisibility(View.GONE);
                Utilities.filterQuestions(response,questions);
                questionsAdapter = new QuestionsListAdapter(MainActivity.this, questions,response);
                questionsList.setAdapter(questionsAdapter);

            }

        }

        @Override
        public void handleException(Exception e) {

        }

        @Override
        public Object callService() throws IOException, JSONException, URISyntaxException {
            response = ServicesHandler.GetPollResponse();
            return response;
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
