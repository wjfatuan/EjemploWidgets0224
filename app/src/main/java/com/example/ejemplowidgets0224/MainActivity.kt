package com.example.ejemplowidgets0224

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.ejemplowidgets0224.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var adapter:TeamsListAdapter
    val teamNames = arrayListOf(
        TeamInfo("Liverpool F.C.",R.drawable.liverpool, "United Kingdom", "1892", "http://www.liverpoolfc.com/", "geo:53.4308326,-2.9630187?z=17"),
        TeamInfo("Manchester City F.C.",R.drawable.manchester, "United Kingdom", "1880", "http://www.mancity.com/","geo:53.4831413,-2.202584?z=17")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        println(intent)
        binding.imgTeamShield.setImageResource(R.drawable.bart)
        binding.btnLiverpool.setOnClickListener { changeTeamFlag("liverpool") }
        binding.btnManchester.setOnClickListener { changeTeamFlag("manchester") }
        binding.rdbLiverpool.setOnClickListener { changeTeamFlag("liverpool") }
        binding.rdbManchester.setOnClickListener { changeTeamFlag("manchester") }
        binding.switchTeam.setOnClickListener {
            if(binding.switchTeam.isChecked)
                changeTeamFlag("liverpool")
            else
                changeTeamFlag("manchester")
        }
        // conectar la lista con el arreglo de datos usando un adaptador
        adapter = TeamsListAdapter(this, R.layout.list_teams, teamNames )
        binding.teamsList.adapter = adapter
        binding.teamsList.setOnItemClickListener { list, view, position, id -> // lambda
            println( "list: $list \n" +
                    "view: $view \n" +
                    "position: $position \n" +
                    "id: $id")
            //changeTeamFlag(teamNames[position].lowercase())
            openTeamInfoActivity(teamNames[position])
        }
    }

    fun changeTeamFlag(teamName:String) {
        val id = resources.getIdentifier(teamName, "drawable", packageName) // R.drawable.teanName
        binding.imgTeamShield.setImageResource(id)
        teamNames.add(TeamInfo("Otro equipo", 0, "No definido", "0000", "http://google.com"))
        adapter.notifyDataSetChanged()
    }

    fun openTeamInfoActivity(team: TeamInfo) {
        val teamInfoIntent = Intent( this, InfoEquipoActivity::class.java)
        teamInfoIntent.putExtra("TEAM_DATA", team)
        startActivity(teamInfoIntent)
    }
}