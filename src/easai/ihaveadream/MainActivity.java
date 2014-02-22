package easai.ihaveadream;

import java.io.IOException;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

	MediaPlayer mlk;
	Button playButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		playButton = (Button) findViewById(R.id.play_button);
		mlk = MediaPlayer.create(this, R.raw.ihaveadream);
		ImageButton shareButton = (ImageButton)findViewById(R.id.share_button);
		OnClickListener listener=new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT, "Check up this app: MLK https://github.com/easai/MLK");
				startActivity(intent);
			}
		};
		shareButton.setOnClickListener(listener);
	}

	@Override
	protected void onResume() {
		mlk.start();
		super.onResume();
	}

	@Override
	protected void onPause() {
		mlk.pause();
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void mediaStart(View v) {
		if (mlk.isPlaying()) { 
			playButton.setText("Play");
			mlk.stop();
			try {
				mlk.prepare();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else { 
			playButton.setText("Pause");
			mlk.start();
		}
	}

	public void openWiki(View v) {
		String url = "http://en.wikipedia.org/wiki/I_Have_a_Dream";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
}
