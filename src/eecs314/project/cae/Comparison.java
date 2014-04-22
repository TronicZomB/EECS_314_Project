package eecs314.project.cae;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author Eric King
 *
 */
public class Comparison extends Fragment {

	EditText input1, input2;
	Button compute;
	TextView result;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.comparison, container, false);
		
		input1 = (EditText) rootView.findViewById(R.id.edit_cpu_time_1);
		input2 = (EditText) rootView.findViewById(R.id.edit_cpu_time_2);
		compute = (Button) rootView.findViewById(R.id.comparison_compute);
		result = (TextView) rootView.findViewById(R.id.comparison_result);

		compute.setText(getText(R.string.comparison_compute));
		compute.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String value1String = input1.getText().toString();
				String value2String = input2.getText().toString();
				double value1 = 0, value2 = 0;
				if(value1String.equals("") || value2String.equals("")) {
					Toast.makeText(getActivity(), "Please make sure all inputs are filled in", Toast.LENGTH_SHORT).show();
					return;
				}
				else {
					value1 = Double.parseDouble(value1String); 
					value2 = Double.parseDouble(value2String);
					if (value1 == 0) {
						Toast.makeText(getActivity(), "Not a Number. Divide by zero.", Toast.LENGTH_SHORT).show();
						return;
					}
				}

				double resultValue = value2 / value1;
				result.setText("CPU A is " + Double.toString(resultValue) + " times faster than CPU B");		
			}
		});
		
		return rootView;
	}
}
