package com.example.jobsearchapplication.data.repository

import com.example.jobsearchapplication.data.dataSources.auth.FirebaseAuthSource
import com.example.jobsearchapplication.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuthSource: FirebaseAuthSource
) : AuthRepository {
    override suspend fun signUp(email: String, password: String, name: String) {
        firebaseAuthSource.signUpWithEmailAndPassword(email, password, name)
    }

    override suspend fun signIn(email: String, password: String) {
        firebaseAuthSource.signInWithEmailAndPassword(email, password)
    }

    override fun getCurrentUser() = firebaseAuthSource.getCurrentUser()

    override suspend fun signOut() {
        firebaseAuthSource.signOut()
    }
}