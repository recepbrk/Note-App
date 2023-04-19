package com.example.notesapp
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.Toast
import com.example.notesapp.databinding.ActivityAddNoteBinding
import java.text.SimpleDateFormat
import java.util.*




class AddNote : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var note: com.example.notesapp.Models.Note
    private lateinit var old_note: com.example.notesapp.Models.Note
    var isUpdate = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            old_note =
                intent.getSerializableExtra("current_note") as com.example.notesapp.Models.Note
            binding.etTitle.setText(old_note.title)
            binding.etNote.setText(old_note.note)
            isUpdate = true


        } catch (e: Exception) {
            e.printStackTrace()
        }
        binding.imgCheck.setOnClickListener {

            val title = binding.etTitle.text.toString()
            val note_desc = binding.etNote.text.toString()

            if (title.isNotEmpty() || note_desc.isNotEmpty()) {
                val formatter = SimpleDateFormat("EEE, d MMM yyy HH;mm a")

                if (isUpdate) {

                    note = com.example.notesapp.Models.Note(

                        old_note.id, title, note_desc, formatter.format(Date())
                    )
                } else {

                    note = com.example.notesapp.Models.Note(
                        null,
                        title,
                        note_desc,
                        formatter.format(Date())
                    )

                }


                val intent = Intent()
                intent.putExtra("note", note)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this@AddNote, "PLEASE ENTER SOME DATA ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
        binding.imgBackArrow.setOnClickListener {
            onBackPressed()
        }

    }
}






