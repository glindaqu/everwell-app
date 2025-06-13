package ru.glindaquint.everwell.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.glindaquint.everwell.viewModels.impl.FamilyGroupViewModel

@AndroidEntryPoint
class FamilyGroupInviteActivity : AppCompatActivity() {
    private val viewModel: FamilyGroupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val familyGroupId = intent.data?.getQueryParameter("familyGroupId")?.toLongOrNull()
        familyGroupId?.let { viewModel.join(it) }

        startActivity(Intent(this, SplashActivity::class.java))
        finish()
    }
}
