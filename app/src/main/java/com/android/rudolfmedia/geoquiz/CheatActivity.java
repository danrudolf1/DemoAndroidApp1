package com.android.rudolfmedia.geoquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CheatActivity extends Activity {

	private static final String EXTRA_ANSWER_IS_TRUE = "com.android.rudolfmedia.geoquiz2.answer_is_true";
	private static final String EXTRA_ANSWER_SHOWN = "com.android.rudolfmedia.geoquiz2.answer_shown";
	private Button mShowAnswer;
	private TextView mAnswerTextView;
	private boolean mAnswerIsTrue;

	public static Intent newIntent(Context packageContext, boolean answerIsTrue){
		Intent i = new Intent(packageContext, CheatActivity.class);
		i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
		return i;
	}

	private void setAnswerShownResult(boolean isAnswerShown){

		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);

	}

	public static boolean wasAnswerShown(Intent result){
		return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);

		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

		mAnswerTextView = (TextView)findViewById(R.id.answerText);

		mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
		mShowAnswer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mAnswerIsTrue){
					mAnswerTextView.setText(R.string.true_button);
				}
				else{
					mAnswerTextView.setText(R.string.false_button);
				}
				setAnswerShownResult(true);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_cheat, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
