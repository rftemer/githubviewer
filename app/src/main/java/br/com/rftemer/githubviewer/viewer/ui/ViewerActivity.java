package br.com.rftemer.githubviewer.viewer.ui;
import java.util.Arrays;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.ListView;

import br.com.rftemer.githubviewer.viewer.adapter.ListAdapter;
import android.os.Parcelable;
import br.com.rftemer.githubviewer.R;
import br.com.rftemer.githubviewer.model.GitHubItem;
import br.com.rftemer.githubviewer.utils.Constants;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

public class ViewerActivity extends AppCompatActivity {

    TextView mProjectName;

    private GitHubItem[] mProjects;
    private String mProjectNameBundle;

    private ListView list;

    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewer_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setElevation(0);

        if (getIntent().getExtras() != null && getIntent().hasExtra(Constants.GRIDITEMS_BUNDLE_ID)) {
            Parcelable[] projects = getIntent().getParcelableArrayExtra(Constants.GRIDITEMS_BUNDLE_ID);
            mProjects = Arrays.copyOf(projects, projects.length, GitHubItem[].class);

        }

        if (mProjects.length > 0) {
            if (getIntent().getExtras() != null
                    && getIntent().hasExtra(Constants.SEARCH_ITEM_BUNDLE_ID)) {
                mProjectNameBundle = getIntent().getExtras().getString(Constants.SEARCH_ITEM_BUNDLE_ID);
            }
            mProjectName = (TextView) findViewById(R.id.project_name);
            mProjectName.setText(mProjectNameBundle);
            list = (ListView) findViewById(R.id.list_view);
            list.setAdapter(new ListAdapter(this, mProjects));

            for (GitHubItem item : mProjects) {
                if (item.getOwner().getLogin().equalsIgnoreCase(mProjectNameBundle)) {
                    imageUrl = item.getOwner().getAvatarUrl();
                    break;
                }
            }
            CircleImageView circle = (CircleImageView) findViewById(R.id.avatar);
            Picasso.with(this)
                    .load(imageUrl)
                    .into(circle);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
