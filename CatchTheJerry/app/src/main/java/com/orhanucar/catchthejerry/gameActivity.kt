package com.orhanucar.catchthejerry

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_main.imageView
import java.util.*
import kotlin.collections.ArrayList

class gameActivity : AppCompatActivity() {

    var score = 0
    var imageArray = ArrayList<ImageView>()
    var handler = Handler()
    var runnable = Runnable {}
    private var tt: MediaPlayer? = null
    var playMusic = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        imageArray.add(imageView)
        imageArray.add(imageView1)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)
        imageArray.add(imageView10)
        imageArray.add(imageView11)
        imageArray.add(imageView12)
        imageArray.add(imageView13)
        imageArray.add(imageView14)
        imageArray.add(imageView15)

        hideImages()

        //CountDown Timer
        object : CountDownTimer(30000,1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeText.text = "Time:" + millisUntilFinished/1000
                if (playMusic == 1) {
                    playAudıo()
                }
                playMusic = 2
            }

            override fun onFinish() {
                timeText.text = "Time : 0"
                handler.removeCallbacks(runnable)
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                val alert = AlertDialog.Builder(this@gameActivity)
                alert.setTitle("Well Played!")
                alert.setMessage("Do you want to play again?")
                alert.setPositiveButton("Yes") {dialog,which ->
                    val intent = getIntent()
                    finish()
                    startActivity(getIntent())
                }
                alert.setNegativeButton("No") {dialog, which ->
                    Toast.makeText(this@gameActivity,"Bye Bye!", Toast.LENGTH_LONG).show()
                    finish()
                }
                tt?.stop()
                alert.show()
            }
        }.start()
    }

    fun hideImages() {
        runnable = object : Runnable {
            override fun run() {
                for(image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                val random = Random()
                val randomIndex = random.nextInt(15)
                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable,375 )
            }
        }
        handler.post(runnable)
    }

    fun increaseScore(view : View) {
        score = score+1
        scoreText.text = "Score : $score"
    }

    private fun playAudıo() {
        tt = MediaPlayer.create(this@gameActivity, R.raw.tt)
        tt?.setOnPreparedListener{
            tt?.start()
        }
        tt?.setOnCompletionListener {
            tt?.start()
        }
    }
}
