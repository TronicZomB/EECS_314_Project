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
 * Eq1 -> CPU Time = CPU Clock Cycles * Clock Cycle Time
 */
/**
 * 
 * @author Steve Paley
 *
 */
public class CPUTimeEq1 extends Fragment {
	
	Button button1, button2;
	EditText dialogEdit1, dialogEdit2;
	TextView textView1, textView2, cpuTimeResult;
	SeekBar seekBar1, seekBar2;
	Spinner spinner1;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.cpu_time_eq1, container, false);
		
		/*
		 * ending in '1' = Clock Cycle Time
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

		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.cpu_time_seconds, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner1.setAdapter(adapter);
	    spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				cpuTimeResult.setText(CPUTimeEq1Result());
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
				cpuTimeResult.setText(CPUTimeEq1Result());
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
							cpuTimeResult.setText(CPUTimeEq1Result());
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
				cpuTimeResult.setText(CPUTimeEq1Result());
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
							cpuTimeResult.setText(CPUTimeEq1Result());
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
	
	public String CPUTimeEq1Result() {
		int cycleTime = seekBar1.getProgress();
		int clockCycles = seekBar2.getProgress();
		
		int result = cycleTime * clockCycles;
		
		double altResult = 0;
		String altUnit = "";
		switch (spinner1.getSelectedItemPosition()) {
		case 0:
			altResult = result / 60;
			altUnit = " min";
			break;
		case 1:
			altResult = result * Math.pow(10, -3);
			altUnit = " sec";
			break;
		case 2:
			altResult = result * Math.pow(10, -6);
			altUnit = " sec";
			break;
		case 3:
			altResult = result * Math.pow(10, -9);
			altUnit = " sec";
			break;
		case 4:
			altResult = result * Math.pow(10, -3);
			altUnit = " ns";
			break;
		}
		
		String CPUTime = getText(R.string.cpu_time_result) + " " + Integer.toString(result) 
				+ " " + spinner1.getSelectedItem().toString() 
				+ " = " + Double.toString(altResult) + altUnit;
		
		return CPUTime;
	}
}
