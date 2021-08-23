package by.godevelopment.livedatalearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import by.godevelopment.livedatalearn.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var liveData: MutableLiveData<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        liveData = MutableLiveData<String>()
        liveData.value = "Hello LiveData"

        if (savedInstanceState == null) {
            val firstFragment = FirstFragment(liveData)
            val secondFragment = SecondFragment(liveData)

            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_first, firstFragment)
            transaction.add(R.id.fragment_second, secondFragment)
            transaction.commit()
        }

        binding.buttonMain.setOnClickListener {
            val text = binding.textInputMain.text.toString()
            liveData.value = text
        }

        liveData.observe(this, Observer {
            binding.textViewMain.text = it
        })

    }
}