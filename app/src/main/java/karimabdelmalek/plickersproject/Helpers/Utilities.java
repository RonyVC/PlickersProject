package karimabdelmalek.plickersproject.Helpers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import karimabdelmalek.plickersproject.DataClasses.Choice;
import karimabdelmalek.plickersproject.DataClasses.PollResponse;
import karimabdelmalek.plickersproject.DataClasses.Question;
import karimabdelmalek.plickersproject.DataClasses.Response;
import karimabdelmalek.plickersproject.MainActivity;
import karimabdelmalek.plickersproject.R;

/**
 * Created by karimtalaat on 9/12/15.
 */
public class Utilities
{
    public static Boolean CheckIfApplicationIsConnected(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null) {
            return activeNetInfo != null;
        } else {
            return false;
        }
    }


    public static void LoadData (Context context,ProgressBar progressBar, WebDataCallBack dataCallback)
    {
        if (Utilities.CheckIfApplicationIsConnected(context)) {
            progressBar.setVisibility(View.VISIBLE);
            //progressBar.setVisibility(View.GONE);
            NetworkThread mainThread = new NetworkThread(dataCallback);
            mainThread.start();

        } else {
            //Utilities.ShowNoConnMsg(ActivityLogin.this);
            progressBar.setVisibility(View.GONE);
            Toast.makeText(context,
                    context.getResources().getString(R.string.noConnectivity),
                    Toast.LENGTH_LONG).show();

        }
    }

    public static void goToPage(Context context, Class<MainActivity> nextPage)
    {
        Intent myIntent = new Intent(context,
                nextPage);
        context.startActivity(myIntent);
    }

    public static String getImageUri(ArrayList<PollResponse> response, String selectedQuestion)
    {
            for (int i=0;i<response.size();i++)
            {
                if(response.get(i).question.body.equals(selectedQuestion))
                {
                    return response.get(i).question.image;
                }
            }
        return null;
    }

    public static void filterQuestions(ArrayList<PollResponse> response, ArrayList<Question> questions)
    {
        for (int i=0; i<response.size() ;i++)
        {
                questions.add(response.get(i).question);
        }
    }



    public static List<Choice> getChoicesList(ArrayList<PollResponse> response,String selectedQuestion)
    {
        for (int i=0; i<response.size() ;i++)
        {
            if(response.get(i).question.body.equals(selectedQuestion))
            {
                return response.get(i).question.choices;
            }
        }
        return null;

    }

    public static void ApplyFont(TextView T, Context context) {
        Typeface font = Typeface.createFromAsset(context.getResources().getAssets(), "chunkfive.otf");
        T.setTypeface(font);
    }

    public static void ApplyFontBold(TextView T, Context context) {
        Typeface font = Typeface.createFromAsset(context.getResources().getAssets(), "chunkfive_bold.otf");
        T.setTypeface(font);
    }

    public static ArrayList<Response> getStudentResponses(ArrayList<PollResponse> response,String selectedQuestion)
    {
        for (int i=0; i<response.size() ;i++)
        {
            if(response.get(i).question.body.equals(selectedQuestion))
            {
                return response.get(i).responses;
            }
        }
        return null;

    }

}
