package com.nikitin.githubsearchchallenge

import android.os.Bundle
import com.nikitin.githubsearchchallenge.repositories.GitHubAPI
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var gitHubAPI: GitHubAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*lifecycleScope.launch(Dispatchers.IO) {
            Log.d("TestPish", "Dispatcher "+this.coroutineContext)
            val userinfo = gitHubAPI.searchUser2("weather")
            Log.d("TestPish", "userinfo $userinfo")
        }*/
    }
}