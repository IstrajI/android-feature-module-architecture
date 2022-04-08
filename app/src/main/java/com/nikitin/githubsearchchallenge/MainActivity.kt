package com.nikitin.githubsearchchallenge

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.nikitin.githubsearchchallenge.repositories.GitHubAPI
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var gitHubAPI: GitHubAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch(Dispatchers.IO) {
            Log.d("TestPish", "Dispatcher "+this.coroutineContext)
            val userinfo = gitHubAPI.searchRepository("weather")
            Log.d("TestPish", "userinfo $userinfo")
        }
    }
}