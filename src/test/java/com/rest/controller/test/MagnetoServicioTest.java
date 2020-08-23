
package com.rest.controller.test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rest.beans.DnaBeans;
import com.rest.dao.StatsDao;
import com.rest.dao.mutanteDao;
import com.rest.xmen.controller.MagnetoServicio;

public class MagnetoServicioTest
{

	
	@Autowired
	private mutanteDao mutante = new mutanteDao();
	private MagnetoServicio magneto = new MagnetoServicio();
	@Test
	public void esMutanteVTest()
	{
		DnaBeans dna = new DnaBeans();
		dna.setDna(new ArrayList<String>(Arrays.asList("ATGCGA", "CGGTGC", "TTATGT", "AGCAGG", "CCACTA", "TCACTG")));
		Assert.assertEquals(true, mutante.validarMutante(dna).isMutante());
	}

	@Test
	public void esMutanteHTest()
	{
		DnaBeans dna = new DnaBeans();
		dna.setDna(new ArrayList<String>(Arrays.asList("ATGCGA", "CCGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG")));
		Assert.assertEquals(true, mutante.validarMutante(dna).isMutante());
	}

	@Test
	public void esMutanteDTest()
	{
		DnaBeans dna = new DnaBeans();
		dna.setDna(new ArrayList<String>(Arrays.asList("ATGCCA", "CATGCA", "TTATGT", "AGCAGG", "CCACTA", "TCACTG")));
		Assert.assertEquals(true, mutante.validarMutante(dna).isMutante());
	}

	@Test
	public void NoEsMutanteTest()
	{
		DnaBeans dna = new DnaBeans();
		dna.setDna(new ArrayList<String>(Arrays.asList("ATGCGA", "CAGTGC", "TTGTTT", "AGAAGG", "CCACTA", "TCACTG")));
		Assert.assertEquals(false, mutante.validarMutante(dna).isMutante());
	}

	@Test
	public void FormatoValidoTest()
	{
		DnaBeans dna = new DnaBeans();
		dna.setDna(new ArrayList<String>(Arrays.asList("ATGCGA", "CAGTGC", "TTGTTA", "AGAAGG", "CAACTA", "TCACTG")));
		Assert.assertEquals(true, mutante.validarFormato(dna));
	}

	@Test
	public void FormatoInvalidoTest()
	{
		DnaBeans dna = new DnaBeans();
		dna.setDna(new ArrayList<String>(Arrays.asList("ATGCGA", "AGGC", "TTGTTT", "AGAAGG", "CCACTA", "TCACTG")));
		Assert.assertEquals(false, mutante.validarFormato(dna));
	}
	@Test
	public void FormatoInvalidoCaracterTest()
	{
		DnaBeans dna = new DnaBeans();
		dna.setDna(new ArrayList<String>(Arrays.asList("ATGCGA", "AGGCG3", "TTGTTT", "AGAAGG", "CCACTA", "TCACTG")));
		Assert.assertEquals(false, mutante.validarFormato(dna));
	}
	@Test
	public void getInfo()
	{
		Assert.assertEquals( Response.ok("Servicio ACTIVO").build().getEntity(),magneto.getinfo().getEntity());
	}
	
	@Test
	public void getstat()
	{
		Assert.assertEquals( 200,magneto.getStats().getStatus());
	}
	
	@Test
	public void DNAValido()
	{
		DnaBeans dna = new DnaBeans();
		dna.setDna(new ArrayList<String>(Arrays.asList("ATGCGA", "CCGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG")));
		Assert.assertEquals( 200,magneto.validarDNA(dna).getStatus());
	}
	
	@Test
	public void DNAInvalido()
	{
		DnaBeans dna = new DnaBeans();
		dna.setDna(new ArrayList<String>(Arrays.asList("ATGCGA", "CAGTGC", "TTGTTT", "AGAAGG", "CCACTA", "TCACTG")));
		Assert.assertEquals( 403,magneto.validarDNA(dna).getStatus());
	}
	@Test
	public void DNAFortmatoMAl()
	{
		DnaBeans dna = new DnaBeans();
		dna.setDna(new ArrayList<String>(Arrays.asList("ATGCGA", "AGGCG3", "TTGTTT", "AGAAGG", "CCACTA", "TCACTG")));
		Assert.assertEquals( 400,magneto.validarDNA(dna).getStatus());
	}
}
