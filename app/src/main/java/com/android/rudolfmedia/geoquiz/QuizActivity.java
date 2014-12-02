package com.android.rudolfmedia.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends Activity {

	///same as declaring variables in C these are accessed by this whole class and set in onCreate
	private Button mTrueButton;
	private Button mFalseButton;
	private Button mNextButton;
	private Button mPreviousButton;
	private Button mCheatButton;
	private TextView mQuestionTextView;
	private static final String TAG = "QuizActivity";
	private static final String KEY_INDEX = "index";
	private static final int REQUEST_CODE_CHEAT = 0;
	private boolean mIsCheater;

	private Question[] mQuestionBank = new Question[]{

			new Question(R.string.question_oceans, true),
			new Question(R.string.question_mideast, false),
			new Question(R.string.question_africa, false),
			new Question(R.string.question_americas, true),
			new Question(R.string.question_asia, true),
	};

	private int mCurrentIndex = 0;

	private void updateQuestion(){
		int question = mQuestionBank[mCurrentIndex].getTextResID();
		mQuestionTextView.setText(question);

	}

	private void checkAnswer(boolean userPressedTrue){

		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

		int messageResId = 0;

		if (mIsCheater){
			messageResId = R.string.judgement_toast;
		}
		else {

			if (userPressedTrue == answerIsTrue) {

				messageResId = R.string.correct_toast;

			} else {

				messageResId = R.string.incorrect_toast;

			}
		}

		Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (savedInstanceState != null){
			mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
		}
		super.onCreate(savedInstanceState);

		Log.d(TAG, "onCreate(Bundle) called!");
		setContentView(R.layout.activity_quiz);

		mQuestionTextView = (TextView)findViewById(R.id.question_textView);
		updateQuestion();


		mTrueButton = (Button) findViewById(R.id.true_button);
		mTrueButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				checkAnswer(true);

			}
		});


		mFalseButton = (Button) findViewById(R.id.false_button);
		mFalseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				checkAnswer(false);
			}
		});

		mNextButton = (Button)findViewById(R.id.next_Button);
		mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mCurrentIndex = (mCurrentIndex +1) % mQuestionBank.length;
				updateQuestion();
			}

		});

		mCheatButton = (Button)findViewById(R.id.cheat_button);
		mCheatButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
				Intent i = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
				startActivityForResult(i, REQUEST_CODE_CHEAT);

			}
		});
	}
	@Override
	protected void onActivityResult (int requestCode, int resultCode, Intent data){
		if (resultCode != Activity.RESULT_OK){
			return;
		}

		if (requestCode == REQUEST_CODE_CHEAT){
			if (data ==null){
				return;
			}
			mIsCheater = CheatActivity.wasAnswerShown(data);

		}
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState){
		super.onSaveInstanceState(savedInstanceState);
		Log.i(TAG, "onSavedInstanceState");
		savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
	}

	@Override
	public void onStart(){
		super.onStart();
		Log.d(TAG, "onStart() called!");
	}

	@Override
	public void onPause(){
		super.onPause();
		Log.d(TAG, "onPause() called!");
	}

	@Override
	public void onResume(){
		super.onResume();
		Log.d(TAG, "onResume() called!");
	}

	@Override
	public void onStop(){
		super.onStop();
		Log.d(TAG, "onStop() called!");
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.d(TAG, "onDestroy Called!");
	}


}
