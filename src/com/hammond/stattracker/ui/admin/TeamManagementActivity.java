package com.hammond.stattracker.ui.admin;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Visibility;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.hammond.stattracker.R;
import com.hammond.stattracker.dao.TeamDaoImpl;
import com.hammond.stattracker.domain.Team;


public class TeamManagementActivity extends Activity {

    protected TeamDaoImpl teamDaoImpl;
	
    private Spinner teamSpinner;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teamDaoImpl = new TeamDaoImpl(this);
        setContentView(R.layout.activity_team_mgmt);        
        populateTeams();
    }
    
    
    public void populateTeams(){
    	teamSpinner = (Spinner) findViewById(R.id.select_team_spinner);
    	List<Team> list = this.teamDaoImpl.getAll();
       	ArrayAdapter<Team> adapter = new ArrayAdapter<Team>(this, android.R.layout.simple_spinner_dropdown_item, list);
    	teamSpinner.setAdapter(adapter);
    	teamSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapter, View view, int position, long rowId) {
				Team team = (Team) teamSpinner.getItemAtPosition(position);				
				System.out.println("selected " + team.getName() + " id " + team.getId());
				
				TextView tv = (TextView) findViewById(R.id.team_management_name_text);
				tv.setText("Team: " + team.getName() + " id " + team.getId());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
    	});
    }

}
 