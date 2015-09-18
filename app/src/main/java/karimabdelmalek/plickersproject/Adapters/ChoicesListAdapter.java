package karimabdelmalek.plickersproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import karimabdelmalek.plickersproject.DataClasses.Choice;
import karimabdelmalek.plickersproject.Helpers.Utilities;
import karimabdelmalek.plickersproject.R;

/**
 * Created by karimtalaat on 9/14/15.
 */
public class ChoicesListAdapter extends ArrayAdapter<Choice>
{
   Choice currentChoice;
    List<Choice> choices;
    Context context;
    LinearLayout choiceLayout;
    TextView title;
    public ChoicesListAdapter(Context context,List<Choice> choices,int resource) {
        super(context, resource, choices);
        this.choices = choices;
        this.context = context;
    }

    @Override
    public View getView(int position, View choice, ViewGroup parent) {
        currentChoice = choices.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.choices_item, parent, false);
        final String currentChoiceItem = choices.get(position).body;
        title = (TextView) row.findViewById(R.id.choiceText);
        title.setText(currentChoiceItem);
        Utilities.ApplyFontBold(title,context);
        choiceLayout = (LinearLayout)row.findViewById(R.id.choicesLayout);
        if(choices.get(position).correct)
            choiceLayout.setBackgroundColor(context.getResources().getColor(R.color.greenColor));
        else
            choiceLayout.setBackgroundColor(context.getResources().getColor(R.color.redColor));


        return row;
    }
}
