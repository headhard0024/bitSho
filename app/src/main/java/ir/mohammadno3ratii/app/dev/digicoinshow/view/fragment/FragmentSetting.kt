package ir.mohammadno3ratii.app.dev.digicoinshow.view.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.kardari.kardariapp.Helper.CirculeRevealHelper
import ir.mohammadno3ratii.app.dev.digicoinshow.R
import ir.mohammadno3ratii.app.dev.digicoinshow.base.G
import ir.mohammadno3ratii.app.dev.digicoinshow.base.PublicConstant
import ir.mohammadno3ratii.app.dev.digicoinshow.base.PublicConstantNumber
import ir.mohammadno3ratii.app.dev.digicoinshow.databinding.FragmentSettingBinding
import ir.mohammadno3ratii.app.dev.digicoinshow.di.AppComponent
import ir.mohammadno3ratii.app.dev.digicoinshow.view.activity.ActivityMain
import ir.mohammadno3ratii.app.dev.digicoinshow.view.dialog.*
import java.util.*

class FragmentSetting : Fragment() {

    lateinit var _binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSettingBinding.inflate(inflater, container, false)
        _binding = binding

        currentTime()

//        binding.txt.setOnClickListener {
//            findNavController().navigate(R.id.action_settingFragment_to_fragmentAbout)
//        }

        binding.layExchange.setOnClickListener {
            setCirculeReveal(it)
            BottomSheetCoinExchangePrice(requireContext()).show()
        }

        binding.layUpdateSpeed.setOnClickListener {
            setCirculeReveal(it)
            BottomSheetCoinUpdateSpeed(requireContext()).show()
        }

        binding.layTheme.setOnClickListener {
            setCirculeReveal(it)
            BottomSheetAppTheme(requireContext()).show()
        }

        BottomSheetAppTheme.getThemeOnClick(object : BottomSheetAppTheme.IAppThemeClickedListener {
            override fun darkModeClicked() {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                startActivity(Intent(context, ActivityMain::class.java))
                activity!!.finish()
            }

            override fun lightModeClicked() {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                startActivity(Intent(context, ActivityMain::class.java))
                activity!!.finish()
            }

            override fun systemDefaultClicked() {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                startActivity(Intent(context, ActivityMain::class.java))
                activity!!.finish()
            }
        })

        binding.laySupport.setOnClickListener {
            setCirculeReveal(it)
            BottomSheetAppSupport(requireContext()).show()
        }

        binding.layInfo.setOnClickListener {
            setCirculeReveal(it)
            BottomSheetAppInfo(requireContext()).show()
        }

        return binding.root
    }

    private fun currentTime() {
        _binding.txtYear.text = G.currentPersianTime.shYear.toString()
        _binding.txtMonthName.text = G.currentPersianTime.monthName()
        _binding.txtShDay.text = G.currentPersianTime.shDay.toString()
        _binding.txtDayName.text = G.currentPersianTime.dayName()
    }

    private fun setCirculeReveal(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CirculeRevealHelper(view, R.color.E8, R.color.background).init()
        }
    }
}