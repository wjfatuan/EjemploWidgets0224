package com.example.ejemplowidgets0224

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.ejemplowidgets0224.databinding.ActivityInfoEquipoBinding

class InfoEquipoActivity : AppCompatActivity() {

    lateinit var binding:ActivityInfoEquipoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoEquipoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val team = intent.getSerializableExtra("TEAM_DATA") as TeamInfo
        binding.teamName.text = team.name
        binding.teamOrigin.text = "${team.country}, ${team.founded}"
        binding.teamFlag.setImageResource(team.flag)
        binding.btnTeamWebpage.setOnClickListener { openTeamWebpage(team.stadiumLocation) }
    }

    fun openTeamWebpage(url:String) {
        val webIntent: Intent = Uri.parse(url).let { webpage ->
            Intent(Intent.ACTION_VIEW, webpage)
        }
        val mapIntent: Intent = Uri.parse(url).let { location ->
            Intent(Intent.ACTION_VIEW, location)
        }

        startActivity(mapIntent)
    }
}