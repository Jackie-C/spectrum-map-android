package com.delta.layouts;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Jackie on 21/10/2016.
 */

public class BatchIdDialogFragment extends DialogFragment {

    public BatchIdDialogFragment() {}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.dialog_layout, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setMessage(R.string.enterBatchID);
        final EditText uName = (EditText) view.findViewById(R.id.batchIdField);

        // Add action buttons
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                String s1 = uName.getText().toString();
                HttpDeleteRequest deleteRequestLocation = new HttpDeleteRequest(getActivity());
                deleteRequestLocation.setPath("/deleterecord/location");
                deleteRequestLocation.setQueryString("pname=BATCH_ID&pvalue=" + s1);
                try {
                    deleteRequestLocation.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                BatchIdDialogFragment.this.getDialog().cancel();
            }
        });
        return builder.create();
    }
}