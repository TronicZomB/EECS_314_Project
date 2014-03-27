package eecs314.project.cae;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
/*
 * Eq1 -> CPU Time = CPU Clock Cycles * Clock Cycle Time
 */
public class CPUTimeEq1 extends Fragment {
	
	EditText editText1, editText2;
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
		
		editText1 = (EditText) rootView.findViewById(R.id.edit_text_1);
		editText2 = (EditText) rootView.findViewById(R.id.edit_text_2);

		
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
				editText1.setText(Integer.toString(progress));
				cpuTimeResult.setText(CPUTimeEq1Result());
			}
		});

		editText1.setFilters(new InputFilter[]{ new InputFilterMinMax(0, 1000)});
		editText1.setText(Integer.toString(seekBar1.getProgress()));
		TextWatcher textWatcher1 = new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String string = s.toString();
				if (!string.equals("")) {
					int value = Integer.parseInt(string);
					seekBar1.setProgress(value);
					editText1.setSelection(editText1.getText().length());
					cpuTimeResult.setText(CPUTimeEq1Result());
				}
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		};
		editText1.addTextChangedListener(textWatcher1);

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
				editText2.setText(Integer.toString(progress));
				cpuTimeResult.setText(CPUTimeEq1Result());
			}
		});
		
		editText2.setFilters(new InputFilter[]{ new InputFilterMinMax(0, 9999)});
		editText2.setText(Integer.toString(seekBar2.getProgress()));
		TextWatcher textWatcher2 = new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String string = s.toString();
				if (!string.equals("")) {
					int value = Integer.parseInt(string);
					seekBar2.setProgress(value);
					editText2.setSelection(editText2.getText().length());
					cpuTimeResult.setText(CPUTimeEq1Result());
				}
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		};
		editText2.addTextChangedListener(textWatcher2);
		
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
