package com.example.zturf;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;

import com.example.zturf.animations.Animation;
import com.example.zturf.json.Json;
import com.example.zturf.user.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class DashBoard extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private  static final String prefName = "myapp";
    private  static final String keyEmail = "email";
    private  static final String keyPass = "password";

    TextView text1 , batteryText;
    ImageView image;
    ListView listView;
    Switch switcher;
    boolean nightMode;
    SharedPreferences.Editor editor;
    BottomNavigationView nav;
    //Button but;
    Menu sortMenu;
    public ProgressBar progressBar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.side_menu ,menu);
       // MenuItem menuItem = menu.findItem(R.id.a);
       // menuItem.setVisible(true);
       // sortMenu = menu;
//        MenuItem profileMenuItem = menu.findItem(R.id.profile);
//        MenuItem bookingsMenuItem = menu.findItem(R.id.bookings);
//        profileMenuItem.setVisible(false);
//        bookingsMenuItem.setVisible(false);
        //sortMenuItems(menu);
        return super.onCreateOptionsMenu(menu);
    }

//    private void sortMenuItems(Menu menu) {
//        SubMenu submenu = menu.findItem(R.id).getSubMenu();
//
//        // Get all submenu items
//        List<MenuItem> subMenuItems = new ArrayList<>();
//        for (int i = 0; i < submenu.size(); i++) {
//            subMenuItems.add(submenu.getItem(i));
//        }
//
//        // Sort submenu items alphabetically based on their titles
//        Collections.sort(subMenuItems, new Comparator<MenuItem>() {
//            @Override
//            public int compare(MenuItem menuItem1, MenuItem menuItem2) {
//                return menuItem1.getTitle().toString().compareToIgnoreCase(menuItem2.getTitle().toString());
//            }
//        });
//
//        // Clear existing submenu items
//        submenu.clear();
//
//        // Add sorted submenu items back to the submenu
//        for (MenuItem menuItem : subMenuItems) {
//            int itemId = menuItem.getItemId();
//            int order = menuItem.getOrder();
//            int groupId = menuItem.getGroupId();
//           // int showAsAction = menuItem.getShowAsAction();
//            CharSequence title = menuItem.getTitle();
//            Drawable icon = menuItem.getIcon(); // Retain the icon
//
//            submenu.add(groupId, itemId, order, title).setIcon(icon);
//        }
//    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle menu item clicks
        switch (item.getItemId()) {
            case R.id.parsing:
                startActivity(new Intent(DashBoard.this, Parsing.class));
                break;
            case R.id.hardware:
                //showToast("Profile Option is Selected");
                Intent act = new Intent(DashBoard.this,HardwareSupport.class);
                startActivity(act);
                break;
            case R.id.group:
//                showToast("Bookings Option is Selected");
                Intent intent = new Intent(DashBoard.this, GroupMessage.class);
                startActivity(intent);
                break;

            case R.id.schedule:
                Intent sms = new Intent(DashBoard.this, MessageSchedule.class);
                startActivity(sms);
               // showToast("Settings Option is Selected");
                break;

            case R.id.profile:
                Intent sms1 = new Intent(DashBoard.this, Profile.class);
                startActivity(sms1);
                // showToast("Settings Option is Selected");
                break;
            case R.id.message:
                //showToast("Help Option is Selected");
//                Intent intents = new Intent(DashBoard.this, Message.class);
//                startActivity(intents);
                Intent intents = new Intent(Intent.ACTION_VIEW);
                intents.setData(Uri.parse("sms:"));
                startActivity(intents);
                break;
            case R.id.loc:
                //showToast("Help Option is Selected");
                Intent i = new Intent(DashBoard.this, Location.class);
                startActivity(i);
                break;

            case R.id.googleMaps:
                startActivity(new Intent(DashBoard.this,GoogleMaps.class));
                break;
            case R.id.media:
                Intent media = new Intent(DashBoard.this,MultiMedia.class);
                startActivity(media);
                break;

            case R.id.logout:
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();
                finish();
                showLogoutNotification();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void showLogoutNotification() {
        String title = "Logout";
        String message = "You have been logged out.";
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Create Notification Channel (Required for Android Oreo and above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "logout_channel";
            CharSequence channelName = "Logout Channel";
            String channelDescription = "Channel for logout notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
            notificationChannel.setDescription(channelDescription);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "logout_channel")
                .setSmallIcon(R.drawable.ic_baseline_logout_24) // Set small icon
                .setContentTitle("Logout") // Set title
                .setContentText("Do you want to Logout") // Set message
                .setAutoCancel(true) // Automatically dismiss the notification when clicked
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

// Add action to log out when "Yes" is clicked
        Intent yesIntent = new Intent(this, Login.class); // Replace LogoutActivity with your actual logout activity
        yesIntent.putExtra("action", "logout");
        PendingIntent yesPendingIntent = PendingIntent.getActivity(this, 0, yesIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.addAction(android.R.drawable.ic_menu_close_clear_cancel, "Yes", yesPendingIntent);

// Add action to dismiss the notification when "No" is clicked
        Intent noIntent = new Intent(this, DashBoard.class); // Replace NotificationDismissReceiver with your broadcast receiver class
        noIntent.setAction("DISMISS_NOTIFICATION");
        PendingIntent noPendingIntent = PendingIntent.getBroadcast(this, 0, noIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.addAction(android.R.drawable.ic_menu_close_clear_cancel, "No", noPendingIntent);
        notificationManager.notify(1, builder.build());

    }


    AirplaneMode airplaneMode = new AirplaneMode();
    BluetoothMode bluetoothMode = new BluetoothMode();
    BatteryLevel batteryLevel = new BatteryLevel();
    BootReceiver bootReceiver = new BootReceiver();

    @SuppressLint("QueryPermissionsNeeded")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        switcher = findViewById(R.id.switcher);
        text1 = findViewById(R.id.username);
        //image = findViewById(R.id.map);
        listView = findViewById(R.id.listView);
        registerForContextMenu(listView);
        //progressBar = findViewById(R.id.progressbar);
        batteryText = findViewById(R.id.battery);

        nav = findViewById(R.id.bottomnav);

        sharedPreferences = getSharedPreferences(prefName,MODE_PRIVATE);
        String prefEmail = sharedPreferences.getString(keyEmail,null);
        String prefPass = sharedPreferences.getString(keyPass,null);

     //   Toast.makeText(this, prefEmail + " " + prefPass, Toast.LENGTH_SHORT).show();

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
//                    case R.id.home:
//                        //showToast("Profile Option is Selected");
//                        Intent act1 = new Intent(DashBoard.this, DashBoard.class);
//                        startActivity(act1);
//                        break;
                    case R.id.animations:
                        startActivity(new Intent(DashBoard.this, Animation.class));
                        break;
                    case R.id.hardware:
                        //showToast("Profile Option is Selected");
                        Intent act = new Intent(DashBoard.this, HardwareSupport.class);
                        startActivity(act);
                        break;
                    case R.id.location:
                        //showToast("Help Option is Selected");
                        Intent i = new Intent(DashBoard.this,GoogleMaps.class);
                        startActivity(i);
                        break;
                    case R.id.profile:
                        Intent sms = new Intent(DashBoard.this, Profile.class);
                        startActivity(sms);
                        break;

                    case R.id.media:
                        Intent media = new Intent(DashBoard.this,MultiMedia.class);
                        startActivity(media);
                        break;

                    case R.id.logout:
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                        auth.signOut();
                        startActivity(new Intent(DashBoard.this,Login.class));
                        showLogoutNotification();
                        break;
                }
             return false;
            }
        });


        sharedPreferences = getSharedPreferences("MODE",Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night",false);

        if(nightMode){
            switcher.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        switcher.setOnClickListener(v->{
            if(nightMode){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor = sharedPreferences.edit();
                editor.putBoolean("night",false);
            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor = sharedPreferences.edit();
                editor.putBoolean("night",true);
            }
            editor.apply();
        });

        //but = findViewById(R.id.button3);

//        but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              // Intent intent = new Intent(DashBoard.this , Broadcast.class);
//              // startActivity(intent);
//            }
//        });

        if ("DISMISS_NOTIFICATION".equals(getIntent().getAction())) {
            int notificationId = 1;
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(notificationId); // Cancel the notification with the specified ID
        }

        // Registering ListView for context menu

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("ZTurf");
        actionBar.setSubtitle("Kickstart Your Game");
       // actionBar.setIcon();

        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);



        String username = getIntent().getStringExtra("username") + " !!";
        //text1.setText(username);

        List<String> itemList = new ArrayList<>();
        itemList.add("Name : Infinity Arena\nLocation : Anna Nagar");
        itemList.add("Name : United Sports\nLocation : KK Nagar");
        itemList.add("Name : Hattrick Sports\nLocation : Mathuthavani");
        // Add more items as needed

        // Create an ArrayAdapter to bind the list to the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Open new activity based on the clicked item
                String selectedItem = itemList.get(position);
                //showAlertDialog(selectedItem);
                if (selectedItem.contains("Infinity Arena")) {
                    Intent intent = new Intent(DashBoard.this, Turf1.class);
                    startActivity(intent);

                } else if (selectedItem.contains("United Sports")) {
                    Intent intent = new Intent(DashBoard.this, Turf2.class);

                    startActivity(intent);
                } else if (selectedItem.contains("Hattrick Sports")) {
                    Intent intent = new Intent(DashBoard.this, Turf3.class);
                    startActivity(intent);
                }
            }

