package mx.com.qualitycode.segundotest.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import mx.com.qualitycode.segundotest.R
import mx.com.qualitycode.segundotest.models.ProfilesSuggestedModel

class FollowAdapter(context: Context): RecyclerView.Adapter<FollowAdapter.ViewHolder>() {

    private var list : List<ProfilesSuggestedModel.Data> = ArrayList()

    var followingMainListCallback: FollowingMainListCallback? = null

     class ViewHolder(itemView: View,var followingListCallback: FollowingMainListCallback?) : RecyclerView.ViewHolder(itemView) {
         val imageMain : ImageView = itemView.findViewById(R.id.image)
         val profileImage : ImageView = itemView.findViewById(R.id.profile_image)
         val user: TextView = itemView.findViewById(R.id.textViewUserName)
         val textViewFollowProfile: TextView = itemView.findViewById(R.id.textViewFollowProfile)

         var listCategories: ProfilesSuggestedModel.Data?= null
         fun bind(item: ProfilesSuggestedModel.Data) = with(itemView) {

             listCategories = item



             Glide.with(context)
                 .load(item.photo).asBitmap()
                 .placeholder(R.drawable.ic_launcher_background)
                 .into(profileImage)

             user.text = item.nickname

             setOnClickListener {
                 listCategories?.let {
                     followingListCallback?.onSelectedFollowerProfile(it)
                 }
             }

             textViewFollowProfile.setOnClickListener {
                 listCategories?.let {
                     textViewFollowProfile.text = context.getString(R.string.following)
                     textViewFollowProfile.backgroundTintList = ColorStateList.valueOf(context.getColor(R.color.black))
                     textViewFollowProfile.setTextColor(context.getColor(R.color.white))
                     textViewFollowProfile.isEnabled = false
                     followingListCallback?.onFollowSelected(it)
                 }
             }

         }

     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_view_following, parent,false)
         return ViewHolder(inflater,followingMainListCallback)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<ProfilesSuggestedModel.Data>) {
        this.list = list
        notifyDataSetChanged()
    }
}

interface FollowingMainListCallback {
    fun onSelectedFollowerProfile(following: ProfilesSuggestedModel.Data)
    fun onFollowSelected(following: ProfilesSuggestedModel.Data)
}