package eecs314.project.cae;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GlossaryFragment extends DialogFragment {
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		builder.setTitle("Glossary");
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.glossary_fragment, null);
		builder.setView(view);
		
		final TableLayout glossaryParent = (TableLayout) view.findViewById(R.id.glossary_parent);
		
		TableRow CPUClockCyclesRow = new TableRow(getActivity());
		TextView CPUClockCycles = new TextView(getActivity());
		CPUClockCycles.setText(getText(R.string.glossary_cpu_clock_cycles));
		CPUClockCyclesRow.addView(CPUClockCycles);
		
		TableRow ClockCycleTimeRow = new TableRow(getActivity());
		TextView ClockCycleTime = new TextView(getActivity());
		ClockCycleTime.setText(getText(R.string.glossary_clock_cycle_time));
		ClockCycleTimeRow.addView(ClockCycleTime);
		
		TableRow ClockRateRow = new TableRow(getActivity());
		TextView ClockRate = new TextView(getActivity());
		ClockRate.setText(getText(R.string.glossary_clock_rate));
		ClockRateRow.addView(ClockRate);
		
		TableRow InstructionCountRow = new TableRow(getActivity());
		TextView InstructionCount = new TextView(getActivity());
		InstructionCount.setText(getText(R.string.glossary_instruction_count));
		InstructionCountRow.addView(InstructionCount);
		
		TableRow CPIRow = new TableRow(getActivity());
		TextView CPI = new TextView(getActivity());
		CPI.setText(getText(R.string.glossary_cpi));
		CPIRow.addView(CPI);
		
		glossaryParent.addView(ClockCycleTimeRow);
		glossaryParent.addView(ClockRateRow);
		glossaryParent.addView(CPIRow);
		glossaryParent.addView(CPUClockCyclesRow);
		glossaryParent.addView(InstructionCountRow);
		
		builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dismiss();
			}
		});
		
		return builder.create();
	}
}
