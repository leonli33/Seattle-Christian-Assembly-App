package com.leon.scaapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Leon on 7/13/2018.
 */

public class MessageAdapter extends BaseAdapter {
    ArrayList<Object> messages;
    ArrayList<String> title = new ArrayList<String>();
    ArrayList<String> MessageLinks = new ArrayList<String>();
    Context c;
    LayoutInflater inflator;
    static final int ROW = 0;
    static final int HEADER =1;
    int clickPosition;

    int mt;

    public int getClickPosition()
    {
        return clickPosition;
    }
    public void setMessageLinks(ArrayList<String> m)
    {
        MessageLinks = m;
    }

    public MessageAdapter(Context context, ArrayList<Object> messages, ArrayList<String> m, ArrayList<String> T, int messageType)
    {
        title = T;
        MessageLinks = m;
        c = context;
        this.messages = messages;
        mt = messageType;
        //super(context,0,messages);
    }
    public int getPosition()
    {
        return clickPosition;
    }
    @Override
    public int getCount()
    {
        return messages.size();
    }
    @Override
    public Object getItem(int pos)
    {
        return messages.get(pos);
    }
    @Override
    public long getItemId(int pos)
    {
        return 0;
    }
    @Override
    public int getItemViewType(int position)
    {
        if (getItem(position) instanceof Message )
        {
            return ROW;
        }
        return HEADER;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        int type = getItemViewType(position);
        ViewHolder mainViewHolder = null;
    //    if (convertView == null) {
            inflator = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            switch (type) {
                case ROW:
                    convertView = inflator.inflate(R.layout.formmessages, null);
                    break;
                case HEADER:
                    convertView = inflator.inflate(R.layout.headermodel, null);

                default:
                    break;
            }


        switch (type) {
            case ROW:
                Message m = (Message) getItem(position);
                TextView messageAuthor = convertView.findViewById(R.id.labelAuthor);
                TextView messageTitle = convertView.findViewById(R.id.labelTitle);
                TextView messageDate = convertView.findViewById(R.id.labelDate);

                messageAuthor.setText(m.getAuthor());
                messageTitle.setText(m.getTitle());
                messageDate.setText(m.getDate());
                ViewHolder v = new ViewHolder();
                v.buttonPlay = convertView.findViewById(R.id.Button);
                v.buttonPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickPosition = position;
                        Intent newIntent = new Intent(c,Listen_To_Message.class);
                        newIntent.putExtra("index",clickPosition);
                        newIntent.putExtra("URLList",MessageLinks);
                        newIntent.putExtra("MessageTitle",title);
                        newIntent.putExtra("MessageType",mt);
                        c.startActivity(newIntent);

                      //  Toast.makeText(c,"Button was clicked for list item" + position,Toast.LENGTH_SHORT).show();
                    }
                });
                convertView.setTag(v);

                break;
            case HEADER :
                String Header = (String) getItem(position);
                TextView HeaderList = convertView.findViewById(R.id.Header);

                HeaderList.setText(Header);
            default:
                break;
        }

       // }

        return convertView;
    }
    public class ViewHolder{
        Button buttonPlay;
    }





//        Message message = getItem(position);
//        if(convertView == null)
//        {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.formmessages,parent,false);
//        }
//        TextView messageAuthor = convertView.findViewById(R.id.labelAuthor);
//        TextView messageTitle = convertView.findViewById(R.id.labelTitle);
//        TextView messageDate = convertView.findViewById(R.id.labelDate);
//
//        messageAuthor.setText(message.author);
//        messageDate.setText(message.date);
//        messageTitle.setText(message.title);
//
//        return convertView;

}
