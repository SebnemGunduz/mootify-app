import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.sebnem.mootify.R

class SycActivity : AppCompatActivity() {

    private lateinit var musicAcImageView: ImageView
    private lateinit var musicKapaImageView: ImageView
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sayac)

        musicAcImageView = findViewById(R.id.musicac)
        musicKapaImageView = findViewById(R.id.musickapa)

        initializeMediaPlayer()

        musicAcImageView.setOnClickListener {
            startMusic()
            musicAcImageView.visibility = View.INVISIBLE
            musicKapaImageView.visibility = View.VISIBLE
        }

        musicKapaImageView.setOnClickListener {
            pauseMusic()
            musicKapaImageView.visibility = View.INVISIBLE
            musicAcImageView.visibility = View.VISIBLE
        }
    }

    private fun initializeMediaPlayer() {
        mediaPlayer = MediaPlayer.create(this, R.raw.odak)
        mediaPlayer.isLooping = true
    }

    private fun startMusic() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
        }
    }

    private fun pauseMusic() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    override fun onDestroy() {
        mediaPlayer.release()  // MediaPlayer'ı serbest bırak
        super.onDestroy()
    }
}
