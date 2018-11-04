package teste01.arcore.geronimo.don.androidarcoreteste01

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.Toast

class ArcoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arcore)
        //checar se tenho permissao de câmera. se nao tiver, para tudo
        if(hasCameraPermission()){

        }
        else{
            Toast.makeText(this, "SEM PERMISSAO DE CAMERA NAO ROLA NE", Toast.LENGTH_LONG).show()
        }
    }

    private fun hasCameraPermission(): Boolean{
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    }
}
