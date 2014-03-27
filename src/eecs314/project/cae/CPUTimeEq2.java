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
 * Eq2 -> CPU Time = CPU Clock Cycles / Clock Rate
 */
public class CPUTimeEq2 extends Fragment {

	EditText editText1, editText2;
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
		
		editText1 = (EditText) rootView.findViewById(R.id.edit_text_1);
		editText2 = (EditText) rootView.findViewById(R.id.edit_text_2);

		
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
				editText1.setText(fvalue);
				cpuTimeResult.setText(CPUTimeEq2Result());
			}
		});

		editText1.setFilters(new InputFilter[]{ new InputFilterMinMaxFloat(0.0f, 100.0f)});
		float value = seekBar1.getProgress() / 10.0f;
		String fvalue = Float.toString(value);
		editText1.setText(fvalue);
		TextWatcher textWatcher1 = new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String string = s.toString();
				if (!string.equals("")) {
					int value = (int) (Float.parseFloat(string) * 10.0);
					seekBar1.setProgress(value);
					editText1.setSelection(editText1.getText().length());
					cpuTimeResult.setText(CPUTimeEq2Result());
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
				cpuTimeResult.setText(CPUTimeEq2Result());
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
					cpuTimeResult.setText(CPUTimeEq2Result());
				}
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		};
		editText2.addTextChangedListener(textWatcher2);
		
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
