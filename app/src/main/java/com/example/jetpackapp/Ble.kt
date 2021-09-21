package com.example.jetpackapp

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_ble.*


class Ble : AppCompatActivity() {

    lateinit var receiver: BroadcastReceiver
    private lateinit var blueAdapter: BluetoothAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ble)

        blueAdapter = BluetoothAdapter.getDefaultAdapter()

        if (blueAdapter != null) {
            statusBluetoothTv.text = "Bluetooth is available"
        } else
            statusBluetoothTv.text = "Bluetooth is Unavailable"

        if (blueAdapter.isEnabled) {
            bluetoothIv.setImageResource(R.drawable.ic_action_on)
        } else
            bluetoothIv.setImageResource(R.drawable.ic_action_off)

        onBtn.setOnClickListener {
            if (!blueAdapter.isEnabled) {
                Toast.makeText(applicationContext, "Turning On Bluetooth..", Toast.LENGTH_SHORT)
                    .show()
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(intent, 0)
            } else
                Toast.makeText(applicationContext, "Bluetooth already on..", Toast.LENGTH_SHORT)
                    .show()
        }

        offBtn.setOnClickListener {
            if (blueAdapter.isEnabled) {
                blueAdapter.disable()
                bluetoothIv.setImageResource(R.drawable.ic_action_off)
                Toast.makeText(applicationContext, "Turning Bluetooth off..", Toast.LENGTH_SHORT)
                    .show()
            } else
                Toast.makeText(applicationContext, "Bluetooth already off..", Toast.LENGTH_SHORT)
                    .show()
        }
        discoverableBtn.setOnClickListener {
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
//            val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
//            registerReceiver(receiver, filter)
//            blueAdapter.startDiscovery()
        }
    }


    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                2
            )
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                4
            )
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.BLUETOOTH_ADMIN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.BLUETOOTH_ADMIN), 3)
        }
        blueAdapter.startDiscovery()

        val list = ArrayList<String>()
        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent?) {
                Toast.makeText(context, "receive", Toast.LENGTH_LONG).show()
                val action = intent?.action
                if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                    val device: BluetoothDevice =
                        intent?.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)!!
                    Toast.makeText(context, "Bluetooth ${device.name}", Toast.LENGTH_LONG).show()
                }
            }
        }
        val intentFilter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(receiver, intentFilter)

    }
}