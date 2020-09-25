package com.example.mvvmsampleappintern.ui.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.mvvmsampleappintern.R
import com.example.mvvmsampleappintern.databinding.ActivityWorkRequestBinding
import com.example.mvvmsampleappintern.workers.MyWorker

class WorkRequestActivity : AppCompatActivity() {
    companion object {
        const val KEY_TASK_DESC = "key_task_desc"
    }

    private lateinit var binding: ActivityWorkRequestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_work_request)
        initWorkerManger()
    }

    private fun initWorkerManger() {
        val data = Data.Builder()
            .putString(KEY_TASK_DESC, "Hey I am sending the work data from activity")
            .build()

        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .setInputData(data)
            .build()

        binding.workStartedMB.setOnClickListener {
            WorkManager.getInstance(this).enqueue(oneTimeWorkRequest)
        }

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
            .observe(this, Observer {
                if (it != null) {
                    if (it.state.isFinished) {
                        val data1 = it.outputData
                        val output = data1.getString(MyWorker.KEY_TASK_OUTPUT)
                        binding.workerResultTV.append("out : $output \n")
                    }
                }

                val status = it.state.name
                binding.workerResultTV.append("status : $status \n")
            })
    }
}
