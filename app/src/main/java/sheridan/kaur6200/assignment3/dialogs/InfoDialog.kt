package sheridan.kaur6200.assignment3.dialogs

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


fun AppCompatActivity.showInfoDialog(title: String, message: String) =
    InfoDialog.showInfoDialog(
        supportFragmentManager,
        title,
        message
    )

fun Fragment.showInfoDialog(title: String, message: String) =
    InfoDialog.showInfoDialog(
        parentFragmentManager,
        title,
        message
    )


class InfoDialog : DialogFragment() {

    companion object {
        private const val TAG = "InfoDialog"
        private const val TITLE = "title"
        private const val MESSAGE = "message"

        fun showInfoDialog(
            fragmentManager: FragmentManager,
            title: String,
            message: String
        ) = newInstance(title, message).show(fragmentManager, TAG)


        private fun newInstance(title: String, message: String) =
            InfoDialog().apply {
                arguments = bundleOf(TITLE to title, MESSAGE to message)
            }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        AlertDialog.Builder(requireContext())
            .setTitle(requireArguments().getString(TITLE))
            .setMessage(requireArguments().getString(MESSAGE))
            .setPositiveButton(android.R.string.ok, null)
            .create()
}