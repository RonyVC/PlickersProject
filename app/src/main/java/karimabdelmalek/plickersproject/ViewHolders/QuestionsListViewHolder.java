package karimabdelmalek.plickersproject.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import karimabdelmalek.plickersproject.R;

/**
 * Created by karimtalaat on 9/13/15.
 */
public class QuestionsListViewHolder extends RecyclerView.ViewHolder
{
    public static TextView questionBody;



    public QuestionsListViewHolder(View view) {
        super(view);
        this.questionBody = (TextView) view.findViewById(R.id.question_body);
    }
}
