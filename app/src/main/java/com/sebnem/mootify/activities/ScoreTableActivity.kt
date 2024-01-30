package com.sebnem.mootify.activities

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.sebnem.mootify.databinding.ActivityScoreTableBinding
import com.sebnem.mootify.db.ScoreTable
import com.sebnem.mootify.db.ScoreTable_Table
import com.sebnem.mootify.util.DateUtil

class ScoreTableActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScoreTableBinding

    private val barEntriesList = ArrayList<BarEntry>()

    private val defaultValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreTableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        barEntriesList.clear()
        val barChart = binding.idBarChart
        barChart.description.isEnabled = false
        val legend = barChart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.setDrawInside(false)
        legend.form = Legend.LegendForm.SQUARE
        legend.formSize = 9f
        legend.textSize = 11f
        legend.xEntrySpace = 4f

        val scoreTableList = SQLite.select().from(ScoreTable::class.java)
            .where(ScoreTable_Table.username.eq(MainActivity.currentUser.username.toString()))
            .queryList()

        if (scoreTableList.isEmpty()) {
            binding.apply {
                barEntriesList.clear()
                barEntriesList.add(BarEntry(0f, defaultValue.toFloat()))
                barEntriesList.add(BarEntry(1f, defaultValue.toFloat()))
                barEntriesList.add(BarEntry(2f, defaultValue.toFloat()))
                barEntriesList.add(BarEntry(3f, defaultValue.toFloat()))
                barEntriesList.add(BarEntry(4f, defaultValue.toFloat()))
                barEntriesList.add(BarEntry(5f, defaultValue.toFloat()))
                barEntriesList.add(BarEntry(6f, defaultValue.toFloat()))
            }
            return
        }

        Log.i("ScoreTable", scoreTableList.toString())

        try {
            val findMondayScoreTable = scoreTableList.last { getDayOfWeek(it.date) == "Monday" }
            val score = findMondayScoreTable?.let { it.score.toString() } ?: kotlin.run { "0" }
            barEntriesList.add(BarEntry(0f, score.toFloat()))
        } catch (e: Exception) {
            barEntriesList.add(BarEntry(0f, defaultValue.toFloat()))
        }

        try {
            val findTuesdayScoreTable = scoreTableList.last { getDayOfWeek(it.date) == "Tuesday" }
            val score = findTuesdayScoreTable?.let { it.score.toString() } ?: kotlin.run { "0" }
            barEntriesList.add(BarEntry(1f, score.toFloat()))
        } catch (e: Exception) {
            barEntriesList.add(BarEntry(1f, defaultValue.toFloat()))
        }

        try {
            val findWednesdayScoreTable = scoreTableList.last { getDayOfWeek(it.date) == "Wednesday" }
            val score = findWednesdayScoreTable?.let { it.score.toString() } ?: kotlin.run { "0" }
            barEntriesList.add(BarEntry(2f, score.toFloat()))
        } catch (e: Exception) {
            barEntriesList.add(BarEntry(2f, defaultValue.toFloat()))
        }

        try {
            val findThursdayScoreTable = scoreTableList.last { getDayOfWeek(it.date) == "Thursday" }
            val score = findThursdayScoreTable?.let { it.score.toString() } ?: kotlin.run { "0" }
            barEntriesList.add(BarEntry(3f, score.toFloat()))
        } catch (e: Exception) {
            barEntriesList.add(BarEntry(3f, defaultValue.toFloat()))
        }

        try {
            val findFridayScoreTable = scoreTableList.last { getDayOfWeek(it.date) == "Friday" }
            val score = findFridayScoreTable?.let { it.score.toString() } ?: kotlin.run { "0" }
            barEntriesList.add(BarEntry(4f, score.toFloat()))
        } catch (e: Exception) {
            barEntriesList.add(BarEntry(4f, defaultValue.toFloat()))
        }

        try {
            val findSaturdayScoreTable = scoreTableList.last { getDayOfWeek(it.date) == "Saturday" }
            val score = findSaturdayScoreTable?.let { it.score.toString() } ?: kotlin.run { "0" }
            barEntriesList.add(BarEntry(5f, score.toFloat()))
        } catch (e: Exception) {
            barEntriesList.add(BarEntry(5f, defaultValue.toFloat()))
        }

        try {
            val findSundayScoreTable = scoreTableList.last { getDayOfWeek(it.date) == "Sunday" }
            val score = findSundayScoreTable?.let { it.score.toString() } ?: kotlin.run { "0" }
            barEntriesList.add(BarEntry(6f, score.toFloat()))
        } catch (e: Exception) {
            barEntriesList.add(BarEntry(6f, defaultValue.toFloat()))
        }

        val barDataSet = BarDataSet(barEntriesList, "Haftalık Skor Tablosu")
        val labels = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        val barData = BarData(barDataSet)
        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.labelCount = labels.size
        xAxis.setDrawGridLines(false)
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val index = value.toInt()
                if (index >= 0 && index < labels.size) {
                    return labels[index]
                }
                return ""
            }
        }
        xAxis.labelCount = labels.size
        barChart.legend.isEnabled = false
        barChart.data = barData
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 16f
        barChart.invalidate()

    }

    private fun getDayOfWeek(date: String?) : String {
        return date?.let {
            val dateFormat = DateUtil.getDate(it)
            dateFormat?.let { dateFormatted ->
                DateUtil.getDayOfTheWeek(dateFormatted)
            } ?: kotlin.run {
                "Monday"
            }
        } ?: kotlin.run {
            "Monday"
        }
    }
}