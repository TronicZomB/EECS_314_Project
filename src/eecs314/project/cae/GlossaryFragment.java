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
	
	int padding = 10;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		builder.setTitle("Glossary");
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.glossary_fragment, null);
		builder.setView(view);
		
		final TableLayout glossaryParent = (TableLayout) view.findViewById(R.id.glossary_parent);
		
		TableRow CPUClockCyclesRow = new TableRow(getActivity());
		CPUClockCyclesRow.setPadding(0, 0, 0, padding);
		TextView CPUClockCycles = new TextView(getActivity());
		CPUClockCycles.setText(getText(R.string.glossary_cpu_clock_cycles));
		CPUClockCyclesRow.addView(CPUClockCycles);
		
		TableRow ClockCycleTimeRow = new TableRow(getActivity());
		ClockCycleTimeRow.setPadding(0, 0, 0, padding);
		TextView ClockCycleTime = new TextView(getActivity());
		ClockCycleTime.setText(getText(R.string.glossary_clock_cycle_time));
		ClockCycleTimeRow.addView(ClockCycleTime);
		
		TableRow ClockRateRow = new TableRow(getActivity());
		ClockRateRow.setPadding(0, 0, 0, padding);
		TextView ClockRate = new TextView(getActivity());
		ClockRate.setText(getText(R.string.glossary_clock_rate));
		ClockRateRow.addView(ClockRate);
		
		TableRow InstructionCountRow = new TableRow(getActivity());
		InstructionCountRow.setPadding(0, 0, 0, padding);
		TextView InstructionCount = new TextView(getActivity());
		InstructionCount.setText(getText(R.string.glossary_instruction_count));
		InstructionCountRow.addView(InstructionCount);
		
		TableRow CPIRow = new TableRow(getActivity());
		CPIRow.setPadding(0, 0, 0, padding);
		TextView CPI = new TextView(getActivity());
		CPI.setText(getText(R.string.glossary_cpi));
		CPIRow.addView(CPI);
		
		TableRow CPIIdealRow = new TableRow(getActivity());
		CPIIdealRow.setPadding(0, 0, 0, padding);
		TextView CPIIdeal = new TextView(getActivity());
		CPIIdeal.setText(getText(R.string.glossary_cpi_ideal));
		CPIIdealRow.addView(CPIIdeal);
		
		TableRow MemoryStallsRow = new TableRow(getActivity());
		MemoryStallsRow.setPadding(0, 0, 0, padding);
		TextView MemoryStalls = new TextView(getActivity());
		MemoryStalls.setText(getText(R.string.glossary_memory_stalls));
		MemoryStallsRow.addView(MemoryStalls);
		
		TableRow ReadStallsRow = new TableRow(getActivity());
		ReadStallsRow.setPadding(0, 0, 0, padding);
		TextView ReadStalls = new TextView(getActivity());
		ReadStalls.setText(getText(R.string.glossary_read_stalls));
		ReadStallsRow.addView(ReadStalls);
		
		TableRow WriteStallsRow = new TableRow(getActivity());
		WriteStallsRow.setPadding(0, 0, 0, padding);
		TextView WriteStalls = new TextView(getActivity());
		WriteStalls.setText(getText(R.string.glossary_write_stalls));
		WriteStallsRow.addView(WriteStalls);
		
		TableRow SingleCycleRow = new TableRow(getActivity());
		SingleCycleRow.setPadding(0, 0, 0, padding);
		TextView Singlecycle = new TextView(getActivity());
		Singlecycle.setText(getText(R.string.glossary_single_cycle));
		SingleCycleRow.addView(Singlecycle);
		
		TableRow MultiCycleRow = new TableRow(getActivity());
		MultiCycleRow.setPadding(0, 0, 0, padding);
		TextView Multicycle = new TextView(getActivity());
		Multicycle.setText(getText(R.string.glossary_multi_cycle));
		MultiCycleRow.addView(Multicycle);
		
		
		glossaryParent.addView(ClockCycleTimeRow);
		glossaryParent.addView(ClockRateRow);
		glossaryParent.addView(CPIRow);
		glossaryParent.addView(CPIIdealRow);
		glossaryParent.addView(CPUClockCyclesRow);
		glossaryParent.addView(InstructionCountRow);
		glossaryParent.addView(MemoryStallsRow);
		glossaryParent.addView(MultiCycleRow);
		glossaryParent.addView(ReadStallsRow);
		glossaryParent.addView(SingleCycleRow);
		glossaryParent.addView(WriteStallsRow);
		
		builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dismiss();
			}
		});
		
		return builder.create();
	}
}
