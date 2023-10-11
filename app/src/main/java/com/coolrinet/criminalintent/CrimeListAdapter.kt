package com.coolrinet.criminalintent

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coolrinet.criminalintent.databinding.ListItemCrimeBinding
import java.util.Locale

class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime, onCrimeClicked: () -> Unit) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = SimpleDateFormat("EEEE, MMMM d, y", Locale.US).format(crime.date)

        binding.root.setOnClickListener {
            onCrimeClicked()
        }

        binding.crimeSolved.visibility = if (crime.isSolved) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}

class CrimeListAdapter (
    private val crimes: List<Crime>,
    private val onCrimeClicked: () -> Unit
) : RecyclerView.Adapter<CrimeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
        return CrimeHolder(binding)
    }

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        val crime = crimes[position]
        holder.bind(crime, onCrimeClicked)
    }

    override fun getItemCount() = crimes.size
}