package teste01.arcore.geronimo.don.androidarcoreteste01

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import com.google.ar.core.ArCoreApk
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val CAMERA_PERMISSION_CODE :Int = 666

    fun onOpenArcoreActivityClick(v:View){
        val intent = Intent(this, ArcoreActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //verifica se tem permissão pra usar a câmera. Se não tiver, pede.
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            val permArray = arrayOf(Manifest.permission.CAMERA)
            ActivityCompat.requestPermissions(this, permArray, CAMERA_PERMISSION_CODE)
        }
        else {
            Log.i("ArcoreTeste01", "camera já habilitada")
        }
        maybeEnableArButton()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //HABILITOU
                Log.i("ArcoreTeste01", "camera habilitada")
            }
            else{
                //não habilitou
                Log.i("ArcoreTeste01", "camera não habilitada")
            }
        }
    }

    ///Serve pra habilitar/desabilitar o btn do arcore de acordo com o estado do arcore.
    private fun maybeEnableArButton() {
        val availability = ArCoreApk.getInstance().checkAvailability(this)//Descreve o estado atual do Arcore
        if(availability.isTransient){
            //Ser transiente indica que devo testar mais tarde. Da
            val monitor = object : Runnable {
                override fun run() {
                    maybeEnableArButton()
                }
            }
            val handler = Handler()
            handler.postDelayed( monitor, 200)//bota esse runnable pra rodar no thread principal
        }
        if(availability.isSupported){
            //libera o botao
            ArButton.visibility = View.VISIBLE
            ArButton.isEnabled = true
        }
        else{
            ArButton.visibility = View.INVISIBLE
            ArButton.isEnabled = false
        }
    }

}
