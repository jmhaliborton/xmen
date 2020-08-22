package com.rest.xmen.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Access;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.rest.beans.DnaBeans;
import com.rest.dao.StatsDao;
import com.rest.dao.mutanteDao;



@Path("/magneto")
public class MagnetoServicio 
{
	@Autowired
	private mutanteDao  mutante  = null;
	@Autowired
	private StatsDao statsDao = null;
	
	@POST
	@Path("/mutant")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response  validarDNA (DnaBeans dna){
		
		mutante = new mutanteDao();
		statsDao = new StatsDao();
		JSONObject js = new JSONObject();
		String resp="";
		int Status= 0;
		Boolean isMutante = false;
		if(mutante.validarFormato(dna)){
		mutante.validarMutante(dna);
		if(dna.isMutante()){
			Status = 200;
			resp ="Para reclutar";
		}
		else{
		Status = 403;
		resp ="Inservible";
		}
		statsDao.saveStats(dna);
		}
		else{
		Status= 400;
		resp="Formato del JSON enviado no es valido";
		}
		
		
		
		
		js.put("info", resp);
		return Response.status(Status).entity(resp).build();
		} 
		

	@GET
	@Path("/stats")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  getStats(){
		statsDao = new StatsDao();
		JSONObject js = new JSONObject();
		js = statsDao.getStats();
		
		if(js.isEmpty())
		return Response.ok("Aún no se validaron cadenas de DNA").build();
		else
		return Response.ok(js).build();
		
	}
	
	@GET
	@Path("/info")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  getinfo(){
		
		return Response.ok("Servicio ACTIVO").build();
		
		
	}
}
