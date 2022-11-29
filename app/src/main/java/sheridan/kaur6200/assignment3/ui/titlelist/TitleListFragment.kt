package sheridan.kaur6200.assignment3.ui.titlelist

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import sheridan.kaur6200.assignment3.MainViewModel
import sheridan.kaur6200.assignment3.R
import sheridan.kaur6200.assignment3.databinding.TitleListFragmentBinding

@AndroidEntryPoint
class TitleListFragment : Fragment(), MenuProvider {

    private val viewModel: TitleListViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = TitleListFragmentBinding.inflate(inflater, container, false)
        navController = findNavController()

        // setup fragment menu
        requireActivity().addMenuProvider(
            this, viewLifecycleOwner, Lifecycle.State.RESUMED
        )

        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(divider)
        val adapter = TitleListAdapter(
            onItemClick = { titleId -> onEditTitle(titleId) },
            onItemDelete = { titleId -> onDeleteTitle(titleId) }
        )
        binding.recyclerView.adapter = adapter

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.addTitleFab.setOnClickListener { onAddTitle() }

        return binding.root
    }

    private fun onAddTitle() {
        navController.navigate(
            TitleListFragmentDirections
                .actionTitleListFragmentToFirstFragment()
        )
    }

    private fun onDeleteTitle(titleId: String) {
        mainViewModel.deleteTitleById(titleId)
    }

   private fun onEditTitle(titleId: String) {
        navController.navigate(
            TitleListFragmentDirections
                .actionTitleListFragmentToFirstFragment(titleId)
        )
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_list, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_clear -> {
                mainViewModel.deleteAllTitles()
                true
            }
            else -> false
        }
    }
}