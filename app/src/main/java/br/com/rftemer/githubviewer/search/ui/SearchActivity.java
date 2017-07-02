package br.com.rftemer.githubviewer.search.ui;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;

import br.com.rftemer.githubviewer.R;
import br.com.rftemer.githubviewer.search.controler.SearchPresenter;
import br.com.rftemer.githubviewer.search.view.SearchView;
import br.com.rftemer.githubviewer.model.GitHubItem;
import br.com.rftemer.githubviewer.viewer.ui.ViewerActivity;
import br.com.rftemer.githubviewer.utils.Constants;

public class SearchActivity extends AppCompatActivity implements SearchView {

    private SearchPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        getSupportActionBar().setElevation(0);
        mPresenter = new SearchPresenter(this);
        final Button button = (Button)findViewById(R.id.button_search);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final EditText mSearchEditText = (EditText)findViewById(R.id.search_edit_text);
                final String seach_text = mSearchEditText.getText().toString();
                if (!seach_text.isEmpty()) {
                    mPresenter.requestProjects(seach_text);
                }
            }
        });

    }

    @Override
    public void onSearchSuccess(final GitHubItem[] items) {
        final EditText mSearchEditText = (EditText)findViewById(R.id.search_edit_text);
        Intent intent = new Intent(this, ViewerActivity.class);
        intent.putExtra(Constants.GRIDITEMS_BUNDLE_ID, items);
        intent.putExtra(Constants.SEARCH_ITEM_BUNDLE_ID, mSearchEditText.getText().toString());
        startActivity(intent);
    }

    @Override
    public void showMessage(int messageResourceId) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(R.string.error_title);
        alertDialog.setMessage(getResources().getString(messageResourceId));
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
