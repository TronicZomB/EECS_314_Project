package eecs314.project.cae;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AmdahlsLaw extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.amdahls_law, container, false);
		TextView textView = (TextView) rootView.findViewById(R.id.section_label);
		textView.setText("amdahls law");
		return rootView;
	}
}
