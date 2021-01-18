package silantyevmn.ru.coinlite.mvp.model.entity.rest

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "charts")
data class GeckoCoinChartRest (
    @PrimaryKey
    var id: String,
    var prices: List<List<Float>>
)
