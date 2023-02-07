package com.example.android2_lessen2.ui.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android2_lessen2.R
import com.example.android2_lessen2.databinding.FragmentRegistrationBinding
import com.example.android2_lessen2.utils.PreferenceHelper
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private var auth: FirebaseAuth? = Firebase.auth
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        init()
        regi()
    }

    private fun init() {
        PreferenceHelper.unit(requireContext())
    }

    private fun setupListener() = with(binding) {
        matButton.setOnClickListener {
            if (etPassword.text.isEmpty()) {
                if (etPassword.text.isEmpty()) {
                    etPassword.error = "asd1"
                }
            } else {
                startPhoneNumberVerification(etPassword.text.toString())
            }
            matButton.isVisible = true
        }
        matButton2.setOnClickListener {
            if (etPassword.text.isEmpty() || etCode.text.isEmpty()) {
                if (etCode.text.isEmpty()) {
                    etCode.error = "asd"
                    if (etCode.text.isEmpty()) {
                        etCode.error = "asd"
                    }
                }
            } else {
                verifyPhoneNumberWithCode(
                    storedVerificationId.toString(),
                    etCode.text.toString()
                )

            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_registrationFragment_to_noteAppFragment)
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(requireActivity(), "Registration is not", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth!!)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private var callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(p0: FirebaseException) {
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {
            storedVerificationId = verificationId
            resendToken = token
        }
    }

    private fun regi() {
        PreferenceHelper.safeBool = true
    }
}