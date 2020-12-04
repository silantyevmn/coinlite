package silantyevmn.ru.coinlite.ui.chart.marker

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import silantyevmn.ru.coinlite.R
import silantyevmn.ru.coinlite.utils.GeckoCoinManager

@SuppressLint("ViewConstructor")
class MarkerChartView(context: Context, layoutResource: Int) : MarkerView(context, layoutResource) {
    private val tvContent: TextView

    init {
        tvContent = findViewById(R.id.tvContent)
    }

    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    override fun refreshContent(e: Entry, highlight: Highlight) {
        tvContent.text = e.y.toString() + "\n" + GeckoCoinManager.convertDateLongToString(e.x.toLong())
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
    }
}