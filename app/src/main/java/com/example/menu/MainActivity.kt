package com.example.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.ActionMode
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var rv: RecyclerView
    var actionMode: ActionMode? = null
    lateinit var drawer: DrawerLayout
    var isDarawerClosed = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar_main)
        val tvContextMenu = findViewById<TextView>(R.id.tv_context_menu)
        val tvActionMode = findViewById<TextView>(R.id.tv_context_action_mode_menu)
        val btnPopUp = findViewById<Button>(R.id.btn_popup_menu)
        drawer = findViewById(R.id.main_drawer)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        registerDrawbleBackPress(drawer)
        registerForContextMenu(tvContextMenu)
        registerForCOntextActionModeMenu(tvActionMode)
        registerForPopUpMenu(btnPopUp)
    }
    override fun onBackPressed() {
        if (isDarawerClosed == false) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    private fun registerDrawbleBackPress(drawer: DrawerLayout) {
        drawer.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            }

            override fun onDrawerOpened(drawerView: View) {
                isDarawerClosed = false
            }

            override fun onDrawerClosed(drawerView: View) {
                isDarawerClosed = true
            }

            override fun onDrawerStateChanged(newState: Int) {
            }

        })

    }

    private fun registerForPopUpMenu(btnPopUp: Button) {
        btnPopUp.setOnClickListener {
            val popUp = PopupMenu(this, btnPopUp)
            val inflater = popUp.menuInflater
            inflater.inflate(R.menu.menu_pop_up, popUp.menu)
            popUp.show()
        }
    }

    private fun registerForCOntextActionModeMenu(tvActionMode: TextView) {
        tvActionMode.setOnLongClickListener { view ->
            when (actionMode) {
                null -> {
                    actionMode = startSupportActionMode(actionModeCallback)
                    view.isSelected = true
                    true
                }
                else -> false
            }
        }
    }

    private val actionModeCallback = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            val inflater: MenuInflater = mode.menuInflater
            inflater.inflate(R.menu.menu_context_action_mode, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.action1 -> {
                    Toast.makeText(
                        this@MainActivity,
                        "click ${item.title} from actionContextMenu",
                        Toast.LENGTH_SHORT
                    ).show()
                    true
                }
                else -> false
            }
        }

        override fun onDestroyActionMode(mode: ActionMode) {
            actionMode = null
        }
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_setting_start_day, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.item1 -> {
                Toast.makeText(this, "click ${item.title} from contextMenu", Toast.LENGTH_SHORT)
                    .show()
                true
            }
            R.id.item2 -> {
                Toast.makeText(this, "click ${item.title} from contextMenu", Toast.LENGTH_SHORT)
                    .show()
                true
            }
            R.id.item3 -> {
                Toast.makeText(this, "click ${item.title} from contextMenu", Toast.LENGTH_SHORT)
                    .show()
                true
            }
            R.id.item4 -> {
                Toast.makeText(this, "click ${item.title} from contextMenu", Toast.LENGTH_SHORT)
                    .show()
                true
            }
            R.id.item5 -> {
                Toast.makeText(this, "click ${item.title} from contextMenu", Toast.LENGTH_SHORT)
                    .show()
                true
            }

            else -> super.onContextItemSelected(item)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_setting_start_day, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> drawer.openDrawer(GravityCompat.START);
            R.id.item1 -> Toast.makeText(this, "${item.title}", Toast.LENGTH_SHORT).show()
            R.id.item2 -> Toast.makeText(this, "${item.title}", Toast.LENGTH_SHORT).show()
            R.id.item3 -> Toast.makeText(this, "${item.title}", Toast.LENGTH_SHORT).show()
            R.id.item4 -> Toast.makeText(this, "${item.title}", Toast.LENGTH_SHORT).show()
            R.id.item5 -> Toast.makeText(this, "${item.title}", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}