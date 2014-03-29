package eecs314.project.cae;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

/*
 * Eq2 -> CPU Time = CPU Clock Cycles / Clock Rate
 */
public class CPUTimeEq2 extends Fragment {

	Button button1, button2;
	EditText dialogEdit1, dialogEdit2;
	TextView textView1, textView2, cpuTimeResult;
	SeekBar seekBar1, seekBar2;
	Spinner spinner1;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.cpu_time_eq2, container, false);

		/*
		 * ending in '1' = Clock Rate
		 * ending in '2' = CPU Clock Cycles
		 */
		
		spinner1 = (Spinner) rootView.findViewById(R.id.spinner_1);
		
		cpuTimeResult = (TextView) rootView.findViewById(R.id.cpu_time_result);
		textView1 = (TextView) rootView.findViewById(R.id.text_view_1);
		textView2 = (TextView) rootView.findViewById(R.id.text_view_2);
		
		seekBar1 = (SeekBar) rootView.findViewById(R.id.seek_bar_1);
		seekBar2 = (SeekBar) rootView.findViewById(R.id.seek_bar_2);
		
		button1 = (Button) rootView.findViewById(R.id.button_1);
		button2 = (Button) rootView.findViewById(R.id.button_2);

		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.cpu_time_hertz, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner1.setAdapter(adapter);
	    spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				cpuTimeResult.setText(CPUTimeEq2Result());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
	    });
		
		textView1.setText(getText(R.string.cpu_time_clock_rate));
		
		seekBar1.setMax(1000);
		seekBar1.setProgress(500);
		seekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				float value = progress / 10.0f;
				String fvalue = Float.toString(value);
				button1.setText(fvalue);
				cpuTimeResult.setText(CPUTimeEq2Result());
			}
		});

		float value = seekBar1.getProgress() / 10.0f;
		String fvalue = Float.toString(value);
		button1.setText(fvalue);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
				LayoutInflater inflater = getActivity().getLayoutInflater();
				View view = inflater.inflate(R.layout.number_picker_dialog, null);
				
				TextView dialogText = (TextView) view.findViewById(R.id.dialog_text_view);
				dialogText.setText("Enter a decimal value between 0.1 - 100.0:");
				
				dialogEdit1 = (EditText) view.findViewById(R.id.dialog_edit_text);
				dialogEdit1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
				dialogEdit1.setText(button1.getText().toString());
				
				builder.setTitle("Set Clock Rate");
				builder.setView(view);
				builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String value = dialogEdit1.getText().toString();
						if (value.equals("")) {
							return;
						}
						float valueAsFloat = Float.parseFloat(value);
						int valueAsInt = (int) (valueAsFloat * 10.0f);
						float truncatedValue = valueAsInt / 10.0f;
						if (truncatedValue >= 0.1f && truncatedValue <= 100.0f) {
							button1.setText(Float.toString(truncatedValue));
							seekBar1.setProgress((int) (truncatedValue * 10.0f));
							cpuTimeResult.setText(CPUTimeEq2Result());
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

		textView2.setText(getText(R.string.cpu_time_clock_cycles));
		
		seekBar2.setMax(9999);
		seekBar2.setProgress(5000);
		seekBar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				button2.setText(Integer.toString(progress));
				cpuTimeResult.setText(CPUTimeEq2Result());
			}
		});
		
		button2.setText(Integer.toString(seekBar2.getProgress()));
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
				LayoutInflater inflater = getActivity().getLayoutInflater();
				View view = inflater.inflate(R.layout.number_picker_dialog, null);
				
				TextView dialogText = (TextView) view.findViewById(R.id.dialog_text_view);
				dialogText.setText("Enter an integer value between 0 - 9999:");
				
				dialogEdit2 = (EditText) view.findViewById(R.id.dialog_edit_text);
				dialogEdit2.setInputType(InputType.TYPE_CLASS_NUMBER);
				dialogEdit2.setText(Integer.toString(seekBar2.getProgress()));
				
				builder.setTitle("Set CPU Clock Cycles");
				builder.setView(view);
				builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String value = dialogEdit2.getText().toString();
						if (value.equals("")) {
							return;
						}
						int valueAsInt = Integer.parseInt(value);
						if (valueAsInt >= 0 && valueAsInt <= 9999) {
							button2.setText(value);
							seekBar2.setProgress(valueAsInt);
							cpuTimeResult.setText(CPUTimeEq2Result());
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
	
	public String CPUTimeEq2Result() {
		int clockCycles = seekBar2.getProgress();
		float clockRate = seekBar1.getProgress() / 10.0f;
		
		float result, altResult = 0;
		String units = "";
		String altUnits = "";
		
		if (clockRate == 0) {
			String CPUTime = getText(R.string.cpu_time_result) + " NaN"; 
			return CPUTime;
		}
		
		result = clockCycles / clockRate;
		switch (spinner1.getSelectedItemPosition()) {
		case 0:
			units = "s";
			altResult = result / 60.0f;
			altUnits = "min";
			break;
		case 1:
			units = "ms";
			altResult = (float) (clockCycles / (clockRate * Math.pow(10, 3)));
			altUnits = "s";
			break;
		case 2:
			units = "us";
			altResult = (float) (clockCycles / (clockRate * Math.pow(10, 6)));
			altUnits = "s";
			break;
		case 3:
			units = "ns";
			altResult = (float) (clockCycles / (clockRate * Math.pow(10, 9)));
			altUnits = "s";
			break;
		}
		
		String CPUTime = getText(R.string.cpu_time_result) + " " + Float.toString(result)
				+ " " + units + " = " + Float.toString(altResult) + " " + altUnits;
		return CPUTime;
	}
}
