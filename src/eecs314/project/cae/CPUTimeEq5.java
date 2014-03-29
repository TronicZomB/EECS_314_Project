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
 * Eq5 -> CPU Time = Instruction count * (CPI-ideal + Memory-Stalls) * Clock Cycle Time
 */
public class CPUTimeEq5 extends Fragment {

	Button button1, button2, button3, button4;
	EditText dialogEdit1, dialogEdit2, dialogEdit3, dialogEdit4;
	TextView textView1, textView2, textView3, textView4, cpuTimeResult;
	SeekBar seekBar1, seekBar2, seekBar3, seekBar4;
	Spinner spinner1;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.cpu_time_eq5, container, false);

		/*
		 * ending in '1' = Clock Cycle Time
		 * ending in '2' = Instruction count
		 * ending in '3' = CPI
		 * ending in '4' = Memory-Stall
		 */
		
		spinner1 = (Spinner) rootView.findViewById(R.id.spinner_1);
		
		cpuTimeResult = (TextView) rootView.findViewById(R.id.cpu_time_result);
		textView1 = (TextView) rootView.findViewById(R.id.text_view_1);
		textView2 = (TextView) rootView.findViewById(R.id.text_view_2);
		textView3 = (TextView) rootView.findViewById(R.id.text_view_3);
		textView4 = (TextView) rootView.findViewById(R.id.text_view_4);
		
		seekBar1 = (SeekBar) rootView.findViewById(R.id.seek_bar_1);
		seekBar2 = (SeekBar) rootView.findViewById(R.id.seek_bar_2);
		seekBar3 = (SeekBar) rootView.findViewById(R.id.seek_bar_3);
		seekBar4 = (SeekBar) rootView.findViewById(R.id.seek_bar_4);
		
		button1 = (Button) rootView.findViewById(R.id.button_1);
		button2 = (Button) rootView.findViewById(R.id.button_2);
		button3 = (Button) rootView.findViewById(R.id.button_3);
		button4 = (Button) rootView.findViewById(R.id.button_4);

		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.cpu_time_seconds, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner1.setAdapter(adapter);
	    spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				cpuTimeResult.setText(CPUTimeEq5Result());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
	    });

		textView1.setText(getText(R.string.cpu_time_cycle_time));
		
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
				button1.setText(Integer.toString(progress));
				cpuTimeResult.setText(CPUTimeEq5Result());
			}
		});

		button1.setText(Integer.toString(seekBar1.getProgress()));
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
				LayoutInflater inflater = getActivity().getLayoutInflater();
				View view = inflater.inflate(R.layout.number_picker_dialog, null);
				
				TextView dialogText = (TextView) view.findViewById(R.id.dialog_text_view);
				dialogText.setText("Enter an integer value between 0 - 1000:");
				
				dialogEdit1 = (EditText) view.findViewById(R.id.dialog_edit_text);
				dialogEdit1.setInputType(InputType.TYPE_CLASS_NUMBER);
				dialogEdit1.setText(Integer.toString(seekBar1.getProgress()));
				
				builder.setTitle("Set Clock Cycle Time");
				builder.setView(view);
				builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String value = dialogEdit1.getText().toString();
						if (value.equals("")) {
							return;
						}
						int valueAsInt = Integer.parseInt(value);
						if (valueAsInt >= 0 && valueAsInt <= 1000) {
							button1.setText(value);
							seekBar1.setProgress(valueAsInt);
							cpuTimeResult.setText(CPUTimeEq5Result());
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

		textView2.setText(getText(R.string.cpu_time_instruction_count));
		
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
				cpuTimeResult.setText(CPUTimeEq5Result());
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
				
				builder.setTitle("Set Instruction Count");
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
							cpuTimeResult.setText(CPUTimeEq5Result());
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

		textView3.setText(getText(R.string.cpu_time_cpi_ideal));
		
		seekBar3.setMax(1000);
		seekBar3.setProgress(500);
		seekBar3.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
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
				button3.setText(fvalue);
				cpuTimeResult.setText(CPUTimeEq5Result());
			}
		});

		float value = seekBar3.getProgress() / 10.0f;
		String fvalue = Float.toString(value);
		button3.setText(fvalue);
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
				LayoutInflater inflater = getActivity().getLayoutInflater();
				View view = inflater.inflate(R.layout.number_picker_dialog, null);
				
				TextView dialogText = (TextView) view.findViewById(R.id.dialog_text_view);
				dialogText.setText("Enter a decimal value between 0.0 - 100.0:");
				
				dialogEdit3 = (EditText) view.findViewById(R.id.dialog_edit_text);
				dialogEdit3.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
				dialogEdit3.setText(button3.getText().toString());
				
				builder.setTitle("Set Ideal CPI");
				builder.setView(view);
				builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String value = dialogEdit3.getText().toString();
						if (value.equals("")) {
							return;
						}
						float valueAsFloat = Float.parseFloat(value);
						int valueAsInt = (int) (valueAsFloat * 10.0f);
						float truncatedValue = valueAsInt / 10.0f;
						if (truncatedValue >= 0.0f && truncatedValue <= 100.0f) {
							button3.setText(Float.toString(truncatedValue));
							seekBar3.setProgress((int) (truncatedValue * 10.0f));
							cpuTimeResult.setText(CPUTimeEq5Result());
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
		
		textView4.setText(getText(R.string.cpu_time_memory_stall));
		
		seekBar4.setMax(1000);
		seekBar4.setProgress(500);
		seekBar4.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
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
				button4.setText(fvalue);
				cpuTimeResult.setText(CPUTimeEq5Result());
			}
		});

		float value2 = seekBar4.getProgress() / 10.0f;
		String fvalue2 = Float.toString(value2);
		button4.setText(fvalue2);
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
				LayoutInflater inflater = getActivity().getLayoutInflater();
				View view = inflater.inflate(R.layout.number_picker_dialog, null);
				
				TextView dialogText = (TextView) view.findViewById(R.id.dialog_text_view);
				dialogText.setText("Enter a decimal value between 0.0 - 100.0:");
				
				dialogEdit4 = (EditText) view.findViewById(R.id.dialog_edit_text);
				dialogEdit4.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
				dialogEdit4.setText(button4.getText().toString());
				
				builder.setTitle("Set Memory-Stall Cycles");
				builder.setView(view);
				builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String value = dialogEdit4.getText().toString();
						if (value.equals("")) {
							return;
						}
						float valueAsFloat = Float.parseFloat(value);
						int valueAsInt = (int) (valueAsFloat * 10.0f);
						float truncatedValue = valueAsInt / 10.0f;
						if (truncatedValue >= 0.0f && truncatedValue <= 100.0f) {
							button4.setText(Float.toString(truncatedValue));
							seekBar4.setProgress((int) (truncatedValue * 10.0f));
							cpuTimeResult.setText(CPUTimeEq5Result());
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
	
	public String CPUTimeEq5Result() {
		int cycleTime = seekBar1.getProgress();
		int instructionCount = seekBar2.getProgress();
		float CPI = seekBar3.getProgress() / 10.0f;
		float MemStall = seekBar4.getProgress() / 10.0f;
		
		float result, altResult = 0;
		String units = "";
		String altUnits = "";
		
		result = cycleTime * instructionCount * (CPI + MemStall);
		units = spinner1.getSelectedItem().toString();
		switch (spinner1.getSelectedItemPosition()) {
		case 0:
			altResult = result / 60.0f;
			altUnits = "min";
			break;
		case 1:
			altResult = (float) (result * Math.pow(10, -3));
			altUnits = "sec";
			break;
		case 2:
			altResult = (float) (result * Math.pow(10, -6));
			altUnits = "sec";
			break;
		case 3:
			altResult = (float) (result * Math.pow(10, -9));
			altUnits = "sec";
			break;
		case 4:
			altResult = (float) (result * Math.pow(10, -3));
			altUnits = "ns";
			break;
		}
		
		String CPUTime = getText(R.string.cpu_time_result) + " " + Float.toString(result)
				+ " " + units + " = " + Float.toString(altResult) + " " + altUnits;
		return CPUTime;
	}
}