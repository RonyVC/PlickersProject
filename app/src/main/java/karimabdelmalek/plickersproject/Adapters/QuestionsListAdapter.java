package karimabdelmalek.plickersproject.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import karimabdelmalek.plickersproject.ActivityQuestionDetails;
import karimabdelmalek.plickersproject.DataClasses.PollResponse;
import karimabdelmalek.plickersproject.DataClasses.Question;
import karimabdelmalek.plickersproject.Helpers.Utilities;
import karimabdelmalek.plickersproject.R;
import karimabdelmalek.plickersproject.ViewHolders.QuestionsListViewHolder;

/**
 * Created by karimtalaat on 9/13/15.
 */
public class QuestionsListAdapter extends RecyclerView.Adapter<QuestionsListViewHolder>
{

    Context context;
    ArrayList<Question> questions;
    ArrayList<PollResponse> response;
    Question currentQuestion;

    public QuestionsListAdapter(Context context, ArrayList<Question> questions,ArrayList<PollResponse>response)

    {
        this.context = context;
        this.questions = questions;
        this.response = response;
    }
    @Override
    public QuestionsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.queston_item_layout, null);
        QuestionsListViewHolder convertView = new QuestionsListViewHolder(v);
        return convertView;
    }

    @Override
    public void onBindViewHolder(final QuestionsListViewHolder holder, final int position)
    {
        currentQuestion = questions.get(position);
        holder.questionBody.setText(currentQuestion.body);
        Utilities.ApplyFont(holder.questionBody,context);
        holder.itemView.setTag(currentQuestion.body);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("selectedQuestion", holder.itemView.getTag().toString());
                i.putParcelableArrayListExtra("Response",response);
               i.setClass(context,ActivityQuestionDetails.class);
                context.startActivity(i);
            }
        });

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return (null != questions ? questions.size()-1 : 0);
    }
}
