package com.mobdeve.s12.aquino.batac.game_bible

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.telecom.Call
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.share.model.ShareHashtag
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityGameDetailsBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import java.lang.Exception
import java.util.*

class GameDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameDetailsBinding
    private lateinit var videoID: String
    private lateinit var tts: TextToSpeech

    private lateinit var gameDetails: Intent
    private lateinit var loginPrompt: AlertDialog.Builder

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//      Show tool bar back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        gameDetails = intent

        val desc = gameDetails.getStringExtra("desc")

        binding.detTitleTv.text = gameDetails.getStringExtra("title")
        binding.detDescTv.text = desc
        binding.detGenreTv.text = "Genre: " + gameDetails.getStringExtra("genre")
        binding.detDateTv.text = "Release Date: " + gameDetails.getStringExtra("releaseDate")
        binding.detDevTv.text = "Developer: " + gameDetails.getStringExtra("developer")
        binding.detPubTv.text = "Publisher: " + gameDetails.getStringExtra("publisher")
        Glide.with(this).load(gameDetails.getStringExtra("img")).into(binding.detThumbIv)

//      Setup - Text-To-Speech API
        initTTS()

//      Setup - YouTube API
//      Setup Link
        videoID = intent.getStringExtra("trailer").toString()
        initYT()

//      Setup - Facebook API
        initFBShare()

//      Viewing Community Reviews
        binding.detViewBtn.setOnClickListener{
            val intent = Intent(this, ViewReviewActivity::class.java)
            val gid = gameDetails.getIntExtra("gid", 0)
            intent.putExtra("gid", gid)

            startActivity(intent)
        }
    }

    private fun initFBShare(){
        binding.detShareIv.setOnClickListener{

            loginPrompt = AlertDialog.Builder(this@GameDetailsActivity)

            val hashTag = ShareHashtag.Builder().setHashtag("#GameBible_${gameDetails.getStringExtra("title")!!.replace(" ", "")}").build()
            val linkContent = ShareLinkContent.Builder()
                .setShareHashtag(hashTag)
                .setQuote(binding.detTitleTv.text.toString())
                .setContentUrl(Uri.parse("https://www.youtube.com/watch?v=$videoID"))
                .build()

            loginPrompt.setTitle("Share to Facebook")
                .setMessage("Do you want to log out of your current Facebook account?\n\nNote: If you have yet to login an account but clicked NO, you will still be redirected to the login screen regardless.")
                .setCancelable(true)
                .setPositiveButton("Yes"){ dialogInterface, it ->
                    LoginManager.getInstance().logOut()
                    ShareDialog.show(this@GameDetailsActivity, linkContent)
                    dialogInterface.dismiss()
                }
                .setNegativeButton("No"){ dialogInterface, it ->
                    ShareDialog.show(this@GameDetailsActivity, linkContent)
                    dialogInterface.dismiss()
                }
                .show()
        }
    }

    private fun initTTS(){
        tts = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {
            if(it == TextToSpeech.SUCCESS){
                tts.setLanguage(Locale.US)
            }
        })

        binding.detSpeechIv.setOnClickListener{
            if(tts.isSpeaking){
                tts.stop()
                binding.detSpeechIv.setImageResource(R.drawable.ic_volume_down)
            }else{
                tts.speak(binding.detDescTv.text.toString(), TextToSpeech.QUEUE_FLUSH, null)
                binding.detSpeechIv.setImageResource(R.drawable.ic_volume_up)
            }
        }
    }
    private fun initYT(){
        lifecycle.addObserver(binding.detYTPlayer)

        binding.detYTPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                // loading the selected video into the YouTube Player
                youTubePlayer.cueVideo(videoID, 0f)
            }

            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                // this method is called if video has ended,
                super.onStateChange(youTubePlayer, state)
            }

            override fun onPlaybackRateChange(
                youTubePlayer: YouTubePlayer,
                playbackRate: PlayerConstants.PlaybackRate
            ) {
                super.onPlaybackRateChange(youTubePlayer, playbackRate)
            }

            override fun onVideoLoadedFraction(
                youTubePlayer: YouTubePlayer,
                loadedFraction: Float
            ) {
                super.onVideoLoadedFraction(youTubePlayer, loadedFraction)
            }

            override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                super.onError(youTubePlayer, error)
            }
        })
    }

    //  Finish activity after clicking back button on ToolBar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}