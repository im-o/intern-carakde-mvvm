package com.example.mvvmsampleappintern.ui.chart

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mvvmsampleappintern.R
import com.example.mvvmsampleappintern.databinding.ActivitySampleChartBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class SampleChartActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySampleChartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_chart)
        initView()
    }

    private fun initView() {
        //just dummy
        val caseList = ArrayList<BarEntry>()
        caseList.add(BarEntry(0f,10f))
        caseList.add(BarEntry(1f,8f))
        caseList.add(BarEntry(2f,6f))
        caseList.add(BarEntry(3f,7f))
        caseList.add(BarEntry(4f,5f))
        caseList.add(BarEntry(5f,9f))

        val deadList = ArrayList<BarEntry>()
        deadList.add(BarEntry(0f,4f))
        deadList.add(BarEntry(1f,5f))
        deadList.add(BarEntry(2f,3f))
        deadList.add(BarEntry(3f,5f))
        deadList.add(BarEntry(4f,2f))
        deadList.add(BarEntry(5f,2f))

        val recoverList = ArrayList<BarEntry>()
        recoverList.add(BarEntry(0f,1f))
        recoverList.add(BarEntry(1f,2f))
        recoverList.add(BarEntry(2f,1f))
        recoverList.add(BarEntry(3f,4f))
        recoverList.add(BarEntry(4f,1f))
        recoverList.add(BarEntry(5f,1f))

        val caseBarDataSet = BarDataSet(caseList, getString(R.string.str_case))
        caseBarDataSet.color = Color.BLUE
        val deadBarDataSet = BarDataSet(deadList, getString(R.string.str_dead))
        deadBarDataSet.color = Color.RED
        val recoverBarDataSet = BarDataSet(recoverList, getString(R.string.str_recover))
        recoverBarDataSet.color = Color.GREEN

        val groupSpace = 0.08f
        val barSpace = 0.03f
        val barWidth = 0.27f
        val groupBar = BarData(caseBarDataSet, deadBarDataSet, recoverBarDataSet)
        groupBar.barWidth = barWidth

        binding.sampleChartBC.apply {
            description.isEnabled = false
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            data = groupBar
            groupBars(0f, groupSpace, barSpace)
            animateXY(100,500)
        }
    }
}