//
      });
//
//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showAlertDialog();
//            }
//        });

        // Setting click listener for the image using PopupMenu
//        image.setOnClickListener(v -> {
//            PopupMenu popupMenu = new PopupMenu(DashBoard.this, v);
//            popupMenu.getMenuInflater().inflate(R.menu.popup_menu , popupMenu.getMenu());
//            popupMenu.setOnMenuItemClickListener(item -> {
//                if (item.getItemId() == R.id.openMaps) {
//                    openMaps(); // Redirect to map
//                    showToast("Opening Google Maps");
//                    return true;
//                }
//                return false;
//            });
//            popupMenu.show();
//        });
    }

    private void showAlertDialog() {
        final List<String> items = new ArrayList<>();
        items.add("Visit");
        items.add("View Location");
        items.add("Share");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do You Want to Logout??")
                .setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Perform action based on the clicked item
                                String selectedItem = items.get(which);
                                Toast.makeText(DashBoard.this, selectedItem + " clicked", Toast.LENGTH_SHORT).show();
                            }
                        })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
        builder.show();
    }

    // Method to open maps
    private void openMaps() {
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="));
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    // Method to show a toast message

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    // Override method to create a context menu for ListView items
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        getMenuInflater().inflate(R.menu.menu_context, menu);
//
//    }
//
//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId() == R.id.location){
//            openMaps();
//        }
//
//
//
//        return super.onContextItemSelected(item);
//    }

    protected void onStart() {
        super.onStart();
        IntentFilter airplaneFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        IntentFilter batteryfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        IntentFilter bluetoothfilter = new IntentFilter(android.bluetooth.BluetoothAdapter.ACTION_STATE_CHANGED);
        IntentFilter bootFilter = new IntentFilter(Intent.ACTION_BOOT_COMPLETED);
        registerReceiver(airplaneMode, airplaneFilter);
        registerReceiver(batteryLevel, batteryfilter);
        registerReceiver(bluetoothMode,bluetoothfilter);
        registerReceiver(bootReceiver,bootFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(airplaneMode);
        unregisterReceiver(bluetoothMode);
        unregisterReceiver(batteryLevel);
        unregisterReceiver(bootReceiver);
    }
    private class BatteryLevel extends BroadcastReceiver{
        private final static String BATTERY_LEVEL = "level";

        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BATTERY_LEVEL, 0);
            //Toast.makeText(context, "Battery Level is" + level, Toast.LENGTH_SHORT).show();
           // progressBar.setProgress(level);
           // batteryText.setText("Battery Level is "+  level);

        }
    }

}



