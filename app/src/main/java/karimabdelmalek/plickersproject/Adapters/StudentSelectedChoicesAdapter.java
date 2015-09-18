package karimabdelmalek.plickersproject.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import karimabdelmalek.plickersproject.DataClasses.Response;
import karimabdelmalek.plickersproject.R;

/**
 * Created by karimtalaat on 9/17/15.
 */
public class StudentSelectedChoicesAdapter extends ArrayAdapter<Response>
{
    Context currentContext;
    List<Response> responses;
    public StudentSelectedChoicesAdapter(Context context, int textViewResourceId,
                         List<Response> _List) {
        super(context, textViewResourceId, _List);
        currentContext = context;
        responses = _List;
        // TODO Auto-generated constructor stub
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(currentContext);
            View row = inflater
                    .inflate(R.layout.students_card_item, parent, false);

            final Response currentResponse = responses.get(position);
            TextView studentEmail = (TextView) row.findViewById(R.id.studentEmail);
            studentEmail.setText(currentResponse.student);
            TextView studentChoice = (TextView) row.findViewById(R.id.studentChoice);
            studentChoice.setText(currentContext.getResources().getString(R.string.choiceStr) + currentResponse.answer);
            TextView studentCard = (TextView) row.findViewById(R.id.studentCard);
            studentCard.setText(currentContext.getResources().getString(R.string.cardStr) + Integer.toString(currentResponse.card));
           return row;
        }
        //else
       // {
            //return convertView;
        //}
}

