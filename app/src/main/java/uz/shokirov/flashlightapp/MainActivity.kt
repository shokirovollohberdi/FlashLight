package uz.shokirov.flashlightapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.hardware.Camera
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var flashSwitch = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        imageMenu.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }

        imageFlash.setOnClickListener {
            when (flashSwitch) {
                0 -> {
                    val animInside = AnimationUtils.loadAnimation(this, R.anim.inside_anim)
                    imageFlash.startAnimation(animInside)
                    animInside.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(p0: Animation?) {

                        }

                        @SuppressLint("ResourceAsColor")
                        override fun onAnimationEnd(p0: Animation?) {
                            val animOutSide =
                                    AnimationUtils.loadAnimation(this@MainActivity, R.anim.outside_anim)
                            imageFlash.startAnimation(animOutSide)

                            try {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    val cameraManager =
                                            this@MainActivity.getSystemService(Context.CAMERA_SERVICE) as CameraManager
                                    cameraManager.setTorchMode(cameraManager.cameraIdList[0], true)
                                } else {
                                    val camera = Camera.open()
                                    val params = camera.parameters
                                    params.flashMode = Camera.Parameters.FLASH_MODE_TORCH
                                    camera.parameters = params
                                    camera.startPreview()
                                }
                            } catch (e: Exception) {

                            }
                            imageFlash.setImageResource(R.drawable.ic_baseline_power_settings_new_24)
                            tvTitle.setTextColor(Color.GREEN)
                            flashSwitch = 1
                        }

                        override fun onAnimationRepeat(p0: Animation?) {

                        }

                    })
                }
                1 -> {
                    val animInside = AnimationUtils.loadAnimation(this, R.anim.inside_anim)
                    imageFlash.startAnimation(animInside)
                    animInside.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(p0: Animation?) {

                        }

                        @SuppressLint("ResourceAsColor")
                        override fun onAnimationEnd(p0: Animation?) {
                            val animOutSide =
                                    AnimationUtils.loadAnimation(this@MainActivity, R.anim.outside_anim)
                            imageFlash.startAnimation(animOutSide)
                            try {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    val cameraManager =
                                            this@MainActivity.getSystemService(Context.CAMERA_SERVICE) as CameraManager
                                    cameraManager.setTorchMode(cameraManager.cameraIdList[0], false)
                                } else {
                                    val camera = Camera.open()
                                    val params = camera.parameters
                                    params.flashMode = Camera.Parameters.FLASH_MODE_OFF
                                    camera.parameters = params
                                    camera.startPreview()
                                }
                            } catch (e: Exception) {

                            }


                            imageFlash.setImageResource(R.drawable.ic_power_off)
                            tvTitle.setTextColor(Color.RED)
                            flashSwitch = 0
                        }

                        override fun onAnimationRepeat(p0: Animation?) {

                        }

                    })
                }
            }
        }

    }
}