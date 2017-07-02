package br.com.rftemer.githubviewer.viewer.adapter;

import android.widget.BaseAdapter;
import android.content.Context;
import br.com.rftemer.githubviewer.model.GitHubItem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.rftemer.githubviewer.R;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
    private Context mContext;
    private GitHubItem[] items;

    public ListAdapter(Context context, GitHubItem[] items) {
        this.items = items;
        this.mContext = context;
    }

    public int getCount() {
        return items.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridview;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            gridview = inflater.inflate(R.layout.list_item, null);
        } else {
            gridview = convertView;
        }
        TextView name = (TextView) gridview.findViewById(R.id.project_name);
        TextView language = (TextView) gridview.findViewById(R.id.language);
        name.setText(items[position].getName());
        language.setText(items[position].getLanguage());
        return gridview;
    }



}
