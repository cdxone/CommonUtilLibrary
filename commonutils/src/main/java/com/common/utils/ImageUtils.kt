import android.content.Context
import android.view.RoundedCorner
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.common.utils.CircleCropTransform


class ImageUtils {

    companion object {
        fun load(context: Context, iv: ImageView, url: String?) {
            Glide.with(context)
                .load(url)
                .into(iv)
        }

        /**
         * 加载圆形图片
         */
        fun loadCircleAndBorder(
            context: Context,
            iv: ImageView,
            path: String?,
            borderWidth: Float,
            borderColor: Int
        ) {
            if (path == null) return
            Glide.with(context)
                .load(path)
                .transform(
                    CircleCropTransform(
                        borderWidth,
                        borderColor
                    )
                )
                .into(iv)
        }

        fun loadBorder(
            context: Context,
            iv: ImageView,
            path: Int,
            borderSize: Int
        ) {
            // 加载为四个都是圆角的图片 可以设置圆角幅度
            val options = RequestOptions.bitmapTransform(RoundedCorners(40))
            Glide.with(context)
                .load(path)
                .apply(options)
                .into(iv)
        }
    }
}
