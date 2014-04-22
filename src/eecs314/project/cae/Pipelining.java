package eecs314.project.cae;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;


/**
 * 
 * @author James Marsh
 *
 */
public class Pipelining extends Fragment {
	
	TextView singleTV, stallTV, result;
	EditText stageCountText, stageLengthText, instructionCountText, cycleLengthText, stallCyclesText;
	CheckBox different, stalls;
	Button calcButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.pipelining, container, false);
		
		TextView textView = (TextView) rootView.findViewById(R.id.section_label);
		textView.setText(getText(R.string.pipeline_definition));
		
		singleTV = (TextView) rootView.findViewById(R.id.textView4);
		stallTV = (TextView) rootView.findViewById(R.id.textView5);
		result = (TextView) rootView.findViewById(R.id.result);
		
		different = (CheckBox) rootView.findViewById(R.id.diff_single_pipe);
		stalls = (CheckBox) rootView.findViewById(R.id.stalls);
		
		different.setChecked(true);
		stalls.setChecked(true);
		
		different.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (different.isChecked()) {
					singleTV.setVisibility(View.VISIBLE);
					cycleLengthText.setVisibility(View.VISIBLE);
				}
				else {
					singleTV.setVisibility(View.INVISIBLE);
					cycleLengthText.setVisibility(View.INVISIBLE);
				}
			}
		});
		stalls.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (stalls.isChecked()) {
					stallTV.setVisibility(View.VISIBLE);
					stallCyclesText.setVisibility(View.VISIBLE);
				}
				else {
					stallTV.setVisibility(View.INVISIBLE);
					stallCyclesText.setVisibility(View.INVISIBLE);
				}
			}
		});
		
		stageCountText = (EditText) rootView.findViewById(R.id.stagecount);
		stageLengthText = (EditText) rootView.findViewById(R.id.stagelength);
		instructionCountText = (EditText) rootView.findViewById(R.id.instructcount);
		cycleLengthText = (EditText) rootView.findViewById(R.id.singlecycle);
		stallCyclesText = (EditText) rootView.findViewById(R.id.stallcycle);
		
		calcButton = (Button) rootView.findViewById(R.id.calculate);
		calcButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				//do the calculation and send to output
				String stageLengthString = stageLengthText.getText().toString();
				String stageCountString = stageCountText.getText().toString();
				String instructionCountString = instructionCountText.getText().toString();
				String singleCycleLengthString = cycleLengthText.getText().toString();
				String stallCycleString = stallCyclesText.getText().toString();
				
				if (different.isChecked() && stalls.isChecked()) {
					if (stageLengthString.equals("") || stageCountString.equals("") || instructionCountString.equals("") || singleCycleLengthString.equals("") || stallCycleString.equals("")) {
						Toast.makeText(getActivity(), "Please make sure all inputs are filled in", Toast.LENGTH_SHORT).show();
					}
					else {
						float stageLength = Float.parseFloat(stageLengthString);
						int stageCount = Integer.parseInt(stageCountString);
						int instructionCount = Integer.parseInt(instructionCountString);
						float singleCycleLength = Float.parseFloat(singleCycleLengthString);
						float stallCycle = Float.parseFloat(stallCycleString);
						
						float singleCycleTime = PipelineCalc.singleCycle(singleCycleLength, instructionCount);
						float pipelineTime = PipelineCalc.basicPipe(stageLength, stageCount, instructionCount) + PipelineCalc.totalWaste(stageLength, stallCycle);
						float comparison = singleCycleTime / pipelineTime;
						
						result.setText("A single cycle implementation would take " + Float.toString(singleCycleTime) + " time quantum to complete.\n"
								+ "A pipelined implementation would take " + Float.toString(pipelineTime) + " time quantum to complete.\n"
								+ "The pipelined implementation offers a " + Float.toString(comparison) + " times speed up over a single cycle implementation.");
					}
				}
				else if (different.isChecked()) {
					if (stageLengthString.equals("") || stageCountString.equals("") || instructionCountString.equals("") || singleCycleLengthString.equals("")) {
						Toast.makeText(getActivity(), "Please make sure all inputs are filled in", Toast.LENGTH_SHORT).show();
					}
					else {
						float stageLength = Float.parseFloat(stageLengthString);
						int stageCount = Integer.parseInt(stageCountString);
						int instructionCount = Integer.parseInt(instructionCountString);
						float singleCycleLength = Float.parseFloat(singleCycleLengthString);
						
						float singleCycleTime = PipelineCalc.singleCycle(singleCycleLength, instructionCount);
						float pipelineTime = PipelineCalc.basicPipe(stageLength, stageCount, instructionCount);
						float comparison = singleCycleTime / pipelineTime;
						
						result.setText("A single cycle implementation would take " + Float.toString(singleCycleTime) + " time quantum to complete.\n"
								+ "A pipelined implementation would take " + Float.toString(pipelineTime) + " time quantum to complete.\n"
								+ "The pipelined implementation offers a " + Float.toString(comparison) + " times speed up over a single cycle implementation.");
					}
				}
				else if (stalls.isChecked()) {
					if (stageLengthString.equals("") || stageCountString.equals("") || instructionCountString.equals("") || stallCycleString.equals("")) {
						Toast.makeText(getActivity(), "Please make sure all inputs are filled in", Toast.LENGTH_SHORT).show();
					}
					else {
						float stageLength = Float.parseFloat(stageLengthString);
						int stageCount = Integer.parseInt(stageCountString);
						int instructionCount = Integer.parseInt(instructionCountString);
						float stallCycle = Float.parseFloat(stallCycleString);
						
						float singleCycleTime = PipelineCalc.singleCycle((stageLength * stageCount), instructionCount);
						float pipelineTime = PipelineCalc.basicPipe(stageLength, stageCount, instructionCount) + PipelineCalc.totalWaste(stageLength, stallCycle);
						float comparison = singleCycleTime / pipelineTime;
						
						result.setText("A single cycle implementation would take " + Float.toString(singleCycleTime) + " time quantum to complete.\n"
								+ "A pipelined implementation would take " + Float.toString(pipelineTime) + " time quantum to complete.\n"
								+ "The pipelined implementation offers a " + Float.toString(comparison) + " times speed up over a single cycle implementation.");
					}
				}
				else {
					if (stageLengthString.equals("") || stageCountString.equals("") || instructionCountString.equals("")) {
						Toast.makeText(getActivity(), "Please make sure all inputs are filled in", Toast.LENGTH_SHORT).show();
					}
					else {
						float stageLength = Float.parseFloat(stageLengthString);
						int stageCount = Integer.parseInt(stageCountString);
						int instructionCount = Integer.parseInt(instructionCountString);
						
						float pipelineTime = PipelineCalc.basicPipe(stageLength, stageCount, instructionCount);
						float singleCycleLength = stageLength * stageCount;
						float singleCycleTime = PipelineCalc.singleCycle(singleCycleLength, instructionCount);
						
						result.setText("A single cycle implementation would take " + Float.toString(singleCycleTime) + " time quantum to complete.\n"
								+ "A pipelined implementation would take " + Float.toString(pipelineTime) + " time quantum to complete.\n"
								+ "The pipelined implementation offers a " + Float.toString(singleCycleTime/pipelineTime) + " times speed up over a single cycle implementation.");
					}
				}
			}
		});
		
		return rootView;
	}
}
