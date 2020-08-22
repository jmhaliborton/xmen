
package com.rest.controller.test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rest.beans.DnaBeans;
import com.rest.dao.mutanteDao;

public class MagnetoServicioTest
{

	@Autowired
	private mutanteDao mutante = new mutanteDao();

	@Test
	public void esMutanteVTest()
	{
		DnaBeans dna = new DnaBeans();
		dna.setDna(new ArrayList<String>(Arrays.asList("ATGCGA", "CGGTGC", "TTATGT", "AGCAGG", "CCACTA", "TCACTG")));
		Assert.assertEquals(true, mutante.validarMutante(dna));
	}

	@Test
	public void esMutanteHTest()
	{
		DnaBeans dna = new DnaBeans();
		dna.setDna(new ArrayList<String>(Arrays.asList("ATGCGA", "CCGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG")));
		Assert.assertEquals(true, mutante.validarMutante(dna));
	}

	@Test
	public void esMutanteDTest()
	{
		DnaBeans dna = new DnaBeans();
		dna.setDna(new ArrayList<String>(Arrays.asList("ATGCCA", "CATGCA", "TTATGT", "AGCAGG", "CCACTA", "TCACTG")));
		Assert.assertEquals(true, mutante.validarMutante(dna));
	}

	@Test
	public void NoEsMutanteTest()
	{
		DnaBeans dna = new DnaBeans();
		dna.setDna(new ArrayList<String>(Arrays.asList("ATGCGA", "CAGTGC", "TTGTTT", "AGAAGG", "CCACTA", "TCACTG")));
		Assert.assertEquals(false, mutante.validarMutante(dna));
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
}
