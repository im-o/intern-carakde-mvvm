package com.example.mvvmsampleappintern.ui.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.mvvmsampleappintern.R
import com.example.mvvmsampleappintern.databinding.ActivityWorkRequestBinding
import com.example.mvvmsampleappintern.workers.MyWorker

class WorkRequestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWorkRequestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_work_request)

        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
        binding.workStartedMB.setOnClickListener {
            WorkManager.getInstance(this).enqueue(oneTimeWorkRequest)
        }

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
            .observe(this, Observer {
                val status = it.state.name
                binding.workerResultTV.append("$status \n")
            })
    }
}
