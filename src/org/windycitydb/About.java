package org.windycitydb;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class About extends Activity {

	private static final String CLASSTAG =  About.class.getSimpleName();

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG, About.CLASSTAG + " onCreate");
        
        setContentView(R.layout.about);
        
        TextView devTextView = (TextView) findViewById(R.id.about_dev);
        devTextView.setText(getResources().getString(R.string.about_app_dev_text));
        
        TextView wcgTeamTextView = (TextView) findViewById(R.id.about_wcgteam);
        wcgTeamTextView.setText(getDevTeamText());
        
        ImageView cr = (ImageView) findViewById(R.id.cr_image);
        cr.setImageDrawable(getResources().getDrawable(R.drawable.image_chicagoruby));
        
        Button crButton = (Button) findViewById(R.id.about_cr_button);
        crButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent crWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("http://chicagoruby.org/"));
				startActivity(crWeb);
			}
		});
        
        ImageView wg = (ImageView) findViewById(R.id.wg_image);
        wg.setImageDrawable(getResources().getDrawable(R.drawable.image_wisdomgroup));
        wg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent wgWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.wisdomgroup.com/"));
				startActivity(wgWeb);
			}
		});
	}

	private String getDevTeamText() {
		String[] devTeamArray = getResources().getStringArray(R.array.dev_team);
        StringBuilder sb = new StringBuilder();
        for(String teamMember : devTeamArray) {
        	sb.append(teamMember+"\n");
        }
        return sb.toString().trim();
	}
}
