

import android.content.res.Resources
import com.common.utils.R

class UploadDownloadUtils {

    companion object {
        fun humanReadableByteCount(bytes: Long, speed: Boolean, res: Resources): String {
            var bytes = bytes
            if (speed) bytes = bytes * 8
            val unit = if (speed) 1000 else 1024
            val exp = Math.max(
                0,
                Math.min((Math.log(bytes.toDouble()) / Math.log(unit.toDouble())).toInt(), 3)
            )
            val bytesUnit = (bytes / Math.pow(unit.toDouble(), exp.toDouble())).toFloat()
            return if (speed) when (exp) {
                0 -> res.getString(R.string.comutil_bits_per_second, bytesUnit)
                1 -> res.getString(R.string.comutil_kbits_per_second, bytesUnit)
                2 -> res.getString(R.string.comutil_mbits_per_second, bytesUnit)
                else -> res.getString(R.string.comutil_gbits_per_second, bytesUnit)
            } else when (exp) {
                0 -> res.getString(R.string.comutil_volume_byte, bytesUnit)
                1 -> res.getString(R.string.comutil_volume_kbyte, bytesUnit)
                2 -> res.getString(R.string.comutil_volume_mbyte, bytesUnit)
                else -> res.getString(R.string.comutil_volume_gbyte, bytesUnit)
            }
        }
    }
}