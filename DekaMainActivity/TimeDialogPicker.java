protected Dialog showTimepicker2(int id) {
        String[] availableSlots = new String[]{
        "10:00 - 10:30",
        "11:00 - 11:30",
        "12:00 - 12:30",
        "13:00 - 13:30",
        "10:00 - 10:30",
        "11:00 - 11:30",
        "12:00 - 12:30",
        "13:00 - 13:30"};
        ArrayAdapter<String> slots = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice);
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Choose a time")
               .setAdapter(slots, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                            // The 'which' argument contains the index position
                            // of the selected item
                        }
                    });
            return builder.create();

    }


public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    AppointmentManager appManager = new AppointmentManager();

    //TimeSlot
    List<String> availableTimeSlot = new ArrayList<String>();
    availableTimeSlot.add("10:00 - 10:30");
    availableTimeSlot.add("11:00 - 11:30");
    availableTimeSlot.add("12:00 - 12:30");
    availableTimeSlot.add("13:00 - 13:30");
    availableTimeSlot.add("10:00 - 10:30");
    availableTimeSlot.add("11:00 - 11:30");
    availableTimeSlot.add("12:00 - 12:30");
    availableTimeSlot.add("13:00 - 13:30");
    //List<String> availableTimeSlot = appManager.getTimeSlotString();

    //Use spinner with radio buttons :)
    timeSlotAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_single_choice, availableTimeSlot);
  
    // Set the dialog title
    builder.setTitle("Select Time")
    // Specify the list array, the items to be selected by default (null for none),
    // and the listener through which to receive callbacks when items are selected
           .setItems(availableTimeSlot, new DialogInterface.OnClickListener(){
              public void onClick(DialogInterface dialog, int which){
                // The 'which' argument contains the index position
                // of the selected item
              }
           })
    return builder.create();
}

ArrayList<String> availableTimeSlot = appManager.getTimeSlotString();
ArrayAdapter<string> adapter = new ArrayAdapter<string>(this, android.R.layout.radio_button, availableTimeSlot);  
dialog.setAdapter(adapter,  
    new OnClickListener() {  
    @Override  
    public void onClick(DialogInterface arg0, int arg1) {  
   // Do something  
      arg0.dismiss();  
    }          
    });  


final ListAdapter adapter = new IconAdapter(AddNote.this, R.layout.simple_list_item_single_choice);
        radio = (Button) findViewById(R.id.radio_dialog);

        radio.setOnClickListener(new OnClickListener() {

            // add button listener

            @Override
            public void onClick(View arg0) {
                ll.setBackgroundColor(Color.RED);
                final CharSequence[] items = { "Red", "Green", "Blue" };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Pick a color");
                builder.setSingleChoiceItems(items, -1,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                Toast.makeText(getApplicationContext(),
                                        items[item], Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                                ll.setBackgroundColor(Color.GRAY);
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

protected void onPrepareDialog(int id, Dialog dialog) {    
    if (id == YOUR_DIALOG_ID) {

        // Create new adapter
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>();
        adapter.add("new items ...");

       // Use the new adapter
        AlertDialog alert = (AlertDialog) dialog;
        alert.getListView().setAdapter(adapter);
    }
}

