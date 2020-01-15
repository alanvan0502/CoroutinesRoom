package com.devtides.coroutinesroom.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.devtides.coroutinesroom.R
import com.devtides.coroutinesroom.model.LoginState
import com.devtides.coroutinesroom.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signoutBtn.setOnClickListener { viewModel.signOut() }
        deleteUserBtn.setOnClickListener { deleteUser() }
        usernameTV.text = LoginState.user?.userName ?: ""

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.signedOut.observe(this, Observer {
            if (it) {
                Toast.makeText(context, "Signed Out", Toast.LENGTH_SHORT).show()
                goToSignUpScreen()
            }
        })
        viewModel.userDeleted.observe(this, Observer {
            if (it) {
                Toast.makeText(context, "User Deleted!", Toast.LENGTH_SHORT).show()
                goToSignUpScreen()
            }
        })
        viewModel.error.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun goToSignUpScreen() {
        val action = MainFragmentDirections.actionGoToSignup()
        Navigation.findNavController(usernameTV).navigate(action)
    }

    private fun deleteUser() {
        activity?.let {
            AlertDialog.Builder(it)
                .setTitle("Delete user")
                .setMessage("Are you sure you want to delete this user?")
                .setPositiveButton("Yes") {p0, p1 -> viewModel.deleteUser()}
                .setNegativeButton("Cancel", null)
                .create()
                .show()
        }
    }

}
