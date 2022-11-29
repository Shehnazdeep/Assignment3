package sheridan.kaur6200.assignment3.ui.editTitle

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import sheridan.kaur6200.assignment3.MainViewModel
import sheridan.kaur6200.assignment3.R
import sheridan.kaur6200.assignment3.databinding.FragmentFirstBinding
import sheridan.kaur6200.assignment3.model.Title
import sheridan.kaur6200.assignment3.ui.binding.bindDate

@AndroidEntryPoint
class FirstFragment : Fragment() , MenuProvider {

    private var _binding: FragmentFirstBinding? = null
    private val binding: FragmentFirstBinding
        get() = _binding!!
    // This property is only valid between onCreateView and
    // onDestroyView.
   // private val binding get() = _binding!!
    private val safeArgs: FirstFragmentArgs by navArgs()
    private val viewModel: EditTitleViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        navController = findNavController()

        // setup fragment menu
        requireActivity().addMenuProvider(
            this, viewLifecycleOwner, Lifecycle.State.RESUMED
        )

        viewModel.liveUiState.observe(viewLifecycleOwner) { uiState ->
            with(binding) {

                    name.setText(uiState.initialTitle.title)
                    description.setText(uiState.initialTitle.description)
                    doneFlagCheckBox.isChecked = uiState.initialTitle.doneFlag
             //       brandSpinner.setSelection(uiState.initialDonut.brand.ordinal)

                    viewModel.setTitleLoaded()

            }
        }

        binding.buttonFirst.setOnClickListener {
            onSave()
        }



        return binding.root

    }
    private fun onSave() {
        if (binding.name.text.toString().isNotBlank()) {
            mainViewModel.saveTitle(
                Title(
                    id = safeArgs.titleId,
                    title = binding.name.text.toString(),
                    description = binding.description.text.toString(),
                    doneFlag = binding.doneFlagCheckBox.isChecked,
                    dueDate = viewModel.dueDate
                )
            )
            showList()
        } else {
            binding.name.error = "cannot be blank"
        }
    }


    private fun showList() {
        findNavController().navigate(
            NavGraphDirections.actionGlobalTitleListFragment()
        )
    }
    private fun onDelete() {
        val titleId = safeArgs.titleId
        if (titleId is String) {
            mainViewModel.deleteTitleById(titleId)
        }
        showList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_edit, menu)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_titleListFragment)
        }
    }
    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_delete -> {
                onDelete()
                true
            }
            R.id.action_save -> {
                onSave()
                true
            }
            else -> false
        }
    }

}