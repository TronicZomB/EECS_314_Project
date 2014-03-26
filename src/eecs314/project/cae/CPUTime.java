package eecs314.project.cae;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class CPUTime extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.cpu_time, container, false);
		TextView CPUTimeDefinition = (TextView) rootView.findViewById(R.id.cpu_time_definition);
		CPUTimeDefinition.setText(getText(R.string.cpu_time_definition));
		
		RadioButton CPUTimeEq1Button = (RadioButton) rootView.findViewById(R.id.cpu_time_eq1);
		CPUTimeEq1Button.setText(getText(R.string.cpu_time_eq1));
		
		RadioButton CPUTimeEq2Button = (RadioButton) rootView.findViewById(R.id.cpu_time_eq2);
		CPUTimeEq2Button.setText(getText(R.string.cpu_time_eq2));
		
		RadioButton CPUTimeEq3Button = (RadioButton) rootView.findViewById(R.id.cpu_time_eq3);
		CPUTimeEq3Button.setText(getText(R.string.cpu_time_eq3));
		
		RadioButton CPUTimeEq4Button = (RadioButton) rootView.findViewById(R.id.cpu_time_eq4);
		CPUTimeEq4Button.setText(getText(R.string.cpu_time_eq4));
	
		RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radio_group);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.cpu_time_eq1:
					CPUTimeEq1 eq1Fragment = new CPUTimeEq1();
					FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
					transaction1.replace(R.id.eq_fragments, eq1Fragment);
					transaction1.commit();
					break;
				case R.id.cpu_time_eq2:
					CPUTimeEq2 eq2Fragment = new CPUTimeEq2();
					FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
					transaction2.replace(R.id.eq_fragments, eq2Fragment);
					transaction2.commit();
					break;
				case R.id.cpu_time_eq3:
					CPUTimeEq3 eq3Fragment = new CPUTimeEq3();
					FragmentTransaction transaction3 = getFragmentManager().beginTransaction();
					transaction3.replace(R.id.eq_fragments, eq3Fragment);
					transaction3.commit();
					break;
				case R.id.cpu_time_eq4:
					CPUTimeEq4 eq4Fragment = new CPUTimeEq4();
					FragmentTransaction transaction4 = getFragmentManager().beginTransaction();
					transaction4.replace(R.id.eq_fragments, eq4Fragment);
					transaction4.commit();
					break;
				}
			}
		});
		
		return rootView;
	}
}