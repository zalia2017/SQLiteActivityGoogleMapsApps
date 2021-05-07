package com.example.sqliteactivitygooglemapsapps

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


public class fragment_list : Fragment() {

    lateinit var myView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        myView =  inflater.inflate(R.layout.fragment_list, container, false)

        setupListofDataIntoRecyclerView(myView)
        return myView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {

        }

    }
    private fun getItemsList(): ArrayList<myActivityModel> {
        val databaseHandler: DatabaseHandler = DatabaseHandler(requireContext())
        val activityList: ArrayList<myActivityModel> = databaseHandler.viewActivity()

        return activityList
    }
    private fun setupListofDataIntoRecyclerView(MyView: View){
        var rvActivityList = MyView.findViewById<RecyclerView>(R.id.rvActivityList)
//        Toast.makeText(requireContext(), "${getItemsList()}", Toast.LENGTH_LONG).show()
        if(getItemsList().size > 0){
            rvActivityList = MyView.findViewById<RecyclerView>(R.id.rvActivityList)
            var tvNoRecordsAvailable = MyView.findViewById<TextView>(R.id.tvNoRecordsAvailable)
            rvActivityList.visibility = View.VISIBLE
            tvNoRecordsAvailable.visibility = View.GONE

            rvActivityList.layoutManager = LinearLayoutManager(requireContext())
            rvActivityList.adapter = ListAdapter(requireContext(), getItemsList(), this){}
        }else{
            rvActivityList = MyView.findViewById<RecyclerView>(R.id.rvActivityList)
            var tvNoRecordsAvailable = MyView.findViewById<TextView>(R.id.tvNoRecordsAvailable)
            rvActivityList.visibility = View.GONE
            tvNoRecordsAvailable.visibility = View.VISIBLE
        }
    }
    public fun deleteRecordAlertDialog(activityModel: myActivityModel) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete Record")
        builder.setMessage("Are You Sure")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton("Yes") {dialog, which ->
            val databaseHandler: DatabaseHandler = DatabaseHandler(requireContext())
            val status = databaseHandler.deleteActivity(myActivityModel(activityModel.id, "", "", ""))
            if(status > -1){
                Toast.makeText(requireContext(), "Record Deleted Successfully", Toast.LENGTH_SHORT).show()
                setupListofDataIntoRecyclerView(myView)
            }
            dialog.dismiss()
        }
        builder.setNegativeButton("No") {dialog, which ->
            dialog.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }


}