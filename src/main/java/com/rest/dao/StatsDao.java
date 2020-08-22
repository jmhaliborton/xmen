package com.rest.dao;

import com.rest.beans.DnaBeans;
import java.sql.*;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
public class StatsDao
{
	public void saveStats (DnaBeans dna){
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xmendb?serverTimezone=UTC", "root", "");
		
		String sql = "insert into t_dna (dna_cadena,mutante) values (?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		String cadena = dna.getDna().stream().map(n -> String.valueOf(n)).collect(Collectors.joining(","));
		ps.setString(1, cadena);
		ps.setBoolean(2, dna.isMutante());
		ps.execute();
		con.close();
	}
	catch (Exception e)
	{
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
//		e.printStackTrace();
	}
	
	} 
	
	public JSONObject getStats(){
	JSONObject json = new JSONObject();
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xmendb?serverTimezone=UTC", "root", "");
			
			String sql = "select \r\n" + 
					"(select count(mutante) from t_dna where mutante = 1) as mutant,\r\n" + 
					"(select count(mutante) from t_dna where mutante = 0) as human";
			Statement s = con.createStatement();
			ResultSet result = s.executeQuery(sql);
			
			while (result.next())
			{
				json.put("count_mutant_dna", result.getString("mutant"));
				json.put("count_human_dna", result.getString("human"));
				Integer m = new Integer(result.getString("mutant").toString());
				Integer h = new Integer((result.getString("human").toString().equals("0")) ? "1" : result.getString("human").toString());
			
				json.put("ratio", m/h );
			}
			
			
			con.close();	
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
		return json;
	}
}
