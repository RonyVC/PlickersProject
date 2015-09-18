package karimabdelmalek.plickersproject;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import karimabdelmalek.plickersproject.Adapters.StudentSelectedChoicesAdapter;
import karimabdelmalek.plickersproject.DataClasses.PollResponse;
import karimabdelmalek.plickersproject.DataClasses.Response;
import karimabdelmalek.plickersproject.Helpers.Utilities;

/**
 * Created by karimtalaat on 9/16/15.
 */
public class ActivityStudentChoices extends ActionBarActivity
{
    Toolbar toolBar;
    ListView studentsChoices;
    String selectedQuestion ;
    ArrayList <PollResponse> response;
    ArrayList<Response> responses;
    StudentSelectedChoicesAdapter listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_choices);

        toolBar = (Toolbar) findViewById(R.id.toolbar);
        toolBar.setTitle(R.string.studentsChoicesTitle);
        toolBar.setTitleTextColor(getResources().getColor(R.color.whiteColor));
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        studentsChoices = (ListView) findViewById(R.id.student_choices_list);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            selectedQuestion = extras.getString("selectedQuestion");
            response = (ArrayList<PollResponse>)extras.getSerializable("Response");
        }

        responses = Utilities.getStudentResponses(response,selectedQuestion);
        listAdapter = new StudentSelectedChoicesAdapter(this,R.layout.students_card_item,responses);
        studentsChoices.setAdapter(listAdapter);


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
