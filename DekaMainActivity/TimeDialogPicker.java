@Override
public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder.setTitle(R.string.pick_color)
           .setItems(R.array.colors_array, new DialogInterface.OnClickListener() {
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
    List<String> availableTimeSlot = appManager.getTimeSlotString();

    //Use spinner with radio buttons :)
	timeSlotAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_single_choice, availableTimeSlot);
	
    // Set the dialog title
    builder.setTitle("Select Time")
    // Specify the list array, the items to be selected by default (null for none),
    // and the listener through which to receive callbacks when items are selected
           .setItems(availableTimeSlot, new DialogInterface.OnClickListener(){
           		public void onClick(DialogInterface dialog, int which){

           		}
           })
    return builder.create();
}