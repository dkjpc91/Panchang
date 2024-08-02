import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mithilakshar.mithilapanchang.UI.Fragments.BhagwatGitaChapterFragmentDirections
import com.mithilakshar.mithilapanchang.Utility.dbHelper
import com.mithilakshar.mithilapanchang.databinding.ItemGitachapterBinding

// Import your View Binding class for the item layout

class GitaChapterAdapter(private val chapterNames: List<dbHelper.Chapter>) : RecyclerView.Adapter<GitaChapterAdapter.ChapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val binding = ItemGitachapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        val chapterName = chapterNames[position]
        holder.bind(chapterName)
    }

    override fun getItemCount(): Int {
        return chapterNames.size
    }

    inner class ChapterViewHolder(private val binding: ItemGitachapterBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            // Set click listener on the item
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Get chapter name associated with the clicked item
                    val chapterName = chapterNames[position]

                    // Navigate to destination fragment using Navigation Component with argument

                    val action = BhagwatGitaChapterFragmentDirections.actionBhagwatGitaChapterFragmentToBhagwatGitaVerseFragment(chapterName.chapterName)
                    binding.root.findNavController().navigate(action)
                }
            }
        }

        fun bind(chapterName: dbHelper.Chapter) {
            binding.apply {
                chaptername.text=chapterName.chapterName
                shlokaCount.text=chapterName.uid.toString()
                chapterdesc.text=chapterName.description

            }
        }
    }
}
