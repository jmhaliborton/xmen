
package com.rest.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.rest.beans.DnaBeans;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class mutanteDao
{

	public DnaBeans validarMutante(DnaBeans dna)
	{
		Boolean isMutante = false;
		if (verificarcadena(dna.getDna()))
		{
			isMutante = true;
		}
		else if (verificarVertical(dna.getDna()))
		{
			isMutante =  true;
		}
		else if (verificarDiagonal(dna.getDna()))
		{
			isMutante =  true;
		}
		else
			isMutante =  false;
	dna.setMutante(isMutante);
	return dna;
	}

	private Boolean verificarcadena(List<String> dna)
	{
		Boolean isMutant = false;
		Pattern patron = Pattern.compile("(.)\\1{3}");

		for (String caso : dna)
		{
			Matcher m = patron.matcher(caso);
			if (m.find())
			{
				isMutant = true;
				break;
			}
		}

		return isMutant;
	}

	private Boolean verificarVertical(List<String> dna)
	{

		ArrayList<String> dnaV = new ArrayList<String>();
		for (int i = 0; i < dna.size(); i++)
		{
			dnaV.add(obtenerCadena(dna, i));
		}

		return verificarcadena(dnaV);
	}

	private Boolean verificarDiagonal(List<String> dna)
	{

		ArrayList<String> dnaD = new ArrayList<String>();

		String val = "";
		String valInf = "";
		int x = 0;
		int xinf = 0;
		for (int j = 0; j < dna.size(); j++)
		{
			x = j;
			xinf=0;
			val = "";
			valInf = "";
			for (int i = 0; i < dna.size(); i++)
			{
				ArrayList<String> cadena = new ArrayList<String>(Arrays.asList(dna.get(i).split("")));

				if (j > 0 && i >= j)
				{
					if (xinf <= cadena.size())
					{
						valInf += cadena.get(xinf);
						xinf++;
					}
				}
				if (x <= cadena.size() - 1)
				{
					val += cadena.get(x);
					x++;
				}
			}
			if (val.length() >= 4)
			{
				dnaD.add(val);
			}
			if (valInf.length() >= 4)
			{
				dnaD.add(valInf);
			}
		}
		return verificarcadena(dnaD);
	}

	private String obtenerCadena(List<String> dna, int i)
	{
		return dna.stream().map(d -> d.codePointAt(i))
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}
	
	public Boolean validarFormato(DnaBeans dna){
	int n = dna.getDna().get(0).length();
	Pattern patron = Pattern.compile("[ACTG]{"+n +"}");

	for (String caso : dna.getDna())
	{
		Matcher m = patron.matcher(caso);
		if (!m.find())
		{
			return false;
		}
	}
	
	return true;
	}

}
