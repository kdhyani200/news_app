package kd.dhyani.newsapp.data.manager.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import kd.dhyani.newsapp.domain.manager.model.Source

@ProvidedTypeConverter
class NewsTypeConvertor {
    @TypeConverter
    fun sourceToString(source: Source): String{
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(source: String): Source{
        return source.split(",").let { sourceArray ->
            Source(sourceArray[0],sourceArray[1])
        }
    }
}