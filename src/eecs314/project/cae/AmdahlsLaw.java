package eecs314.project.cae;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * 
 * @author Selena Pigoni
 *
 */
public class AmdahlsLaw extends Fragment {

	SeekBar changebar;
	SeekBar unchangebar;
	SeekBar factorbar;
	TextView solution;
	TextView changedtext;
	TextView unchangedtext;
	TextView factortext;
	Button cbutton, ubutton, fbutton;
	RadioButton AmdahlsEq1Button, AmdahlsEq2Button;
	EditText dialogEdit1, dialogEdit2, dialogEdit3;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.amdahls_law, container, false);
		TextView textView = (TextView) rootView.findViewById(R.id.section_label);
		textView.setText(getText(R.string.amdahls_law_desc));
		
		AmdahlsEq1Button = (RadioButton) rootView.findViewById(R.id.amdahleq1);
		AmdahlsEq1Button.setText("Speed Up = 1/(U + C/F)");
		
		AmdahlsEq2Button = (RadioButton) rootView.findViewById(R.id.amdahleq2);
		AmdahlsEq2Button.setText("Execution Time after Improvement = U + C/F");
		AmdahlsEq2Button.setChecked(true);
		
		changebar = (SeekBar) rootView.findViewById(R.id.change);
		unchangebar = (SeekBar) rootView.findViewById(R.id.unchange);
		factorbar = (SeekBar) rootView.findViewById(R.id.factor);
		solution = (TextView) rootView.findViewById(R.id.EQResult);
		changedtext = (TextView) rootView.findViewById(R.id.changedtxt);
		unchangedtext = (TextView) rootView.findViewById(R.id.unchangedtxt);
		factortext = (TextView) rootView.findViewById(R.id.factortxt);
		cbutton = (Button) rootView.findViewById(R.id.changeb);
		ubutton = (Button) rootView.findViewById(R.id.unchangeb);
		fbutton = (Button) rootView.findViewById(R.id.factorb);
		
		RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radio_group);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
					case R.id.amdahleq1:
						Eq1(changebar.getProgress(), unchangebar.getProgress(), factorbar.getProgress());
						break;
					case R.id.amdahleq2:
						Eq2(changebar.getProgress(), unchangebar.getProgress(), factorbar.getProgress());
						break;
				}
			}
			
		});
		
		changebar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				int changedValue = changebar.getProgress();
				int newUnchanged = 100 - changedValue;
				String newUnchangedString = Integer.toString(newUnchanged);
				
				unchangebar.setProgress(newUnchanged);
				ubutton.setText(newUnchangedString);
				cbutton.setText(Integer.toString(progress));
				if (AmdahlsEq1Button.isChecked()) {
					Eq1(changebar.getProgress(), unchangebar.getProgress(), factorbar.getProgress());
				}
				else {
					Eq2(changebar.getProgress(), unchangebar.getProgress(), factorbar.getProgress());
				}
			}
		});
		unchangebar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				int unchangedValue = unchangebar.getProgress();
				int newChanged = 100 - unchangedValue;
				String newChangedString = Integer.toString(newChanged);
				
				changebar.setProgress(newChanged);
				cbutton.setText(newChangedString);
				ubutton.setText(Integer.toString(progress));
				if (AmdahlsEq1Button.isChecked()) {
					Eq1(changebar.getProgress(), unchangebar.getProgress(), factorbar.getProgress());
				}
				else {
					Eq2(changebar.getProgress(), unchangebar.getProgress(), factorbar.getProgress());
				}
			}
		});
		factorbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				fbutton.setText(Integer.toString(progress));
				if (AmdahlsEq1Button.isChecked()) {
					Eq1(changebar.getProgress(), unchangebar.getProgress(), factorbar.getProgress());
				}
				else {
					Eq2(changebar.getProgress(), unchangebar.getProgress(), factorbar.getProgress());
				}
			}
		});
		
		cbutton.setText(Integer.toString(changebar.getProgress()));
		cbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
				LayoutInflater inflater = getActivity().getLayoutInflater();
				View view = inflater.inflate(R.layout.number_picker_dialog, null);
				
				TextView dialogText = (TextView) view.findViewById(R.id.dialog_text_view);
				dialogText.setText("Enter an integer value between 0 - 100:");
				
				dialogEdit1 = (EditText) view.findViewById(R.id.dialog_edit_text);
				dialogEdit1.setInputType(InputType.TYPE_CLASS_NUMBER);
				dialogEdit1.setText(Integer.toString(changebar.getProgress()));
				
				builder.setTitle("Set percentage that can be changed");
				builder.setView(view);
				builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String value = dialogEdit1.getText().toString();
						if (value.equals("")) {
							return;
						}
						int valueAsInt = Integer.parseInt(value);
						if (valueAsInt >= 0 && valueAsInt <= 100) {
							changebar.setProgress(valueAsInt);
							int changedValue = changebar.getProgress();
							int newUnchanged = 100 - changedValue;
							String newUnchangedString = Integer.toString(newUnchanged);
							
							unchangebar.setProgress(newUnchanged);
							ubutton.setText(newUnchangedString);
							cbutton.setText(value);
							if (AmdahlsEq1Button.isChecked()) {
								Eq1(changebar.getProgress(), unchangebar.getProgress(), factorbar.getProgress());
							}
							else {
								Eq2(changebar.getProgress(), unchangebar.getProgress(), factorbar.getProgress());
							}
						}
						else {
							return;
						}
					}
				});
				builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Do nothing
					}
				});
				
				builder.show();
			}
		});
		ubutton.setText(Integer.toString(unchangebar.getProgress()));
		ubutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
				LayoutInflater inflater = getActivity().getLayoutInflater();
				View view = inflater.inflate(R.layout.number_picker_dialog, null);
				
				TextView dialogText = (TextView) view.findViewById(R.id.dialog_text_view);
				dialogText.setText("Enter an integer value between 0 - 100:");
				
				dialogEdit1 = (EditText) view.findViewById(R.id.dialog_edit_text);
				dialogEdit1.setInputType(InputType.TYPE_CLASS_NUMBER);
				dialogEdit1.setText(Integer.toString(unchangebar.getProgress()));
				
				builder.setTitle("Set percentage that can't be changed");
				builder.setView(view);
				builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String value = dialogEdit1.getText().toString();
						if (value.equals("")) {
							return;
						}
						int valueAsInt = Integer.parseInt(value);
						if (valueAsInt >= 0 && valueAsInt <= 100) {
							unchangebar.setProgress(valueAsInt);
							int unchangedValue = unchangebar.getProgress();
							int newChanged = 100 - unchangedValue;
							String newChangedString = Integer.toString(newChanged);
							
							changebar.setProgress(newChanged);
							cbutton.setText(newChangedString);
							ubutton.setText(value);
							if (AmdahlsEq1Button.isChecked()) {
								Eq1(changebar.getProgress(), unchangebar.getProgress(), factorbar.getProgress());
							}
							else {
								Eq2(changebar.getProgress(), unchangebar.getProgress(), factorbar.getProgress());
							}
						}
						else {
							return;
						}
					}
				});
				builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Do nothing
					}
				});
				
				builder.show();
			}
		});
		fbutton.setText(Integer.toString(factorbar.getProgress()));
		fbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
				LayoutInflater inflater = getActivity().getLayoutInflater();
				View view = inflater.inflate(R.layout.number_picker_dialog, null);
				
				TextView dialogText = (TextView) view.findViewById(R.id.dialog_text_view);
				dialogText.setText("Enter an integer value between 0 - 100:");
				
				dialogEdit1 = (EditText) view.findViewById(R.id.dialog_edit_text);
				dialogEdit1.setInputType(InputType.TYPE_CLASS_NUMBER);
				dialogEdit1.setText(Integer.toString(factorbar.getProgress()));
				
				builder.setTitle("Set the factor by which we can decrease by");
				builder.setView(view);
				builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String value = dialogEdit1.getText().toString();
						if (value.equals("")) {
							return;
						}
						int valueAsInt = Integer.parseInt(value);
						if (valueAsInt >= 0 && valueAsInt <= 100) {
							fbutton.setText(value);
							factorbar.setProgress(valueAsInt);
							if (AmdahlsEq1Button.isChecked()) {
								Eq1(changebar.getProgress(), unchangebar.getProgress(), factorbar.getProgress());
							}
							else {
								Eq2(changebar.getProgress(), unchangebar.getProgress(), factorbar.getProgress());
							}
						}
						else {
							return;
						}
					}
				});
				builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Do nothing
					}
				});
				
				builder.show();
			}
		});
		return rootView;
	}
	
	public void Eq1(float change, float unchange, float factor){
		//cbutton.setText(Float.toString(change));
		//ubutton.setText(Float.toString(unchange));
		//fbutton.setText(Float.toString(factor));
		float speedUp = (1/(unchange + (change/factor))) * 100;
		solution.setText("Speed up = " + Float.toString(speedUp) + "%");
	}
	
	public void Eq2(float change, float unchange, float factor){
		//cbutton.setText(Float.toString(change));
		//ubutton.setText(Float.toString(unchange));
		//fbutton.setText(Float.toString(factor));
		float improved = unchange + (change/factor);
		solution.setText("Execution Time after improvement = " + Float.toString(improved) + " time quantum");
	}
}
		
